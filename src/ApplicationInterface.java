

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationInterface {

    private HealthAndFitnessMemberJDBCConnect connect;


    private int member_id;


    boolean quit;
    boolean validEmail = false;

    String userName;

    String userPassword;

    String userEmail;

    String userDateOfBirth;

    String userAddress;




    public ApplicationInterface(HealthAndFitnessMemberJDBCConnect connect ){

        this.connect = connect;
    }

    public boolean checkIfExists(String userName){

        String findUser = "SELECT username FROM member WHERE username = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findUser);
            preparedStatement.setString(1, userName);

            ResultSet userFound = preparedStatement.executeQuery();

            if (userFound.next()){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void createAccount(String userName, String userPassword, String userEmail, String userDateOfBirth, String userAddress){

        String insertAccount = "INSERT INTO member (username, password, email, date_of_birth, address) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(insertAccount);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setDate(4, java.sql.Date.valueOf(userDateOfBirth));
            preparedStatement.setString(5, userAddress);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean validateLogin(String userName, String Password){

        String findUser = "SELECT member_id, username, password FROM member WHERE username = ? AND password = ?";

        try{

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findUser);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, Password);
            ResultSet userMatch = preparedStatement.executeQuery();

            if (userMatch.next()){

                this.member_id = userMatch.getInt("member_id");
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void memberLogin(){

        System.out.println("1. Login\n2. Create Account?");

        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        if (Integer.parseInt(userInputString) == 1){

            System.out.println("Please Enter User name: ");
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            System.out.println("Please Enter Password: ");
            Scanner scanUserPassword = new Scanner(System.in);
            this.userPassword = scanUserPassword.nextLine();

            boolean login = validateLogin(userName, userPassword);

            if (login){
                System.out.println("Login success!");


                MemberFunctions memberFunctions = new MemberFunctions(this.connect, this.member_id);
                memberFunctions.startMemberFunctions();
            }
            else{
                System.out.println("Incorrect Password!");
            }


        }
        else if (Integer.parseInt(userInputString) == 2) {

            System.out.println("Please Enter User name: ");
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            if (checkIfExists(userName)) {
                System.out.println("User already exists!");

            } else {


                System.out.println("Please Enter Password: ");
                Scanner scanUserPassword = new Scanner(System.in);
                this.userPassword = scanUserPassword.nextLine();

                while (!validEmail) {
                    System.out.println("Please Enter Email:");
                    Scanner scanEmail = new Scanner(System.in);
                    this.userEmail = scanEmail.nextLine();

                    emailValidator(userEmail);


                    if (!validEmail) {
                        System.out.println("Email not recognized!");
                    }

                }

                System.out.println("Please Enter Date of Birth");
                Scanner scanDateOfBirth = new Scanner(System.in);
                String dataOfBirthString = scanDateOfBirth.nextLine();
                this.userDateOfBirth = dataOfBirthString;

                System.out.println("Please enter Home address");
                Scanner scanHomeAddress = new Scanner(System.in);
                String homeAddressString = scanHomeAddress.nextLine();
                this.userAddress = homeAddressString;

                createAccount(this.userName, this.userPassword, this.userEmail, this.userDateOfBirth, this.userAddress);


            }
        }


    }



    public void emailValidator(String email){

        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        this.validEmail = matcher.matches();

    }

    public void trainerLogin(){}

    public void adminLogin(){}

    public void run(){

        while(!quit){

            System.out.println("Application running : Please indicate whether you are a Member, Trainer, or Admin");

            Scanner userType = new Scanner(System.in);
            String userTypeInput = userType.nextLine();

            if (userTypeInput.equalsIgnoreCase("member")){


                memberLogin();

            }
            else if (userTypeInput.toLowerCase().equals("trainer")){
                trainerLogin();

            }
            else if (userTypeInput.toLowerCase().equals("admin")){
                adminLogin();

            }



        }
    }
}
