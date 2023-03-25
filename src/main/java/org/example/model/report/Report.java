package org.example.model.report;

import org.example.model.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private final Subscriber subscriber;
    private final String tariffIndex;
    private float totalCost;

    private List<ReportLine> reportLines;

    public Report(Subscriber subscriber) {
        this.subscriber = subscriber;
        this.tariffIndex = subscriber.getTariffIndex();
        this.totalCost = 0;
        this.reportLines = new ArrayList<>();
        generate();
    }

    private void generate() {
        this.reportLines =
                subscriber.getCalls().stream()
                        .map(call -> {
                            float cost = subscriber.getTariff().calculateCost(call);
                            this.totalCost += cost;
                            return new ReportLine(
                                    call.getCallType().getCallIndex(),
                                    call.getStartTime(),
                                    call.getEndTime(),
                                    cost
                            );
                        })
                        .collect(Collectors.toList());
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public String getTariffIndex() {
        return tariffIndex;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public List<ReportLine> getReportLines() {
        return reportLines;
    }
}
