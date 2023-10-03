

package animaldata;
import objects.Animal;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.stream.IntStream;

public class Genome {
    //DODAJ NOWA KLASE MUTATE
    private final int numOfGenes;
    //tablica z ruchami
    private int[] behaviour;
    //wskaznik do obecnej pozycji
    private int pointerToCurrentGen = 0;

    //generuje nowe zwierzęta (np na starcie )
    public Genome(int numOfGenes)
    {
         this.numOfGenes = numOfGenes;
         this.behaviour = new Random()
                .ints(numOfGenes, 0, 6)
                .toArray();

    }

    //tworze nowy genom z rodzicow (zwierze 1, zwierze 2, dlugosc genomu)
    public Genome(Animal parent1, Animal parent2,int numOfGenes) {
        this.numOfGenes = numOfGenes;
        this.behaviour = new int[numOfGenes];
        createNewGenom(parent1,parent2);
    }

    public int getNumOfGenes() {
        return numOfGenes;
    }

    private void createNewGenom(Animal parent1, Animal parent2)
    {
        Random random = new Random();
        int sumOfEnergy = parent1.getEnergy() + parent2.getEnergy();
        double proportionParent1 =  (double) parent1.getEnergy() / sumOfEnergy;
        proportionParent1 *=100;
        int sideToStart = random.nextInt(2);

        int[] pointers = cutGenome(sideToStart,Math.max((int) Math.round(proportionParent1),100- (int)Math.round(proportionParent1)));

        Animal geneDonor = (proportionParent1 >= 50) ? parent1 : parent2;
        for(int i=pointers[0];i<pointers[1];i++)
            behaviour[i] = geneDonor.getGenome().getBehaviour()[i];

        pointers = (pointers[0] == 0) ? new int[]{pointers[1],numOfGenes} : new int[]{0,pointers[0]};

        geneDonor = (proportionParent1 < 50) ? parent1 : parent2;
        for(int i=pointers[0];i<pointers[1];i++)
            behaviour[i] = geneDonor.getGenome().getBehaviour()[i];

        //Mutacja genomu
        Mutation mutate = new Mutation(this);
        mutate.mutateGenome();


    }


    private int[]cutGenome(int sideToStart,int proportion)
    {
        return switch (sideToStart) {
            case 0 -> new int[]{0,(int) Math.round((numOfGenes * ((double)proportion/100)))};
            case 1 -> new int[]{numOfGenes - (int) Math.round((numOfGenes * ((double)proportion/100))) , numOfGenes};
            default -> throw new IllegalStateException("Unexpected value: " + sideToStart);
        };

    }

    public int []getBehaviour() {
        return behaviour;
    }

    public int getPointerToCurrentGen() {
        int prevPointer = pointerToCurrentGen;
        changePointer();
        return prevPointer;
    }

    //w 80% przypadkow biore nastepny gen w 20% losuje nowy
    public void changePointer()
    {
        Random random = new Random();
        int n = random.nextInt(10);
        if (n < 8)
            pointerToCurrentGen = (pointerToCurrentGen + 1 < numOfGenes) ? pointerToCurrentGen + 1 : 0;
        else
        {
            ArrayList<Integer> possibleBehaviour = new ArrayList<>();
//            Arrays.stream(behaviour).filter(x -> x != pointerToCurrentGen).forEach(possibleBehaviour::add);
            IntStream.rangeClosed(1, behaviour.length-1)
                    .filter(x -> x!= pointerToCurrentGen).forEach(possibleBehaviour::add);
            Collections.shuffle(possibleBehaviour);
            pointerToCurrentGen = possibleBehaviour.get(0);
        }

    }





}
