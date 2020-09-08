package ru.gasheva.backend;

import java.time.LocalDate;

public class NotNormEntity {
    private int gal_id;
    private String gal_name;

    public String getGal_name() {
        return gal_name;
    }

    public void setGal_name(String gal_name) {
        this.gal_name = gal_name;
    }

    private String gal_location;
    private int id_check_date;
    private LocalDate check_date;
    private String checker_name;
    private boolean need_rest;
    private String marks;
    private int id_paint;
    private String paint_name;
    private int paint_price;
    private int artist_id;
    private String artist_fio;
    private String artist_b_city;
    private boolean artist_have_awards;
    private int checker_id;
    private LocalDate start_year;

    public int getChecker_id() {
        return checker_id;
    }

    public void setChecker_id(int checker_id) {
        this.checker_id = checker_id;
    }

    public LocalDate getStart_year() {
        return start_year;
    }

    public void setStart_year(LocalDate start_year) {
        this.start_year = start_year;
    }

    public int getGal_id() {
        return gal_id;
    }

    public void setGal_id(int gal_id) {
        this.gal_id = gal_id;
    }

    public String getGal_location() {
        return gal_location;
    }

    public void setGal_location(String gal_location) {
        this.gal_location = gal_location;
    }

    public int getId_check_date() {
        return id_check_date;
    }

    public void setId_check_date(int id_check_date) {
        this.id_check_date = id_check_date;
    }

    public LocalDate getCheck_date() {
        return check_date;
    }

    public void setCheck_date(LocalDate check_date) {
        this.check_date = check_date;
    }

    public String getChecker_name() {
        return checker_name;
    }

    public void setChecker_name(String checker_name) {
        this.checker_name = checker_name;
    }

    public boolean isNeed_rest() {
        return need_rest;
    }

    public void setNeed_rest(boolean need_rest) {
        this.need_rest = need_rest;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public int getId_paint() {
        return id_paint;
    }

    public void setId_paint(int id_paint) {
        this.id_paint = id_paint;
    }

    public String getPaint_name() {
        return paint_name;
    }

    public void setPaint_name(String paint_name) {
        this.paint_name = paint_name;
    }

    public int getPaint_price() {
        return paint_price;
    }

    public void setPaint_price(int paint_price) {
        this.paint_price = paint_price;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_fio() {
        return artist_fio;
    }

    public void setArtist_fio(String artist_fio) {
        this.artist_fio = artist_fio;
    }

    public String getArtist_b_city() {
        return artist_b_city;
    }

    public void setArtist_b_city(String artist_b_city) {
        this.artist_b_city = artist_b_city;
    }

    public boolean isArtist_have_awards() {
        return artist_have_awards;
    }

    public void setArtist_have_awards(boolean artist_have_awards) {
        this.artist_have_awards = artist_have_awards;
    }


}
