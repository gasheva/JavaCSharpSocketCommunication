package ru.gasheva.backend.normEntities;

public class ArtistEntity {
	private int id;
	private String fio;
    private String b_city;
    private boolean have_awards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getB_city() {
        return b_city;
    }

    public void setB_city(String b_city) {
        this.b_city = b_city;
    }

    public boolean isHave_awards() {
        return have_awards;
    }

    public void setHave_awards(boolean have_awards) {
        this.have_awards = have_awards;
    }
}
