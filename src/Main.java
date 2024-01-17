import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void menu(){

    }


    public static void main(String[] args) {
        Hospital SanPedro = new Hospital();
        SanPedro.addSpeciality("Traumatology");
        SanPedro.addSpeciality("Cardiology");
        SanPedro.addSpeciality("Pulmonology");
        SanPedro.addSpeciality("Pediatrics");
        SanPedro.addSpeciality("Dermatology");
        SanPedro.addDoctor(new Doctor("342121331D","Paco","Pérez","Fulmonology",53423,30,653534234));
        SanPedro.addDoctor(new Doctor("347654331G","Fulgencio","Fulgencius","Traumatology",53000,35,653534855));
        System.out.println(SanPedro);
        SanPedro.addPatient(new Patient("53532512X","Fulgencia","Fulgencius","Taumatology","64564644964",65,658654659));
        //System.out.println(SanPedro.getPatientsWaiting());
        //System.out.println(SanPedro);

    }
}
