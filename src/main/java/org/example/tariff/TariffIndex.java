package org.example.tariff;

public enum TariffIndex {
    Unlimited("06"),
    Common("11"),
    MinuteByMinute("03");

    private final String tariff;
    TariffIndex(String tariff) {
        this.tariff = tariff;
    }

    public String getTariffIndex() {
        return tariff;
    }
}
