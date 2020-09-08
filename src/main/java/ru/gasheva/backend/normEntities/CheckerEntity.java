package ru.gasheva.backend.normEntities;

import java.time.LocalDate;

public class CheckerEntity {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStart_year() {
		return start_year;
	}

	public void setStart_year(LocalDate start_year) {
		this.start_year = start_year;
	}

	private LocalDate start_year;
}
