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
    private int[][] map;
    private int columns, rows;

    public WorldMap( Model gameworld ){
        // create list of tiles
        try {
            Tile roadTile = new Tile( ImageIO.read( getClass().getResourceAsStream( "/background/road.png" ) ) );
            Tile grassTile = new Tile ( ImageIO.read( getClass().getResourceAsStream( "/background/grass.png" ) ) );
            Tile pathTile = new Tile ( ImageIO.read( getClass().getResourceAsStream( "/background/path.png" ) ) );
            Tile roadLineTile = new Tile ( ImageIO.read( getClass().getResourceAsStream( "/background/road-line.png" ) ) );
            tiles = new Tile[4];
            tiles[ 0 ] = grassTile;
            tiles[ 1 ] = pathTile;
            tiles[ 2 ] = roadTile;
            tiles[ 3 ] = roadLineTile;
        } catch (IOException e ) {
            e.printStackTrace();
        }
        columns = gameworld.getMaxWorldColumns();
        rows = gameworld.getMaxWorldRows();
        map = new int[ rows ][ columns ];

        getMapInput( gameworld );
    }

    private void getMapInput( Model gameworld ) {
        // Initialise the map array
        try {
            InputStream inputStream = getClass().getResourceAsStream("/background/world-map.txt" );
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );

            for( int i = 0; i < gameworld.getMaxWorldRows(); i++ ) {
                String currentLine = bufferedReader.readLine();
                for ( int j = 0; j < gameworld.getMaxWorldColumns(); j++ ) {
                    String [] mapInputs = currentLine.split( " " ); // map inputs from current line in an array
                    int currentTile = Integer.parseInt( mapInputs[j] );

                    // place current tile number into the map array
                    map[ i ][ j ] = currentTile;
                }
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public int[][] getMap() { return map; }
    public int getColumns() { return columns; }
    public int getRows() { return rows; }
    public Tile[] getTiles() { return tiles; }
}
