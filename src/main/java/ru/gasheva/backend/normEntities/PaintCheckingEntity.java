package ru.gasheva.backend.normEntities;

import java.time.LocalDate;

public class PaintCheckingEntity {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheck_date() {
		return check_date;
	}

	public void setCheck_date(LocalDate check_date) {
		this.check_date = check_date;
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

	public int getId_checker() {
		return id_checker;
	}

	public void setId_checker(int id_checker) {
		this.id_checker = id_checker;
	}

	public int getId_gal() {
		return id_gal;
	}

	public void setId_gal(int id_gal) {
		this.id_gal = id_gal;
	}

	public int getId_paint() {
		return id_paint;
	}

	public void setId_paint(int id_paint) {
		this.id_paint = id_paint;
	}

	//Ключ - все значения вместе
	private int id;
    private LocalDate check_date;
	private boolean need_rest;
    private String marks;
	private int id_checker;
	private int id_gal;
	private int id_paint;
}
