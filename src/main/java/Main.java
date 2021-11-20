import org.jfugue.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static final int FIELD_SIZE=7;
    static final int EVOLUCJE=1000;
    static final int POPULACIJA=2 ;
    static final int MUTATION_CHANCE=90;
    static final int MAX_AGE=200;
    static Random random = new Random();
    static final char[] sveNote = new char[]{'C','D','E','F','G','A','B'};

    static ArrayList<Kromosom> kromosoms = new ArrayList<>();
    public static void main(String[] args) {

        for(int i=0;i<POPULACIJA;i++){
            char[] a = new char[FIELD_SIZE];
            popuniRand(a);
            printKromosom(a);
            //kromosoms.add(new Kromosom(FIELD_SIZE,a,calculateFF(a)));
        }

        //Player player = new Player();
        //player.play("C D E F G A B A A A A A");
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


}
