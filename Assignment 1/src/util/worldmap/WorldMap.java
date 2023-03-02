package util.worldmap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Model;
import main.Viewer;

/*
* Class to layout the full map for the game
* Map will be made from an array of Tile objects as defined by the Tile class
 */

public class WorldMap {
    private Tile[] tiles;
    private Tile[] map;
    private int columns, rows;

    public WorldMap(){
        // create list of tiles
        try {
            Tile roadTile = new Tile(ImageIO.read(getClass().getResourceAsStream("/Road-1.png.png")));
            Tile grassTile = new Tile (ImageIO.read(getClass().getResourceAsStream("/background/grass.png")));
            tiles = new Tile[2];
            tiles[0] = roadTile;
            tiles[1] = grassTile;
        } catch (IOException e ) {
            e.printStackTrace();
        }

        getMapInput();
    }

    private void getMapInput() {
        // Initialise the map array
        // TODO
    }

    public void drawMap ( Model gameworld, Graphics g ) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/background/world-map.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int x = 0, y = 0;
            for ( int i = 0; i < gameworld.getMaxScreenColumns(); i++ ) {
                // get current line of map input
                String currentLine = bufferedReader.readLine();
                for ( int j = 0; j < gameworld.getMaxScreenRows(); j++ ) {
                    String [] mapInputs = currentLine.split( " " ); // map inputs from current line in an array
                    int currentTile = Integer.parseInt( mapInputs[j] );
                    g.drawImage(tiles[currentTile].getTile(),
                            x, y,
                            gameworld.getScaledTileSize(), gameworld.getScaledTileSize(), null);
                    y += gameworld.getScaledTileSize();
                }
                y = 0;
                x += gameworld.getScaledTileSize();
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public Tile[] getMap() {
        return map;
    }
}
