package com.example.javafxcomputerapplication_rp_jm;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HelloController implements Initializable {

    @FXML
    private TableView<Computer> tblOrdinateur;
    @FXML
    private ImageView imgMac;
    @FXML
    private ImageView imgLinux;
    @FXML
    private ImageView imgWindows;
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
    private ComboBox<String> cmbAffCartes;

    @FXML
    private ComboBox<String> cmbListeRec;

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

    private InputStream inputLinux;
    private InputStream inputWindows;
    private InputStream inputMac;



    private int nbProc;

    private int nbRam;

    public void onAjouterCarteClick(ActionEvent event) {

        String regExpIp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
        String regExpSm = "^((2[0-5][0-5]|1[\\d][\\d]|[\\d][\\d]|[\\d])\\.){3}(2[0-5][0-5]|1[\\d][\\d]|[\\d][\\d]|[\\d])$";

        if (txtAddIp.getText().matches(regExpIp) && txtSM.getText().matches(regExpSm)) {
            String adresseIp = txtAddIp.getText();
            String masqueSR = txtSM.getText();
            NetworkCard carte = new NetworkCard(adresseIp, masqueSR);
            carteReseau.add(carte);

            cmbAffCartes.getItems().add(carte.getIpAddress());


            System.out.println(adresseIp + masqueSR);
        } else {
            txtSM.setStyle("-fx-border-color:red");
            txtAddIp.setStyle("-fx-border-color:red");
            txtSM.setText("");
            txtAddIp.setText("");
            System.out.println("erreur");
        }

    }

    public void onEnregistrerClick(ActionEvent event) {

        String regExpTexte = "^[A-Za-z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        if (txtNom.getText().matches(regExpTexte) && txtModel.getText().matches(regExpTexte)) {
            String nom = txtNom.getText();
            String model = txtModel.getText();
            int memory = nbRam;
            int nbProcessors = nbProc;
            int HDD = Integer.parseInt(txtQteStock.getText());
            String OS = "";
            if (rbtnLinux.isSelected()) {
                OS = rbtnLinux.getText();
            } else if (rbtnMac.isSelected()) {
                OS = rbtnMac.getText();
            } else if (rbtnWindows.isSelected()) {
                OS = rbtnWindows.getText();
            }

        Computer computer = new Computer (nom, model, memory, nbProcessors, HDD, OS);

        for (NetworkCard c : carteReseau){
            computer.setCard(c);
        }
        ordinateur.add(computer);
            tblOrdinateur.getItems().add(computer);


            System.out.println(nom + model);


        } else {
            System.out.println("Erreur Texte");
        }

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
        for (int i = 0; i < carteReseau.size(); i++){
            for (NetworkCard c : carteReseau) {

                cmbAffCartes.getItems().remove(String.valueOf(c.getIpAddress()));
            }
        }

    }

    public void onFilterCombo (ActionEvent event){
        String osSelectionne = cmbListeRec.getSelectionModel().getSelectedItem();
        if (osSelectionne == null || osSelectionne.isEmpty() || osSelectionne.equals("Tous les ordinateurs")) {
            tblOrdinateur.getItems().setAll(ordinateur);
        } else {
            ArrayList<Computer> ordinateursLinux = new ArrayList<>();
            for (Computer c : ordinateur) {
                if (c.getOS().equals(osSelectionne)) {
                    ordinateursLinux.add(c);
                }
            }
            tblOrdinateur.getItems().setAll(ordinateursLinux);
        }

    }

    public void onExporterClick(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter vers un fichier texte");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier texte (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(btnExporter.getScene().getWindow());

        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Parcourir les éléments du tableau
                for (Computer computer : tblOrdinateur.getItems()) {
                    // Écrire les informations dans le fichier texte
                    bufferedWriter.write("Nom : " + computer.getName() + "\n");
                    bufferedWriter.write("Modèle : " + computer.getModel() + "\n");
                    bufferedWriter.write("Mémoire RAM : " + computer.getMemory() + " Go\n");
                    bufferedWriter.write("Nombre de processeurs : " + computer.getNbProcessors() + "\n");
                    bufferedWriter.write("Quantité de stockage : " + computer.getHDD() + " Go\n");
                    bufferedWriter.write("Système d'exploitation : " + computer.getOS() + "\n");
                    bufferedWriter.write("Carte(s) réseau : \n");
                    bufferedWriter.write("- Adresse IP : " + computer.getCard().getIpAddress() + ", Masque sous-réseau : " + computer.getCard().getMask() + "\n");
                    bufferedWriter.write("\n");
                }

                bufferedWriter.close();
                fileWriter.close();
                System.out.println("Données exportées avec succès !");
            } catch (IOException e) {
                System.out.println("Erreur lors de l'exportation des données : " + e.getMessage());
            }
        }
    }

    public void onImporterClick(ActionEvent event) {
    }

    public void onPingClick(ActionEvent event) throws IOException {

        String[] command = {"cmd.exe", "/c", "start", "cmd.exe", "/k", "ping", "IP"+ txtAddIp.getText()};

            ProcessBuilder probuilder = new ProcessBuilder(command);
            Process process = probuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Optional: print ping output to console
            }
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbListeRec.getItems().addAll("Tous les ordinateurs", "Linux", "Windows", "Mac");
        cmbListeRec.getSelectionModel().selectFirst(); // sélectionne "Tous les ordinateurs" par défaut

        cmbListeRec.setOnAction(this::onFilterCombo);

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

        TableColumn<Computer, String> colIP = new TableColumn<>("IP");

        colIP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Computer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Computer, String> param) {
                Computer computer = param.getValue();
                ArrayList<String> ipAddresses = new ArrayList<>();
                for (NetworkCard c : carteReseau){
                    ipAddresses.add(c.getIpAddress());
                }
                return new SimpleStringProperty(String.join(", ", ipAddresses));
            }
        });







        tblOrdinateur.getColumns().add(colName);
        tblOrdinateur.getColumns().add(colModel);
        tblOrdinateur.getColumns().add(colMemory);
        tblOrdinateur.getColumns().add(colStore);
        tblOrdinateur.getColumns().add(colOS);
        tblOrdinateur.getColumns().add(colProc);
        tblOrdinateur.getColumns().add(colIP);

        if (rbtnLinux.isSelected()) {
            InputStream input2 = this.getClass().getResourceAsStream("/linux.jpg");
            imgLinux.setImage(new Image(input2));
        }else {
            InputStream input2 = this.getClass().getResourceAsStream("/windows.jpg");
            imgWindows.setImage(new Image(input2));
        }
    }




}

