import java.lang.reflect.Array;
import java.util.*;

public class Hospital {
    //Now we must create a new class called Hospital with the following attributes: name,
    //money, specialities (Array List), patientsWaiting (Array List) and doctors (Array List).
    //Don’t forget to add a constructor, getters, setters and toString() method.

    String name;
    int money;
    ArrayList<String> specialities;
    ArrayList<Patient> patientsWaiting;
    ArrayList<Doctor> doctors;
    HashMap<Patient, Doctor> attendedPatients;

    ArrayList<Patient> unatendendedPatients;

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
        Patient pat = patientsWaiting.get(0);
        if (validSpeciality(pat.getDisease())) {
            Doctor doc = getDoctorFromDisease(pat.getDisease());
            if (specilityDoctorFromDisease(pat.getDisease())) {
                attendedPatients.put(pat, doc);
                patientsWaiting.remove(0);
            } else {
                System.out.println("We cannot attend such speciality");
                unatendendedPatients.add(pat);
            }
        }
    }
    public void getDoctorFromPatient(Doctor doc){
        for(Patient pat: attendedPatients.keySet()){
            if(attendedPatients.containsValue(doc) && attendedPatients.containsKey(pat)){
                System.out.println(pat);
            }
        }
    }

    public void menuPatients(){
        //1 - List of waiting patients
        //2 - List of all attended patients
        //3 - List of attended patients by a specific doctor (search by DNI).
        //4.- Patients who have not been able to be treated
        System.out.println("#########################");
        System.out.println("Choose an option: ");
        System.out.println("1 - List of waiting patients.");
        System.out.println("2 - List of all atended patients.");
        System.out.println("3 - List of attended patients by a specific doctor.");
        System.out.println("4 - Patients who have not been able to be treated.");

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        switch (input){

            case(1):
                System.out.println(patientsWaiting);
            case(2):
                System.out.println(attendedPatients);
            case(3):
                System.out.println("Please introduce the doctor DNI: ");
                String DNI = sc.next();
                Doctor doc = getDoctorFromDNI(DNI);
                getDoctorFromPatient(doc);
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
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        boolean flag = true;
        switch (input) {
            case (1):
                System.out.print("Introduce DNI: ");
                String DNI = sc.next();
                System.out.print("Introduce first name: ");
                String name = sc.next();
                System.out.print("Introduce last name: ");
                String lastName = sc.next();
                System.out.print("Introduce disease: ");
                String disease = sc.next();
                System.out.print("Introduce SSN: ");
                String ssn = sc.next();
                System.out.print("Introduce age: ");
                int age = sc.nextInt();
                System.out.print("Introduce phone: ");
                String phone = sc.next();
                Patient p1 = new Patient(DNI, name, lastName, disease, ssn, age, phone);
                addPatient(p1);
                System.out.println("Patient " + p1.getName() + " " + p1.getLastName() + " has been registered.");
                break;
            case (2):
                System.out.print("Introduce DNI: ");
                String DNID = sc.next();
                System.out.print("Introduce first name: ");
                String nameD = sc.next();
                System.out.print("Introduce last name: ");
                String lastNameD = sc.next();
                System.out.print("Introduce speciality: ");
                String speciality = sc.next();
                System.out.print("Introduce salary: ");
                int salary = sc.nextInt();
                System.out.print("Introduce age: ");
                int ageD = sc.nextInt();
                System.out.print("Introduce phone: ");
                String phoneD = sc.next();
                Doctor d1 = new Doctor(DNID, nameD, lastNameD, speciality, salary, ageD, phoneD);
                if (validSpeciality(speciality)) {
                    System.out.println("Doctor " + nameD + " " + lastNameD + " has been registered.");
                    addDoctor(d1);
                }
                break;
            case (3): //modificar paciente
                System.out.print("Introduce DNI: ");
                String DNI3 = sc.next();
                if (patientRegistered(DNI3)) {
                    patientsWaiting.remove(getPatientFromDNI(DNI3));
                } else {
                    System.out.println("The given DNI is not registered");
                    break;
                }
                System.out.print("Introduce first name: ");
                String name3 = sc.next();
                System.out.print("Introduce last name: ");
                String lastName3 = sc.next();
                System.out.print("Introduce disease: ");
                String dis3 = sc.next();
                System.out.print("Introduce SSN: ");
                String ssn3 = sc.next();
                System.out.print("Introduce age: ");
                int age3 = sc.nextInt();
                System.out.print("Introduce phone: ");
                String phone3 = sc.next();
                Patient p3 = new Patient(DNI3, name3, lastName3, dis3, ssn3, age3, phone3);
                patientsWaiting.add(p3);
                break;
            case (4)://Modificar doctor
                System.out.print("Introduce DNI: ");
                String DNI4 = sc.next();
                if (doctorRegistered(DNI4)) {
                    doctors.remove(getDoctorFromDNI(DNI4));
                } else {
                    System.out.println("The given DNI is not registered");
                    break;
                }
                System.out.print("Introduce first name: ");
                String name4 = sc.next();
                System.out.print("Introduce last name: ");
                String lastName4 = sc.next();
                System.out.print("Introduce speciality: ");
                String dis4 = sc.next();
                System.out.print("Introduce salary: ");
                int ssn4 = sc.nextInt();
                System.out.print("Introduce age: ");
                int age4 = sc.nextInt();
                System.out.print("Introduce phone: ");
                String phone4 = sc.next();
                if (validSpeciality(dis4)) {
                    Doctor d4 = new Doctor(DNI4, name4, lastName4, dis4, ssn4, age4, phone4);
                    System.out.println("Doctor" + name4 + " " + lastName4 + " profile has been modified.");
                    doctors.add(d4);
                }
                break;
            case (5):
                System.out.println(getPatientsWaiting());
                break;
            case (6):
                System.out.println(getDoctors());
                break;
            case (7):
                System.out.println("Introduce DNI: ");
                String DNI7 = sc.next();
                if (doctorRegistered(DNI7)) {
                    System.out.println("Doctor " + getDoctorFromDNI(DNI7).getName() + " has been removed.");
                    doctors.remove(getDoctorFromDNI(DNI7));
                }
                break;
            case(9):
                attendPatient();
            default:
                flag = false;
        }
        if (!flag) {
            System.out.println("Bye!");
        } else menu();
    }
}

