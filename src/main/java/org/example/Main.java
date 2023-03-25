package org.example;

import org.example.model.Subscriber;
import org.example.model.report.Report;
import org.example.util.InputOutputUtil;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Subscriber> subscribers = InputOutputUtil.parseInputFile("file_to_parse.txt");
        for (Subscriber subscriber : subscribers.values()) {
            Report report = new Report(subscriber);
            InputOutputUtil.convertToTable(report);
        }
    }
}