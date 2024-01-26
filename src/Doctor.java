public class Doctor {
    private String DNI,name,lastName,speciality,phone;
    private int salary,age;


    public String toString() {
        return           ("DNI-> " + DNI +
                        "\n Name-> " + name +
                        "\n Last name-> " + lastName +
                        "\n Speciality-> " + speciality +
                        "\n Salary-> " + salary +
                        "\n Age-> " + age +
                        "\n Phone-> " + phone + " \n ");
    }

    public Doctor(String DNI, String name, String lastName, String speciality, int salary, int age, String  phone) {
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

    public String  getPhone() {
        return phone;
    }

    public void setPhone(String  phone) {
        this.phone = phone;
    }
}
