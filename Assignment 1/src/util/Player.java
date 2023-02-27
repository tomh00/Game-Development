package util;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Player extends GameObject{
    public Player(){
        try {
            forward1 = ImageIO.read(getClass().getResourceAsStream("Main Character/back-facing-dude-1.png"));
            forward2 = ImageIO.read(getClass().getResourceAsStream("Main Character/back-facing-dude-2.png"));
            backward1 = ImageIO.read(getClass().getResourceAsStream("Main Character/front-facing-dude-1.png"));
            backward2 = ImageIO.read(getClass().getResourceAsStream("Main Character/front-facing-dude-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("Main Character/side-facing-dude-1.1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("Main Character/side-facing-dude-2.1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("Main Character/side-facing-dude-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("Main Character/side-facing-dude-2.png"));
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
