package com.coherent.store.helpers;

import com.coherent.domain.Category;
import com.coherent.store.Store;

import java.sql.*;
import java.util.Random;
import java.util.Set;

import static com.coherent.store.helpers.RandomStorePopulator.createCategories;

public class DBHelper {
    private final static String URL = "jdbc:postgresql://localhost:5432/onlinestore";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "password";
    private static Connection CON = null;
    private static Statement STMT1 = null;
    private static Statement STMT2 = null;
    private static ResultSet RS1 = null;
    Store store;

    public DBHelper(Store store) {
        this.store = store;
    }

    public void accessStore() {
        System.out.println("Accessing Store...\n");
        connectDB();
        clearDB();
        createCategoriesTable();
        createProductsTable();
        populateStore();
        displayStore();
        addProductsToCart();
        displayShoppingCart();
    }

    public void connectDB() {
        System.out.println("Connecting to PostgreSQL Database...");
        try {
            CON = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("\t- Connection was successful!\n");

            STMT1 = CON.createStatement();
            STMT2 = CON.createStatement();

        } catch (SQLException e) {
            System.out.println("\t- Error occurred while connecting to PostgreSQL Database!");
            throw new RuntimeException(e);
        }
    }

    public void clearDB() {
        System.out.println("Clearing Database...");
        String deleteCategories = "DROP TABLE IF EXISTS public.\"CATEGORIES\";";
        String deleteProducts = "DROP TABLE IF EXISTS public.\"PRODUCTS\";";
        try {
            STMT1.execute(deleteCategories);
            System.out.println("\t- Categories were successfully cleared!");
            STMT1.execute(deleteProducts);
            System.out.println("\t- Products were successfully cleared!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while clearing Database!");
            throw new RuntimeException(e);
        }
    }

    public void createCategoriesTable() {
        System.out.println("Creating Categories Table...");
        String categoriesQuery = """
                CREATE TABLE IF NOT EXISTS public."CATEGORIES"
                (
                    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 ),
                    name text NOT NULL,
                    PRIMARY KEY (id)
                );""";
        try {
            PreparedStatement createTable = CON.prepareStatement(categoriesQuery);
            createTable.execute();
            System.out.println("\t- Categories Table Created!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while creating Categories Table");
            throw new RuntimeException(e);
        }
    }

    public void createProductsTable() {
        System.out.println("Creating Products Table...");
        String productsQuery = """
                CREATE TABLE IF NOT EXISTS public."PRODUCTS"
                (
                    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000000 ),
                    category_id integer NOT NULL,
                    name text NOT NULL,
                    rate double precision NOT NULL,
                    price double precision NOT NULL,
                    PRIMARY KEY (id)
                );""";
        try {
            PreparedStatement createTable = CON.prepareStatement(productsQuery);
            createTable.execute();
            System.out.println("\t- Products Table Created!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while creating Products Table");
            throw new RuntimeException(e);
        }
    }

    public void createOrderTable() {
        System.out.println("\nCreating Order Table...");
        String orderQuery = """
                    CREATE TABLE IF NOT EXISTS public."ORDER"
                    (
                        id integer NOT NULL,
                        category_id integer NOT NULL,
                        name text NOT NULL,
                        rate double precision NOT NULL,
                        price double precision NOT NULL,
                        PRIMARY KEY (id)
                    );""";
        try {
            PreparedStatement createTable = CON.prepareStatement(orderQuery);
            createTable.execute();
            System.out.println("\t- Order Table Created!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while creating Order Table");
            throw new RuntimeException(e);
        }
    }

    public void clearShoppingCart() {
        System.out.println("Clearing Shopping Cart...");
        String deleteCart = "DELETE FROM public.\"ORDER\";";
        try {
            STMT1.execute(deleteCart);
            System.out.println("\t- Shopping Cart was successfully cleared!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while clearing Shopping Cart!");
            throw new RuntimeException(e);
        }
    }

    public void addProductsToCart() {
        createOrderTable();
        clearShoppingCart();
        Random random = new Random();
        System.out.println("Adding Products to Shopping Cart...");
        String addRandomProductQuery = "INSERT INTO public.\"ORDER\" " +
                "(SELECT * FROM public.\"PRODUCTS\" WHERE id = ?) ON CONFLICT (id) DO NOTHING";
        try {
            for (int i = 0; i < random.nextInt(1, 11); i++) {
                int productID = random.nextInt(1, 31);
                PreparedStatement addRandomProducts = CON.prepareStatement(addRandomProductQuery);
                addRandomProducts.setInt(1, productID);
                addRandomProducts.execute();
            }
            System.out.println("\t- Shopping Cart was successfully filled!\n");
        } catch (SQLException e) {
            System.out.println("\t- Error occurred while adding products to Shopping Cart!");
            throw new RuntimeException(e);
        }
    }

    public void populateStore() {
        RandomProductCreator productCreator = new RandomProductCreator();
        Set<Category> categories = createCategories();
        System.out.println("Populating Categories & Products Tables...");

        for (Category category : categories) {
            try {
                PreparedStatement addCategories = CON.prepareStatement(
                        "INSERT INTO public.\"CATEGORIES\"(name) VALUES(?);"
                );
                addCategories.setString(1, category.getName());
                addCategories.execute();

                PreparedStatement getCategoryID = CON.prepareStatement(
                        "SELECT id FROM public.\"CATEGORIES\" WHERE name = ?;"
                );
                getCategoryID.setString(1, category.getName());
                RS1 = getCategoryID.executeQuery();

                int id = 0;
                while (RS1.next()) {
                    id = RS1.getInt("id");
                }

                for (int i = 0; i < 10; i++) {
                    PreparedStatement addProduct = CON.prepareStatement(
                            "INSERT INTO public.\"PRODUCTS\"(category_id, name, rate, price) VALUES(?, ?, ?, ?);"
                    );
                    addProduct.setInt(1, id);
                    addProduct.setString(2, productCreator.getName(category.getName()));
                    addProduct.setDouble(3, productCreator.getRate());
                    addProduct.setDouble(4, productCreator.getPrice());
                    addProduct.execute();
                }
                System.out.println("\t\t- " + category.getName() + " Category was successfully populated!");

            } catch (SQLException e) {
                System.out.println("\t- Error occurred while populating Database!");
                throw new RuntimeException(e);
            }
        }
    }

    public void displayStore() {
        System.out.println("\nDisplaying Store...\n");
        try {
            System.out.println("\033[1mONLINE STORE HOMEPAGE\033[0m");
            System.out.print("Hi, " + USERNAME + "! Enjoy Your Shopping!");
            RS1 = STMT1.executeQuery("SELECT * FROM public.\"CATEGORIES\"");
            while (RS1.next()) {
                System.out.println("\n\n\033[1mCategory: " + RS1.getString("name") + "\033[0m");
                ResultSet RS2 = STMT2.executeQuery(
                        "SELECT * FROM public.\"PRODUCTS\" WHERE category_id = " + RS1.getInt("id")
                );

                System.out.printf("\033[4m%-5s%-40s%-10s%5s\033[0m\n", "ID", "Name", "Rate", "Price");
                while (RS2.next()) {
                    System.out.printf("%-5d%-40s%-10.2f%5.2f\n",
                            RS2.getInt("id"),
                            RS2.getString("name"),
                            RS2.getDouble("rate"),
                            RS2.getDouble("price"));
                }
            }

        } catch (SQLException e) {
            System.out.println("\t- Error occurred while displaying Store!");
            throw new RuntimeException(e);
        }
    }

    public void displayShoppingCart() {
        System.out.println("\nDisplaying Shopping Cart...\n");
        try {
            System.out.println("\033[1mSHOPPING CART\033[0m");
            RS1 = STMT1.executeQuery("SELECT * FROM public.\"ORDER\"");
            System.out.printf("\033[4m%-5s%-40s%-10s%5s\033[0m\n", "ID", "Name", "Rate", "Price");
            while (RS1.next()) {
                System.out.printf("%-5d%-40s%-10.2f%5.2f\n",
                        RS1.getInt("id"),
                        RS1.getString("name"),
                        RS1.getDouble("rate"),
                        RS1.getDouble("price"));
            }

        } catch (SQLException e) {
            System.out.println("\t- Error occurred while displaying Store!");
            throw new RuntimeException(e);
        }
    }
}