package ru.neoflex.conveyor.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MaritalStatus {
    MARRIED("married"), DIVORCED("divorced"), SINGLE("single"), WIDOW_WIDOWER("widow_widower");
    private final String string;

    MaritalStatus(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @JsonCreator
    public static MaritalStatus fromString(String text) {
        for (MaritalStatus maritalStatus : MaritalStatus.values()) {
            if (maritalStatus.toString().equals(text)) {
                return maritalStatus;
            }
        }
        throw new IllegalArgumentException();
    }
}
