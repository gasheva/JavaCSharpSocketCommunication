package ru.gasheva.frontend;

import ru.gasheva.backend.MainModel;
import ru.gasheva.controllers.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame implements DBObserver{
    private JButton btnConverter;
    private JButton btnExcel;
    private JPanel mainPanel;
    private MainController controller;
    private MainModel model;

    public MainForm(MainController controller, MainModel model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver((DBObserver)this);
    }
    public void createView(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void createControls(){
        btnConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnConverterPress();
            }
        });
        btnExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnExcelPress();
            }
        });
    }
    private void BtnConverterPress(){
        controller.writeToDB();
    }
    private void BtnExcelPress(){
        controller.writeToExcel();
    }
    public void WriteToExcelSuccess(){
        JOptionPane.showMessageDialog(this, "Данные загружены в файл");
    }
    public void WriteToExcelFail(){
        JOptionPane.showMessageDialog(this, "Данные не загружены в файл");
    }

    @Override
    public void update() {

    }
}
