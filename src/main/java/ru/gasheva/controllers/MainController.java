package ru.gasheva.controllers;

import ru.gasheva.backend.BackgroundModel;
import ru.gasheva.backend.MainModel;
import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;
import ru.gasheva.frontend.MainForm;
import sun.applet.Main;

import java.util.concurrent.ExecutionException;

public class MainController {
    MainForm view;
    MainModel model;
    BackgroundController backgroundController;
    BackgroundModel backgroundModel;
    NormDao normDao;
    NotNormDao notNormDao;

    public MainController(MainModel model) {
        normDao = new NormDao();
        notNormDao = new NotNormDao();
        this.model = model;
        view = new MainForm(this, model);
        view.createView();
        view.createControls();
        //блокировка кнопки записи в excel?
        model.init(this.normDao, this.notNormDao);
    }
    public void writeToDB(){
        boolean isAllRight = model.writeToDB();
        if (isAllRight) view.WriteMessage("Данные успешно записаны в нормализованную БД");
        else view.WriteMessage("Данные не записаны в нормализованную БД");
    }
    public void writeToExcel(){
        backgroundModel = new BackgroundModel();
        backgroundController = new BackgroundController(backgroundModel, normDao);
        backgroundController.execute();
        //while (!backgroundController.isDone()){}
        try {
            if (backgroundController.get()) view.WriteMessage("Данные загружены в файл");
            else view.WriteMessage("Данные не загружены в файл");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
