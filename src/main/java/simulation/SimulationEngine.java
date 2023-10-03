package simulation;


import animaldata.Energy;
import animaldata.Genome;
import core.Config;
import core.Vector2d;
import gui.App;
import map.IWorldMap;
import objects.Animal;
import animalmanagement.Creator;
import animalmanagement.DeathSpecter;
import animalmanagement.Interaction;
import animalmanagement.Movement;
import java.util.*;

import java.util.ArrayList;

public class SimulationEngine implements  IEngine {

    private Config config;

    private ArrayList<SimulationListener> simulationObservers = new ArrayList<>();


    private IWorldMap map;


    public SimulationEngine(Config config, IWorldMap map, App app)
    {
        this.config = config;
        this.map = map;
    }

    public void run()
    {
        DeathSpecter specter = new DeathSpecter(config);
        generateAnimals(specter);
        TimeManager timeManager = new TimeManager();
        Creator creator = new Creator(config);
        Movement movement = new Movement(map);

        Interaction interaction = new Interaction(config,map,specter,creator);
        timeManager.loadActions(map);
        timeManager.loadActions(movement);
        timeManager.loadActions(interaction);
        timeManager.loadActions(specter);
        while (true) {
            try {
                Thread.sleep(config.getSimulationSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



            timeManager.changeDay();
            try {
                Thread.sleep(config.getSimulationSpeed());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ChangeOnMap();



        }



    }



    public void generateAnimals(DeathSpecter specter)
    {
          Random random = new Random();
          for(int i=0; i<config.getStartNumberOfAnimal();i++)
          {
              specter.PutDeathSpec(
                      new Animal(
                              new Genome(config.getNumOfGenens()),
                              new Energy(config.getStartEnergy()),
                              new Vector2d(random.nextInt(map.getWidth()), random.nextInt(map.getHeight())),
                              map));

          }

       }
    public void addSimulationObservers(SimulationListener listener)
    {
        simulationObservers.add(listener);

    }
    public void removeSimulationObservers(SimulationListener listener)
    {
        simulationObservers.remove(listener);

    }


    public void ChangeOnMap()
    {
        for(SimulationListener listener : simulationObservers)
        {
            listener.updateMap();

        }

    }













    }






