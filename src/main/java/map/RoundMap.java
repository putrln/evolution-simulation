package map;

import core.Config;
import core.Vector2d;
import objects.Animal;
import simulation.SimulationStats;

public class RoundMap extends AbstractWorldMap {


    public RoundMap(Config config, SimulationStats stats) {
        super( config,stats);
    }

    @Override
    public Vector2d newAnimalPosition(Vector2d position, Animal animal) {


        if (position.getY() < 0)
        {
            animal.setOrientation(animal.getOrientation().reverse());
            position =  new Vector2d(position.getX(),0); // -1 0
        }

        else if(position.getY()  >= height)
        {
            animal.setOrientation(animal.getOrientation().reverse());
            position =  new Vector2d(position.getX(),width-1);
        }

         if(position.getX() >= width)
        {
            position =  new Vector2d(0,position.getY());

        }

        else if(position.getX() < 0  )
        {
            position=  new Vector2d(width-1, position.getY() );
        }
        return  position;


    }


}
