package org.example.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ConversationTime {
    public static long getConversationTimeInMinute(Date start, Date end) {
        long differenceInMillis = end.getTime() - start.getTime();
        return TimeUnit.MILLISECONDS.toMinutes(differenceInMillis);
    }

    public static String getDuration(Date start, Date end) {

        long diffInMillis = end.getTime() - start.getTime();
        long diffInSeconds = diffInMillis / 1000;

        int hours = (int) (diffInSeconds / 3600);
        int minutes = (int) ((diffInSeconds % 3600) / 60);
        int seconds = (int) (diffInSeconds % 60);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
