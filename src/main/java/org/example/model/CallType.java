package org.example.model;

public enum CallType {
    incoming("01"),
    outgoing("02");

    private final String callIndex;
    CallType(String callIndex) {
        this.callIndex = callIndex;
    }

    public String getCallIndex() {
        return callIndex;
    }
}
