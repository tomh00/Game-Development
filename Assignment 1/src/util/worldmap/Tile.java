package util.worldmap;

import util.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    private BufferedImage tile;
    private boolean isObstruction;

    public Tile ( BufferedImage tile, boolean isObstruction ) {
        this.tile = tile;
        this.isObstruction = isObstruction;
    }

    public BufferedImage getTile () { return tile; }

    public boolean isObstruction() { return isObstruction; }
    public void setIsObsruction( boolean obsruction ) { isObstruction = obsruction;}
}
