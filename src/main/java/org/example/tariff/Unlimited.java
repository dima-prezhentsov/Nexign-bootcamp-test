package org.example.tariff;

import org.example.model.Call;
import org.example.util.ConversationTime;

public class Unlimited implements Tariff{
    private long minutesLeft = 300;
    private static final float periodPrice = 100f;
    private static final float pricePerMinute = 1f;

    public static float getPeriodPrice() {
        return periodPrice;
    }

    public static float getPricePerMinute() {
        return pricePerMinute;
    }

    // Есть вопрос по условию. После окончания бесплатных минут, цена за минуту начисляется только для исходящих звонков или для всех
    // Буду считать что для всех
    @Override
    public float calculateCost(Call call) {
        long duration = ConversationTime.getConversationTimeInMinute(call.getStartTime(), call.getEndTime());
        float cost = 0;
        minutesLeft -= duration;
        if (minutesLeft < 0) {
            cost = duration * (-1) * minutesLeft;
            minutesLeft = 0;
        }
        return cost;
    }
}
