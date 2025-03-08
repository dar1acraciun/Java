public class Problem {
    private Student[] listOfStudents = new Student[100];
    private Project[] listOfProjects = new Project[100];
    private Teacher[] listOfTeacher = new Teacher[100];

    public Problem(Student[] listOfStudents, Project[] listOfProjects, Teacher[] listOfTeacher) {

        this.listOfStudents = listOfStudents;
        this.listOfProjects = listOfProjects;
        this.listOfTeacher = listOfTeacher;
    }

    public void setListOfStudents(Student[] listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public void setListOfProjects(Project[] listOfProjects) {
        this.listOfProjects = listOfProjects;
    }

    public void setListOfTeacher(Teacher[] listOfTeacher) {
        this.listOfTeacher = listOfTeacher;
    }

    public Student[] getListOfStudents() {
        return listOfStudents;
    }

    public Project[] getListOfProjects() {
        return listOfProjects;
    }

    public Teacher[] getListOfTeacher() {
        return listOfTeacher;
    }

    public Person[] getListOfPersons() {

        Person[] persons = new Person[listOfStudents.length + listOfProjects.length + listOfTeacher.length + 1];
        System.arraycopy(listOfStudents, 0, persons, 0, listOfStudents.length);
        System.arraycopy(listOfTeacher, 0, persons, listOfStudents.length, listOfTeacher.length);
        return persons;

    }
}
