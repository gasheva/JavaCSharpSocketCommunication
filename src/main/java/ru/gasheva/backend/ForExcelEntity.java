package ru.gasheva.backend;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ForExcelEntity {
    private String header;
    private List<String> tableHeaders;
    private LocalDate checkDate;
    private String checkerName;
    private boolean needRest;
    private int paintId;
    private String paintName;
    private String artistFio;
    private String gallery;

    public ForExcelEntity(LocalDate checkDate, String checkerName, boolean needRest, int paintId, String paintName, String artistFio, String gallery) {
        this.header = "Отчет по проверкам за последние 5 лет";
        this.tableHeaders = new LinkedList<String>();
        tableHeaders.addAll(Arrays.asList("Дата проверки", "ФИО проверяющего", "Необходимость реставрации",
                "ID картины", "Название картины", "Художник", "Галерея"));
        this.checkDate = checkDate;
        this.checkerName = checkerName;
        this.needRest = needRest;
        this.paintId = paintId;
        this.paintName = paintName;
        this.artistFio = artistFio;
        this.gallery = gallery;
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

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public boolean isNeedRest() {
        return needRest;
    }

    public void setNeedRest(boolean needRest) {
        this.needRest = needRest;
    }

    public int getPaintId() {
        return paintId;
    }

    public void setPaintId(int paintId) {
        this.paintId = paintId;
    }

    public String getPaintName() {
        return paintName;
    }

    public void setPaintName(String paintName) {
        this.paintName = paintName;
    }

    public String getArtistFio() {
        return artistFio;
    }

    public void setArtistFio(String artistFio) {
        this.artistFio = artistFio;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }
}
