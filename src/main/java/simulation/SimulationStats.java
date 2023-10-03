package simulation;

public class SimulationStats {


    //kontrola statystyk, ściąganie danych do tablicy / csvk

    private int numOfAnimals;

    private int numOfAllPlants;

    private int numOfFreeFields;

    private int averageAnimalEnergy;

    private  int averageAnimalLifespan;

    private int avergeLifeDeadAnimal = 0;

    private int numOfDeadAnimal = 0;



    public SimulationStats(int numOfAnimals, int numOfAllPlants,int numOfFreeFields)
    {
        this.numOfAnimals = numOfAnimals;
        this.numOfAllPlants= numOfAllPlants;
        this.numOfFreeFields = numOfFreeFields;

    }


    public void updateStats(int numOfAnimals,int numOfAllPlants,int numOfFreeFields,int averageAnimalEnergy,int [] avergeLifeDeadAnimalStats)
    {
        this.numOfAnimals = numOfAnimals;
        this.numOfAllPlants = numOfAllPlants;
        this.numOfFreeFields = numOfFreeFields;
        this.averageAnimalEnergy = averageAnimalEnergy;
        this.avergeLifeDeadAnimal +=avergeLifeDeadAnimalStats[0];
        this.numOfDeadAnimal += avergeLifeDeadAnimalStats[1];

        this.averageAnimalLifespan = ( numOfDeadAnimal > 0) ? (avergeLifeDeadAnimal)/(numOfDeadAnimal): 0  ;







    }

    public int getNumOfAnimals() {
        return numOfAnimals;
    }

    public int getNumOfAllPlants() {
        return numOfAllPlants;
    }

    public int getNumOfFreeFields() {
        return numOfFreeFields;
    }

    public int getAverageAnimalEnergy() {
        return averageAnimalEnergy;
    }

    public int getAverageAnimalLifespan() {
        return averageAnimalLifespan;
    }
}
