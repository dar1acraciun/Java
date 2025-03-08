import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Reprezintă soluția pentru problema alocării proiectelor.
 */
public class Solution{

    private Problem problem;
    private Student[] finalListOfStudents;
    private Project[] finalListOfProjects;
    private Map<Project,Integer> frecvOfAccProjects=new HashMap<Project,Integer>();
    private Boolean hallCondition=true;


    public Solution(Problem problem) {
        this.problem = problem;
        finalListOfStudents=new Student[problem.getListOfStudents().length+1];
        finalListOfProjects=new Project[problem.getListOfProjects().length+1];

    }

    public void calculateFrecvProjects(){
        for (int i = 0; i < problem.getListOfStudents().length; i++) {

            Student currentStudent=problem.getListOfStudents()[i];

            for (int j =0;j<currentStudent.getAccProjects().length;j++) {
                frecvOfAccProjects.put(currentStudent.getAccProjects()[j], frecvOfAccProjects.getOrDefault(currentStudent.getAccProjects()[j], 0) + 1);
            }
        }
    }
    /**
     * Implementează un algoritm greedy pentru alocarea proiectelor studenților.
     * Algoritmul greedy se bazeaza pe calcularea frecventei ficarui proiect in listele studentilor
     * Mereu aloca cel mai nepreferat proiect din lista de preferinte.
     */
    public void greedy() {
        System.out.println("-Homework-\n");

        calculateFrecvProjects();
        for (int i = 0; i < problem.getListOfStudents().length; i++) {

            int minFrecvProjects = 9999;
            Project projectChoose = null;
            Student currentStudent=problem.getListOfStudents()[i];

            for (int j = 0; j < currentStudent.getAccProjects().length; j++) {
                Project currentProject = currentStudent.getAccProjects()[j];
                if (!frecvOfAccProjects.containsKey(currentProject)) {
                    continue;
                }
                else {
                    if (frecvOfAccProjects.get(currentProject) < minFrecvProjects) {
                        minFrecvProjects = frecvOfAccProjects.get(currentProject);
                        projectChoose = currentProject;
                    }
                }

            }

            finalListOfStudents[i] =currentStudent;
            finalListOfProjects[i] = projectChoose;
            frecvOfAccProjects.remove(projectChoose);
        }
        for(int i = 0; i< finalListOfStudents.length-1; i++){
            System.out.printf(finalListOfStudents[i].getName()+"-"+finalListOfProjects[i].getName()+"\n");
        }
    }

    /**
     * bonusul implementeza teorema casatoriei lui Hall->pentru fiecare submultime numarul de proiecte distincte
     * trebuie sa fie cel putin egal cu numarul persoanelor.
     *
     */

    public void bonus()
    { int findSolution=1;
        System.out.println("\n-Bonus-\n");
        int [] generareCombStudents=new int[problem.getListOfStudents().length+1];

        for(int i=0;i<problem.getListOfStudents().length;i++) {
            bkt(i,1,generareCombStudents);
        }

        if(hallCondition) {
            System.out.println("S-a gasit solutie folosind Hall\n");
        }
        else
            System.out.println("Nu se poate gasi solutie folosind Hall\n");
        }

    /**
     *
     * @param p -marimea combinarii
     * @param k-count pentru recusrie
     * @param generare-vectorul in care genereaza comb
     */

    public void bkt(int p,int k,int[] generare)
    {
        for(int i=generare[k-1]+1;i<=problem.getListOfStudents().length;i++)
        {
            generare[k]=i;

            if(k==p)
                verfHallCond(generare,k);
            else
                bkt(p,k+1,generare);
        }
    }


    public void verfHallCond(int[] generare,int k)
    {
        List<Project> projectsChoose=new ArrayList<>();

        for(int i=0;i<problem.getListOfStudents().length;i++) {

            Student currentStudent=problem.getListOfStudents()[i];

            for(int j=0;j<currentStudent.getAccProjects().length;j++) {

                Project currentProject = currentStudent.getAccProjects()[j];

                if(!projectsChoose.contains(currentProject)) {
                    projectsChoose.add(currentProject);
                }
            }

        }
        if(projectsChoose.size()<k) {

            hallCondition=false;
        }
    }
}
