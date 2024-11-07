import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Corsa {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci la lunghezza massima in metri: ");
        int fine= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Quanti cavalli vuoi inserie: ");
        int numCav = scanner.nextInt();
        scanner.nextLine();
        List<Cavallo> cavalli = new ArrayList<>();

        for(int i=0; i< numCav;i++){
            System.out.println("inseriisci il nome cavallo "+(i+1)+" :");
            String nome = scanner.nextLine();
            cavalli.add(new Cavallo (nome,fine));
        }
        System.out.println("inizio");
        for(Cavallo cavallo: cavalli){
            cavallo.start();
        }

        for(Cavallo cavallo:cavalli){
            try{
                cavallo.join();
            }
            catch(InterruptedException e ){
                e.printStackTrace();
            }
        }
       System.out.println("Fine"); 
       scanner.close();
    }
}