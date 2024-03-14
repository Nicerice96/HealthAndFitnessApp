import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String userWeightString = scanner.nextLine();
        int userWeight = Integer.parseInt(userWeightString);

        String updateWeight = "UPDATE member_fitness_metric SET weight = ? WHERE member_id = ?";

        try{
            PreparedStatement preparedStatement = connect.getConn().prepareStatement(updateWeight);
            preparedStatement.setInt(1, userWeight);
            preparedStatement.setInt(2, this.member_id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateHeight(){

    }

    public void updateBMI(){

    }

    public void updateBodyFatPercentage(){

    }

    public void updateMeasurementData(){

    }

    public void exit(){

        this.flag = false;

    }
    public void profileManagement(){

        System.out.println("What would you like to do to your account?\n1. weight\n2. height\n3. bmi\n4. body_fat_percentage\n5. measurement_date\nPress 0 to exit");

        Scanner userUpdate = new Scanner(System.in);
        String userUpdateString = userUpdate.nextLine();

        if (Integer.parseInt(userUpdateString) == 1){
            updateWeight();
        }
        else if (Integer.parseInt(userUpdateString) == 2){
            updateHeight();
        }
        else if (Integer.parseInt(userUpdateString) == 3){
            updateBMI();
        }
        else if (Integer.parseInt(userUpdateString) == 4){
            updateBodyFatPercentage();
        }
        else if (Integer.parseInt(userUpdateString) == 5){
            updateMeasurementData();
        }
        else {

            exit();

        }

    }

    public void dashboardDisplay(){}

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
