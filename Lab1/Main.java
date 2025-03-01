import java.awt.event.ActionListener;
import java.lang.reflect.GenericArrayType;
import java.util.Vector;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    int[][] a;
    int ok;
    int clique;

    public static void main(String[] args) {
      Main lab1 = new Main();
      lab1.compulsory();
      lab1.homework(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
      lab1.bonus(Integer.parseInt(args[1]));


    }
    void compulsory()
    {
        System.out.println("Hello world!\n");
        String[] languages={"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n=(int)(Math.random()*100000000);
        System.out.printf("%d\n",n);
        n *= 3;
        System.out.printf("%d\n",n);
        String d="10101";
        int decimal=Integer.parseInt(d);
        n+=decimal;
        System.out.printf("%d\n",n);
        String HexNumber="FF";
        decimal=Integer.parseInt(HexNumber,16);
        n+=decimal;
        n*=6;
        int s;
        do{
            s=0;
            while(n!=0)
            {
                s+=n%10;
                n/=10;
            }
            n=s;

        }while(n>=10);
        System.out.printf("%d\n",n);
        System.out.printf("Willy-nilly, this semester I will learn " + languages[n]+"\n");
    }
    void homework(int n,int k)
    {
        System.out.println("\n-TEMA\n");
        a=new int[n][n];
        long startTime = System.currentTimeMillis();
        Vector<Integer> clique=new Vector<>();
        Vector<Integer> stable=new Vector<>();
        if(n<(2*k-1))
            System.out.println("nu putem respecta proprietatile\n");

        else {
            System.out.println("Clica este formata din:");
            for (int i = 0; i < k; i++) {
                int random;
                do {
                    random = (int) (Math.random() * n);
                } while (clique.contains(random));
                clique.add(random);
                System.out.printf("%d ", clique.get(i));
            }
            for (int nr1 : clique)
                for (int nr2 : clique)
                    if (nr1 != nr2)
                        a[nr1][nr2] = a[nr2][nr1] = 1;
            int []generare=new int[n];
            System.out.println("\nTestam functia de verificare cicla:");

            clique(a,n,k,0,generare,0);

                int it_is_clique=0;
                System.out.printf("\nMultimea stabila este formata din:\n");
                for(int i=0;i<k;i++)
                {

                    int random;
                    int ok;
                    do {
                        ok=1;
                        do {
                            random = (int) (Math.random() * n);
                        } while (stable.contains(random));
                        if(clique.contains(random)&&it_is_clique==0)
                            it_is_clique=1;
                        else if(clique.contains(random)&&it_is_clique==1)
                            ok=0;
                    }while(ok==0);
                    stable.add(random);
                    System.out.printf("%d ", stable.get(i));
                }
                for(int i=0;i<n;i++)
                    for(int j=0;j<n/2;j++)
                    {
                        int random=(int)(Math.random()*2);
                        if(!stable.contains(i)&&!stable.contains(j)&&i!=j&&a[i][j]!=1)
                            a[i][j]=a[j][i]=random;
                    }

                System.out.println("\n");
                String representationString="";
            int count_edges=0,max_degree=0,min_degree=n,sum_degree=0;
            if(n<30000){
           for (int i = 0; i < n; i++) {
                int degree=0;
                for (int j = 0; j < n; j++) {

                    if (a[i][j] == 1) {
                       representationString+='\u2B2C';
                        degree++;
                        if(i<j)
                             count_edges++;

                    }
                    else {
                        representationString+='\u2B2D';
                    }

                }
                if(degree<min_degree)
                    min_degree=degree;
                if(degree>max_degree)
                    max_degree=degree;
                sum_degree+=degree;
                representationString+='\n';

            }

            System.out.printf("%s\n","-REPREZENTARE MATRICE-\n"+representationString);
            System.out.printf("nr. muchii=%d\n",count_edges);
            System.out.printf("\u0394(G)=%d\n",max_degree);
            System.out.printf("\u03B4(G)=%d\n",min_degree);
            if(sum_degree==2*count_edges)
                System.out.printf("Adevarat:%d=2*%d\n",sum_degree,count_edges);}
            long endTime = System.currentTimeMillis();
            System.out.println("Timpul:" + (endTime - startTime) + " millisecunde");

            }

    }
    void bonus(int k)
    {
        int n;
        n=(int)(Math.random()*7);
        System.out.printf("-BONUS-\n",n);
        System.out.printf("dimensiunea grafului=%d\n",n);
        int[][] matriceRand=new int[n][n];
        for(int i=0;i<n;i++)
            for (int j=0;j<i;j++)
                matriceRand[i][j]=matriceRand[j][i]=(int)(Math.random()*2);
        System.out.println("\nGraful random este:\n");
        for(int i=0;i<n;i++) {
            for (int j = 0; j <n; j++)
                System.out.print(matriceRand[i][j] + " ");
            System.out.println('\n');
        }
        int[] generare=new int[k];
        clique=0;
        clique(matriceRand,n,k,0,generare,0);
        if(clique==0)
            System.out.printf("\nGraful nu contine o cicla de lungime %d",k);
        int[][] matrice_inv=new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<i;j++)
                matrice_inv[i][j]=matrice_inv[j][i]=1-matriceRand[i][j];
        clique=0;
        clique(matrice_inv,n,k,0,generare,0);
        if(clique==1)
            System.out.printf("\nGraful contine o multime stabila de lungime %d",k);
        else
            System.out.printf("\nGraful nu contine o multime stabila de lungime %d",k);




    }
    void clique(int[][] matriceRand, int n, int k, int p, int generare[], int start) {
        if (p == k) {
            verf(matriceRand, n, k,generare);
            return;
        }

        for (int i = start; i < n; ++i) {
            generare[p] = i;
            clique(matriceRand, n, k, p + 1, generare, i + 1);
        }
    }
   void verf(int[][] matriceRand,int n,int k,int[] generare)
   {
       ok=1;
       for(int i = 0 ; i < k ;  i++)
       {
           for(int j = i+1 ; j < k ; j++)
               if(matriceRand[generare[i]][generare[j]]==0)
                   ok=0;

       }
       if(ok==1) {
           System.out.printf("\nGraful contine o cicla de lungime %d \n", k);
          clique=1;
       }

   }

}
