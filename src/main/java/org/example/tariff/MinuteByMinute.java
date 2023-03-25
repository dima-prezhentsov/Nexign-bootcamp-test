package org.example.tariff;

import org.example.model.Call;
import org.example.util.ConversationTime;

public class MinuteByMinute implements Tariff{
    private static final float pricePerMinute = 1.5f;

    public static float getPricePerMinute() {
        return pricePerMinute;
    }
    @Override
    public float calculateCost(Call call) {
        long duration = ConversationTime.getConversationTimeInMinute(call.getStartTime(), call.getEndTime());
        return duration * pricePerMinute;
    }
}
