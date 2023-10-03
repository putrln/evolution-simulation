package gui;

import core.Config;
import javafx.scene.layout.BorderPane;
import map.IWorldMap;
import map.PortalMap;
import map.RoundMap;
import simulation.SimulationEngine;
import simulation.SimulationListener;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.stage.Screen;
import javafx.stage.Stage;
import simulation.SimulationStats;


public class App implements SimulationListener {


    private IWorldMap map;
    private SimulationVisualizer visualizer;
    private Stage primaryStage;
    private BorderPane borderPane;
    private final  int SCREEN_WIDTH = 1600;
    private final int SCREEN_HEIGHT = 800;
    private SimulationStats stats;

    public App(Stage primaryStage,Config config) {
        this.stats = new SimulationStats(config.getStartNumberOfAnimal(),config.getStartNumberOfPlants(),config.getMapHeight()*config.getMapHeight() - config.getStartNumberOfPlants());
        IWorldMap map =  switch (config.getTypeOfMap()){

            case ROUNDMAP -> new RoundMap(config,stats);
            case PORTALMAP -> new PortalMap(config,stats);
        };

        this.map = map;
        SimulationEngine engine = new SimulationEngine(config,map,this);
        engine.addSimulationObservers(this);
        Thread engineThread = new Thread(engine);
        engineThread.start();
        visualizer = new SimulationVisualizer(map,SCREEN_WIDTH/map.getWidth(), SCREEN_HEIGHT/map.getHeight());
        borderPane = visualizer.setMap(stats);
        this.primaryStage = primaryStage;

    }


    public void start() throws Exception {
        visualizer.setMap(stats);

        Scene scene = new Scene(borderPane,SCREEN_WIDTH,SCREEN_HEIGHT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        double windowWidth = primaryStage.getWidth();
        double windowHeight = primaryStage.getHeight();
        primaryStage.setX((screenWidth - windowWidth) / 2);
        primaryStage.setY((screenHeight - windowHeight) / 2);





    }
    public void updateMap()
    {
        Platform.runLater(() -> {


            borderPane = visualizer.setMap(stats);

        });



    }


}
