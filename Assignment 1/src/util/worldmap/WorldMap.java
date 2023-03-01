package util.worldmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import main.Viewer;

/*
* Class to layout the full map for the game
* Map will be made from an array of Tile objects as defined by the Tile class
 */

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

    public void drawMap (Viewer viewer, Graphics g ) {
        int x = 0, y = 0;
        for( int i = 0; i < viewer.getMaxScreenColumns(); i++ ) {
            for( int j = 0; j < viewer.getMaxScreenRows(); j++ ) {
                g.drawImage( map[1].getTile(),
                        x, y, null);
                y += viewer.getScaledTileSize();
            }
            y = 0;
            x += viewer.getScaledTileSize();
        }
    }

    public Tile[] getMap() {
        return map;
    }
}
