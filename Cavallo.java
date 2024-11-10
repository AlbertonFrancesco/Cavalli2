import java.io.FileWriter;
import java.io.IOException;

public class Cavallo extends Thread{
    private String nome;
    private int distanza;
    private int fine;
    private static int distmax = 10;
    private int velocita;
    private boolean infortunato = false; 
    private long tempoFine; 
    
    public Cavallo(String nome, int fine, int velocita){
        this.nome = nome;
        this.fine = fine;
        this.distanza=0;
        this.velocita= velocita;
    }

    public String getNome(){
        return nome;
    }

    public long getTempoFine() {
        return tempoFine;
    }

    @Override
    public void run(){
        long inizioTempo= System.currentTimeMillis();
        while (distanza < fine&& !infortunato){
            if(Math.random() < 0.1){
                infortunato= true;
                System.out.println(nome+"si è infortunato.");
                break;
            }
            int step = (int) (Math.random()*distmax)+1;
            distanza+=step;
            if (distanza> fine){
                distanza = fine;
            }
            System.out.println(nome + " ha corso " + distanza + " metri ");
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e ){
            e.printStackTrace();
        }
        }
        if(!infortunato){
            tempoFine = System.currentTimeMillis()-inizioTempo;
            System.out.println(nome + " ha finito la gara in "+tempoFine+" ms.");
        }
        
    }
    }