package util.worldmap;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;

public class WorldMap {
    private Tile[] map;

    public WorldMap(){
        try {
            Tile roadTile = new Tile(ImageIO.read(getClass().getResourceAsStream("/Road-1.png.png")));
            Tile grassTile = new Tile (ImageIO.read(getClass().getResourceAsStream("/grass.png")));
            map = new Tile[2];
            map[0] = roadTile;
            map[1] = grassTile;
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public Tile[] getMap() {
        return map;
    }
}
