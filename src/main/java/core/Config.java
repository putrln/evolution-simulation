package core;

public class Config {


    private int energyReqToCreateAnimal;

    private int numOfGenens;

    private int startEnergy;

    private int energyForFood;

    private int startNumberOfAnimal;

    private int energyLossEachDay;

    private int mapWidth;

    private int mapHeight;

    private int startNumberOfPlants;

    private int numberOfPlantsGrowingPerDay;

    private int simulationSpeed;

    private TypeOfMap typeOfMap;

    public Config(int energyReqToCreateAnimal,int startEnergy, int numOfGenens,int energyForFood,int startNumberOfAnimal,int energyLossEachDay,int mapWidth,int mapHeight,int startNumberOfPlants,int numberOfPlantsGrowingPerDay,int simulationSpeed,TypeOfMap typeOfMap)
    {
        this.energyReqToCreateAnimal = energyReqToCreateAnimal;
        this.numOfGenens = numOfGenens;
        this.energyForFood = energyForFood;
        this.startEnergy = startEnergy;
        this.startNumberOfAnimal = startNumberOfAnimal;
        this.energyLossEachDay = energyLossEachDay;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.startNumberOfPlants = startNumberOfPlants;
        this.numberOfPlantsGrowingPerDay = numberOfPlantsGrowingPerDay;
        this.simulationSpeed = simulationSpeed;
        this.typeOfMap = typeOfMap;

    }

    public int getEnergyReqToCreateAnimal() {
        return energyReqToCreateAnimal;
    }

    public int getEnergyLossEachDay() {
        return energyLossEachDay;
    }

    public int getNumOfGenens() {
        return numOfGenens;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getEnergyForFood() {
        return energyForFood;
    }

    public int getStartNumberOfAnimal() {
        return startNumberOfAnimal;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getStartNumberOfPlants() {
        return startNumberOfPlants;
    }

    public int getNumberOfPlantsGrowingPerDay() {
        return numberOfPlantsGrowingPerDay;
    }

    public int getSimulationSpeed() {
        return simulationSpeed;
    }

    public TypeOfMap getTypeOfMap() {
        return typeOfMap;
    }
}
