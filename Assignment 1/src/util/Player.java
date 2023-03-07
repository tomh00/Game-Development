package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends GameObject{
    private boolean boosted = false;
    // images for animating a game object
    public BufferedImage forward1, forward2, backward1, backward2, left1, left2, right1, right2;
    private int spritePosition = 0;
    private int frameNum = 0;

    public Player(int width, int height, Point3f centre, int speed, Rectangle rectangle){
        try {
            forward1 = ImageIO.read(getClass().getResourceAsStream("/Main Character/back-facing-dude-1.png"));
            forward2 = ImageIO.read(getClass().getResourceAsStream("/Main Character/back-facing-dude-2.png"));
            backward1 = ImageIO.read(getClass().getResourceAsStream("/Main Character/front-facing-dude-1.png.png"));
            backward2 = ImageIO.read(getClass().getResourceAsStream("/Main Character/front-facing-dude-2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Main Character/side-facing-dude-1.1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Main Character/side-facing-dude-2.1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Main Character/side-facing-dude-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Main Character/side-facing-dude-2.png"));
        } catch (IOException e){
            System.out.println(e);
        }
        this.setCurrentImage(backward1);
        this.setWidth(width);
        this.setHeight(height);
        this.setCentre(centre);
        this.setDefaultSpeed(speed);
        setCurrentSpeed( this.getDefaultSpeed() );
        this.setCollisionArea( rectangle );
    }

    public void animateSprite(){
        if( frameNum > 15 ){
            if( spritePosition == 0 ) {
                spritePosition = 1;
            }
            else {
                spritePosition = 0;
            }
            frameNum = 0;
        }
        frameNum++;
    }

    public void setBoosted( boolean boosted ) { this.boosted = boosted; }
    public boolean isBoosted() { return boosted; }
    public int getSpritePosition() { return spritePosition; }
    public void setCurrentImage(BufferedImage currentImage) {
        this.currentImage = currentImage;
    }
}
