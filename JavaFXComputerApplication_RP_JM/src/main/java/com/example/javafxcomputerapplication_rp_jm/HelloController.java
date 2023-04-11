/*
    Auteur:         Monteiro Josimar, Pimenta Afonso Raphael
    Date:           11.04.2023
    Description:    Application permettant la saisie d'informations pour des ordinateurs des clients
 */

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

    // Attribut de classe lié au hello-view.fxml
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

    //Méthode pour ajouter les différentes cartes réseaux
    public void onAjouterCarteClick(ActionEvent event) {

        //Déclaration des regex pour les adresses IP et les masque de sous réseaux.
        String regExpIp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
        String regExpSm = "^(((255\\.){3}(255|254|252|248|240|224|192|128|0+))|((255\\.){2}(255|254|252|248|240|224|192|128|0+)\\.0)|((255\\.)(255|254|252|248|240|224|192|128|0+)(\\.0+){2})|((255|254|252|248|240|224|192|128|0+)(\\.0+){3}))$";

        //Contrôle que les informations entrées sont correctes.
        if (txtAddIp.getText().matches(regExpIp) && txtSM.getText().matches(regExpSm)) {
            String adresseIp = txtAddIp.getText();
            String masqueSR = txtSM.getText();

            //Ajout des cartes réseaux
            NetworkCard carte = new NetworkCard(adresseIp, masqueSR);
            carteReseau.add(carte);

            cmbAffCartes.getItems().add(carte.getIpAddress());

            System.out.println(adresseIp + masqueSR);
        } else {
            txtSM.setStyle("-fx-border-color:red");
            txtAddIp.setStyle("-fx-border-color:red");
            txtSM.setText("");
            txtAddIp.setText("");
        }

    }

    //Méthode pour ajouter les ordinateurs
    public void onEnregistrerClick(ActionEvent event) {

        //Regex pour les noms des ordinateurs.
        String regExpTexte = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$";

        //Dans le if nous testons que les différents champs correspondent au regex et que un OS est séléctionné.
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

        //Nous créons un ordinateur et ajoutons les cartes réseaux de ceux-ci.
        Computer computer = new Computer (nom, model, memory, nbProcessors, HDD, OS);
        for (NetworkCard c : carteReseau){
        computer.setCard(carteReseau);
        }
        ordinateur.add(computer);
            tblOrdinateur.getItems().add(computer);


            System.out.println(nom + model);


        } else {
            txtModel.setStyle("-fx-border-color:red");
            txtNom.setStyle("-fx-border-color:red");
            txtModel.setText("");
            txtNom.setText("");
        }

    }

    //Méthode pour effacer les données entrées.
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
        imgLinux.setImage(null);
        imgWindows.setImage(null);
        imgMac.setImage(null);

        //Boucle pour effacer toutes les cartes réseau qui sont contenues dans l'ArrayList.
        for (int i = 0; i < carteReseau.size(); i++){
            for (NetworkCard c : carteReseau) {

                cmbAffCartes.getItems().remove(String.valueOf(c.getIpAddress()));
            }
        }

    }

    //Méthode qui permet de filtrer les ordinateurs par l'OS.
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

    //Méthode pour exporter le tableau des ordinateurs.
    public void onExporterClick(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter vers un fichier texte");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier texte (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(btnExporter.getScene().getWindow());

        //On contrôle que le fichier n'est pas vide.
        if (file != null) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Parcourir les éléments du tableau
                for (Computer computer : tblOrdinateur.getItems()) {
                    // Écrire les informations dans le fichier texte
                    bufferedWriter.write("Nom : " + computer.getName() + "; \n");
                    bufferedWriter.write("Modèle : " + computer.getModel() + ";\n");
                    bufferedWriter.write("Mémoire RAM : " + computer.getMemory() + " Go;\n");
                    bufferedWriter.write("Nombre de processeurs : " + computer.getNbProcessors() + ";\n");
                    bufferedWriter.write("Quantité de stockage : " + computer.getHDD() + " Go;\n");
                    bufferedWriter.write("Système d'exploitation : " + computer.getOS() + ";\n");
                    bufferedWriter.write("Carte(s) réseau : \n");
                    for (NetworkCard networkCard : computer.getCard()) {
                        bufferedWriter.write("- Adresse IP : " + networkCard.getIpAddress() + ", Masque sous-réseau : " + networkCard.getMask() + "\n");
                    }
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

    //Méthode qui permet d'importer des ordinateurs depuis un document texte.
    public void onImporterClick(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un document texte");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt"));
        File file = fileChooser.showOpenDialog(null);

        //On contrôle que le fichier n'est pas vide
        if (file != null) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(";"); // Assumer que les données sont séparées par des points-virgules
                    if (data.length == 6) { // Assumer que chaque ligne doit contenir 6 colonnes
                        String nom = data[0].trim();
                        String model = data[1].trim();
                        int memory = Integer.parseInt(data[2].trim());
                        int nbProcessors = Integer.parseInt(data[3].trim());
                        int HDD = Integer.parseInt(data[4].trim());
                        String OS = data[5].trim();

                        Computer computer = new Computer(nom, model, memory, nbProcessors, HDD, OS);
                        ordinateur.add(computer);
                    } else {
                        System.out.println("Erreur : La ligne ne contient pas le bon nombre de colonnes");
                    }
                }
                reader.close();
                tblOrdinateur.getItems().addAll(ordinateur);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur : Impossible de lire le fichier");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Erreur : Les données ne sont pas au bon format");
            }
        }
    }

    //Méthode qui permet de ping les cartes réseaux des ordinateurs enregistrés.
    public void onPingClick(ActionEvent event) throws IOException {

        Computer computer = tblOrdinateur.getSelectionModel().getSelectedItem();
        if (computer != null) {
            Computer computer1 = tblOrdinateur.getSelectionModel().getSelectedItem();
            String ipAddress = String.valueOf(computer1.getCard());
            String[] command = {"cmd.exe", "/c", "start", "cmd.exe", "/k", "ping", "IP"+ ipAddress};
            ProcessBuilder probuilder = new ProcessBuilder(command);
            Process process = probuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            try {
                // Effectuer le ping vers l'adresse IP de l'ordinateur
                InetAddress inetAddress = InetAddress.getByName(ipAddress);
                boolean isReachable = inetAddress.isReachable(5000); // Timeout de 5 secondes
                if (isReachable) {
                    System.out.println("L'ordinateur " + computer.getName() + " est en ligne.");
                } else {
                    System.out.println("L'ordinateur " + computer.getName() + " est hors ligne.");
                }
            } catch (UnknownHostException e) {
                System.out.println("Adresse IP invalide : " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erreur lors de la tentative de ping : " + e.getMessage());
            }
        } else {
            System.out.println("Aucun ordinateur sélectionné.");
        }
    }

    //Slider de la RAM
    public void onSliRAM(){
        nbRam = (int) slRam.getValue();
        System.out.println(nbRam);
        lblRAM.setText(Integer.toString(nbRam));
    }

    //Slider du nombre de processeurs
    public void onNbProc(){
        nbProc = (int) slNbProc.getValue();
        System.out.println(nbProc);
        lblValProc.setText(Integer.toString(nbProc));
    }

    //Radio boutton pour la selection de Linux
    public void onBtnLinuxClick(ActionEvent event) {

        if (rbtnLinux.isSelected()) {
            InputStream input = this.getClass().getResourceAsStream("/linux.jpg");
            imgLinux.setImage(new Image(input));
            imgWindows.setImage(null);
            imgMac.setImage(null);
        }else {
            imgLinux.setImage(null);
        }
    }

    //Radio boutton pour la selection de Windows
    public void onBtnWindowsClick(ActionEvent event) {
        if (rbtnWindows.isSelected()) {
            InputStream input2 = this.getClass().getResourceAsStream("/windows.jpg");
            imgWindows.setImage(new Image(input2));
            imgLinux.setImage(null);
            imgMac.setImage(null);
        } else {
            imgWindows.setImage(null);
        }
    }

    //Radio boutton pour la selection de Mac
    public void onBtnMacClick(ActionEvent actionEvent) {
        if (rbtnMac.isSelected()) {
            InputStream input3 = this.getClass().getResourceAsStream("/mac.jpg");
            imgMac.setImage(new Image(input3));
            imgLinux.setImage(null);
            imgWindows.setImage(null);
        } else {
            imgMac.setImage(null);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //Partie filtre du tableau
        cmbListeRec.getItems().addAll("Tous les ordinateurs", "Linux", "Windows", "Mac");
        // sélectionne "Tous les ordinateurs" par défaut
        cmbListeRec.getSelectionModel().selectFirst();

        cmbListeRec.setOnAction(this::onFilterCombo);

        //Création des colonnes du tableau et leur implémentation.
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

    }
}

