import java.util.HashMap;
import java.util.Scanner;

public class HospitalHolding {
    //PART 9 – EXPANDING OUR EMPIRE
    //Finally, we would suppose that our hospital has grown so much that we have to build
    //a new one. For this we need to register each hospital separately.

    String businessName;
    private HashMap <Integer, Hospital> hospitals;
    private HashMap <Integer,String> passwords;
    private int mapID;
    Scanner sc = new Scanner(System.in);
    public HospitalHolding(){
        hospitals=new HashMap<>();
        passwords=new HashMap<>();
        mapID=1;
    }
    public void menu() {
        System.out.println("#####################################");
        System.out.println("Welcome to : " + businessName + " currently we have " + mapID + " hospitals where to be attended.");
        if (!hospitals.isEmpty()) {
            for (int key : hospitals.keySet())
                System.out.println(mapID + " - " + hospitals.get(mapID).getName());
        }
        System.out.println(" 8 - New Hospital");
        System.out.println(" 9 - Delete Hospital");
        System.out.println("Choose an option:");


        String input = sc.nextLine();
    }
     //   switch (input) {
            /*case mapID:
                hospitals.get(mapID).menu();
                break;
            case 8:
                addHospital();
                break;
        }
            //case 9:
            //    removeHospital();

        }
    }

    /*private void removeHospital() {
        int aux = mapID;
        while (aux>0) {
            System.out.println(aux + " - " + hospitals.get(aux).getName());
            aux--;
        }
    }
        if (hospitals.isEmpty()){
            System.out.println("We don't have any registered hospital.");
            return;
        }
        else {
        System.out.println("Choose the hospital you want to remove: ");
        int counter = 1;
        for (int hsKey: hospitals.keySet()){
            System.out.println(hsKey + "  " + hospitals.get(hsKey));
            counter++;
        }
        String input = sc.nextLine();
        switch (input)
            case c
        }
*/
    public void addHospital(){
        if (mapID==5){
            System.out.println("We cannot build more hospitals! Sorry!");
        }
        else {
            System.out.println("Choose a name for your hospital.");
            String name = sc.nextLine();
            hospitals.put(mapID, new Hospital(name));
            System.out.println("Now please select a password for the hospital.");
            String pass = sc.nextLine();
            passwords.put(mapID,pass);
            mapID++;
        }
    }

    //WELCOME TO “BUSINESS NAME”
    //CURRENTLY WE HAVE “X” HOSPITALS WHERE TO BE ATTENDED
    //1- Name of Hospital 1
    //2- Name of Hospital 2
    //3- ...
    //8- New Hospital
    //9- Delete Hospital
    //CHOOSE THE HOSPITAL YOU WAN TO MANAGE INTRODUCING ITS NUMBER OR
    //CREATE/DELETE A HOSPITAL
    //First time we run the program the hospital list will be empty. So, we must type the 8
    //option to create a new one.
    //There will be a class called HospitalHolding where we’ll manage the info of all the
    //objects of class Hospital.
    //HospitalHolding will store the hospitals in a HashMap collection as a member field.
    //HashMap<Integer, Hospital> hospitals= new HashMap<Integer,
    //Hospital>();
    //The key of HashMap is the hospital ID that will be automatically autoincreased (first
    //hospital will have ID 1, second hospital ID 2, and so on). The value of the HashMap
    //is an object of class Hospital. So, to save the hospital we will need to enter the name,
    //password and specialities of the hospital. We can only have 5 hospitals at most.
    //Passwords must be saved in another HashMap collection as a member field of
    //HospitalHolding. The key is the same ID as the Hospital, and the value is the
    //password for this hospital.
    //HashMap<Integer, String> passwords= new HashMap<Integer, String>();
    //To delete a hospital, the program will first ask for the password. This way the hospital
    //is deleted by the same person who created it.
    //The Hospital Project Part 5-10
    //Página 6 de 7
    //When we want to manage an already created hospital, the program should also ask
    //for the password. Then if the password is correct the menu will be displayed. Check
    //that you don't lose any functionality.
    //There are 3 tries allowed to enter correct password
}
