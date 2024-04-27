package org.backend.Controllers;

import org.backend.HealthAndFitnessMemberJDBCConnect;
import org.postgresql.util.PGInterval;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.String.valueOf;

/**
 * Performs Admin related functions
 * @author Zarif, Arun
 * @version 1.0
 */
public class AdminFunctions {

    private boolean flag = true;

    private double standardTrainerFee;

    private HealthAndFitnessMemberJDBCConnect connect;

    private double totalBill;

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
    public String displayAllRooms(){

        String display = "SELECT * FROM room_booking";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(display);
            ResultSet result = preparedStatement.executeQuery();

            StringBuilder rooms = new StringBuilder();

            while (result.next()){

                int roomNumber = result.getInt("room_number");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");

                rooms.append("Room number: ").append(roomNumber).append(" Room is available from: ").append(startDate).append(" to ").append(endDate).append("\n");


            }

            return valueOf(rooms);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adds a new room to the room booking system.
     */
    public void addRooms(int roomNumber, String startDate, String endDate){

        displayAllRooms();


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
    public void removeRooms(int roomNumber){

        displayAllRooms();


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
    public void updateRoomAvailability(int roomNumber, String startDate, String endDate){

        displayAllRooms();

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
//            addRooms();
        }
        else if(userInput == 2){
//            removeRooms();
        }
        else if (userInput == 3){
//            updateRoomAvailability();    updateRoomAvailability();
        }
    }
    /**
     * Displays all fitness equipment along with their last maintenance date.
     */
    public String displayAllEquipment(){

        String displayAll = "SELECT * FROM equipment_maintaince";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder equipment = new StringBuilder();
            while (resultSet.next()){

                Date lastMaintained = resultSet.getDate("maintaince_date");
                String description = resultSet.getString("description");

                equipment.append("Equipment: ").append(description).append(" last maintained: ").append(lastMaintained).append("\n");

            }

            return valueOf(equipment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Updates the maintenance date of fitness equipment.
     */
    public void updateMaintainceDate(String lastMaintained){

        displayAllEquipment();

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
    public void removeEquipment(String equipmentDescription){

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
    public void addEquipment(String equipmentDescription, String lastMaintainedDate){



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
//                updateMaintainceDate();
                break;
            case 2:
//                addEquipment();
                break;
            case 3:
//                removeEquipment();
                break;
        }
    }
    /**
     * Adds a new class to the class schedule.
     */
    public void addClass(String className, String startTime, String endTime) {
        String addClassQuery = "INSERT INTO class_schedule (class_name, start_time, end_time) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(addClassQuery);
            preparedStatement.setString(1, className);

            // Parsing and formatting start time
            LocalTime parsedStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("hh:mm a"));
            String formattedStartTime = parsedStartTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            preparedStatement.setTime(2, java.sql.Time.valueOf(formattedStartTime));

            // Parsing and formatting end time
            LocalTime parsedEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("hh:mm a"));
            String formattedEndTime = parsedEndTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            preparedStatement.setTime(3, java.sql.Time.valueOf(formattedEndTime));

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
    public void removeClass(int classId){


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
//                addClass();
                break;
            case 2:
//                removeClass();
                break;
        }
    }

    public String displayAllClasses(){
        String displayAllClasses = "SELECT * FROM class_schedule";
        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayAllClasses);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder classes = new StringBuilder();
            while (resultSet.next()){
                 String className = resultSet.getString("class_name");
                 Time startTime = resultSet.getTime("start_time");
                 Time endTime = resultSet.getTime("end_time");
                 classes.append(className).append(" ").append(startTime).append(" ").append(endTime).append("\n");
            }
            return valueOf(classes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public String dislpayBillPayees(){

        String displayPayees = "SELECT * FROM billing";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayPayees);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder payees = new StringBuilder();
            while (resultSet.next()){

                int memberID = resultSet.getInt("member_id");
                Date billDueDate = resultSet.getDate("billing_date");
                double billAmount = resultSet.getDouble("amount");

                payees.append("Member_id ").append(memberID).append(" Bill due date: ").append(billDueDate).append(" Amount due: ").append(billAmount).append("\n");

            }

            return payees.toString();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Processes billing for members, calculating the amount due based on personal training sessions.
     */
    public void billing(int memberID, String dueDate){

        int timespan = calculateDateDifference(memberID);

        totalBill = timespan * this.standardTrainerFee;



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

    public double getTotal(){
        return this.totalBill;
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
//                    billing();
                    break;
                case 0:
                    exit();
                    break;

            }

        }
    }



}
