// Importation de classes
package com.example.javafxcomputerapplication_rp_jm;
import java.io.File;
import java.util.ArrayList;

/**

 Cette classe définit les caractéristiques d'un ordinateur.

 Elle contient plusieurs méthodes pour modifier et accéder aux attributs de l'ordinateur.
 */
public class Computer {

    // Attributs de l'ordinateur
    private String name;
    private String model;
    private int memory;
    private int nbProcessors;
    private int HDD;
    private String OS;
    private NetworkCard card;

    // Constructeur pour un ordinateur avec un nom, un modèle et un système d'exploitation
    public Computer(String name, String model, String OS) {
        this.name = name;
        this.model = model;
        this.OS = OS;
    }

    // Constructeur pour un ordinateur avec un nom, un modèle, une mémoire, un nombre de processeurs, un disque dur et un système d'exploitation
    public Computer(String name, String model, int memory, int nbProcessors, int HDD, String OS) {
        this.name = name;
        this.model = model;
        this.memory = memory;
        this.nbProcessors = nbProcessors;
        this.HDD = HDD;
        this.OS = OS;
    }

    // Accesseurs pour les attributs de l'ordinateur
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getNbProcessors() {
        return nbProcessors;
    }

    public void setNbProcessors(int nbProcessors) {
        this.nbProcessors = nbProcessors;
    }

    public int getHDD() {
        return HDD;
    }

    public void setHDD(int HDD) {
        this.HDD = HDD;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public NetworkCard getCard() {
        return card;
    }

    public void setCard(NetworkCard card) {
        this.card = card;
    }

    // Méthode pour exporter les caractéristiques de l'ordinateur dans un fichier
    public void export( File f ) {

    }
}