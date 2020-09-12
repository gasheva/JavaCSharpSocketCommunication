package ru.gasheva.backend;

import ru.gasheva.backend.jsonparser.JSONParser;
import ru.gasheva.database.NormDao;

import java.io.IOException;

public class BackgroundModel {
    NormDao normDao;
    public void init(NormDao normDao){
        this.normDao = normDao;
    }


    public boolean writeToExcel() {
        String message = JSONParser.Parser(normDao.readRequestForXML());
        System.out.println(message);
        Server server = new Server(message);
        boolean isAllRight=false;
        try {
            isAllRight = server.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isAllRight;
    }
}
