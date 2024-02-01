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

        /*DNI lenght 10 */ SanPedro.addDoctor(new Doctor("342121331D","Paco","Pérez","Traumatology",53423,30,"653534234"));
        /*DNI ya registrado*/  SanPedro.addDoctor(new Doctor("34765433G","Fulgencio","Fulgencius","Traumatology",53000,35,"653534855"));
        /*SSN 11*/ SanPedro.addPatient(new Patient("53532512X","Marta","Mar","Traumatology","45646449642",65,"658654659"));
        /*Correcto*/ SanPedro.addDoctor(new Doctor("34765433G","Fulgencio","Fulgencius","Traumatology",53000,35,"653534855"));
        /*Correcto*/ SanPedro.addPatient(new Patient("84229441T","Paca","Ful","Traumatology","645116644964",20,"658211659"));
        /*Teléfono mal*/ SanPedro.addPatient(new Patient("84242421Q","Samuel","Loco","Pediatrics","252352352551",76,"258254353"));
        /*Edad mal*/  SanPedro.addPatient(new Patient("84242421T","Samuel","Loco","Pediatrics","252352352551",151,"658254353"));
        //SanPedro.menu();
        System.out.println(SanPedro.getDNIlist());
        System.out.println(SanPedro.getDoctors());
        System.out.println(SanPedro.getPatientsWaiting());
        //System.out.println(SanPedro.getPatientsWaiting());
        //System.out.println(SanPedro);
        HospitalHolding h1 = new HospitalHolding("Pacompany");
        h1.menu();
        //SanPedro.attendPatient();
        //SanPedro.attendPatient();
       // System.out.println(SanPedro.getPatientsWaiting());
    }
}
