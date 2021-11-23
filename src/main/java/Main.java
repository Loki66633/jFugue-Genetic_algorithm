import org.jfugue.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    static char[]trazeneNote = new char[]{'C','E','E','E','G','A','B'};

    static final int FIELD_SIZE=trazeneNote.length;
    static final int EVOLUCJE=500;
    static final int POPULACIJA=50 ;
    static final int MUTATION_CHANCE=90;
    static final int MAX_AGE=10;


    static final char[] sveNote = new char[]{'C','D','E','F','G','A','B'};
    static Random random = new Random();
    static ArrayList<Kromosom> kromosoms = new ArrayList<>();


    public static void main(String[] args) {

        //Generiraj pocetnu populaciju
        for(int i=0;i<POPULACIJA;i++){
            String a;
            a =popuniRand();
            kromosoms.add(new Kromosom(a,FIELD_SIZE,calculateFF(a)));
        }
        int c=0;
        boolean nemaRjesenja = true;

        while(nemaRjesenja){
            if(c > EVOLUCJE-1){
                nemaRjesenja = false;
                System.out.println("Riješenje nije nađeno!");
                break;
            }
            for (int i=0;i<kromosoms.size();i++){

            }




            kromosoms.sort(new Comp());

            //Ako smo našli rjesenje
            if (kromosoms.get(0).getFf() == FIELD_SIZE) {
                System.out.println("RIJEŠENJE NAĐENO U "+(c+1)+". GENERACIJI");
                printKromosom(kromosoms.get(0).getNote());
                System.out.println("FF:" + kromosoms.get(0).getFf());
                System.exit(0);
            }

            //Makni kromosome koji su pre stari
            for (int i = 0; i < kromosoms.size(); i++) {
                kromosoms.get(i).age++;
                if (kromosoms.get(i).age > MAX_AGE)
                    kromosoms.remove(i);
            }

            //Ako je previse kromosoma -> makni zadnje dok ne dodemo do broja populacije
            while (kromosoms.size() > POPULACIJA) {
                kromosoms.remove(kromosoms.size() - 1);
            }


            c++;
            //System.out.print("FF: " + kromosoms.get(0).getFf() + " ");
            //System.out.print("Populacija: " + kromosoms.size() + " ");
            //System.out.println("Generacija:"+c);

            //Rekombinacija za 50% najboljih jedniki
            for(int i = 1; i < POPULACIJA/2; i = i + 2){
                rekombinacijaUJednojTocki(kromosoms.get(i-1),kromosoms.get(i));
            }
            removeDuplicates();

        }

        kromosoms.sort(new Comp());

        for(int i=0; i<kromosoms.size();i++){
            System.out.print((kromosoms.get(kromosoms.size()-1-i).getFf()) + " ");
            printKromosom(kromosoms.get(kromosoms.size()-1-i).getNote());
        }
        System.out.println("GENERACIJA: "+ c);

        Player player = new Player();
        //player.play("C D E F G A B");
    }




    public static String popuniRand(){
        char[] note = new char[FIELD_SIZE];
        for(int i=0;i<FIELD_SIZE;i++){
            note[i] = sveNote[random.nextInt(7)];
        }
        return new String(note);
    }

    public static void printKromosom(char[]note){
        for(int i=0;i<note.length;i++){
            System.out.print(note[i]);
        }
        System.out.println();

    }

    public static int calculateFF(String str){
        int ff=0;
        char[] note = str.toCharArray();
        for(int i=0;i<trazeneNote.length;i++){
            if(note[i]==trazeneNote[i]){
                ff++;
            }
        }
        return ff;
    }

    public static void rekombinacijaUJednojTocki(Kromosom parent1, Kromosom parent2) {

        Random random=new Random();

        int index = random.nextInt(FIELD_SIZE-2)+1;

        char[] poljeDijete1 = new char[FIELD_SIZE];
        char[] poljeDijete2 = new char[FIELD_SIZE];

        for (int i = 0; i <= index; i++) {
            poljeDijete1[i] = parent1.getNote()[i];
            poljeDijete2[i] = parent2.getNote()[i];
        }
        for (int i = index; i < FIELD_SIZE; i++) {
            poljeDijete1[i] = parent2.getNote()[i];
            poljeDijete2[i] = parent1.getNote()[i];
        }


        String d1 = new String(poljeDijete1);
        String d2 = new String(poljeDijete2);

        if(calculateFF(d1)!=FIELD_SIZE){
            d1 = vrijednosnaMutacija(d1);
            System.out.println("Mutacija");
        }
        if(calculateFF(d2)!=FIELD_SIZE){
            d2 = vrijednosnaMutacija(d2);
            System.out.println("Mutacija");
        }


        Kromosom dijete1 = new Kromosom(d1,FIELD_SIZE,calculateFF(d1));
        Kromosom dijete2 = new Kromosom(d2,FIELD_SIZE,calculateFF(d2));
        kromosoms.add(dijete1);
        kromosoms.add(dijete2);

    }

    public static void removeDuplicates(){
        List<Kromosom> deduped = kromosoms.stream().distinct().collect(Collectors.toList());

        kromosoms.clear();
        kromosoms.addAll(deduped);

    }

    public static String permutacijskaMutacija(String str){
        char[] note = str.toCharArray();
        if(random.nextInt(100)<MUTATION_CHANCE) {

            printKromosom(note);
            int prvi = random.nextInt(FIELD_SIZE - 1);
            int drugi = random.nextInt(FIELD_SIZE - 1);
            while(prvi==drugi){
                drugi=random.nextInt(FIELD_SIZE-1);
            }

            for (int i = 0; i < note.length; i++) {
                char temp = note[prvi];
                note[prvi] = note[drugi];
                note[drugi] = temp;

            }
        }
        printKromosom(note);
        return new String(note);

    }

    public static String vrijednosnaMutacija(String str){
        char[] note = str.toCharArray();
        int rand1 = random.nextInt(FIELD_SIZE-1);
        int rand2 = random.nextInt(FIELD_SIZE-1);
        printKromosom(note);
        while(note[rand1] == sveNote[rand2]){
            rand2 = random.nextInt(FIELD_SIZE-1);
        }
        note[rand1] = sveNote[rand2];
        printKromosom(note);
        return new String(note);

    }



}
