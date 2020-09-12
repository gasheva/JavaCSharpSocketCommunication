package ru.gasheva.controllers;

import ru.gasheva.backend.MainModel;
import ru.gasheva.backend.Server;
import ru.gasheva.backend.jsonparser.JSONParser;
import ru.gasheva.database.NormDao;
import ru.gasheva.database.NotNormDao;
import ru.gasheva.frontend.MainForm;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainController {
    MainForm view;
    MainModel model;
    static NormDao normDao;
    NotNormDao notNormDao;

    public MainController(MainModel model) {
        normDao = new NormDao();
        notNormDao = new NotNormDao();
        this.model = model;
        view = new MainForm(this, model);
        view.createView();
        view.createControls();
        //блокировка кнопки записи в excel?
        model.init(this.normDao, this.notNormDao);
    }
    public void writeToDB(){
        boolean isAllRight = model.writeToDB();
        if (isAllRight) view.WriteMessage("Данные успешно записаны в нормализованную БД");
        else view.WriteMessage("Данные не записаны в нормализованную БД");
    }
    public void writeToExcel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                go();
            }
        });
    }
    public static void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Loading...");
        JProgressBar jpb = new JProgressBar();
        jpb.setIndeterminate(true);
        panel.add(label);
        panel.add(jpb);
        frame.add(panel);
        frame.pack();
        frame.setSize(200,90);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Task_IntegerUpdate(jpb, label, normDao, frame).execute();
    }

    static class Task_IntegerUpdate extends SwingWorker<Boolean, Integer> {
        JProgressBar jpb;
        JLabel label;
        NormDao normDao;
        JFrame frame;
        public Task_IntegerUpdate(JProgressBar jpb, JLabel label, NormDao normDao, JFrame frame) {
            this.normDao = normDao;
            this.jpb = jpb;
            this.label = label;
            this.frame = frame;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
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

        @Override
        protected void done() {
            try {
                boolean isAllRight = get();
                if (isAllRight) JOptionPane.showMessageDialog(jpb.getParent(), "Отчет сформирован", "Success", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(jpb.getParent(), "Отчет не сформирован", "Fail", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
