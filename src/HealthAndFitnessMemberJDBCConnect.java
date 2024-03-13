

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HealthAndFitnessMemberJDBCConnect {

    private Connection conn;


    HealthAndFitnessMemberJDBCConnect(){}


    public void connectToDatabase(){
        String url = "jdbc:postgresql://localhost:5432/HealthAndFitnessDB";
        String user = "Admin";
        String password = "M3mb3rP@ssw0rd!";

        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                System.out.println("Connected to pgAdmin successfully!");
            }
            else{
                System.out.println("Could not connect!");
            }



        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeJDBCConnection(){

        try {
            this.conn.close();
        }
        catch (Exception e){

            e.printStackTrace();
        }

    }

    public Connection getConn(){

        return this.conn;
    }





    public static void main(String[] args) {

        HealthAndFitnessMemberJDBCConnect healthAndFitnessMemberJDBCConnect = new HealthAndFitnessMemberJDBCConnect();

        healthAndFitnessMemberJDBCConnect.connectToDatabase();


        ApplicationInterface applicationInterface = new ApplicationInterface(healthAndFitnessMemberJDBCConnect);

        applicationInterface.run();

    }


}
