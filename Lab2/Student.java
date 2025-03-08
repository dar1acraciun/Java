import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Student extends Person {
    int registrationNumber;
    private Project[] accProjects=new Project[10];

    public Student(String name,String date,int registrationNumber, Project[] accProjects) {
        this.registrationNumber = registrationNumber;
        this.accProjects = accProjects;
        this.name = name;
        this.dateOfBirth= LocalDate.parse(date);
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public Project[] getAccProjects() {
        return accProjects;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setAccProjects(Project[] accProjects) {
        this.accProjects = accProjects;
    }
    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Student{" +
                "registrationNumber=" + registrationNumber +
                ", accProjects=" + Arrays.toString(accProjects) +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
