package com.example.javafxcomputerapplication_rp_jm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private Button btnAjoutCarte;

    @FXML
    private Button btnEffacer;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private Button btnExporter;

    @FXML
    private Button btnPing;

    @FXML
    private ComboBox<?> cmbAffCartes;

    @FXML
    private ComboBox<?> cmbListeRec;

    @FXML
    private Label lblAddIp;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblNbProc;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblQteMem;

    @FXML
    private Label lblQteStock;

    @FXML
    private Label lblSM;

    @FXML
    private ListView<?> lvListeView;

    @FXML
    private RadioButton rbtnLinux;

    @FXML
    private RadioButton rbtnMac;

    @FXML
    private RadioButton rbtnWindows;

    @FXML
    private Slider slNbProc;

    @FXML
    private Slider slRam;

    @FXML
    private TextField txtAddIp;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtQteStock;

    @FXML
    private TextField txtSM;

    private ArrayList<Computer> ordinateur = new ArrayList<Computer>();

    public void onAjouterCarteClick(ActionEvent event) {
    }

    public void onEnregistrerClick(ActionEvent event) {
        String nom = txtNom.getText();
        String model = txtModel.getText();
        int memory = (int) slRam.getValue();
        int nbProcessors = (int) slNbProc.getValue();
        int HDD = Integer.parseInt(txtQteStock.getText());
        String OS = "";
        if (rbtnLinux.isSelected()){
            OS = rbtnLinux.getText();
        }
        else if (rbtnMac.isSelected()){
            OS = rbtnMac.getText();
        }
        else if (rbtnWindows.isSelected()){
            OS = rbtnWindows.getText();
        }

        Computer computer = new Computer (nom, model, memory, nbProcessors, HDD, OS);
        ordinateur.add(computer);

    }

    public void onEffacerClick(ActionEvent event) {
    }

    public void onExporterClick(ActionEvent event) {
    }

    public void onImporterClick(ActionEvent event) {
    }

    public void onPingClick(ActionEvent event) {
    }
}
