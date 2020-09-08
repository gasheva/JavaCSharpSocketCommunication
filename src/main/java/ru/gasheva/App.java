package ru.gasheva;

import ru.gasheva.backend.DBConvertator;
import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;

public class App {
    public static void main(String[] args) {
        NotNormDao notNormDao = new NotNormDao();
        NormDao normDao = new NormDao();
        DBConvertator.writeToNormDB(notNormDao, normDao);
    }
}
