import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Teacher extends Person {
    Project[] proposeProjects=new Project[1000];

    public Teacher(String name, LocalDate date, Project[] projects) {
        this.proposeProjects = projects;
        this.name = name;
        this.dateOfBirth=date;
    }

    public void setProposeProjects(Project[] proposeProjects) {
        this.proposeProjects = proposeProjects;
    }

    public Project[] getProposeProjects() {
        return proposeProjects;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "proposeProjects=" + Arrays.toString(proposeProjects) +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
