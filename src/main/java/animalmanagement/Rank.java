package animalmanagement;

import objects.Animal;

import java.util.List;
import java.util.Comparator;

public class Rank {






    public Animal getPrioritizedAnimal(List<Animal> animals)
    {

        return getStrongestAnimal(animals);
    }


    public Animal getStrongestAnimal(List<Animal> animals)
    {
        List<Integer> withoutDuplicatesAnimals = animals.stream().mapToInt(Animal::getEnergy).distinct().boxed().toList();
        if (!(withoutDuplicatesAnimals.size() == 1))
        {

            return animals.stream()
                .max(Comparator.comparingInt(Animal::getEnergy))
                .orElse(null);
        }

        return getOldestAnimal(animals);


    }
    public Animal getOldestAnimal(List<Animal> animals)
    {
        List<Integer> withoutDuplicatesAnimals = animals.stream().mapToInt(Animal::getAge).distinct().boxed().toList();
        if (!(withoutDuplicatesAnimals.size() == 1))
        {

            return animals.stream()
                    .max(Comparator.comparingInt(Animal::getAge))
                    .orElse(null);
        }

        return animals.get(0);


    }

//TO-DO dodaj wiecej

}
