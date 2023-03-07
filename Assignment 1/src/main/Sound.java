package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[ 30 ];

    public Sound () {
        soundURL[ 0 ] = getClass().getResource("/sounds/school-bell-1.wav");
        soundURL[ 1 ] = getClass().getResource( "/sounds/theme-tune.wav" );
        soundURL[ 2 ] = getClass().getResource( "/sounds/car-horn.wav" );
        soundURL[ 3 ] = getClass().getResource( "/sounds/red-bull-sound.wav" );
        soundURL[ 4 ] = getClass().getResource( "/sounds/losing-1.wav" );
        soundURL[ 5 ] = getClass().getResource( "/sounds/winning-1.wav" );
        soundURL[ 6 ] = getClass().getResource( "/sounds/car-collision.wav" );

        // duplicate for more sounds
    }

    public void setFile( int tune ) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( soundURL[ tune ] );
            clip = AudioSystem.getClip();
            clip.open( audioInputStream );
        } catch ( Exception e ){

        }
    }

    public void play(){
        clip.start();
    }
    public void loop() {
        clip.loop( clip.LOOP_CONTINUOUSLY );

    }
    public void stop(){
        clip.stop();
    }
}
