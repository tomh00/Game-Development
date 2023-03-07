package main;

import java.awt.*;
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
            String text;
            int textLength;
            int x, y;

            text = "MISSION FAILED!";
            textLength = ( int ) g.getFontMetrics().getStringBounds( text, g ).getWidth();

            x = gameWorld.getScreenWidth() / 2 - textLength / 2;
            y = gameWorld.getScreenHeight() / 2 - ( gameWorld.getScaledTileSize() * 3 ) ;

            g.drawString( text, x, y );
        }
        else if ( gameWorld.isGameCompleted() ) {
            String text;
            int textLength;
            int x, y;

            text = "completed";
            textLength = ( int ) g.getFontMetrics().getStringBounds( text, g ).getWidth();

            x = gameWorld.getScreenWidth() / 2 - textLength / 2;
            y = gameWorld.getScreenHeight() / 2 - ( gameWorld.getScaledTileSize() * 3 ) ;

            g.drawString( text, x, y );
        } else {
            g.setFont(font);
            g.setColor(Color.white);

            // time
            timer += (double) 1 / 100;
            g.drawString("Time: " + decimalFormat.format(timer), gameWorld.getScaledTileSize() * 11, 65);
            if (messageInDisplay) {
                g.drawString(message, 500, 500);
            }
        }
    }
}
