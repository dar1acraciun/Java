//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Project project1=new Project(ProjectType.PRACTICAL,"Math");
        Project project2=new Project(ProjectType.PRACTICAL,"English");
        Project project3=new Project(ProjectType.PRACTICAL,"Science");
        Project project4=new Project(ProjectType.PRACTICAL,"Physics");

        Project[] listOfProjects1 = {project1,project2},listOfProjects2={project1,project3},listOfProjects3={project3,project4},listOfProjects4={project1,project4};
        Project[] listOfAllProjects={project1,project2,project3,project4};

        Student student1=new Student("Ion","2004-12-19",1234,listOfProjects1);
        Student student2=new Student("Iustin","2004-12-30",1324,listOfProjects2);
        Student student3=new Student("Razvan","2004-12-30",1934,listOfProjects3);
        Student student4=new Student("Maria","2004-12-30",1662,listOfProjects4);

        System.out.println(student1.toString()+"\n");
        System.out.println(project2.toString()+"\n");

        Student[] listOfAllStudents={student1,student2,student3,student4};

        Problem problem=new Problem(listOfAllStudents,listOfAllProjects,null);
        Solution solution=new Solution(problem);
        solution.greedy();
        solution.bonus();


        Student[] listStudentsBonus=new Student[100];
        Project[] listProjectsBonus=new Project[200];

        long startTime = System.currentTimeMillis();

        for(int i=0;i<20;i++){

            Project currentProjectBonus=new Project(ProjectType.PRACTICAL,"p"+i);
            listProjectsBonus[i]=currentProjectBonus;

        }
        for(int i=0;i<10;i++){

            Project[] listProjectsAddStudent=new Project[100];
            listProjectsAddStudent[0]=listProjectsBonus[i];
            listProjectsAddStudent[1]=listProjectsBonus[i+1];
            Student currentAddStudent=new Student("s"+i,"2004-12-19",i,listProjectsAddStudent);

        }
        Problem problemBonus=new Problem(listStudentsBonus,listProjectsBonus,null);
        Solution solutionBonus=new Solution(problemBonus);
        solutionBonus.bonus();
        long endTime = System.currentTimeMillis();
        System.out.println("Timpul:" + (endTime - startTime) + " millisecunde");
    }

}
