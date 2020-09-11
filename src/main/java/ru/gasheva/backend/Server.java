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
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        // Receiving
        byte[] lenBytes = new byte[4];
        is.read(lenBytes, 0, 4);
        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
        byte[] receivedBytes = new byte[len];
        is.read(receivedBytes, 0, len);
        String received = new String(receivedBytes, 0, len);

        System.out.println("Server received: " + received);

        // Sending
        String toSend = "Echo: " + received;
        byte[] toSendBytes = toSend.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
        os.write(toSendLenBytes);
        os.write(toSendBytes);

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
