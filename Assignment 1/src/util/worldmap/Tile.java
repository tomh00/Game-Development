package util.worldmap;

import util.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    private BufferedImage tile;
    private boolean collision;

    public Tile (BufferedImage tile ) {
        this.tile = tile;
    }

    public BufferedImage getTile () { return tile; }

}
