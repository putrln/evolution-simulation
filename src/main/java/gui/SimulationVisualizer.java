package gui;

import core.Vector2d;
import javafx.scene.layout.*;
import map.IWorldMap;
import objects.IMapElement;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import simulation.SimulationStats;

public class SimulationVisualizer {



    private IWorldMap map;
    private GridPane gridPane;

    private BorderPane borderPane;
    //private GridPane gridPane;
    public SimulationVisualizer(IWorldMap map,int widthSize, int heightSize)
    {
        this.map = map;

        this.borderPane = new BorderPane();
        this.gridPane = new GridPane();
        setSizeOfSingleCell(map.getWidth(),map.getHeight(),widthSize,heightSize);
        gridPane.setGridLinesVisible(true);

        gridPane.setStyle("-fx-background-color: #CD853F;");


    }


    public BorderPane setMap(SimulationStats stats)
    {
        gridPane.requestLayout();
        gridPane.getChildren().retainAll(gridPane.getChildren().get(0));

        int mapHeight = map.getHeight();
        int mapWidth = map.getWidth();

        for(int i=0; i<mapHeight; i++)
        {
            IMapElement object = map.objectsAt(new Vector2d(0,i));
            if (object != null)
            {

                Label label = new Label("");
                label.setGraphic(object.getToDisplay().getImage());
                gridPane.add(label,0,i );
                GridPane.setHalignment(label, HPos.CENTER);
            }
            else {
                gridPane.add(new Label(),0,i);

            }

            for(int j=1; j<mapWidth;j++)
            {
                 object = map.objectsAt(new Vector2d(j,i));
                if (object != null)
                {

                    Label label = new Label("");
                    label.setGraphic(object.getToDisplay().getImage());
                    gridPane.add(label,j,i );
                    GridPane.setHalignment(label, HPos.CENTER);

                }
                else{
                    gridPane.add(new Label(),j,i );
                }




            }
        }
        Label lab = new Label("Statystyki");
        Label lab1 = new Label("liczby wszystkich zwierzat " + stats.getNumOfAnimals());
        Label lab2 = new Label("liczby wszystkich roslin " + stats.getNumOfAllPlants());
        Label lab3 = new Label("liczby wolnych po l" + stats.getNumOfFreeFields());
        Label lab4 = new Label("sredniego poziomu energii dla zyjacych zwierzat " + stats.getAverageAnimalEnergy());
        Label lab5 = new Label("średniej dlugosci zycia zwierzat dla martwych zwierzat " + stats.getAverageAnimalLifespan());

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(lab, lab1, lab2, lab3, lab4, lab5);
        borderPane.setCenter(vbox);
        borderPane.setRight(gridPane);

        return borderPane;


    }

    private void setSizeOfSingleCell(int mapWidth, int mapHeight,int widthSize,int heightSize)
    {

        for(int i=0; i < mapWidth;i++)
        {
            ColumnConstraints column = new ColumnConstraints(widthSize/2);
            gridPane.getColumnConstraints().add(column);
        }

        for(int i=0; i < mapHeight;i++)
        {
            RowConstraints row = new RowConstraints(heightSize);

            gridPane.getRowConstraints().add(row);
        }

    }



}
