package com.example.javafxcomputerapplication_rp_jm;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView tblOrdinateur;
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

    @FXML
    private Label lblRAM;

    @FXML
    private Label lblValProc;

    private ArrayList<Computer> ordinateur = new ArrayList<Computer>();
    private ArrayList<NetworkCard> carteReseau = new ArrayList<NetworkCard>();

    private int nbProc;

    private int nbRam;

    public void onAjouterCarteClick(ActionEvent event) {
        String adresseIp = txtAddIp.getText();
        String masqueSR = txtSM.getText();
        NetworkCard carte = new NetworkCard(adresseIp, masqueSR);
        carteReseau.add(carte);


    }

    public void onEnregistrerClick(ActionEvent event) {

        String nom = txtNom.getText();
        String model = txtModel.getText();
        int memory = nbRam;
        int nbProcessors = nbProc;
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
       // for (NetworkCard c : carteReseau){
        computer.setCard(carteReseau);
        //}
        ordinateur.add(computer);
        for (Computer c: ordinateur){
            System.out.println(c.getName());
        }
        tblOrdinateur.getItems().add(ordinateur);


    }

    public void onEffacerClick(ActionEvent event) {
        txtNom.setText("");
        txtModel.setText("");
        txtSM.setText("");
        txtAddIp.setText("");
        txtQteStock.setText("");
        rbtnLinux.setSelected(false);
        rbtnMac.setSelected(false);
        rbtnWindows.setSelected(false);
        slNbProc.setValue(2);
        slRam.setValue(2);
        lblRAM.setText("1");
        lblValProc.setText("1");


    }

    public void onExporterClick(ActionEvent event) {
    }

    public void onImporterClick(ActionEvent event) {
    }

    public void onPingClick(ActionEvent event) {
    }
    public void onListeSelectionChange(Event event) {

    }

    public void onSliRAM(){
        nbRam = (int) slRam.getValue();
        System.out.println(nbRam);
        lblRAM.setText(Integer.toString(nbRam));
    }

    public void onNbProc(){
        nbProc = (int) slNbProc.getValue();
        System.out.println(nbProc);
        lblValProc.setText(Integer.toString(nbProc));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        TableColumn<Computer, String> colName = new TableColumn<>("Nom");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Computer, String> colModel = new TableColumn<>("Modèle");
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Computer, String> colMemory = new TableColumn<>("RAM");
        colMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));

        TableColumn<Computer, String> colProc = new TableColumn<>("Processeurs logiques");
        colProc.setCellValueFactory(new PropertyValueFactory<>("nbProcessors"));

        TableColumn<Computer, String> colStore = new TableColumn<>("Stockage");
        colStore.setCellValueFactory(new PropertyValueFactory<>("HDD"));

        TableColumn<Computer, String> colOS = new TableColumn<>("OS");
        colOS.setCellValueFactory(new PropertyValueFactory<>("OS"));

        tblOrdinateur.getColumns().add(colName);
        tblOrdinateur.getColumns().add(colModel);
        tblOrdinateur.getColumns().add(colMemory);
        tblOrdinateur.getColumns().add(colStore);
        tblOrdinateur.getColumns().add(colOS);
        tblOrdinateur.getColumns().add(colProc);
    }



}

