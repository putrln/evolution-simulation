package map;

import core.IPositionChangeObserver;
import core.Vector2d;
import objects.Animal;
import objects.IMapElement;
import objects.Plant;
import simulation.IDayChangeAction;

import java.util.List;
import java.util.Map;

public interface IWorldMap extends IPositionChangeObserver, IDayChangeAction {

    Vector2d newAnimalPosition(Vector2d position,Animal animal);
    void putAnimalOnMap(Animal animal);

    IMapElement objectsAt(Vector2d position);


    void putPlantOnMap(Vector2d position);
    int getHeight();

    int getWidth();
    void removePlantFromMap(Vector2d Position);

    Map<Vector2d, List<Animal>> getAnimals();
    Map<Vector2d, Plant> getPlants();

}
