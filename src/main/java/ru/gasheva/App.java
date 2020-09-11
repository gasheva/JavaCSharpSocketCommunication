package ru.gasheva;

import ru.gasheva.backend.DBConvertator;
import ru.gasheva.backend.MainModel;
import ru.gasheva.backend.jsonparser.JSONParser;
import ru.gasheva.controllers.MainController;
import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;

public class App {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainController controller = new MainController(model);
    }
}
