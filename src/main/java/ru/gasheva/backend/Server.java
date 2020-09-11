package ru.gasheva.backend;

import ru.gasheva.backend.jsonparser.JSONParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
            server.setSoTimeout(40*1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean handleWithSocket() throws IOException {


        PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
        System.out.println(message);
        out.println(message);


        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean ans = br.readLine().toLowerCase().equals("true");
        System.out.println("Ответ от клиента - " + ans);

        out.close();
        br.close();
        return ans;
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
