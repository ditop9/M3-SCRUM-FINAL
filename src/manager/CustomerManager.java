package manager;

import app.Main;
import classes.Customer;
import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerManager {
    private final static ArrayList<Customer> customers = Input.readCustomersFile();
    public static void run() {
        int option;
        do {
            displayMenu();
            option = DataInput.getValidInteger("Escull una opció");
            handleOption(option);
        } while (option != 0);
    }
    public static void displayMenu() {
        System.out.println("""
                ===============================
                |  ==== MENÚ DE CLIENTS ====  |
                |_____________________________|
                | 1. AFEGIR CLIENT            |
                | 2. ELIMINAR CLIENT          |
                | 3. BUSCAR CLIENT            |
                | 0. TORNAR AL MENÚ PRINCIPAL |
                |_____________________________|""");
    }
    public static void handleOption(int option) {
        switch (option) {
            case 1:
                addNewCustomer();
                break;
            case 2:
                deleteCustomer();
                break;
            case 3:
                break;
            case 0:
                System.out.println("Tornant al menú principal");
                Main.run();
                break;
            default:
                System.out.println("Error: No és una opció vàlida");
                break;
        }
    }
    static int getNewIdentifier() {
        return customers.getLast().getIdentifier() + 1;
    }
    public static Customer createNewCustomer() {
        int identifier = getNewIdentifier();
        String dni = DataInput.getValidDni();
        String name = DataInput.getValidString("Introdueix el nom del client");
        int age = DataInput.getValidAge();
        return new Customer(identifier, dni, name, age);
    }
    public static void addNewCustomer() {
        Customer customer = createNewCustomer();
        try {
            Output.writeCustomersFile(customer);
        } catch (IOException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de clients");
            Main.run();
        }
    }
    public static void searchCustomerById() {
        int id = DataInput.getValidInteger("Introdueix l'ID del client");
        ArrayList<Customer> customers = Input.readCustomersFile();

    }
    public static Customer selectCustomerById(int id) {
        for (Customer c : customers) {
            if (id == c.getIdentifier()) {
                return c;
            }
        }
        return null;
    }
    public static Customer chooseExistingCustomer() {
        Input.showCustomers();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        DataInput.handleExit(String.valueOf(id));
        for (Customer c : customers) {
            if (c.getIdentifier() == id) {
                return c;
            }
        }
        System.out.println("Error: No s'ha trobat el client");
        Main.run();
        return null;
    }
    public static void deleteCustomer() {
        if (customers.isEmpty()) {
            System.out.println("Error: No es troben clients");
            Main.run();
        }
        Customer customer = chooseExistingCustomer();
        System.out.println(customer);
        if (customer == null) {
            System.out.println("S'ha produït un error");
            Main.run();
        } else {
            if (DataInput.confirmAction()) {
                customers.removeIf(c -> c.getIdentifier() == customer.getIdentifier());
                try {
                    Output.reWriteCustomersFile(customers);
                    System.out.println("S'ha eliminat el client");
                } catch (FileNotFoundException e) {
                    System.out.println("Error: No s'ha trobat l'arxiu dels clients");
                    Main.run();
                }
            } else {
                System.out.println("Error: No s'ha proporcionat el número correcte");
                Main.run();
            }
        }
    }
}