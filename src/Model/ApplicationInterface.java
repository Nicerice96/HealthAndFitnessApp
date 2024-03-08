package Model;

import java.util.Scanner;

public class ApplicationInterface {


    boolean quit;




    public ApplicationInterface(){}


    public void memberLogin(String userName, String userPassword){


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
                Scanner userUserName = new Scanner(System.in);
                String userName = userUserName.nextLine();

                System.out.println("Please Enter Password: ");
                Scanner userUserPassword = new Scanner(System.in);
                String userPassword = userUserPassword.nextLine();

                memberLogin(userName, userPassword);
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
