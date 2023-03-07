package util.worldmap;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage tile;
    private boolean isSlowTileRank1;
    private boolean isSlowTileRank2;


    public Tile ( BufferedImage tile, boolean isObstruction, boolean isSlowTileRank1 ) {
        this.tile = tile;
        this.isSlowTileRank2 = isObstruction;
        this.isSlowTileRank1 = isSlowTileRank1;
    }

    public BufferedImage getTile () { return tile; }

    public boolean isSlowTileRank2() { return isSlowTileRank2; }
    public void setIsObsruction( boolean obsruction ) { isSlowTileRank2 = obsruction;}

    public boolean isSlowTileRank1() { return isSlowTileRank1; }
}
