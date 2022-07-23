package ru.neoflex.deal.embeddables;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PassportTest {
    Passport passport = new Passport();

    @Test
    void testToString() {
        passport = new Passport.Builder()
                .number("1234")
                .series("123456")
                .issueBranch("MFC")
                .issueDate(LocalDate.of(2020, 2, 3))
                .build();
        System.out.println(passport.getIssueBranch());
        System.out.println(passport.getIssueDate());
    }

}