public class Patient {
    private String DNI,name,lastName,disease,SSN,phone;
    private int age;

    @Override
    public String toString() {
        return  "DNI='" + DNI + '\'' +
                "\n name='" + name + '\'' +
                "\n lastName='" + lastName + '\'' +
                "\n disease='" + disease + '\'' +
                "\n SSN=" + SSN +
                "\n age=" + age +
                "\n phone=" + phone;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Patient(String DNI, String name, String lastName, String disease, String SSN, int age, String phone) {
        this.DNI = DNI;
        this.name = name;
        this.lastName = lastName;
        this.disease = disease;
        this.SSN = SSN;
        this.age = age;
        this.phone = phone;
    }
}
