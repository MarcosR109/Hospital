public class Doctor {
    private String DNI,name,lastName,speciality;
    private int salary,age,phone;


    public String toString() {
        return           ("DNI=" + DNI + '\'' +
                        ", name=" + name + '\'' +
                        ", lastName=" + lastName + '\'' +
                        ", speciality=" + speciality + '\'' +
                        ", salary=" + salary +
                        ", age=" + age +
                        ", phone=" + phone);
    }

    public Doctor(String DNI, String name, String lastName, String speciality, int salary, int age, int phone) {
        this.DNI = DNI;
        this.name = name;
        this.lastName = lastName;
        this.speciality = speciality;
        this.salary = salary;
        this.age = age;
        this.phone = phone;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
