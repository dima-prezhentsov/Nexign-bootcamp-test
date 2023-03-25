package org.example.model;

import org.example.tariff.*;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private final String phoneNumber;
    private Tariff tariff;
    private List<Call> calls;

    public Subscriber(String phoneNumber, String tariff) {
        this.phoneNumber = phoneNumber;
        switch (tariff) {
            case "11" -> this.tariff = new Common();
            case "03" -> this.tariff = new MinuteByMinute();
            case "06" -> this.tariff = new Unlimited();
        }
        this.calls = new ArrayList<>();
    }


    public void addCall(Call call) {
        this.calls.add(call);
    }

    public String getTariffIndex() {
        return TariffIndex.valueOf(tariff.getClass().getSimpleName()).getTariffIndex();
    }
    public Tariff getTariff() {
        return this.tariff;
    }
    public List<Call> getCalls() {
        return calls;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "phone number: " + phoneNumber + "\n" +
                "tariff: " + tariff.getClass().getSimpleName() + "\n";
    }
}
