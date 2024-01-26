import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.*;

public class Hospital {
    //Now we must create a new class called Hospital with the following attributes: name,
    //money, specialities (Array List), patientsWaiting (Array List) and doctors (Array List).
    //Don’t forget to add a constructor, getters, setters and toString() method.
    private static Scanner sc = new Scanner(System.in);
    private String name;
    private int money;
    private ArrayList<String> specialities;
    private ArrayList<Patient> patientsWaiting;
    private ArrayList<Doctor> doctors;
    private HashMap<Patient, Doctor> attendedPatients;

    private ArrayList<Patient> unattendendedPatients;

    @Override
    public String toString() {
        return "Name-> " + name + '\'' +
                "\n Money-> " + money +
                "\n Specialities-> " + specialities +
                "\n Patients waiting-> " + patientsWaiting +
                "\n Doctors-> " + doctors;
    }


    public Hospital(String name) {
        this.name = name;
        this.money = 0;
        this.specialities = new ArrayList<>();
        this.patientsWaiting = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.attendedPatients = new HashMap<>();
        unattendendedPatients = new ArrayList<>();
    }

    public void addSpeciality(String speciality) {
        specialities.add(speciality);
    }

    public boolean validSpeciality(String speciality) {
        for (String sp : specialities) {
            if (sp.equals(speciality)) {
                return true;
            }
        }
        return false;
    }

    public void addDoctor(Doctor dotor) {
        if (validSpeciality(dotor.getSpeciality()) && !registeredDoctorDNI(dotor.getDNI())) {
            doctors.add(dotor);
        } else System.out.println("We do not have licence for such speciality or the given DNI is already registered.");
    }

    public void addPatient(Patient patient) {
        if (validSpeciality(patient.getDisease())) {
            patientsWaiting.add(patient);
        } else {
            patientsWaiting.add(patient);
            System.out.println("We do not have such speciality");
        }
    }

    public void removeDoctor(Doctor d1) {
        if (doctors.contains(d1)) {
            doctors.remove(d1);
        } else System.out.println("The doctor is not registered");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<String> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(ArrayList<String> specialities) {
        this.specialities = specialities;
    }

    public ArrayList<Patient> getPatientsWaiting() {
        return patientsWaiting;
    }

    public void setPatientsWaiting(ArrayList<Patient> patientsWaiting) {
        this.patientsWaiting = patientsWaiting;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public boolean doctorRegistered(String DNI) {
        for (Doctor doc : doctors) {
            if (doc.getDNI().equals(DNI))
                return true;
        }
        return false;
    }

    public boolean registeredDoctorDNI(String DNI) {
        for (Doctor doc : doctors) {
            if (doc.getDNI().equals(DNI))
                return true;
        }
        return false;
    }

    public Doctor getDoctorFromDNI(String DNI) {
        for (Doctor doc : doctors) {
            if (doc.getDNI().equals(DNI))
                return doc;
        }
        return null;
    }

    public Doctor getDoctorFromDisease(String disease) {
        for (Doctor doc : doctors) {
            if (doc.getSpeciality().equals(disease)) {
                return doc;
            }
        }
        return null;
    }

    public boolean specilityDoctorFromDisease(String disease) {
        for (Doctor doc : doctors) {
            if (doc.getSpeciality().equals(disease))
                return true;
        }
        return false;
    }

    //https://www.baeldung.com/java-check-string-number
    //Método isNumeric
    //Recomiendan hacer este método para solucionar la comprobación de dígitos. Si no se hace con try y catch daría el error NumberFormatException
    //al intentar hacer parse al double.
    //Así que a través del catch conseguimos que el método devuelva un booleano, también comprueba que el String no está vacío.
    private boolean isNumeric(String str) {
            if (str == null) {
                return false;
            }
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
    }
    //At this moment, it's the time to create new validations when we enter data. For
//example:
//• DNI must be 9 characters (8 numbers following by 1 letter)
//• SSN must be 12 digits
//• Age mut be positive and smaller than 150
//• Phone Number must be 9 digits starting with 6, 7, 8, or 9.
//• ,... In case of error, the system must show the corresponding error message
//and a message explaining the correct format.
//Imagine how your program can be broken: phones, empty waiting patients, empty
//data, duplicated DNIs, duplicated specialities... and make the corresponding checks
//and modifications in your program.
    //https://www.w3api.com/Java/Character/isLetter/
    //MÉTODO Character.isLetter()
    public boolean validDNI(String DNI) {
        return DNI.length() == 9 && isNumeric(DNI.substring(0, 8)) && Character.isLetter(DNI.charAt(8));
    }

    public boolean patientRegistered(String DNI) {
        for (Patient pat : patientsWaiting) {
            if (pat.getDNI().equals(DNI))
                return true;
        }
        return false;
    }

    public Patient getPatientFromDNI(String DNI) {
        for (Patient pat : patientsWaiting) {
            if (pat.getDNI().equals(DNI))
                return pat;
        }
        return null;
    }


    public void attendPatient() {
        if (!patientsWaiting.isEmpty()) {
            Patient pat = patientsWaiting.get(0);
            if (validSpeciality(pat.getDisease())) {
                Doctor doc = getDoctorFromDisease(pat.getDisease());
                if (specilityDoctorFromDisease(pat.getDisease())) {
                    attendedPatients.put(pat, doc);
                    patientsWaiting.remove(0);
                    System.out.println("The patient " + pat.getName() + " " + pat.getLastName() + " has been attended.");
                } else {
                    System.out.println("We cannot attend " + pat.getDisease() + "," + pat.getName() + " " + pat.getLastName() + " has been moved into the unattended patients list.");
                    patientsWaiting.remove(0);
                    unattendendedPatients.add(pat);
                }
            }
        } else System.out.println("We have attended all of our patients.");
    }

    public void printDoctorFromPatient(Doctor doc) {
        System.out.println("The doctor " + doc.getName() + " " + doc.getLastName() + " has attended : ");
        for (Patient pat : attendedPatients.keySet()) {
            if (attendedPatients.containsValue(doc) && attendedPatients.containsKey(pat)) {
                System.out.println(pat);
            }
        }
    }

    public void menuPatients() {
        //1 - List of waiting patients
        //2 - List of all attended patients
        //3 - List of attended patients by a specific doctor (search by DNI).
        //4.- Patients who have not been able to be treated

        System.out.println("#########################");
        System.out.println("Choose an option: ");
        System.out.println("1 - List of waiting patients.");
        System.out.println("2 - List of all attended patients.");
        System.out.println("3 - List of attended patients by a specific doctor.");
        System.out.println("4 - Patients who have not been able to be treated.");
        System.out.println("5 - Menu.");
        System.out.println("#########################");
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case (1):
                if (patientsWaiting.isEmpty()) {
                    System.out.println("We don't have any patient waiting. ");
                } else {
                    System.out.println(patientsWaiting);
                }
                break;
            case (2):
                if (attendedPatients.isEmpty()) {
                    System.out.println("We haven't attended any patient yet. ");
                } else {
                    System.out.println(attendedPatients.keySet());
                }
                break;
            case (3):
                System.out.println("Please introduce the doctor DNI: ");
                String DNI = sc.nextLine();
                Doctor doc = getDoctorFromDNI(DNI);
                if (attendedPatients.containsValue(doc)) {
                    printDoctorFromPatient(doc);
                } else System.out.println("The given doctor hasn't attended any patient. ");
                break;
            case (4):
                if (unattendendedPatients.isEmpty()) {
                    System.out.println("We don't have any unattended patient");
                } else {
                    System.out.println(unattendendedPatients);
                }
                break;
            default:
                break;
        }
    }


    public void menu() {
//1- Register patient
//2- Register doctor
//3- Modify patient
//4- Modify doctor
//5- Show all patients
//6- Show all doctors
//7- Delete doctor
// 8- Attend a patient (this is the new option)
// 9- Exit
//PART 6 – COMPLETING THE MENU
//Let´s complete de menu. Before the options it must appear this:
//WELCOME TO 'HOSPITAL_NAME'
//NOW THERE ARE 'N' DOCTORS AND 'M' SATISFIED PATIENTS
//In the place of n and m should show the corresponding numbers. The satisfied
//patients are the attend patients.
//PART 7 – NEW SPECIALITY
//Now we're going to suppose that in our hospital we're ready to attend a new
//speciality. So, we must add a new menu option.
//10- New speciality
//11- Exit
//Once you implement it, you must test that after adding a new Speciality, we can add
//a new doctor with this speciality and we can attend a new patient with that disease.
        System.out.println("Welcome to the hospital " + getName() + " we now have " + doctors.size() + " doctors and " + attendedPatients.size() + " satisfied patients.");
        System.out.println("#########################");
        System.out.println("Choose an option: ");
        System.out.println("1 - Register patient");
        System.out.println("2 - Register doctor");
        System.out.println("3 - Modify patient");
        System.out.println("4 - Modify doctor");
        System.out.println("5 - Show all patients");
        System.out.println("6 - Show all doctors");
        System.out.println("7 - Delete doctor");
        System.out.println("8 - Attend a patient");
        System.out.println("9 - Info specialities");
        System.out.println("10 - New speciality");
        System.out.println("11 - Exit");
        System.out.println("#########################");
        String input = sc.nextLine();
        boolean flag = true;
        switch (input) {
            case ("1"):
                registerPatient();
                break;
            case ("2"):
                registerDoctor();
                break;
            case ("3"): //modificar paciente
                modifyPatient();
                break;
            case ("4")://Modificar doctor
                modifyDoctor();
                break;
            case ("5"):
                menuPatients();
                break;
            case ("6"):
                System.out.println(getDoctors());
                break;
            case ("7"):
                System.out.println("Introduce DNI: ");
                String DNI7 = sc.nextLine();
                if (doctorRegistered(DNI7)) {
                    System.out.println("Doctor " + getDoctorFromDNI(DNI7).getName() + " " + getDoctorFromDNI(DNI7).getLastName() + " has been removed.");
                    doctors.remove(getDoctorFromDNI(DNI7));
                } else System.out.println("The given DNI is not registered.");
                break;
            case ("8"):
                attendPatient();
                break;
            case ("9"):
                showSpecialitiesMenu();
            case ("10"):
                System.out.println("Introduce speciality : ");
                String sp = sc.nextLine();
                if (specialities.contains(sp))
                    System.out.println("The given speciality is already registered.");
                else {
                    addSpeciality(sp);
                    System.out.println("Speciality " + sp + " has been registered.");
                }
                break;
            default:
                flag = false;
                break;
        }


        if (flag) {
            menu();
        } else {
            System.out.println("Bye!");
        }
    }

    //PART 5 – SPECIALITIES
    //We're going to add a new option to our app to display info about specialities:
    //9 - Info specialities [Info]
    //10- Exit
    //If the user chooses this option, it must display a submenu with the following options:
    //1 - List of specialities attended by our doctors
    //2 - List of specialities not covered with unattended patients
    //3 - Exit.
    //First option will display all the specialities covered at our hospital and which doctor
    //works in each of them and second option will display all the specialities not covered
    //in our hospital with unattended patients.
    private void showSpecialitiesMenu() {
        System.out.println("Choose an option: ");
        System.out.println("1 - List of specialities attended by our doctors.");
        System.out.println("2 - List of specialities not covered with unattended patients.");
        System.out.println("3 - Exit.");
        boolean flag = true;
        String input = sc.nextLine();
        switch (input) {
            case "1":
                showDoctorSpecialities();
                break;
            case "2":
                showUnattendedPatientsDiseases();
                break;
            default:
                flag = false;
                break;
        }
        if (flag) showSpecialitiesMenu();
        else menu();
    }

    private void showUnattendedPatientsDiseases() {
        if (unattendendedPatients.isEmpty()) {
            System.out.println("We don't have any unattended patient");
        } else {
            System.out.println("These are the diseases of our unattended patients: ");
            for (Patient pat : unattendendedPatients) {
                System.out.println(pat.getDisease());
            }
        }
    }

    private void showDoctorSpecialities() {
        if (doctors.isEmpty()) {
            System.out.println("We don't have any registered doctor.");
        } else {
            System.out.println("These are the specialities of our doctors: ");
            for (Doctor doc : doctors) {
                System.out.println(doc.getSpeciality());
            }
        }
    }

    private void modifyDoctor() {
        System.out.print("Introduce DNI: ");
        String DNI4 = sc.nextLine();
        if (doctorRegistered(DNI4)) {
            doctors.remove(getDoctorFromDNI(DNI4));
        } else {
            System.out.println("The given DNI is not registered");
            System.out.print("Introduce first name: ");
            String name4 = sc.nextLine();
            System.out.print("Introduce last name: ");
            String lastName4 = sc.nextLine();
            System.out.print("Introduce speciality: ");
            String dis4 = sc.nextLine();
            System.out.print("Introduce salary: ");
            int ssn4 = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduce age: ");
            int age4 = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduce phone: ");
            String phone4 = sc.nextLine();
            if (validSpeciality(dis4)) {
                Doctor d4 = new Doctor(DNI4, name4, lastName4, dis4, ssn4, age4, phone4);
                System.out.println("Doctor" + name4 + " " + lastName4 + " profile has been modified.");
                doctors.add(d4);
            }
        }
    }

    private void registerPatient() {
        System.out.print("Introduce DNI: ");
        String DNI = sc.nextLine();
        System.out.print("Introduce first name: ");
        String name = sc.nextLine();
        System.out.print("Introduce last name: ");
        String lastName = sc.nextLine();
        System.out.print("Introduce disease: ");
        String disease = sc.nextLine();
        System.out.print("Introduce SSN: ");
        String ssn = sc.nextLine();
        System.out.print("Introduce age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduce phone: ");
        String phone = sc.nextLine();
        Patient p1 = new Patient(DNI, name, lastName, disease, ssn, age, phone);
        addPatient(p1);
        System.out.println("Patient " + p1.getName() + " " + p1.getLastName() + " has been registered.");
    }

    private void modifyPatient() {
        System.out.print("Introduce DNI: ");
        String DNI3 = sc.nextLine();
        if (patientRegistered(DNI3)) {
            patientsWaiting.remove(getPatientFromDNI(DNI3));
            System.out.print("Introduce first name: ");
            String name3 = sc.nextLine();
            System.out.print("Introduce last name: ");
            String lastName3 = sc.nextLine();
            System.out.print("Introduce disease: ");
            String dis3 = sc.nextLine();
            System.out.print("Introduce SSN: ");
            String ssn3 = sc.nextLine();
            System.out.print("Introduce age: ");
            int age3 = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduce phone: ");
            String phone3 = sc.nextLine();
            Patient p3 = new Patient(DNI3, name3, lastName3, dis3, ssn3, age3, phone3);
            patientsWaiting.add(p3);
        } else {
            System.out.println("The given DNI is not registered");
        }
    }

    private void registerDoctor() {
        System.out.print("Introduce DNI: ");
        String DNID = sc.nextLine();
        System.out.print("Introduce first name: ");
        String nameD = sc.nextLine();
        System.out.print("Introduce last name: ");
        String lastNameD = sc.nextLine();
        System.out.print("Introduce speciality: ");
        String speciality = sc.nextLine();
        System.out.print("Introduce salary: ");
        int salary = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduce age: ");
        int ageD = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduce phone: ");
        String phoneD = sc.nextLine();
        Doctor d1 = new Doctor(DNID, nameD, lastNameD, speciality, salary, ageD, phoneD);
        if (validSpeciality(speciality)) {
            System.out.println("Doctor " + nameD + " " + lastNameD + " has been registered.");
            addDoctor(d1);
        }
    }


}

