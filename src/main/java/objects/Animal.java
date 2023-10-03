package objects;

import animaldata.Energy;
import animaldata.Genome;
import core.IPositionChangeObserver;
import core.Vector2d;
import gui.GuiElementBox;
import map.IWorldMap;
import map.MapDirection;


import java.util.ArrayList;
import java.util.Objects;

public class Animal implements IMapElement {

    private Genome genome;
    private Energy energy;

    private int age=0;

    private Boolean isAlive;
    private MapDirection orientation = MapDirection.NORTH;

    private GuiElementBox toDisplay;

    private Vector2d position;

    private IWorldMap map;




    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    //zwierze wygenerowane
    public Animal(Genome genome,Energy energy,IWorldMap map)
    {

        this.genome = genome;
        this.energy = energy;
        this.isAlive = true;
        this.map = map;
        this.toDisplay = loadImg();
        setRandomPosition();


    }
    //zwierzak powstały
    public Animal(Genome genome,Energy energy, Vector2d position,IWorldMap map)
    {
        this.genome = genome;
        this.energy = energy;
        this.position = position;
        this.isAlive = true;
        this.map = map;
        this.toDisplay = loadImg();



        addPositionObserver(this.map);
        this.map.putAnimalOnMap(this);

    }

    public int getEnergy() {
        return energy.getEnergy();
    }

    public Genome getGenome() {
        return genome;
    }

    private void setRandomPosition()
    {
        //losowa pozycja i kierunek

    }

    public void move(int direction)
    {
        Vector2d newPosition = new Vector2d(position.getX(), position.getY());

        for(int i=0;i<direction;i++)
        {
            this.orientation = this.orientation.next();
        }
        newPosition = this.position.add(this.orientation.toUnitVector());

        newPosition = this.map.newAnimalPosition(newPosition,this);
        positionChanged(position,newPosition);
        this.position = newPosition;
        this.age+=1;
    }

    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }


    public void setAnimalEnergy(int newEnergy) {
        energy.setEnergy(newEnergy);
    }

    public Boolean isAt(Vector2d position) {
        return Objects.equals(this.position, position);

    }

    public void addPositionObserver(IPositionChangeObserver observer){observers.add(observer);}
    private void removePositionObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }
    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() { return position; }

    public IWorldMap getMap() {
        return map;
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition)
    {

        for(IPositionChangeObserver observer: observers)
        {
            observer.positionChanged(oldPosition,newPosition,this);
        }
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public Boolean getAlive() {
        return isAlive;
    }


    public int getAge() {
        return age;
    }

    public void eat(int energyGaiend)
    {

        setAnimalEnergy(energy.getEnergy() + energyGaiend);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return Objects.equals(genome, animal.genome) && Objects.equals(energy, animal.energy) && Objects.equals(isAlive, animal.isAlive) && orientation == animal.orientation && Objects.equals(toDisplay, animal.toDisplay) && Objects.equals(position, animal.position) && Objects.equals(map, animal.map) && Objects.equals(observers, animal.observers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genome, energy, isAlive, orientation, toDisplay, position, map, observers);
    }

    @Override
    public String getResources() {
        return "src/main/resources/animal.png";
    }

    public GuiElementBox getToDisplay() {
        return toDisplay;
    }
}
