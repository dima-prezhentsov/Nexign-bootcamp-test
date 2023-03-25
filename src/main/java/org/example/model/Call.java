package org.example.model;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Call {
    private CallType callType;
    private final Date startTime;
    private final Date endTime;

    public Call(String callType, String startTime, String endTime) {

        switch (callType) {
            case "01" -> this.callType = CallType.outgoing;
            case "02" -> this.callType = CallType.incoming;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            this.startTime = format.parse(startTime);
            this.endTime = format.parse(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public CallType getCallType() {
        return callType;
    }
}
