package ru.gasheva.backend;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Message {
    private String header;
    private List<String> tableHeaders;
    private List<MessagePart> messageParts;

    public Message(List<MessagePart> messageParts) {

        this.header = "Отчет по проверкам за последние 5 лет";
        this.tableHeaders = new LinkedList<String>();
        tableHeaders.addAll(Arrays.asList("Дата проверки", "ФИО проверяющего", "Необходимость реставрации",
                "ID картины", "Название картины", "Художник", "Галерея"));
        this.messageParts = messageParts;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getTableHeaders() {
        return tableHeaders;
    }

    public void setTableHeaders(List<String> tableHeaders) {
        this.tableHeaders = tableHeaders;
    }

    public List<MessagePart> getMessageParts() {
        return messageParts;
    }

    public void setMessageParts(List<MessagePart> messageParts) {
        this.messageParts = messageParts;
    }
}
