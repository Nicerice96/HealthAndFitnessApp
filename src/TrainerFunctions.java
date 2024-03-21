import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * Performs Trainer related functions
 * @author Zarif, Arun
 * @version 1.0
 */
public class TrainerFunctions {

    private boolean flag = true;

    private HealthAndFitnessMemberJDBCConnect connect;
    private int trainerID;
    /**
     * Constructor for TrainerFunctions class.
     * @param connect JDBC connection instance
     * @param trainerID ID of the trainer
     */
    TrainerFunctions(HealthAndFitnessMemberJDBCConnect connect, int trainerID){

        this.connect = connect;
        this.trainerID = trainerID;
    }
    /**
     * Retrieves the start date of the trainer's availability.
     * @return The start date of availability
     */
    public Date returnStartDate(){

        String returnStartDate = "SELECT trainer.start_availability FROM trainer WHERE trainer_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(returnStartDate);
            preparedStatement.setInt(1, this.trainerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Date startDate = resultSet.getDate("start_availability");

                return startDate;
            }

            return null;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Retrieves the end date of the trainer's availability.
     * @return The end date of availability
     */
    public Date returnEndDate(){

        String returnStartDate = "SELECT trainer.end_availability FROM trainer WHERE trainer_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(returnStartDate);
            preparedStatement.setInt(1, this.trainerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Date endDate = resultSet.getDate("end_availability");

                return endDate;
            }

            return null;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Updates the availability of the trainer for personal training sessions.
     * @param startDate Start date of availability
     * @param endDate End date of availability
     */
    public void updateAvailableToMembers(Date startDate, Date endDate){

        String updateTrainer = "UPDATE personal_training SET start_date = ?, end_date = ? WHERE trainer_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateTrainer);
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setInt(3, this.trainerID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Adds the trainer to the personal training schedule, making them available to members.
     */
    public void makeAvailableToMembers(){
        //adds trainer to the personal training table

        String insertTrainer = "INSERT INTO personal_training (trainer_id, start_date, end_date) VALUES (?, ?, ?)";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(insertTrainer);
            preparedStatement.setInt(1, this.trainerID);
            preparedStatement.setDate(2, returnStartDate());
            preparedStatement.setDate(3, returnEndDate());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Removes the trainer from the personal training schedule, making them unavailable to members.
     */
    public void makeUnavailableToMembers(){

        String makeUnavailable = "DELETE FROM personal_training WHERE trainer_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(makeUnavailable);
            preparedStatement.setInt(1, this.trainerID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //removes the trainer from the personal training table
    }
    /**
     * Updates the availability schedule of the trainer.
     */
    public void updateAvailability(){

        System.out.println("Enter new start date:");
        Scanner scanStartDate = new Scanner(System.in);
        String startDateString = scanStartDate.nextLine();

        System.out.println("Enter new end date: ");
        Scanner scanEndDate = new Scanner(System.in);
        String endDateString = scanEndDate.nextLine();

        String update = "UPDATE trainer SET start_date = ?, end_date = ? WHERE trainer_ID = ? ";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedStartDate = dateFormat.parse(startDateString);
            java.sql.Date startDateSQL = new java.sql.Date(parsedStartDate.getTime());

            java.util.Date parsedEndDate = dateFormat.parse(endDateString);
            java.sql.Date endDateSQL = new java.sql.Date(parsedEndDate.getTime());

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(update);
            preparedStatement.setDate(1,startDateSQL);
            preparedStatement.setDate(2, endDateSQL);
            preparedStatement.setInt(3, this.trainerID);

            preparedStatement.executeUpdate();
            updateAvailableToMembers(startDateSQL, endDateSQL);


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Manages the schedule of the trainer, allowing them to make themselves available or unavailable to members.
     */
    public void manageSchedule(){

        System.out.println("Manage Schedule:\n1. Make Schedule Available to Members\n2. Make Schedule Unavailable To Members\n3. Update Schedule Availability");
        Scanner scanInput = new Scanner(System.in);
        int userInput = scanInput.nextInt();

        switch(userInput){

            case 1:
                makeAvailableToMembers();
                break;
            case 2:
                makeUnavailableToMembers();
                break;
            case 3:
                updateAvailability();
                break;
        }
    }
    /**
     * Displays profiles of members assigned to the trainer along with their training schedule.
     */
    public void viewMemberProfiles(){

        String getMemberID = "SELECT start_date, end_date, username, trainer.name FROM personal_training JOIN \"member\" ON personal_training.member_id = \"member\".member_id JOIN trainer ON personal_training.trainer_id = trainer.trainer_id WHERE personal_training.trainer_id = ?";
        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(getMemberID);
            preparedStatement.setInt(1, this.trainerID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String username = resultSet.getString("username");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");

                System.out.println("username: " + username + " start date: " + startDate + " end date: " + endDate);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Exits the trainer functions menu.
     */
    public void exit(){

        this.flag = false;
    }
    /**
     * Starts the TrainerFunctions interface, allowing trainers to perform various actions.
     */
    public void startTrainerFunctions() {

        while (flag) {

            System.out.println("Welcome to the Trainer landing page!\nWhat action would you like to perform?\n1. manageSchedule\n2. viewMemberProfiles\nPress 0 to exit");
            Scanner scanTrainerAction = new Scanner(System.in);
            int trainerAction = scanTrainerAction.nextInt();

            switch (trainerAction) {

                case 1:
                    manageSchedule();
                    break;
                case 2:
                    viewMemberProfiles();
                    break;
                case 0:
                    exit();

            }

        }
    }

}
