package org.example.tariff;

import org.example.model.Call;
import org.example.model.CallType;
import org.example.util.ConversationTime;

public class Common implements Tariff{
    private long discountMinutesLeft = 100;
    private static final float pricePerMinuteWithoutDiscount = MinuteByMinute.getPricePerMinute();
    private static final float pricePerMinuteWithDiscount = 0.5f;
    @Override
    public float calculateCost(Call call) {
        long duration = ConversationTime.getConversationTimeInMinute(call.getStartTime(), call.getEndTime());
        float cost;
        discountMinutesLeft -= duration;
        if (discountMinutesLeft >= 0) {
            return (call.getCallType() == CallType.incoming) ? 0 : duration * pricePerMinuteWithDiscount;
        }
        else {
            cost = (-1) * discountMinutesLeft * pricePerMinuteWithoutDiscount
                    + (discountMinutesLeft + duration) * pricePerMinuteWithDiscount;
            discountMinutesLeft = 0;
        }
        return cost;
    }
}
