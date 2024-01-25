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

    private ArrayList<Patient> unatendendedPatients;

    @Override
    public String toString() {
        return "name=" + name + '\'' +
                "\n money=" + money +
                "\n specialities=" + specialities +
                "\n patientsWaiting=" + patientsWaiting +
                "\n doctors=" + doctors;
    }


    public Hospital(String name) {
        this.name = name;
        this.money = 0;
        this.specialities = new ArrayList<>();
        this.patientsWaiting = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.attendedPatients = new HashMap<>();
        unatendendedPatients = new ArrayList<>();
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
                    System.out.println("We cannot attend such speciality " + pat.getName() + " " + pat.getLastName() + " has been moved into the unattended patients list.");
                    patientsWaiting.remove(0);
                    unatendendedPatients.add(pat);
                }
            }
        }
        else System.out.println("We have attended all of our patients.");
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
                if (patientsWaiting.isEmpty()){
                    System.out.println("We don't have any patient waiting. ");
                }
                else {
                    System.out.println(patientsWaiting);
                }
                break;
            case (2):
                if(attendedPatients.isEmpty()){
                    System.out.println("We haven't attended any patient yet. ");
                }
                else  {
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
                if (unatendendedPatients.isEmpty()){
                    System.out.println("We don't have any unattended patient");
                }
                else {
                    System.out.println(unatendendedPatients);
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
        System.out.println("9 - Exit");
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
                    System.out.println("Doctor " + getDoctorFromDNI(DNI7).getName() +" "+getDoctorFromDNI(DNI7).getLastName() +  " has been removed.");
                    doctors.remove(getDoctorFromDNI(DNI7));
                } else System.out.println("The given DNI is not registered.");
                break;
            case ("8"):
                attendPatient();
                break;
            case ("9"):
                flag = false;
                break;
            default:
                flag=false;
                break;
        }
        if(flag)
    {
        menu();
    } else
    {
        System.out.println("Bye!");
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

