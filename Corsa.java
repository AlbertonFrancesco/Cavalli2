import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.Comparator;
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
            System.out.println("Inserisci la velocità di "+ nome+ " metri al secondo");
            int velocita= scanner.nextInt();
            scanner.nextLine();
            cavalli.add(new Cavallo (nome,fine,velocita));
        }
        System.out.println("inizio");
        for(Cavallo cavallo: cavalli){
            cavallo.start();
        }

        List<Cavallo> terminanti= new ArrayList<>();
        for(Cavallo cavallo:cavalli){
            try{
                cavallo.join();
                if(!cavallo.isInterrupted()){
            terminanti.add(cavallo);
                }
            }
            catch(InterruptedException e ){
                e.printStackTrace();
            }
        }

        terminanti.sort(Comparator.comparingLong(Cavallo::getTempoFine));
        System.out.println("/nClassifica dei primi tre cavalli: ");
        for(int i=0; i<Math.min(3,terminanti.size());i++){
            Cavallo cavallo= terminanti.get(i);
            System.out.println((i+1)+ cavallo.getNome()+" in: "+cavallo.getTempoFine()+" ms.");
        }
       System.out.println("Fine"); 
       scanner.close();
    }
}