package animalmanagement;

import core.Config;
import objects.Animal;
import simulation.IDayChangeAction;

import java.util.ArrayList;

public class DeathSpecter implements IDayChangeAction {

    private ArrayList<Animal> animalsDestinedToDeath = new ArrayList<Animal>();
    private Config config;



    public DeathSpecter(Config config)
    {
        this.config = config;
    }

    @Override
    public void OnPassingDay() {

        checkForDeath();



    }


    private void checkForDeath()
    {
        takeEnergy();
        ArrayList <Animal> copyAnimalsDestinedToDeath = new ArrayList<>(animalsDestinedToDeath);
        for(Animal animal : copyAnimalsDestinedToDeath)
        {
            if (animal.getEnergy() <=0)
            {
                animal.setAlive(false);
                animalsDestinedToDeath.remove(animal);

            }
        }
    }

    public void PutDeathSpec(Animal animal)
    {
        animalsDestinedToDeath.add(animal);

    }

    private void takeEnergy()
    {
        for(Animal animal : animalsDestinedToDeath)
        {

            animal.setAnimalEnergy(animal.getEnergy() - config.getEnergyLossEachDay());


        }



    }

}
