package ru.gasheva.controllers;

import ru.gasheva.backend.BackgroundModel;
import ru.gasheva.database.NormDao;
import ru.gasheva.frontend.BackgroundView;

import javax.swing.*;

public class BackgroundController extends SwingWorker<Boolean, Object> {
    private BackgroundModel model;
    private BackgroundView view;
    private NormDao normDao;

    public BackgroundController(BackgroundModel model, NormDao normDao) {
        this.model = model;
        this.normDao = normDao;
        view = new BackgroundView(this, model);
        view.createView();
        view.createControls();
        //блокировка кнопки записи в excel?
        model.init(this.normDao);
    }

    private boolean writeToExcel(){
        return model.writeToExcel();

    }
    @Override
    protected Boolean doInBackground() throws Exception {
        return writeToExcel();
    }
}
