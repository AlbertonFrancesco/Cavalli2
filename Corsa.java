import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("inserisci il nome del cavallo "+(i+1)+" :");
            String nome = scanner.nextLine();
            System.out.println("Inserisci la velocità di "+ nome+ " in metri al secondo");
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
        
        int posizione = 1;
        for (Cavallo cavallo : terminanti) {
         if (cavallo.getTempoFine() != -1 && posizione <= 3) { 
           System.out.println(posizione + ". " + cavallo.getNome() + " in: " + cavallo.getTempoFine() + " ms.");
           posizione++;
    }
}

       System.out.println("In che file vuoi inserire i dati? ");
       String nomeF = scanner.nextLine();
       try (FileWriter file = new FileWriter(nomeF, true)) {
       file.write("Classifica: \n");
       posizione = 1;
       for (Cavallo cavallo : terminanti) {
        if (cavallo.getTempoFine() != -1 && posizione <= 3) { // Ignora cavalli infortunati
            file.write(posizione + ". " + cavallo.getNome() + " in: " + cavallo.getTempoFine() + " ms.\n");
            posizione++;
        }
    }
     file.write("\n");
       System.out.println("Risultati salvati");
    }   catch (IOException e) {
    System.out.println("Errore nel salvataggio: " + e.getMessage());
}
        
       System.out.println("Fine"); 
       scanner.close();
    }
}