import java.util.Scanner;

public class TrainerFunctions {

    TrainerFunctions(int trainerID){}

    public void makeAvailable(){
        //adds trainer to the personal training table
    }

    public void makeUnavailable(){

        //removes the trainer from the personal training table
    }

    public void updateAvailability(){
        //updates the start and end time of a Trainer
    }

    public void manageSchedule(){}

    public void viewMemberProfiles(){}

    public void startTrainerFunctions(){

        System.out.println("Welcome to the Trainer landing page!\nWhat action would you like to perform?\n1. manageSchedule()\n2. viewMemberProfiles");
        Scanner scanTrainerAction = new Scanner(System.in);
        int trainerAction = scanTrainerAction.nextInt();

        switch(trainerAction){

            case 1:
                manageSchedule();
                break;
            case 2:
                viewMemberProfiles();
                break;
        }

    }

}
