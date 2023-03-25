package org.example.tariff;

import org.example.model.Call;

public interface Tariff {
    float calculateCost(Call call);
}
