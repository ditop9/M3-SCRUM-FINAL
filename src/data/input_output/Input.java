package data.input_output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import main.Main;

public class Input {
    static ArrayList<String[]> readFileData(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<String[]> fileData = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            fileData.add(line);
        }
        return fileData;
    }
    public static ArrayList<User> readUsersFile() {
        File file = new File("database/UsersData.csv");
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No es troba l'arxiu d'usuaris");
            Main.run();
        }
        for (String[] line : fileData) {
            users.add(new User(Integer.parseInt(line[0]), line[1], line[2]));
        }
        return users;
    }
    public static void showUsers() {
        ArrayList<User> users = readUsersFile();
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }
    public static void showCustomers() {
        ArrayList<Customer> customers = readCustomersFile();
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }
    }

    public static ArrayList<Product> readProductsFile() throws FileNotFoundException {
        File file = new File("database/ProductsData.csv");
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
             fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de productes");
            Main.run();
        }
        for (String[] line : fileData) {
            products.add(new Product(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]), Boolean.parseBoolean(line[3])));
        }
        return products;
    }
    public static ArrayList<Customer> readCustomersFile() {
        File file = new File("database/CustomersData.csv");
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de clients");
            Main.run();
        }
        for (String[] line : fileData){
            customers.add(new Customer(Integer.parseInt(line[0]),line[1], line[2], Integer.parseInt(line[3])));
        }
        return customers;
    }
}
