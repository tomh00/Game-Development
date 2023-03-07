package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    Model gameWorld;
    Font font;
    public boolean messageInDisplay;
    public String message = "";
    public boolean completed;
    double timer;
    DecimalFormat decimalFormat = new DecimalFormat( "#0.00" );

    public UI ( Model gameWorld ) {
        this.gameWorld = gameWorld;
        font = new Font( "Arial", Font.PLAIN, 40 );

    }

    public void showMessage ( String text ) {
        message = text;
        messageInDisplay = true;


    }

    public void draw ( Graphics g ) {
        if ( gameWorld.getPlayerlives() <= 0 ) {
            // stop main theme music, play winning sound effect
            gameWorld.stopSound();
            gameWorld.playSoundEffect( 4 );
            String text;
            int textLength;
            int x, y;

            g.setFont( g.getFont().deriveFont( Font.BOLD, 96F ) );
            text = "MISSION FAILED!";
            textLength = ( int ) g.getFontMetrics().getStringBounds( text, g ).getWidth();

            x = gameWorld.getScreenWidth() / 2 - textLength / 2;
            y = gameWorld.getScreenHeight() / 2 - ( gameWorld.getScaledTileSize() * 3 ) ;

            //shadow and main text
            g.setColor( Color.gray );
            g.drawString( text, x+5, y+5 );
            g.setColor( Color.white );
            g.drawString( text, x, y );

            gameWorld.gameThread = null;
        }
        else if ( gameWorld.isGameCompleted() ) {
            // stop main music, play winning sound
            gameWorld.stopSound();
            gameWorld.playSoundEffect( 5 );
            String text;
            int textLength;
            int x, y;

            g.setFont( g.getFont().deriveFont( Font.BOLD, 86F ) );
            text = "COMPLETED IN: " + decimalFormat.format( timer );
            timer += (double) 1 / 100;
            textLength = ( int ) g.getFontMetrics().getStringBounds( text, g ).getWidth();

            x = gameWorld.getScreenWidth() / 2 - textLength / 2;
            y = gameWorld.getScreenHeight() / 2 - ( gameWorld.getScaledTileSize() * 3 ) ;

            g.setColor( Color.gray );
            g.drawString( text, x+5, y+5 );
            g.setColor( Color.white );
            g.drawString( text, x, y );

            gameWorld.gameThread = null;
        } else {
            g.setFont(font);
            g.setColor(Color.white);

            // time
            timer += (double) 1 / 100;
            g.drawString("Time: " + decimalFormat.format(timer), gameWorld.getScaledTileSize() * 11, 65);
            if (messageInDisplay) {
                g.drawString(message, 500, 500);
            }

            // lives
            try {
                BufferedImage heart = ImageIO.read(getClass().getResourceAsStream("/heart.png"));

                for (int i = 0,
                     x = gameWorld.getScaledTileSize() / 2,
                     y = gameWorld.getScaledTileSize();
                     i < gameWorld.getPlayerlives();
                     i++, x += gameWorld.getScaledTileSize() ) {
                    g.drawImage( heart, x, y, gameWorld.getScaledTileSize(), gameWorld.getScaledTileSize(), null );
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}
