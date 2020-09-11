package ru.gasheva.backend.jsonparser;

import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import ru.gasheva.backend.ForExcelEntity;
import ru.gasheva.backend.TestMessage;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JSONParser {
    public static String Parser(List<ForExcelEntity> entities){
        Gson gson = new Gson();
        return gson.toJson(entities);
    }
    public static boolean Answer(String ans){
        Gson gson = new Gson();
        return gson.fromJson(ans, Boolean.class);
    }
}
