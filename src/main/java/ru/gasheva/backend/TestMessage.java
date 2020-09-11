package ru.gasheva.backend;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TestMessage {
    private String name;
    private int age;
    private LocalDate birth;
    private List<String> strings;
    public TestMessage(String name, int age, LocalDate birth, List<String> strings) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.strings = strings;
    }

    public String getBirth() {
        return birth.toString();
    }
}
