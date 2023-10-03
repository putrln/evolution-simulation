package animalmanagement;


import animaldata.Energy;
import animaldata.Genome;
import core.Config;
import objects.Animal;

public class Creator {


    private Config config;


    public Creator(Config config)
    {
        this.config = config;
    }

    public Boolean canCreateAnimal(Animal animal)
    {
        return animal.getEnergy() >= config.getEnergyReqToCreateAnimal();
    }

    public Animal createNewAnimal(Animal animal1, Animal animal2,DeathSpecter specter)
    {
        Genome newAnimalGenom = new Genome(animal1,animal2,config.getNumOfGenens());
        Energy energy = new Energy(config.getStartEnergy());
        animal1.setAnimalEnergy(animal1.getEnergy() - config.getEnergyReqToCreateAnimal());
        animal2.setAnimalEnergy(animal1.getEnergy() - config.getEnergyReqToCreateAnimal());
        Animal bornAnimal = new Animal(newAnimalGenom,energy,animal1.getPosition(),animal1.getMap());
        specter.PutDeathSpec(bornAnimal);
        return bornAnimal;

    }





}
