package ru.gasheva.backend.jsonparser;

import com.google.gson.Gson;
import ru.gasheva.backend.Message;
import ru.gasheva.backend.MessagePart;

import java.util.List;

public class JSONParser {
    public static String Parser(List<MessagePart> entities){
        Gson gson = new Gson();
        Message message = new Message(entities);
        return gson.toJson(message);
    }
}
