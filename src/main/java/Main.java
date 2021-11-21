import org.jfugue.player.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {

    static char[]trazeneNote = new char[]{'C','D','E','F','G','A','B'};

    static final int FIELD_SIZE=trazeneNote.length;
    static final int EVOLUCJE=1000;
    static final int POPULACIJA=2 ;
    static final int MUTATION_CHANCE=90;
    static final int MAX_AGE=200;


    static final char[] sveNote = new char[]{'C','D','E','F','G','A','B'};
    static Random random = new Random();
    static ArrayList<Kromosom> kromosoms = new ArrayList<>();


    public static void main(String[] args) {

        for(int i=0;i<POPULACIJA;i++){
            char[] a = new char[FIELD_SIZE];
            popuniRand(a);
            System.out.print(calculateFF(a) + " ");
            printKromosom(a);
            kromosoms.add(new Kromosom(a,FIELD_SIZE,calculateFF(a)));
        }

        int c=0;
        boolean nemaRjesenja = true;

        while(nemaRjesenja){
            if(c > EVOLUCJE){
                nemaRjesenja = false;
                System.out.println("Riješenje nije nađeno!");
            }
            kromosoms.sort(new Comparator());
            c++;
        }

        Player player = new Player();
        //player.play("C D E F G A B");
    }




    public static void popuniRand(char[] note){
        for(int i=0;i<note.length;i++){
            note[i] = sveNote[random.nextInt(7)];
        }
    }

    public static void printKromosom(char[]note){
        for(int i=0;i<note.length;i++){
            System.out.print(note[i]);
        }
        System.out.println();

    }

    public static int calculateFF(char[] note){
        int ff=0;
        for(int i=0;i<trazeneNote.length;i++){
            if(note[i]==trazeneNote[i]){
                ff++;
            }
        }
        return ff;
    }


}
