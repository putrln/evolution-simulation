package map;

import core.Vector2d;

public enum MapDirection {
    NORTH,NORTHEAST,EAST,SOUTHEAST,SOUTH,SOUTHWEST,WEST,NORTHWEST;


    public MapDirection next()
    {
        return switch (this) {
            case NORTH ->  NORTHEAST;
            case NORTHEAST -> EAST;
            case EAST ->   SOUTHEAST;
            case SOUTHEAST -> SOUTH;
            case SOUTH ->  SOUTHWEST;
            case SOUTHWEST -> WEST;
            case WEST ->   NORTHWEST;
            case NORTHWEST -> NORTH;

        };
    }
    public MapDirection previous()
    {
       return switch (this) {
            case NORTH ->  NORTHWEST;
            case NORTHWEST -> WEST;
            case WEST -> SOUTHWEST;
            case SOUTHWEST -> SOUTH;
            case SOUTH ->  SOUTHEAST;
            case SOUTHEAST -> EAST;
            case EAST ->   NORTHEAST;
            case NORTHEAST -> NORTH;


        };

    }

    public MapDirection reverse()
    {
        return switch (this) {
            case NORTH ->  SOUTH;
            case NORTHWEST -> SOUTHEAST;
            case WEST -> EAST;
            case SOUTHWEST -> NORTHEAST;
            case SOUTH ->  NORTH;
            case SOUTHEAST -> NORTHWEST;
            case EAST ->   WEST;
            case NORTHEAST -> SOUTHWEST;
        };
    }

    public Vector2d toUnitVector()
    {
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case NORTHEAST -> new Vector2d(1,1);
            case NORTHWEST -> new Vector2d(-1,1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);

        };


    }

    @Override
    public String toString() {
        return switch (this) {

            case NORTH ->  "Północ";
            case NORTHEAST -> "Północny Wschód";
            case NORTHWEST -> "Północny Zachód";
            case SOUTH ->  "Południe";
            case SOUTHEAST -> "Południowy Wschód";
            case SOUTHWEST -> "Południowy Zachód";
            case EAST ->   "Wschód";
            case WEST ->   "Zachód";

        };

    }
}
