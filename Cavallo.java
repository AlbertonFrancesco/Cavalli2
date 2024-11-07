public class Cavallo extends Thread{
    private String nome;
    private int distanza;
    private int fine;
    private static int distmax = 10;
    
    public Cavallo(String nome, int fine){
        this.nome = nome;
        this.fine = fine;
        this.distanza=0;
    }
    @Override
    public void run(){
        while (distanza < fine){
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
        System.out.println(nome + " ha finito la gara ");
    }
    }