package mapmanagement;

import core.Vector2d;
import map.IWorldMap;

import java.util.*;

public class PlantGenerator {


    private int height;
    private int width;

    private IWorldMap map;


    protected Map<Vector2d, Integer> toGrowPlantList = new HashMap<>();

    public PlantGenerator(int width, int height,IWorldMap map)
    {
        this.width = width;
        this.height = height;
        this.map = map;
    }


    public void generatePlantsOnStart(int numOfPlants)
    {

        List<Vector2d> Combinations = new ArrayList<>();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                Combinations.add(new Vector2d(y, x));
                toGrowPlantList.put(new Vector2d(y,x), 0);
            }
        }
        Collections.shuffle(Combinations);
        for (int i = 0; i < numOfPlants; i++) {
            map.putPlantOnMap(Combinations.get(i));


        }

    }

    public void generateToxicCorpes(int growPlant)
    {
        List<Vector2d> preferredField = new ArrayList<>();
        List<Vector2d> unprefferedField = new ArrayList<>();
        LinkedHashMap<Vector2d,Integer> sortedGrowPlantList = new LinkedHashMap<Vector2d,Integer>();
        ArrayList<Integer> listToHelpToSortMap = new ArrayList<>();
        for(Map.Entry<Vector2d,Integer> entry : toGrowPlantList.entrySet())
        {
            listToHelpToSortMap.add(entry.getValue());
        }

        Collections.sort(listToHelpToSortMap);

        for(int num : listToHelpToSortMap)
        {
            for (Map.Entry<Vector2d,Integer> entry : toGrowPlantList.entrySet())
            {
                if(entry.getValue().equals(num))
                {
                    sortedGrowPlantList.put(entry.getKey(),num);
                }
            }

        }
        int numOfPrefferedField = (int)(0.20 * listToHelpToSortMap.size());
        int i =0;
        for(Vector2d position : sortedGrowPlantList.keySet())
        {
            if(!map.getPlants().containsKey(position))
            {
                if (i<=numOfPrefferedField)
                {
                    preferredField.add(position);
                    i+=1;

                }
                else{
                    unprefferedField.add(position);
                }

            }


        }


        if(i == 0) {

        }
        else if (unprefferedField.size() == 0)
        {
            //sprawdzam czy preffered ma wystarczającą ilość
            //jesli ma losuje jesli nie biore wszystkie
            drawWhenOneArrayIsEmpty(preferredField,growPlant);

        }

        else {
            Random rand = new Random();

            //petle w ktorej za kazdym razem losuje procentowo
            //jesli sie okaze ze w ktoryms nie ma to losuje z unpreffered albo jesli nie ma w unpreffered to losuje z preffered
            for (int j = 0; j < growPlant; j++) {
                int randomNum = rand.nextInt(10);
                if(unprefferedField.size() > 0 && preferredField.size() > 0) {
                    if (randomNum <= 1) {

                        //napraw
                        //positive wywalilo nie ma itemu
                        int elementOfArray = rand.nextInt(unprefferedField.size());
                        map.putPlantOnMap(unprefferedField.get(elementOfArray));

                        unprefferedField.remove(elementOfArray);

                    } else {

                        int elementOfArray = rand.nextInt(preferredField.size());
                        map.putPlantOnMap(preferredField.get(elementOfArray));
                        preferredField.remove(elementOfArray);
                    }
               }
                else if (preferredField.size() > 0) {
                    drawWhenOneArrayIsEmpty(preferredField,growPlant - j);
                    break;

                }
                else{
                    drawWhenOneArrayIsEmpty(unprefferedField, growPlant - j);
                    break;
                }

            }
        }

        }

    public void markAsDeathField(Vector2d position)
    {

        toGrowPlantList.put(position,toGrowPlantList.get(position) + 1);




    }

    private void drawWhenOneArrayIsEmpty(List<Vector2d> notEmptyList,int numOfDraws)
    {

        if (numOfDraws > notEmptyList.size())
        {
            for(Vector2d position: notEmptyList)
                map.putPlantOnMap(position);
        }
        else {
            int i = 0;
            while (i < numOfDraws)
            {
                Random rand = new Random();
                int drawedPosition = rand.nextInt(notEmptyList.size());
                map.putPlantOnMap(notEmptyList.get(drawedPosition));
                notEmptyList.remove(drawedPosition);
                i+=1;
            }
        }




    }







}
