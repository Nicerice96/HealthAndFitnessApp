
import org.postgresql.util.PGInterval;

import javax.xml.transform.Result;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * Performs Admin related functions
 * @author Zarif, Arun
 * @version 1.0
 */
public class AdminFunctions {

    private boolean flag = true;

    private double standardTrainerFee;

    private HealthAndFitnessMemberJDBCConnect connect;

    /**
     * Constructor for AdminFunctions class.
     * @param connect JDBC connection instance
     */
    AdminFunctions(HealthAndFitnessMemberJDBCConnect connect){

        this.connect = connect;
        this.standardTrainerFee = 17.9;
    }

    /**
     * Displays all available rooms along with their booking dates.
     */
    public void displayAllRooms(){

        String display = "SELECT * FROM room_booking";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(display);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){

                int roomNumber = result.getInt("room_number");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");

                System.out.println("Room number: " + roomNumber + " Room is available from: " + startDate + " to " + endDate);


            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adds a new room to the room booking system.
     */
    public void addRooms(){

        displayAllRooms();

        System.out.println("Enter room number:");
        Scanner scanRoomNumber = new Scanner(System.in);
        int roomNumber = scanRoomNumber.nextInt();

        System.out.println("When is this room available?");
        Scanner scanStartDate = new Scanner(System.in);
        String startDate = scanStartDate.nextLine();

        System.out.println("When is this room no longer available?");
        Scanner scanEndDate = new Scanner(System.in);
        String endDate = scanEndDate.nextLine();

        String addRoom = "INSERT INTO room_booking (room_number, start_date, end_date) VALUES (?, ?, ?)";

        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.sql.Date startDateSQL = new java.sql.Date(parsedStartDate.getTime());

            java.util.Date parsedEndDate = dateFormat.parse(endDate);
            java.sql.Date endDateSQL = new java.sql.Date(parsedEndDate.getTime());



            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(addRoom);
            preparedStatement.setInt(1, roomNumber);
            preparedStatement.setDate(2, startDateSQL);
            preparedStatement.setDate(3, endDateSQL);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Removes a room from the room booking system.
     */
    public void removeRooms(){

        displayAllRooms();

        System.out.println("Which room would you like to delete?");
        Scanner scanRoomNumber = new Scanner(System.in);
        int roomNumber = scanRoomNumber.nextInt();

        String deleteRoom = "DELETE FROM room_bookings WHERE room_number = ?";

        try{


            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(deleteRoom);
            preparedStatement.setInt(1, roomNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Updates the availability schedule of a room.
     */
    public void updateRoomAvailability(){

        displayAllRooms();

        System.out.println("Which room would you like to update?");
        Scanner scanRoomNumber = new Scanner(System.in);
        int roomNumber = scanRoomNumber.nextInt();

        System.out.println("When is this room available?");
        Scanner scanStartDate = new Scanner(System.in);
        String startDate = scanStartDate.nextLine();

        System.out.println("When is this room no longer available?");
        Scanner scanEndDate = new Scanner(System.in);
        String endDate = scanEndDate.nextLine();

        String updateRoom = "UPDATE room_booking SET start_date = ?, end_date = ? WHERE room_number = ?";

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.sql.Date startDateSQL = new java.sql.Date(parsedStartDate.getTime());

            java.util.Date parsedEndDate = dateFormat.parse(endDate);
            java.sql.Date endDateSQL = new java.sql.Date(parsedEndDate.getTime());

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateRoom);
            preparedStatement.setDate(1, startDateSQL);
            preparedStatement.setDate(2, endDateSQL);
            preparedStatement.setInt(3, roomNumber);

            preparedStatement.executeUpdate();

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Manages room bookings, allowing admins to add, remove, or update room availability.
     */

    public void manageRoomBookings(){

        System.out.println("Would you like to add or subtract a room?\n1. add room\n2. remove room\n3. update room availability");
        Scanner scanUserInput = new Scanner(System.in);
        int userInput = scanUserInput.nextInt();

        if(userInput == 1){
            addRooms();
        }
        else if(userInput == 2){
            removeRooms();
        }
        else if (userInput == 3){
            updateRoomAvailability();
        }
    }
    /**
     * Displays all fitness equipment along with their last maintenance date.
     */
    public void displayAllEquipment(){

        String displayAll = "SELECT * FROM equipment_maintaince";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Date lastMaintained = resultSet.getDate("maintaince_date");
                String description = resultSet.getString("description");

                System.out.println("Equipment: " + description + " last maintained: " + lastMaintained);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Updates the maintenance date of fitness equipment.
     */
    public void updateMaintainceDate(){

        displayAllEquipment();

        System.out.println("Enter the last time this equipment was maintained:");
        Scanner scanner = new Scanner(System.in);
        String lastMaintained = scanner.nextLine();

        String updateMaintainceDate = "UPDATE equipment_maintaince SET maintaince_date = ? WHERE description = ?";

        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedDate = dateFormat.parse(lastMaintained);
            java.sql.Date lastMaintainedDate = new java.sql.Date(parsedDate.getTime());

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateMaintainceDate);
            preparedStatement.setDate(1, lastMaintainedDate);
            preparedStatement.setString(2, lastMaintained);

            preparedStatement.executeUpdate();

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Removes fitness equipment from the inventory.
     */
    public void removeEquipment(){

        System.out.println("Enter the description of the equipment you want to remove:");
        Scanner scanner = new Scanner(System.in);
        String equipmentDescription = scanner.nextLine();
        scanner.close();

        String removeEquipmentQuery = "DELETE FROM equipment_maintenance WHERE description = ?";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(removeEquipmentQuery);
            preparedStatement.setString(1, equipmentDescription);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error removing equipment: " + e.getMessage());
        }
    }
    /**
     * Adds new fitness equipment to the inventory.
     */
    public void addEquipment(){

        System.out.println("Enter the description of the equipment:");
        Scanner scanner = new Scanner(System.in);
        String equipmentDescription = scanner.nextLine();

        System.out.println("Enter the last time this equipment was maintained (yyyy-MM-dd):");
        String lastMaintainedDate = scanner.nextLine();
        scanner.close();

        String addEquipmentQuery = "INSERT INTO equipment_maintenance (description, maintenance_date) VALUES (?, ?)";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(lastMaintainedDate);
            java.sql.Date maintenanceDate = new java.sql.Date(parsedDate.getTime());

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(addEquipmentQuery);
            preparedStatement.setString(1, equipmentDescription);
            preparedStatement.setDate(2, maintenanceDate);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Equipment '" + equipmentDescription + "' added successfully.");
            } else {
                System.out.println("Failed to add equipment '" + equipmentDescription + "'.");
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException("Error adding equipment: " + e.getMessage());
        }

    }
    /**
     * Monitors fitness equipment, allowing admins to update maintenance dates, add, or remove equipment.
     */
    public void monitorFitnessEquipment(){
        System.out.println("Options:\n1. Update maintaince Date\n2. Add equipment\n3. Remove equipment");
        Scanner userInput = new Scanner(System.in);
        int userInputInt = userInput.nextInt();

        switch(userInputInt){

            case 1:
                updateMaintainceDate();
                break;
            case 2:
                addEquipment();
                break;
            case 3:
                removeEquipment();
                break;
        }
    }
    /**
     * Adds a new class to the class schedule.
     */
    public void addClass(){
            System.out.println("Enter the class name:");
            Scanner scanner = new Scanner(System.in);
            String className = scanner.nextLine();

            System.out.println("Enter the start time of the class (HH:mm:ss):");
            String startTime = scanner.nextLine();

            System.out.println("Enter the end time of the class (HH:mm:ss):");
            String endTime = scanner.nextLine();
            scanner.close();

            String addClassQuery = "INSERT INTO class_schedule (class_name, start_time, end_time) VALUES (?, ?, ?)";

            try {
                PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(addClassQuery);
                preparedStatement.setString(1, className);
                preparedStatement.setTime(2, java.sql.Time.valueOf(startTime));
                preparedStatement.setTime(3, java.sql.Time.valueOf(endTime));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Class '" + className + "' added successfully.");
                } else {
                    System.out.println("Failed to add class '" + className + "'.");
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error adding class: " + e.getMessage());
            }
    }
    /**
     * Removes a class from the class schedule.
     */
    public void removeClass(){

        System.out.println("Enter the ID of the class you want to remove:");
        Scanner scanner = new Scanner(System.in);
        int classId = scanner.nextInt();
        scanner.close();

        String removeClassQuery = "DELETE FROM class_schedule WHERE class_id = ?";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(removeClassQuery);
            preparedStatement.setInt(1, classId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Class with ID " + classId + " removed successfully.");
            } else {
                System.out.println("Class with ID " + classId + " not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error removing class: " + e.getMessage());
        }

    }
    /**
     * Updates class schedules, allowing admins to add or remove classes.
     */
    public void updateClassSchedules(){

        System.out.println("Would you like to:\n1. Add Class\n2. Remove Class");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();

        switch (userInput){
            case 1:
                addClass();
                break;
            case 2:
                removeClass();
                break;
        }
    }
    /**
     * Calculates the difference in days between the start and end date of personal training sessions for a member.
     * @param memberID The ID of the member
     * @return The difference in days
     */
    public int calculateDateDifference(int memberID){

        String difference = "SELECT AGE(end_date, start_date) FROM personal_training WHERE member_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(difference);
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                PGInterval interval = (PGInterval) resultSet.getObject("age");


                int totalHours = interval.getHours() + interval.getDays() * 24 + interval.getMonths() * 30 * 24 + interval.getYears() * 365 * 24;

                int fullWeeks = totalHours / (7 * 24);

                // Calculate the total hours excluding the excluded days per week
                int totalWorkHours = totalHours - (fullWeeks * 5 * 24);

                return totalWorkHours;

            }
            else{
                return 0;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * Displays all bill payees along with their billing details.
     */

    public void dislpayBillPayees(){

        String displayPayees = "SELECT * FROM billing";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayPayees);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                int memberID = resultSet.getInt("member_id");
                Date billDueDate = resultSet.getDate("billing_date");
                double billAmount = resultSet.getDouble("amount");

                System.out.println("Member_id " + memberID + " Bill due date: " + billDueDate + " Amount due: " + billAmount);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Processes billing for members, calculating the amount due based on personal training sessions.
     */
    public void billing(){

        dislpayBillPayees();

        System.out.println("Select the member you would like to calculate and a bill to?");
        Scanner scanMemberID = new Scanner(System.in);
        int memberID = scanMemberID.nextInt();

        System.out.println("Enter when this bill is due:");
        Scanner scanDueDate = new Scanner(System.in);
        String dueDate = scanDueDate.nextLine();


        int timespan = calculateDateDifference(memberID);

        double totalBill = timespan * this.standardTrainerFee;

        System.out.println(totalBill);


        String billMember = "UPDATE billing SET billing_date = ?, amount = ? WHERE member_id = ?";


        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dueDate);
            java.sql.Date billDueDate = new java.sql.Date(parsedDate.getTime());



            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(billMember);
            preparedStatement.setDate(1, billDueDate);
            preparedStatement.setDouble(2, totalBill);
            preparedStatement.setInt(3, memberID);

            preparedStatement.executeUpdate();

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Exits the admin functions menu.
     */
    public void exit(){

        this.flag = false;
    }
    /**
     * Starts the AdminFunctions interface, allowing admins to perform various actions.
     */
    public void startAdminFunctions(){

        while (flag) {

            System.out.println("Welcome to the Admin landing page!\n1. Manage room bookings?\n2. Manage fitness equipment\n3. Update Class Schedules\n4. Billing\nPress 0 to exit");
            Scanner scanTrainerAction = new Scanner(System.in);
            int trainerAction = scanTrainerAction.nextInt();

            switch (trainerAction) {

                case 1:
                    manageRoomBookings();
                    break;

                case 2:

                    monitorFitnessEquipment();
                    break;
                case 3:
                    updateClassSchedules();
                    break;

                case 4:
                    billing();
                    break;
                case 0:
                    exit();
                    break;

            }

        }
    }



}
