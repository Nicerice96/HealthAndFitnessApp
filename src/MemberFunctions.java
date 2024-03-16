import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MemberFunctions {

    private int member_id;

    private HealthAndFitnessMemberJDBCConnect connect;

    private boolean memberWeightAdded;


    boolean flag = true;

    MemberFunctions(HealthAndFitnessMemberJDBCConnect connect, int member_id){
        this.member_id = member_id;
        this.connect = connect;
        memberWeightAdded = false;
    }


    public void updateWeight(){

        System.out.println("Please input your weight:");

        Scanner scanner = new Scanner(System.in);
        float userWeightFloat = scanner.nextFloat();
        float userWeight = userWeightFloat;

        String updateWeight = "UPDATE member_fitness_metric SET weight = ? WHERE member_id = ?";

        try{
            PreparedStatement preparedStatement = connect.getConn().prepareStatement(updateWeight);
            preparedStatement.setFloat(1, userWeight);
            preparedStatement.setInt(2, this.member_id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertWeight() {

        System.out.println("Enter a your weight: ");
        Scanner scanNewWeight = new Scanner(System.in);
        float newWeightFloat = scanNewWeight.nextFloat();

        System.out.println("Enter the day of the measured weight");
        Scanner newDate = new Scanner(System.in);
        String newDateString = newDate.nextLine();



        String insertWeight = "INSERT INTO member_fitness_metric (member_id, weight, measurement_date) VALUES (?, ?, ?)";

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(newDateString);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());

            PreparedStatement preparedStatement = connect.getConn().prepareStatement(insertWeight);
            preparedStatement.setInt(1, this.member_id);
            preparedStatement.setFloat(2, newWeightFloat);
            preparedStatement.setDate(3, startRoutineDate);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateHeight(){

        System.out.println("Enter you height in cm: ");
        Scanner scanHeight = new Scanner(System.in);
        float heightFloat = scanHeight.nextFloat();

        String updateHeight = "UPDATE member_fitness_metric SET height = ? WHERE member_id = ?";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateHeight);
            preparedStatement.setFloat(1, heightFloat);
            preparedStatement.setInt(2, this.member_id);

            preparedStatement.executeUpdate();

            updateMeasurementDate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void updateBMI(){

        System.out.println("Enter you bmi: ");
        Scanner scanBMI = new Scanner(System.in);
        float bmiFloat = scanBMI.nextFloat();

        String updateBMI = "UPDATE member_fitness_metric SET bmi = ? WHERE member_id = ?";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateBMI);
            preparedStatement.setFloat(1, bmiFloat);
            preparedStatement.setInt(2, this.member_id);

            preparedStatement.executeUpdate();

            updateMeasurementDate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateBodyFatPercentage(){
        System.out.println("Enter your body fat percentage: ");
        Scanner scanBodyFatPercentage = new Scanner(System.in);
        float bodyFatPercentage = scanBodyFatPercentage.nextFloat();

        String updateBodyFatPercentage = "UPDATE member_fitness_metric SET body_fat_percentage = ? WHERE member_id = ?";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateBodyFatPercentage);
            preparedStatement.setFloat(1, bodyFatPercentage);
            preparedStatement.setInt(2, this.member_id);

            preparedStatement.executeUpdate();

            updateMeasurementDate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateMeasurementDate(){

        System.out.println("When did you measure this metric? (Enter date in format yyyy-MM-dd)");
        Scanner scanDate = new Scanner(System.in);
        String dateStringInput = scanDate.next();


        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateStringInput);
            java.sql.Date date = new java.sql.Date(parsedDate.getTime());
            String setDate = "UPDATE member_fitness_metric SET measurement_date = ? WHERE member_id = ?";

            try{
                PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(setDate);
                preparedStatement.setDate(1, date);
                preparedStatement.setInt(2, this.member_id);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter date in format yyyy-MM-dd");
        }





        //update last time you made the above measurments
    }

    public void updateFitnessGoals(){

        System.out.println("Enter your Fitness Goal/Exercise Routine: ");
        Scanner scanFitnessGoals = new Scanner(System.in);
        String scanFitnessString = scanFitnessGoals.nextLine();

        System.out.println("Describe your Fitness Goal/Exercise Routine");
        Scanner scanDescription = new Scanner(System.in);
        String scanDescriptionString = scanDescription.nextLine();

        System.out.println("When do you plan to start this routine?");
        Scanner scanStartDate = new Scanner(System.in);
        String startDate = scanStartDate.nextLine();

        System.out.println("When do you plan to end this routine?");
        Scanner scanEndDate = new Scanner(System.in);
        String endDate = scanEndDate.nextLine();

        String updateGoal = "UPDATE member_routine SET routine_title = ? WHERE member_id = ?";
        String updateDescription = "UPDATE member_routine SET description = ? WHERE member_id = ?";
        String updateStartDate = "UPDATE member_routine SET start_date = ? WHERE member_id = ?";
        String updateEndDate = "UPDATE member_routine SET end_date = ? WHERE member_id = ?";


        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.util.Date parsedEndDate = dateFormat.parse(endDate);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());
            java.sql.Date endRoutineDate = new java.sql.Date(parsedEndDate.getTime());


            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateGoal);
            PreparedStatement preparedStatement1 = this.connect.getConn().prepareStatement(updateDescription);
            PreparedStatement preparedStatement2 = this.connect.getConn().prepareStatement(updateStartDate);
            PreparedStatement preparedStatement3 = this.connect.getConn().prepareStatement(updateEndDate);

            preparedStatement.setString(1, scanFitnessString);
            preparedStatement.setInt(2, this.member_id);
            preparedStatement1.setString(1, scanDescriptionString);
            preparedStatement1.setInt(2, this.member_id);


            preparedStatement2.setDate(1, startRoutineDate);
            preparedStatement2.setInt(2, this.member_id);
            preparedStatement3.setDate(1, endRoutineDate);
            preparedStatement3.setInt(2, this.member_id);

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }


    }


    public void updateMeasurementData(int selectedOption){

        if (selectedOption == 1){

            System.out.println("Would you like to:\n1. Insert a new weight\n2. Update your already existing weight?");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                insertWeight();
            }
            else{
                updateWeight();
            }
        }
        else if (selectedOption == 2){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");
            updateHeight();
        }
        else if (selectedOption == 3){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");

            updateBMI();
        }
        else if (selectedOption == 4){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");

            updateBodyFatPercentage();
        }
        else if (selectedOption == 5){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");

            updateMeasurementDate();
        }
        else if (selectedOption == 6){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");

            updateFitnessGoals();
        }
        else {

            //exiting

        }

    }

    public void exit(){

        this.flag = false;

    }
    public void profileManagement(){

        System.out.println("What would you like to do to your account?\n1. Update weight\n2. Update height\n3. Update bmi\n4. Update body fat percentage\n5. Update measurement date\n6. Update fitness routine/goals\nPress 0 to exit");

        Scanner userUpdate = new Scanner(System.in);
        String userUpdateString = userUpdate.nextLine();


        updateMeasurementData(Integer.parseInt(userUpdateString));


    }

    public void dashboardDisplay(){

        String displayAll = "SELECT * FROM member_routine ORDER BY routine_id ASC";


        try {

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String routineTitle = resultSet.getString("routine_title");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");

                System.out.println("routineTitle: " + routineTitle + ", description: " + description +", start Date: " + startDate + ", end Date: " + endDate);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void scheduleManagement(){}

    public void startMemberFunctions(){
        System.out.println("Welcome! Ready to get healthy! (I'm not) what actions would you like perform?");

        while(flag){
            System.out.println("Options:\n1. profileManagement\n2. dashboardDisplay\n3. scheduleManagement\nPress 0 to exit ");
            Scanner userAction = new Scanner(System.in);
            String userActionString = userAction.nextLine();

            if (Integer.parseInt(userActionString) == 1){
                profileManagement();
            }
            else if (Integer.parseInt(userActionString) == 2){
                dashboardDisplay();
            }
            else if (Integer.parseInt(userActionString) == 3){
                scheduleManagement();
            }
            else{
                exit();
            }

        }


    }


}
