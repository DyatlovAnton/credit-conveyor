package ru.neoflex.conveyor.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmploymentStatus {
    UNEMPLOYED("unemployed"), SELF_EMPLOYED("self_employed"), EMPLOYED("employed"), BUSINESS_OWNER("business owner");
    private final String string;

    EmploymentStatus(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }

    @JsonCreator
    public static EmploymentStatus fromString(String text) {
        for (EmploymentStatus employmentStatus : EmploymentStatus.values()) {
            if (employmentStatus.toString().equals(text)) {
                return employmentStatus;
            }
        }
        throw new IllegalArgumentException();
    }
}
