package gui;

import core.Config;
import core.TypeOfMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Menu  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Menu");

        Label startNumberOfAnimalsLabel = new Label("Podaj startowa ilosc zwierzat:");
        TextField startNumberOfAnimalsField = new TextField();
        Label startEnergyLabel = new Label("Podaj poczatkowa energie zwierzat:");
        TextField startEnergyField = new TextField();
        Label numberOfGenesLabel = new Label("Podaj liczbe genow:");
        TextField numberOfGenesField = new TextField();
        Label neededEnergyToCreateAnimalLabel = new Label("Podaj ilosc energii potrzebnej do utworzenia nowego zwierzecia:");
        TextField neededEnergyToCreateAnimalField = new TextField();
        Label energyLostPerDayLabel = new Label("Podaj ile energii dziennie maja tracic zwierzeta:");
        TextField energyLostPerDayField = new TextField();
        Label energyGainPerPlantLabel = new Label("Podaj ile energii beda zyskiwaly zwierzeta po zjedzeniu rosliny:");
        TextField energyGainPerPlantField = new TextField();
        Label heightLabel = new Label("Podaj wysokosc:");
        TextField heightField = new TextField();
        Label widthLabel = new Label("Podaj szerokosc:");
        TextField widthField = new TextField();
        Label startNumberOfPlantsLabel = new Label("Podaj startowa liczbe roslin:");
        TextField startNumberOfPlantsField = new TextField();
        Label numberOfPlantsGrowingPerDayLabel = new Label("Podaj liczbe roslin rosnacych kazdego dnia:");
        TextField numberOfPlantsGrowingPerDayField = new TextField();
        Label simulationSpeedLabel = new Label("Podaj szybkosc symulacji (w ms):");
        TextField simulationSpeedField = new TextField();
        Label typeOfMapLabel = new Label("Wybierz typ mapy: ");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add(TypeOfMap.ROUNDMAP.toString());
        choiceBox.getItems().add(TypeOfMap.PORTALMAP.toString());
        Button submitButton = new Button("Uruchom symulacje");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane.setConstraints(startNumberOfAnimalsLabel, 0, 0);
        GridPane.setConstraints(startNumberOfAnimalsField, 1, 0);
        GridPane.setConstraints(startEnergyLabel, 0, 1);
        GridPane.setConstraints(startEnergyField, 1, 1);
        GridPane.setConstraints(numberOfGenesLabel, 0, 2);
        GridPane.setConstraints(numberOfGenesField, 1, 2);
        GridPane.setConstraints(neededEnergyToCreateAnimalLabel, 0, 3);
        GridPane.setConstraints(neededEnergyToCreateAnimalField, 1, 3);
        GridPane.setConstraints(energyLostPerDayLabel, 0, 4);
        GridPane.setConstraints(energyLostPerDayField, 1, 4);
        GridPane.setConstraints(energyGainPerPlantLabel, 0, 5);
        GridPane.setConstraints(energyGainPerPlantField, 1, 5);
        GridPane.setConstraints(heightLabel, 0, 6);
        GridPane.setConstraints(heightField, 1, 6);
        GridPane.setConstraints(widthLabel, 0, 7);
        GridPane.setConstraints(widthField, 1, 7);
        GridPane.setConstraints(startNumberOfPlantsLabel, 0, 8);
        GridPane.setConstraints(startNumberOfPlantsField, 1, 8);
        GridPane.setConstraints(numberOfPlantsGrowingPerDayLabel, 0, 9);
        GridPane.setConstraints(numberOfPlantsGrowingPerDayField, 1, 9);
        GridPane.setConstraints(simulationSpeedLabel, 0, 10);
        GridPane.setConstraints(simulationSpeedField, 1, 10);
        GridPane.setConstraints(typeOfMapLabel, 0,11);
        GridPane.setConstraints(choiceBox,1,11);
        GridPane.setConstraints(submitButton, 0, 12, 2, 1, HPos.CENTER, VPos.CENTER);


        grid.getChildren().addAll(startNumberOfAnimalsLabel, startNumberOfAnimalsField, startEnergyLabel , startEnergyField,
                numberOfGenesLabel,numberOfGenesField,neededEnergyToCreateAnimalLabel,neededEnergyToCreateAnimalField,
                energyLostPerDayLabel, energyLostPerDayField, energyGainPerPlantLabel, energyGainPerPlantField,
                heightLabel, heightField, widthLabel, widthField, startNumberOfPlantsLabel, startNumberOfPlantsField,
                numberOfPlantsGrowingPerDayLabel, numberOfPlantsGrowingPerDayField, simulationSpeedLabel, simulationSpeedField,typeOfMapLabel,
                choiceBox, submitButton);



        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();


        submitButton.setOnAction(event -> handleButtonClick(event,startNumberOfAnimalsField,startEnergyField,numberOfGenesField,neededEnergyToCreateAnimalField,
               energyLostPerDayField, energyGainPerPlantField,heightField,widthField,startNumberOfPlantsField,numberOfPlantsGrowingPerDayField,simulationSpeedField,choiceBox));
    }

    private void handleButtonClick(ActionEvent event,TextField startNumberOfAnimalsField,TextField startEnergyField,TextField numberOfGenesField,
                                   TextField neededEnergyToCreateAnimalField,TextField energyLostPerDayField,TextField energyGainPerPlantField,TextField heightField,TextField widthField,
                                   TextField startNumberOfPlantsField,TextField numberOfPlantsGrowingPerDayField,TextField simulationSpeedField,ChoiceBox choiceBox)
    {

        String startNumberOfAnimals = startNumberOfAnimalsField.getText();
        String startEnergy = startEnergyField.getText();
        String numberOfGenes = numberOfGenesField.getText();
        String neededEnergyToCreateAnimal = neededEnergyToCreateAnimalField.getText();
        String energyLostPerDay = energyLostPerDayField.getText();
        String energyGainPerPlant = energyGainPerPlantField.getText();
        String height = heightField.getText();
        String width = widthField.getText();
        String startNumberOfPlants = startNumberOfPlantsField.getText();
        String numberOfPlantsGrowingPerDay = numberOfPlantsGrowingPerDayField.getText();
        String simulationSpeed = simulationSpeedField.getText();
        String typeOfMap = choiceBox.getValue().toString();

        TypeOfMap choosedOption = switch (choiceBox.getValue().toString())
        {
            case "ROUNDMAP" -> TypeOfMap.ROUNDMAP;
            case "PORTALMAP" -> TypeOfMap.PORTALMAP;
            default -> TypeOfMap.ROUNDMAP;

        };


            Platform.runLater(() -> {
                Stage newWindow = new Stage();
                newWindow.initModality(Modality.NONE);
                App app = new App(newWindow, new Config(
                        Integer.parseInt(neededEnergyToCreateAnimal),
                        Integer.parseInt(startEnergy),
                        Integer.parseInt(numberOfGenes),
                        Integer.parseInt(energyGainPerPlant),
                        Integer.parseInt(startNumberOfAnimals),
                        Integer.parseInt(energyLostPerDay),
                        Integer.parseInt(width),
                        Integer.parseInt(height),
                        Integer.parseInt(startNumberOfPlants),
                        Integer.parseInt(numberOfPlantsGrowingPerDay),
                        Integer.parseInt(simulationSpeed),
                        choosedOption
                ));
                try {
                    app.start();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });


    }


}
