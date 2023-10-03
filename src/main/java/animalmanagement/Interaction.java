package animalmanagement;

import core.Config;
import core.Vector2d;
import map.IWorldMap;
import objects.Animal;
import simulation.IDayChangeAction;

import java.util.*;
import java.util.stream.Collectors;

public class Interaction implements IDayChangeAction {
    private Config config;

    private IWorldMap map;

    private DeathSpecter specter;

    private Creator creator;

    public Interaction(Config config,IWorldMap map,DeathSpecter specter,Creator creator )
    {

        this.config = config;
        this.map = map;
        this.specter = specter;
        this.creator = creator;
    }

    public void OnPassingDay()
    {
        eatFoodIfCan();
        createAnimalIfCan();


    }

    public void createAnimalIfCan()
    {
        for(List<Animal> reproductiveAnimals : map.getAnimals().values() )
        {
            List<Animal> copyOfReproductiveAnimals = reproductiveAnimals.stream().filter(creator::canCreateAnimal).collect(Collectors.toList());
         if (copyOfReproductiveAnimals.size() > 1)
         {
             Rank stat = new Rank();
             Animal firstParent = stat.getStrongestAnimal(copyOfReproductiveAnimals);
             copyOfReproductiveAnimals.remove(firstParent);
             Animal secondParent = stat.getStrongestAnimal(copyOfReproductiveAnimals);
             copyOfReproductiveAnimals.remove(secondParent);
             creator.createNewAnimal(firstParent,secondParent,specter);

         }

        }

    }

    public void eatFoodIfCan()
    {

        Rank stat = new Rank();
        Set<Vector2d> copyOfPlantsPosition = new HashSet<>(map.getPlants().keySet());
        for(Vector2d position : copyOfPlantsPosition ){
            if (map.getAnimals().containsKey(position))
            {
                if (map.getAnimals().get(position).size() == 1)
                {
                    map.getAnimals().get(position).get(0).eat(config.getEnergyForFood());
                    map.removePlantFromMap(position);

                }
                else if (map.getAnimals().get(position).size() > 1)
                {
                    Animal fedAnimal = stat.getStrongestAnimal(map.getAnimals().get(position));
                    fedAnimal.eat(config.getEnergyForFood());
                    map.removePlantFromMap(position);


                }

            }
        }


    }




}
