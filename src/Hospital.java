
import java.util.*;


public class Hospital {

    private static Scanner sc = new Scanner(System.in);
    private String name;
    private int money;
    private ArrayList<String> specialities;
    private ArrayList<Patient> patientsWaiting;
    private ArrayList<Doctor> doctors;
    private HashMap<Patient, Doctor> attendedPatients;

    private ArrayList<Patient> unattendendedPatients;

    private ArrayList<String> DNIlist;

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
        DNIlist = new ArrayList<>();
    }

    public void addSpeciality(String speciality) {
        specialities.add(speciality);
    }

    private boolean validAge(int age) {
        return age > 0 && age < 150;
    }


    public boolean validSpeciality(String speciality) {
        for (String sp : specialities) {
            if (sp.equals(speciality)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDNI(String DNI) {
        return DNIlist.contains(DNI);
    }

    /*
     * El método recibe un doctor ya creado para realizar todas las validaciones de datos.
     * Me he tomado la libertad de crear un Arraylist de DNI para que sea más fácil comprobar si el DNI ya está registrado.
     */
    public void addDoctor(Doctor dotor) {
        if (validDNI(dotor.getDNI())) {
            if (validAge(dotor.getAge())) {
                if (validPhone(dotor.getPhone())) {
                    if (!checkDNI(dotor.getDNI())) {
                        if (validSpeciality(dotor.getSpeciality())) {
                            doctors.add(dotor);
                            DNIlist.add(dotor.getDNI());
                        } else System.out.println("We do not have licence for such speciality.");
                    } else System.out.println("The given DNI is already registered.");
                } else System.out.println("The phone must have 9 digit and start with 6,7,8 or 9");
            } else System.out.println("The age must be positive and below 150");
        } else
            System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
    }

    public void addPatient(Patient patient) {
        if (validDNI(patient.getDNI())) {
            if (validPhone(patient.getPhone())) {
                if (validSSN(patient.getSSN())) {
                    if (validAge(patient.getAge())) {
                        if (!checkDNI(patient.getDNI())) {
                            if (validSpeciality(patient.getDisease())) {
                                patientsWaiting.add(patient);
                                DNIlist.add(patient.getDNI());
                            } else {
                                patientsWaiting.add(patient);
                                DNIlist.add(patient.getDNI());
                                System.out.println("We do not have such speciality");
                            }
                        } else System.out.println("The given DNI is already registered");
                    } else System.out.println("The age must be positive and below 150");
                } else System.out.println("The SSN must have 12 digits.");
            } else System.out.println("The phone must have 9 digits and start with 6,7,8 or 9");
        } else
            System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
    }


    public void removeDoctor(Doctor d1) {
        if (doctors.contains(d1)) {
            doctors.remove(d1);
            DNIlist.remove(d1.getDNI());
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

    public ArrayList<String> getDNIlist() {
        return DNIlist;
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

    private boolean validSSN(String SSN) {
        return SSN.length() == 12;
    }

    /*
     * Primero hago un replace para eliminar los posibles espacios en el input.
     */
    private boolean validPhone(String phone) {
        phone = phone.replace(" ", "");
        return phone.length() == 9 && (phone.startsWith("6") || phone.startsWith("7") || phone.startsWith("8") || phone.startsWith("9"));
    }

    //https://www.w3api.com/Java/Character/isLetter/
    //MÉTODO Character.isLetter()
    public boolean validDNI(String DNI) {
        return DNI.length() == 9 && isNumeric(DNI.substring(0, 8)) && Character.isLetter(DNI.charAt(8));
    }

    /**
     * @param DNI
     * @return Devuelve si el DNI del paciente pertenece a algún DNI que esté ya en la lista de pacientes.
     */
    public boolean patientRegistered(String DNI) {
        for (Patient pat : patientsWaiting) {
            if (pat.getDNI().equals(DNI))
                return true;
        }
        return false;
    }

    /**
     * @param DNI
     * @return Devuelve el paciente cuyo DNI coincida con el parámetro, sino devuelve null.
     */
    public Patient getPatientFromDNI(String DNI) {
        for (Patient pat : patientsWaiting) {
            if (pat.getDNI().equals(DNI))
                return pat;
        }
        return null;
    }

    /**
     * Primero comprueba que la lista de pacientes no está vacía, de ahí comprueba la especialidad de este, y si encuentra un doctor cuya especialidad coincida
     * lo "atiende" y lo añade al HashMap attendedPatients y borra del patientsWaiting.
     */

    public void attendPatient() {
        if (!patientsWaiting.isEmpty()) {
            Patient pat = patientsWaiting.get(0);
            if (validSpeciality(pat.getDisease())) {
                Doctor doc = getDoctorFromDisease(pat.getDisease());
                if (specilityDoctorFromDisease(pat.getDisease())) {
                    attendedPatients.put(pat, doc);
                    patientsWaiting.remove(0);
                    System.out.println("The patient " + pat.getName() + " " + pat.getLastName() + " has been attended.");
                }
            } else {
                System.out.println("We cannot attend " + pat.getDisease() + "," + pat.getName() + " " + pat.getLastName() + " has been moved into the unattended patients list.");
                patientsWaiting.remove(0);
                unattendendedPatients.add(pat);
            }
        } else System.out.println("We have attended all of our patients.");
    }

    /**
     * @param doc un doctor para comprobar si ha atendido a algún paciente, recorre el HashMap attendedPatients y si encuentra una combinación
     *            clave/valor que encaje, la imprime.
     */
    public void printDoctorFromPatient(Doctor doc) {
        System.out.println("The doctor " + doc.getName() + " " + doc.getLastName() + " has attended : ");
        for (Patient pat : attendedPatients.keySet()) {
            if (attendedPatients.containsValue(doc) && attendedPatients.containsKey(pat)) {
                System.out.println(pat);
            }
        }
    }

    /**
     * Primero comprueba que la lista attendedPatients no está vacía, si no lo está, devuelve la lista pertinente al input del usuario.
     */

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
                if (attendedPatients.isEmpty()) {
                    System.out.println("We haven't attended any patient yet.");
                } else {
                    System.out.println("Please introduce the doctor DNI: ");
                    String DNI = sc.nextLine();
                    if (validDNI(DNI)) {
                        Doctor doc = getDoctorFromDNI(DNI);
                        if (attendedPatients.containsValue(doc)) {
                            printDoctorFromPatient(doc);
                        } else if (registeredDoctorDNI(DNI)) {
                            System.out.println("The given doctor hasn't attended any patient. ");
                        } else System.out.println("The given DNI is not registered.");
                    } else {
                        System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
                    }
                }
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
                if (validDNI(DNI7)) {
                    if (doctorRegistered(DNI7)) {
                        System.out.println("Doctor " + getDoctorFromDNI(DNI7).getName() + " " + getDoctorFromDNI(DNI7).getLastName() + " has been removed.");
                        doctors.remove(getDoctorFromDNI(DNI7));
                        DNIlist.remove(DNI7);
                    } else System.out.println("The given DNI is not registered.");
                } else {
                    System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
                }
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
        if (validDNI(DNI4)) {
            if (doctorRegistered(DNI4)) {
                System.out.print("Introduce first name: ");
                String name4 = sc.nextLine();
                System.out.print("Introduce last name: ");
                String lastName4 = sc.nextLine();
                System.out.print("Introduce speciality: ");
                String dis4 = sc.nextLine();
                if (validSpeciality(dis4)) {
                    System.out.print("Introduce salary: ");
                    int salary = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Introduce age: ");
                    int age4 = sc.nextInt();
                    sc.nextLine();
                    if (validAge(age4)) {
                        System.out.print("Introduce phone: ");
                        String phone4 = sc.nextLine();
                        if (validPhone(phone4)) {
                            Doctor d4 = new Doctor(DNI4, name4, lastName4, dis4, salary, age4, phone4);
                            System.out.println("Doctor " + name4 + " " + lastName4 + " profile has been modified.");
                            doctors.add(d4);
                            doctors.remove(getDoctorFromDNI(DNI4));
                        } else System.out.println("The phone must have 9 digits and start with 6,7,8 or 9");
                    } else System.out.println("The age must be positive and below 150");
                } else System.out.println("The given speciality is not registered.");
            } else System.out.println("The given DNI is not registered");
        } else
            System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
    }

    private void registerPatient() {
        System.out.print("Introduce DNI: ");
        String DNI = sc.nextLine();
        if (validDNI(DNI)) {
            if (!checkDNI(DNI)) {
                System.out.print("Introduce first name: ");
                String name = sc.nextLine();
                System.out.print("Introduce last name: ");
                String lastName = sc.nextLine();
                System.out.print("Introduce disease: ");
                String disease = sc.nextLine();
                System.out.print("Introduce SSN: ");
                String ssn = sc.nextLine();
                if (validSSN(ssn)) {
                    System.out.print("Introduce age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    if (validAge(age)) {
                        System.out.print("Introduce phone: ");
                        String phone = sc.nextLine();
                        if (validPhone(phone)) {
                            Patient p1 = new Patient(DNI, name, lastName, disease, ssn, age, phone);
                            addPatient(p1);
                            System.out.println("Patient " + p1.getName() + " " + p1.getLastName() + " has been registered.");
                        } else System.out.println("The phone must have 9 digits and start with 6,7,8 or 9");
                    } else System.out.println("The age must be positive and below 150");
                } else System.out.println("The SSN must have 12 digits.");
            } else System.out.println("The given DNI is already registered.");
        } else
            System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
    }

    private void modifyPatient() {
        System.out.print("Introduce DNI: ");
        String DNI3 = sc.nextLine();
        if (validDNI(DNI3)) {
            if (patientRegistered(DNI3)) {
                System.out.print("Introduce first name: ");
                String name3 = sc.nextLine();
                System.out.print("Introduce last name: ");
                String lastName3 = sc.nextLine();
                System.out.print("Introduce disease: ");
                String dis3 = sc.nextLine();
                System.out.print("Introduce SSN: ");
                String ssn3 = sc.nextLine();
                if (validSSN(ssn3)) {
                    System.out.print("Introduce age: ");
                    int age3 = sc.nextInt();
                    sc.nextLine();
                    if (validAge(age3)) {
                        System.out.print("Introduce phone: ");
                        String phone3 = sc.nextLine();
                        if (validPhone(phone3)) {
                            Patient p3 = new Patient(DNI3, name3, lastName3, dis3, ssn3, age3, phone3);
                            patientsWaiting.add(p3);
                            patientsWaiting.remove(getPatientFromDNI(DNI3));
                            System.out.println("Patient" + name3 + " " + lastName3 + " profile has been modified.");
                        } else System.out.println("The phone must have 9 digits and start with 6,7,8 or 9");
                    } else System.out.println("The age must be positive and below 150");
                } else System.out.println("The SSN must have 12 digits.");
            } else System.out.println("The given DNI is not registered");
        } else System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
    }

    private void registerDoctor() {
        System.out.print("Introduce DNI: ");
        String DNID = sc.nextLine();
        if (!checkDNI(DNID)) {
            if (validDNI(DNID)) {
                System.out.print("Introduce first name: ");
                String nameD = sc.nextLine();
                System.out.print("Introduce last name: ");
                String lastNameD = sc.nextLine();
                System.out.print("Introduce speciality: ");
                String speciality = sc.nextLine();
                if (validSpeciality(speciality)) {
                    System.out.print("Introduce salary: ");
                    int salary = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Introduce age: ");
                    int ageD = sc.nextInt();
                    sc.nextLine();
                    if (validAge(ageD)) {
                        System.out.print("Introduce phone: ");
                        String phoneD = sc.nextLine();
                        if (validPhone(phoneD)) {
                            Doctor d1 = new Doctor(DNID, nameD, lastNameD, speciality, salary, ageD, phoneD);
                            System.out.println("Doctor " + nameD + " " + lastNameD + " has been registered.");
                            addDoctor(d1);
                        } else System.out.println("The phone must have 9 digits and start with 6,7,8 or 9");
                    } else System.out.println("The age must be positive and below 150");
                } else System.out.println("The given speciality is not valid.");
            } else
                System.out.println("The given DNI is not valid. Please introduce a sequence of 8 numbers followed by a letter.");
        } else System.out.println("The given DNI is already registered.");
    }
}

