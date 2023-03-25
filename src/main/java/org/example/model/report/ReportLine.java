package org.example.model.report;

import org.example.util.ConversationTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportLine {
    private final String callIndex;
    private final String startTime;
    private final String endTime;
    private final String duration;
    private final String cost;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ReportLine(String callIndex, Date startTime, Date endTime, Float cost) {
        this.callIndex = callIndex;
        this.startTime = dateFormat.format(startTime);
        this.endTime = dateFormat.format(endTime);
        this.duration = ConversationTime.getDuration(startTime, endTime);
        this.cost = Float.toString(cost);
    }

    public String getCallIndex() {
        return callIndex;
    }

    public String getStartTime() {
        return startTime;
    }

    public Date getStartTimeLikeDate() {
        try {
            return dateFormat.parse(this.startTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getCost() {
        return cost;
    }
}
