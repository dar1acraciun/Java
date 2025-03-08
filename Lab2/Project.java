/**
 * Reprezintă un proiect propus de un profesor.
 */

public class Project {
    String name;
    ProjectType type;


    public Project(ProjectType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectType getType() {
        return type;
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
        return "Project{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }


}
