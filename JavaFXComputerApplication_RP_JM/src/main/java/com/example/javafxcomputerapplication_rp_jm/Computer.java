package com.example.javafxcomputerapplication_rp_jm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lbi
 * @version 1.0
 */
public class Computer {

    private String name;
    private String model;
    private int memory;
    private int nbProcessors;
    private int HDD;
    private String OS;
    private ArrayList<NetworkCard> card;

    public Computer(String name, String model, String OS) {
        this.name = name;
        this.model = model;
        this.OS = OS;
    }

    public Computer(String name, String model, int memory, int nbProcessors, int HDD, String OS) {
        this.name = name;
        this.model = model;
        this.memory = memory;
        this.nbProcessors = nbProcessors;
        this.HDD = HDD;
        this.OS = OS;
    }

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

    public ArrayList<NetworkCard> getCard() {
        return card;
    }

    public void setCard(ArrayList<NetworkCard> card) {
        this.card = card;
    }

    public void export( File f ) {

    }


}
