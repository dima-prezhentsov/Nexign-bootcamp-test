package org.example.util;

import org.example.model.Call;
import org.example.model.Subscriber;
import org.example.model.report.Report;
import org.example.model.report.ReportLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InputOutputUtil {
    public static Map<String, Subscriber> parseInputFile(String path) {
        Map<String, Subscriber> subscribers = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                String[] info = line.split(", ");

                String callType = info[0];
                String phoneNumber = info[1];
                String startTime = info[2];
                String endTime = info[3];
                String tariff = info[4];

                Call call = new Call(callType, startTime, endTime);
                Subscriber subscriber = subscribers.get(phoneNumber);

                if (subscriber == null) {
                    Subscriber newSubscriber = new Subscriber(phoneNumber, tariff);
                    newSubscriber.addCall(call);
                    subscribers.put(phoneNumber, newSubscriber);
                }
                else {
                    subscriber.addCall(call);
                    // Проверил может ли быть у телефона несколько тарифных планов в течении выгрузки
                    if (!subscriber.getTariffIndex().equals(tariff)) {
                        System.out.println(subscriber.getPhoneNumber() + " " + subscriber.getTariffIndex() + " " + tariff);
                    }

                }

                line = reader.readLine();
            }

            fileReader.close();
            reader.close();
            return subscribers;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void convertToTable(Report report) {
        Path path = Paths.get("C:\\Users\\dimon\\Desktop\\projects\\Nexign-bootcamp-test\\reports");
        Path filePath = Paths.get("C:\\Users\\dimon\\Desktop\\projects\\Nexign-bootcamp-test\\reports\\"
                + report.getSubscriber().getPhoneNumber() + ".txt");

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);

            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        StringBuilder mdTable =
                new StringBuilder("Tariff index: " + report.getTariffIndex() + "\n" +
                        "----------------------------------------------------------------------------\n" +
                        "Report for phone number " + report.getSubscriber().getPhoneNumber() + ":\n" +
                        "----------------------------------------------------------------------------\n" +
                        "| Call Type |   Start Time        |     End Time        | Duration | Cost  |\n" +
                        "----------------------------------------------------------------------------\n");

        List<ReportLine> sortedReportLines = report.getReportLines().stream()
                .sorted(Comparator.comparing(ReportLine::getCallIndex)
                        .thenComparing(ReportLine::getStartTimeLikeDate))
                .toList();


        for (ReportLine line : sortedReportLines) {
            mdTable
                    .append("|     ")
                    .append(line.getCallIndex())
                    .append("    | ")
                    .append(line.getStartTime())
                    .append(" | ")
                    .append(line.getEndTime())
                    .append(" | ")
                    .append(line.getDuration())
                    .append(" | ")
                    .append(line.getCost())
                    .append(" |\n");
        }
        mdTable
                .append("----------------------------------------------------------------------------\n"
                        + "|                                           Total Cost: |     ")
                .append(report.getTotalCost()).append(" rubles |\n")
                .append("----------------------------------------------------------------------------\n");

        try {
            File file = new File(filePath.toUri());
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(mdTable.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
