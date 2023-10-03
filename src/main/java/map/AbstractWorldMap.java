package map;

import core.Config;
import core.Vector2d;
import objects.Animal;
import objects.Plant;
import  objects.IMapElement;
import mapmanagement.PlantGenerator;
import simulation.SimulationStats;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap{
    protected final int width;
    protected final int height;

    private SimulationStats stats;

    private int startNumOfPlants;

    private int growPlantEachDay;

    protected Config config;

    private PlantGenerator plantGenerator;
    protected Map<Vector2d, List<Animal>> animals = new HashMap<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();


    public AbstractWorldMap(Config config,SimulationStats stats) {
        this.width = config.getMapWidth();
        this.height = config.getMapHeight();
        this.startNumOfPlants = config.getStartNumberOfPlants();
        this.growPlantEachDay = config.getNumberOfPlantsGrowingPerDay();
        this.config = config;
        this.stats = stats;
        this.plantGenerator = new PlantGenerator(width,height,this);
        plantGenerator.generatePlantsOnStart(startNumOfPlants);



    }


    public  void OnPassingDay()
    {
        int avergeLifeDeadAnimal[] = cleanMap();
        int numOfAnimals = 0;
        int sumOfEnergy = 0;
        for(List<Animal> animalsOnMap : animals.values()) {
            numOfAnimals += animalsOnMap.size();
            for(Animal animal : animalsOnMap)
            {
                sumOfEnergy += animal.getEnergy();
            }


        }
        stats.updateStats(numOfAnimals,plants.size(),width*height-plants.size(),(numOfAnimals > 0) ? sumOfEnergy/numOfAnimals : 0,avergeLifeDeadAnimal);


    }
    public int[] cleanMap()
    {
        int averageDeadAnimalLifespan = 0;
        int numOfDeadAnimal = 0;
        HashMap<Vector2d,List<Animal>> copyAnimals = new HashMap<>(animals);

        for(Vector2d animalsPosition: copyAnimals.keySet())
        {
            List<Animal> animalsOnPosition = new ArrayList<>(animals.get(animalsPosition));

            for(Animal animal: animalsOnPosition)
            {
                if (!animal.getAlive()) {
                    //dodaj hash i eq
                    averageDeadAnimalLifespan+=animal.getAge();
                    numOfDeadAnimal+=1;
                    plantGenerator.markAsDeathField(animal.getPosition());
                    animals.get(animalsPosition).remove(animal);


                }


            }


        }

        plantGenerator.generateToxicCorpes(growPlantEachDay);
        return new int[]{averageDeadAnimalLifespan,numOfDeadAnimal};




    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition,Animal animal) {

        List<Animal> animalSamePositions = animals.get(oldPosition);
        animalSamePositions.remove(animal);;
        List<Animal> animalToAddNewAnimal = animals.get(newPosition);
        if(animalToAddNewAnimal == null)
        {
            animalToAddNewAnimal = new ArrayList<Animal>();
            animals.put(newPosition,animalToAddNewAnimal);

        }
        animalToAddNewAnimal.add(animal);
    }



    @Override
    public Map<Vector2d,List<Animal>> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }

    public Map<Vector2d,Plant> getPlants() {
        return Collections.unmodifiableMap(plants);
    }

    public void putAnimalOnMap(Animal animal)
    {
        List<Animal> onSamePosition = animals.get(animal.getPosition());
        if (onSamePosition == null)
        {
            onSamePosition = new ArrayList<Animal>();
            animals.put(animal.getPosition(),onSamePosition);
        }
        onSamePosition.add(animal);

    }
    public void removePlantFromMap(Vector2d position)
    {
        plants.remove(position);

    }

    public IMapElement objectsAt(Vector2d position) {
        //DO ZMIANY

        if (animals.get(position) != null && animals.get(position).size() > 0)
        {
            return animals.get(position).get(0);
        }
        if(plants.get(position) != null)
        {

            return plants.get(position);
        }


        return null;



    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void putPlantOnMap(Vector2d position)
    {

        if (position != null)
            plants.put(position,new Plant(position));


    }
}
