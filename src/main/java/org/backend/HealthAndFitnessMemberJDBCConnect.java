package org.backend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class establishes a connection to a Health and Fitness database and initiates the application.
 * It utilizes JDBC to interact with the PostgreSQL database.
 *
 * @author Zarif, Arun
 * @version 1.0
 */
public class HealthAndFitnessMemberJDBCConnect extends Application {

    private Connection conn;

    private static HealthAndFitnessMemberJDBCConnect instance;

    /**
     * Constructs a new HealthAndFitnessMemberJDBCConnect object.
     */
    public HealthAndFitnessMemberJDBCConnect(){

        connectToDatabase();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Landing.fxml"));
        try {
            // Load the FXML layout
            Parent root = fxmlLoader.load();
            System.out.println("FXML file loaded successfully.");

            // Create the scene
            Scene scene = new Scene(root);

            // Set the scene and show the stage
            stage.setScene(scene);
            stage.setTitle("Health and Fitness App");
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());

            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the PostgreSQL database.
     *
     * @throws RuntimeException if there is an error while loading the JDBC driver or connecting to the database.
     */
    public void connectToDatabase(){
        String url = "jdbc:postgresql://localhost:5432/HealthAndFitnessDB";
        String user = "Admin";
        String password = "M3mb3rP@ssw0rd!";

        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                System.out.println("Connected to pgAdmin successfully!");
            } else {
                System.out.println("Could not connect!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the JDBC connection.
     *
     * @throws SQLException if there is an error while closing the connection.
     */
    public void closeJDBCConnection(){
        try {
            if (this.conn != null) {
                this.conn.close();
                System.out.println("JDBC connection closed.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the JDBC Connection object.
     *
     * @return the Connection object
     */
    public Connection getConn(){
        return this.conn;
    }

    /**
     * The main method to execute the program.
     *
     */

    public static HealthAndFitnessMemberJDBCConnect getInstance(){

        if (instance == null){
            instance =  new HealthAndFitnessMemberJDBCConnect();
        }
        return instance;

    }
    public static void main(String[] args) {

        launch(args);

    }
}
