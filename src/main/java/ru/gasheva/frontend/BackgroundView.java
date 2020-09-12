package ru.gasheva.frontend;

import ru.gasheva.backend.BackgroundModel;
import ru.gasheva.controllers.BackgroundController;

import javax.swing.*;
import java.awt.event.*;

public class BackgroundView extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JProgressBar pbConnect;
    private BackgroundModel model;
    private BackgroundController controller;

    public BackgroundView(BackgroundController controller, BackgroundModel model) {
        this.controller = controller;
        this.model = model;
    }
    public void createView(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);
        pbConnect.setIndeterminate(true);
        setVisible(true);
    }
    public void createControls(){
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void Dispose(){
        dispose();
    }
    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
