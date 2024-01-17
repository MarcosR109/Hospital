import java.util.ArrayList;

public class Hospital {
    //Now we must create a new class called Hospital with the following attributes: name,
    //money, specialities (Array List), patientsWaiting (Array List) and doctors (Array List).
    //Don’t forget to add a constructor, getters, setters and toString() method.

    String name;
    int money;
    ArrayList <String> specialities;
    ArrayList <Patient> patientsWaiting;
    ArrayList <Doctor> doctors;

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", money=" + money +
                ", specialities=" + specialities +
                ", patientsWaiting=" + patientsWaiting +
                ", doctors=" + doctors;
    }


    public Hospital(ArrayList<String> specialities) {
        this.name = "Hospital";
        this.money = 0;
        this.specialities=specialities;
        this.patientsWaiting =new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    public boolean validSpeciality(String speciality){
        for(String sp: specialities){
            if(sp.equals(speciality)){
                return true;
            }
        }
        return false;
    }

    public void addDoctor(Doctor dotor){
        if(validSpeciality(dotor.getSpeciality())){
            doctors.add(dotor);
        }
        else System.out.println("We do not have licence for such speciality.");
    }

    public void addPatient(Patient patient){
        if(validSpeciality(patient.getDisease())){
            patientsWaiting.add(patient);
        }
        else{
            patientsWaiting.add(patient);
            System.out.println("We do not such speciality");
        }
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
}
