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


    boolean flag = true;

    MemberFunctions(HealthAndFitnessMemberJDBCConnect connect, int member_id){
        this.member_id = member_id;
        this.connect = connect;
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

        System.out.println("Enter your height in cm: ");
        Scanner scanHeight = new Scanner(System.in);
        float heightFloat = scanHeight.nextFloat();


        String updateHeight = "UPDATE member_fitness_metric SET height = ? WHERE member_id = ?";

        try {


            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateHeight);
            preparedStatement.setFloat(1, heightFloat);
            preparedStatement.setInt(2, this.member_id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void insertHeight() {

        System.out.println("Enter a your height: ");
        Scanner scanNewHeight = new Scanner(System.in);
        float newHeight = scanNewHeight.nextFloat();



        System.out.println("Enter the day of the measured height");
        Scanner newDate = new Scanner(System.in);
        String newDateString = newDate.nextLine();


        String insertHeight = "INSERT INTO member_fitness_metric (member_id, height, measurement_date) VALUES (?, ?, ?)";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(newDateString);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());

            PreparedStatement preparedStatement = connect.getConn().prepareStatement(insertHeight);
            preparedStatement.setInt(1, this.member_id);
            preparedStatement.setFloat(2, newHeight);
            preparedStatement.setDate(3, startRoutineDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertBMI(){


        System.out.println("Enter a your BMI: ");
        Scanner scanNewBMI = new Scanner(System.in);
        float newBMI = scanNewBMI.nextFloat();



        System.out.println("Enter the day of the measured your BMI");
        Scanner newDate = new Scanner(System.in);
        String newDateString = newDate.nextLine();


        String insertHeight = "INSERT INTO member_fitness_metric (member_id, bmi, measurement_date) VALUES (?, ?, ?)";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(newDateString);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());

            PreparedStatement preparedStatement = connect.getConn().prepareStatement(insertHeight);
            preparedStatement.setInt(1, this.member_id);
            preparedStatement.setFloat(2, newBMI);
            preparedStatement.setDate(3, startRoutineDate);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
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


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void insertBodyFatPercentage(){

        System.out.println("Enter a your body fat percentage: ");
        Scanner scanNewBodyFatPercentage = new Scanner(System.in);
        float newBodyFatPercentage = scanNewBodyFatPercentage.nextFloat();



        System.out.println("Enter the day of the measured your body fat percentage");
        Scanner newDate = new Scanner(System.in);
        String newDateString = newDate.nextLine();


        String insertHeight = "INSERT INTO member_fitness_metric (member_id, body_fat_percentage, measurement_date) VALUES (?, ?, ?)";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(newDateString);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());

            PreparedStatement preparedStatement = connect.getConn().prepareStatement(insertHeight);
            preparedStatement.setInt(1, this.member_id);
            preparedStatement.setFloat(2, newBodyFatPercentage);
            preparedStatement.setDate(3, startRoutineDate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }



    public void updateMeasurementDate() throws SQLException, ParseException {
        System.out.println("Would you like to update:\n1. Metrics\n2. Routine");
        Scanner scanUserMetricChoice = new Scanner(System.in);
        int userMetricChoice = scanUserMetricChoice.nextInt();

        if (userMetricChoice == 1) {
            System.out.println("When did you measure this metric? (Enter date in format yyyy-MM-dd)");
            Scanner scanDate = new Scanner(System.in);
            String dateStringInput = scanDate.next();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateStringInput);
            java.sql.Date date = new java.sql.Date(parsedDate.getTime());

            String updateQuery = "UPDATE member_fitness_metric SET measurement_date = ? WHERE member_id = ?";
            try (PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateQuery)) {
                preparedStatement.setDate(1, date);
                preparedStatement.setInt(2, this.member_id);
                preparedStatement.executeUpdate();
            }
        }

        else if (userMetricChoice == 2) {
            display("member_routine", "routine_title");
            System.out.println("Select a routine you would like to update by typing it out:");
            try  {
                Scanner scanSelectedRoutine = new Scanner(System.in);
                String selectedRoutineString = scanSelectedRoutine.nextLine();

                System.out.println("Would you like to update:\n1. Start Date\n2. End Date");
                int userInput = scanUserMetricChoice.nextInt();

                if (userInput == 1 || userInput == 2) {
                    System.out.println("Enter the new date (yyyy-MM-dd):");
                    try (Scanner scanDate = new Scanner(System.in)) {
                        String newDateString = scanDate.nextLine();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parsedDate = dateFormat.parse(newDateString);
                        java.sql.Date date = new java.sql.Date(parsedDate.getTime());

                        String updateColumn = (userInput == 1) ? "start_date" : "end_date";
                        String updateQuery = "UPDATE member_routine SET " + updateColumn + " = ? WHERE routine_title = ?";
                        try (PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(updateQuery)) {
                            preparedStatement.setDate(1, date);
                            preparedStatement.setString(2, selectedRoutineString);
                            preparedStatement.executeUpdate();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void updateFitnessGoal(){

        display("member_routine", "routine title" );

        System.out.println("Enter the Fitness Goal/Exercise Routine you want to UPDATE: ");
        Scanner scanRoutine = new Scanner(System.in);
        String routineString = scanRoutine.nextLine();

        System.out.println("Describe your Fitness Goal/Exercise Routine");
        Scanner scanDescription = new Scanner(System.in);
        String scanDescriptionString = scanDescription.nextLine();

        System.out.println("When do you plan to start this routine?");
        Scanner scanStartDate = new Scanner(System.in);
        String startDate = scanStartDate.nextLine();

        System.out.println("When do you plan to end this routine?");
        Scanner scanEndDate = new Scanner(System.in);
        String endDate = scanEndDate.nextLine();

        String updateDescription = "UPDATE member_routine SET description = ? WHERE routine_title = ?";
        String updateStartDate = "UPDATE member_routine SET start_date = ? WHERE routine_title = ?";
        String updateEndDate = "UPDATE member_routine SET end_date = ? WHERE routine_title = ?";


        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.util.Date parsedEndDate = dateFormat.parse(endDate);
            java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());
            java.sql.Date endRoutineDate = new java.sql.Date(parsedEndDate.getTime());


            PreparedStatement preparedStatement1 = this.connect.getConn().prepareStatement(updateDescription);
            PreparedStatement preparedStatement2 = this.connect.getConn().prepareStatement(updateStartDate);
            PreparedStatement preparedStatement3 = this.connect.getConn().prepareStatement(updateEndDate);


            preparedStatement1.setString(1, scanDescriptionString);
            preparedStatement1.setString(2, routineString);
            preparedStatement2.setDate(1, startRoutineDate);
            preparedStatement2.setString(2, routineString);
            preparedStatement3.setDate(1, endRoutineDate);
            preparedStatement3.setString(2, routineString);


            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }


    }

    //TODO: deleteroutine();

    public void insertFitnessGoal(){

        Scanner scanGoal = new Scanner(System.in);
        System.out.println("Enter your Fitness Goal/Exercise Routine:");
        String fitnessGoal = scanGoal.nextLine();

        Scanner scanDescription = new Scanner(System.in);
        System.out.println("Describe your Fitness Goal/Exercise Routine:");
        String description = scanDescription.nextLine();

        Scanner scanStartDate = new Scanner(System.in);
        System.out.println("When do you plan to start this routine? (yyyy-MM-dd):");
        String startDateStr = scanStartDate.nextLine();

        Scanner scanEndDate = new Scanner(System.in);
        System.out.println("When do you plan to end this routine? (yyyy-MM-dd):");
        String endDateStr = scanEndDate.nextLine();


        String insertGoal = "INSERT INTO member_routine (member_id, routine_title, description, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedStartDate = dateFormat.parse(startDateStr);
                java.util.Date parsedEndDate = dateFormat.parse(endDateStr);
                java.sql.Date startRoutineDate = new java.sql.Date(parsedStartDate.getTime());
                java.sql.Date endRoutineDate = new java.sql.Date(parsedEndDate.getTime());

                try (PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(insertGoal)) {
                    preparedStatement.setInt(1, this.member_id);
                    preparedStatement.setString(2, fitnessGoal);
                    preparedStatement.setString(3, description);
                    preparedStatement.setDate(4, startRoutineDate);
                    preparedStatement.setDate(5, endRoutineDate);

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException | ParseException e) {
                throw new RuntimeException(e);
            }

    }

    public void display(String table, String coloumn){

        String display = "SELECT ? FROM ? WHERE member_id = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(display);
            preparedStatement.setString(1, table);
            preparedStatement.setString(2, coloumn);
            preparedStatement.setInt(3, this.member_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                String routineTitle = resultSet.getString(coloumn);
                System.out.println("- " + routineTitle);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateMeasurementData(int selectedOption) throws SQLException, ParseException {

        if (selectedOption == 1){

            System.out.println("Would you like to:\n1. Insert a new weight\n2. Update your already existing weight?");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                insertWeight();
            }
            else if (userInput == 2){
                updateWeight();
            }

        }
        else if (selectedOption == 2){
            System.out.println("Would you like to:\n1. Insert a new height\n2. Update your already existing height?");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1){
                insertHeight();
            }
            else if (userInput == 2){
                updateHeight();
            }
        }
        else if (selectedOption == 3){
            System.out.println("Would you like to:\n1. Insert a new bmi\n2. Update your already existing bmi?");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                insertBMI();
            }

            else if (userInput == 2){
                updateBMI();
            }
        }
        else if (selectedOption == 4){
            System.out.println("Would you like to:\n1. Insert a new body fat percentage\n2. Update your already existing body fat percentage?");
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();

            if (userInput == 1){
                insertBodyFatPercentage();
            }
            else{
                updateBodyFatPercentage();
            }


        }
        else if (selectedOption == 5){
            System.out.println("You selected \"Updating Measurement Date\"");
            updateMeasurementDate();
        }
        else if (selectedOption == 6){
            System.out.println("Would you like to:\n1. Insert a new goal\n2. Update your already existing goal?");
            Scanner scanner = new Scanner(System.in);
            int userInt = scanner.nextInt();
            if (userInt == 1){
                insertFitnessGoal();
            }
            else if (userInt == 2){
                updateFitnessGoal();
            }
        }

        //Press any key to exit
    }

    public void exit(){

        this.flag = false;

    }
    public void profileManagement(){

        try {

            System.out.println("What would you like to do to your account?\n1. Update weight\n2. Update height\n3. Update bmi\n4. Update body fat percentage\n5. Update measurement date\n6. Update fitness routine/goals\nPress 0 to exit");

            Scanner userUpdate = new Scanner(System.in);
            int userInput = userUpdate.nextInt();
            userUpdate.nextLine();


            System.out.println("User entered: " + userInput);


            updateMeasurementData(userInput);
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    public void dashboardDisplay(){

        String displayRoutine = "SELECT * FROM member_routine WHERE member_id = ?";
        String fitnessMetrics = "SELECT * FROM member_fitness_metric WHERE member_id = ?";


//

        try {

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(displayRoutine);
            PreparedStatement preparedStatement1 = this.connect.getConn().prepareStatement(fitnessMetrics);

            preparedStatement.setInt(1, this.member_id);
            preparedStatement1.setInt(1, this.member_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();



            while (resultSet.next()) {


                String routineTitle = resultSet.getString("routine_title");
                String description = resultSet.getString("description");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");

                System.out.println("Routine Title: " + routineTitle + ", Description: " + description +", Start Date: " + startDate + ", End Date: " + endDate);
            }

            while (resultSet1.next()) {
                float weight = resultSet1.getFloat("weight");
                float height = resultSet1.getFloat("height");
                float bmi = resultSet1.getFloat("bmi");
                float bodyFatPercentage = resultSet1.getFloat("body_fat_percentage");
                Date date = resultSet1.getDate("measurement_date");

                System.out.println("Weight: " + weight + ", Height: " + height +", BMI: " + bmi + ", Body Fat Percentage: " + bodyFatPercentage + ", Measured on: " + date);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMemberToBilling(){


        String addBilling = "INSERT INTO billing (member_id) VALUES (?)";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(addBilling);
            preparedStatement.setInt(1, this.member_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void scheduleManagement(){


        System.out.println("Specify time-frame you would like to be scheduled for a session and you will be allocated an available Trainer (YYYY-MM-DD)\nStart Date:");
        Scanner scanStartDate = new Scanner(System.in);
        String startDate = scanStartDate.nextLine();

        System.out.println("End Date:");
        Scanner scanEndDate = new Scanner(System.in);
        String endDate = scanEndDate.nextLine();


        String findTrainer = "UPDATE personal_training SET member_id = ? WHERE start_date = ? AND end_date = ?";
        ;

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.sql.Date startDateSQL = new java.sql.Date(parsedStartDate.getTime());

            java.util.Date parsedEndDate = dateFormat.parse(endDate);
            java.sql.Date endDateSQL = new java.sql.Date(parsedEndDate.getTime());
            //"2023-02-23"	"2024-03-01"
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findTrainer);

            preparedStatement.setInt(1, this.member_id);
            preparedStatement.setDate(2, startDateSQL);
            preparedStatement.setDate(3, endDateSQL);

            preparedStatement.executeUpdate();
            addMemberToBilling();


        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

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
