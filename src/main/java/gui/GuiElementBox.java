package gui;

import objects.IMapElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image image;

    private ImageView imageView;

    private IMapElement element;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        this.element = element;
        //obsluz
        this.image = new Image(new FileInputStream(element.getResources()));
        this.imageView = new ImageView(image);
    }
    //Wczytywanie plików, dostosowywanie wymiarów itd.

    public ImageView getImage()  {



        return imageView;

    }

}
