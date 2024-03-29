package classes;

import data.DataInput;
import data.input_output.Input;
import main.Main;

import java.util.ArrayList;

public class Supermarket {
    private int identifier;
    private String name;

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Supermarket(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }
    static Supermarket chooseExistingSupermarket() {
        ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();
        Input.showSupermarkets();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        DataInput.handleExit(String.valueOf(id));
        for (Supermarket s : supermarkets) {
            if (s.getIdentifier() == id) {
                return s;
            }
        }
        System.out.println("Error: No s'ha trobat el supermercat");
        Main.run();
        return null;
    }

    @Override
    public String toString() {
        return "Supermarket" +
                " ID " + identifier +
                " Nom " + name;
    }
}
