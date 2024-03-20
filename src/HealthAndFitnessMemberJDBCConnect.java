import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class establishes a connection to a Health and Fitness database and initiates the application.
 * It utilizes JDBC to interact with the PostgreSQL database.
 *
 * @author Zarif
 * @version 1.0
 */
public class HealthAndFitnessMemberJDBCConnect {

    private Connection conn;

    /**
     * Constructs a new HealthAndFitnessMemberJDBCConnect object.
     */
    HealthAndFitnessMemberJDBCConnect(){}

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
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        HealthAndFitnessMemberJDBCConnect healthAndFitnessMemberJDBCConnect = new HealthAndFitnessMemberJDBCConnect();
        healthAndFitnessMemberJDBCConnect.connectToDatabase();

        ApplicationInterface applicationInterface = new ApplicationInterface(healthAndFitnessMemberJDBCConnect);
        applicationInterface.run();
    }
}
