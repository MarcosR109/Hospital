import java.util.ArrayList;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Hospital SanPedro = new Hospital("San Pedro");
        SanPedro.addSpeciality("Traumatology");
        SanPedro.addSpeciality("Cardiology");
        SanPedro.addSpeciality("Pulmonology");
        SanPedro.addSpeciality("Pediatrics");
        SanPedro.addSpeciality("Dermatology");
        SanPedro.addDoctor(new Doctor("342121331D","Paco","Pérez","Fulmonology",53423,30,"653534234"));
        SanPedro.addDoctor(new Doctor("347654331G","Fulgencio","Fulgencius","Traumatology",53000,35,"653534855"));
        //System.out.println(SanPedro);
        SanPedro.addPatient(new Patient("53532512X","Marta","Mar","Traumatology","64564644964",65,"658654659"));
        SanPedro.addPatient(new Patient("84229441T","Paca","Ful","Traumatology","645116644964",20,"658211659"));
        SanPedro.addPatient(new Patient("84242421Q","Samuel","Loco","Pediatrics","252352352551",76,"658254353"));
        //System.out.println(SanPedro.getPatientsWaiting());
        //System.out.println(SanPedro);
        //SanPedro.menu();
        SanPedro.attendPatient();
        SanPedro.attendPatient();
        System.out.println(SanPedro.getPatientsWaiting());
    }
}
