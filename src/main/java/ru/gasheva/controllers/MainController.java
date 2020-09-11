package ru.gasheva.controllers;

import ru.gasheva.backend.MainModel;
import ru.gasheva.frontend.MainForm;
import sun.applet.Main;

public class MainController {
    MainForm view;
    MainModel model;


    public MainController(MainModel model) {
        this.model = model;
        view = new MainForm(this, model);
        view.createView();
        view.createControls();
        //блокировка кнопки записи в excel?
        model.init();
    }
    public void writeToDB(){
        model.writeToDB();
    }
    public void writeToExcel(){
        boolean isAllRight = model.writeToExcel();
        if (isAllRight) view.WriteToExcelSuccess();
        else view.WriteToExcelFail();
    }
}
