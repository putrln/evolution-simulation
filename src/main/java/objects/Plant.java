package objects;

import core.Vector2d;
import gui.GuiElementBox;


public class Plant implements IMapElement  {

    private Vector2d position;
    private GuiElementBox toDisplay;

    public Plant(Vector2d position)  {
            this.position = position;
            toDisplay = loadImg();


    }
    public Vector2d getPosition()
    {
        return this.position;

    }

    @Override
    public String getResources() {
        return "src/main/resources/plant.png";
    }

    public GuiElementBox getToDisplay() {
        return toDisplay;
    }
}
