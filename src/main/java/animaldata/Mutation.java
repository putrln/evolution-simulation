package animaldata;
import java.util.Random;
public class Mutation {

    private Genome genome;


    public Mutation(Genome genome)
    {
        this.genome = genome;

    }
    //rodzaj mutacji: lekka korekta
    public void mutateGenome()
    {
        Random random = new Random();
        int numOfGensToMutate = random.nextInt(genome.getNumOfGenes());

        for (int i =0; i< numOfGensToMutate; i++)
        {
            int takeGen = random.nextInt(genome.getNumOfGenes());
            int addOrSubtract = random.nextInt(2);
            genome.getBehaviour()[takeGen]  = switch (addOrSubtract)
                    {
                        case 0 -> (genome.getBehaviour()[takeGen] +1 < 8)? genome.getBehaviour()[takeGen]+1 : 0;
                        case 1 -> (genome.getBehaviour()[takeGen] - 1 >=0)? genome.getBehaviour()[takeGen]-1 : 8;
                        default -> throw new IllegalStateException("Unexpected value: " + addOrSubtract);
                    };
        }

    }


}
