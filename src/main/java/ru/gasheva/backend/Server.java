package ru.gasheva.backend;

import ru.gasheva.backend.jsonparser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private Socket client;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private String message;

    public Server(String message){
        this.message = message;
        try {
            server = new ServerSocket(6666);
            server.setSoTimeout(10*2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean handleWithSocket() throws IOException {



        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        System.out.println(message);
        out.print(message);
        out.flush();


        boolean isAllRight = false;
        System.out.println("Ждем ответа от клиента");
        client.setSoTimeout(5*1000);
        InputStream stream = client.getInputStream();
        byte[] data = new byte[10];
        stream.read(data);
        System.out.println(data);

        stream.close();
        out.close();
        System.out.println("Ответ получен. Все в порядке? -");

        return false;
    }
    public boolean startServer() throws IOException {
        boolean isAllRight=false;
        //Runtime runtime = Runtime.getRuntime();
        //Process process = runtime.exec("C:\\Users\\Таня\\source\\repos\\ExcelReportWriter\\ExcelReportWriter\\bin\\Debug\\ExcelReportWriter.exe");

        while(!server.isClosed()){
            try {
                System.out.println("Ожидание нового клиента");
                client = server.accept();
                System.out.println("Новый клиент был добавлен");
                isAllRight = handleWithSocket();
                client.close();
                System.out.println("Клиент закрыт");
                server.close();
                System.out.println("Сервер закрыт - "+server.isClosed());
            } catch (IOException e) {
                if (!server.isClosed()){server.close();}
                System.out.println("Сервер закрыт - "+server.isClosed());
                System.out.println(e.getMessage());
                //process.destroy();
                //System.exit(1);
            }
        }
        return isAllRight;
    }
}
