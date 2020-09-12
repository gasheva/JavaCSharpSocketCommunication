package ru.gasheva.backend;

import ru.gasheva.backend.jsonparser.JSONParser;
import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;
import ru.gasheva.frontend.DBObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainModel {
    List<DBObserver> observers = new ArrayList<>();
    NotNormDao notNormDao;
    NormDao normDao;

    public void registerObserver(DBObserver o){
        observers.add(o);
    }
    public void init(NormDao normDao, NotNormDao notNormDao){
        this.normDao=normDao;
        this.notNormDao = notNormDao;
    }
    public boolean writeToExcel(){
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
    public boolean writeToDB(){
        return DBConvertator.writeToNormDB(notNormDao, normDao);
    }
}
