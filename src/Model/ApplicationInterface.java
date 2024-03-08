package Model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationInterface {


    boolean quit;
    boolean validEmail = false;

    String userName;

    String userPassword;

    String userEmail;

    String userDateOfBirth;

    String userAddress;




    public ApplicationInterface(){}


    public void memberLogin(String userName, String userPassword, String userEmail, String userDateOfBirth, String userAddress){


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

            if (userTypeInput.toLowerCase().equals("member")){

                System.out.println("Please Enter User name: ");
                Scanner scanUsername = new Scanner(System.in);
                this.userName = scanUsername.nextLine();

                System.out.println("Please Enter Password: ");
                Scanner scanUserPassword = new Scanner(System.in);
                this.userPassword = scanUserPassword.nextLine();

                while (!validEmail){
                    System.out.println("Please Enter Email:");
                    Scanner scanEmail = new Scanner(System.in);
                    this.userEmail = scanEmail.nextLine();

                    emailValidator(userEmail);


                    if(!validEmail){
                        System.out.println("Email not recognized!");
                    }

                }
                System.out.println("Please Enter Address");
                Scanner scanAddress = new Scanner(System.in);
                this.userAddress = scanAddress.nextLine();


                System.out.println("Please Enter Date of Birth");

                System.out.println("Please enter Home address");

                memberLogin(this.userName, this.userPassword, this.userEmail, this.userDateOfBirth, this.userAddress);

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
