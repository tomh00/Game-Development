package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import util.worldmap.WorldMap;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	// May Use the following (slightly different?) version
	// Can use tiles for a full on game world rather than one screen
	// Reference: https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1
	/*private final int tileSize = 16;
	private final int scale = 3;
	private final int scaledTileSize = tileSize * scale;
	private final int maxScreenColumns = 20;
	private final int maxScreenRows = 20;
	private final int screenHeight = scaledTileSize * maxScreenRows;
	private final int screenWidth = scaledTileSize * maxScreenColumns;*/

	public Viewer( Model gameworld ) {
		this.setPreferredSize( new Dimension(gameworld.getScreenWidth(), gameworld.getScreenHeight() ) );
		this.setBackground(Color.black);
		this.gameworld = gameworld;
	}


	private long CurrentAnimationTime = 0;

	Model gameworld = new Model();

//	public main.Viewer(main.Model World) {
//		this.gameworld=World;
//		// TODO Auto-generated constructor stub
//	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {

		this.repaint();
		// TODO Auto-generated method stub

	}


	public void paintComponent( Graphics g ) {
		super.paintComponent( g );

		// draw the game
		drawWorldMap( gameworld.getWorldMap(), g );
		drawPlayer( g );

		CurrentAnimationTime++; // runs animation time step


		//Draw player Game Object
//		int x = (int) gameworld.getPlayer().getCentre().getX();
//		int y = (int) gameworld.getPlayer().getCentre().getY();
//		int width = (int) gameworld.getPlayer().getWidth();
//		int height = (int) gameworld.getPlayer().getHeight();
//		String texture = gameworld.getPlayer().getTexture();
//
//		//Draw background
//		//drawBackground((int) gameworld.background.getCentre().getX(), (int) gameworld.background.getCentre().getY(),gameworld.background.getWidth(), gameworld.background.getHeight(), gameworld.background.getTexture(), g);
//
//		//Draw player
//		drawPlayer(x, y, width, height, texture, g);
//
//		//Draw Bullets
//		// change back
//		gameworld.getBullets().forEach((temp) ->
//		{
//			drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);
//		});
//
		//Draw Enemies
		gameworld.getRedBulls().forEach( ( temp ) ->
				drawRedBulls( ( int ) temp.getCentre().getX(), ( int ) temp.getCentre().getY(), ( int ) temp.getWidth(),
						( int ) temp.getHeight(), temp.getCurrentImage(), g ));
		gameworld.getCarList().forEach( ( temp ) ->
				drawCars ( g, temp.getCurrentImage(), ( int ) temp.getCentre().getX(), ( int ) temp.getCentre().getY(),
						temp.getWidth(), temp.getHeight() ) );
		gameworld.ui.draw( g );
	}

	private void drawCars( Graphics g, BufferedImage car, int x, int y, int width, int height ) {
		int screenPosX = x - ( int ) gameworld.getPlayer().getCentre().getX() + gameworld.getScreenWidth() / 2;
		int screenPosY = y - ( int ) gameworld.getPlayer().getCentre().getY() + gameworld.getScreenHeight() / 2;
		g.drawImage( car, screenPosX, screenPosY, width, height, null );
	}

	private void drawRedBulls(int x, int y, int width, int height, BufferedImage image, Graphics g) {
		// draw relative to player
		int redBullScreenPosX = x - ( int ) gameworld.getPlayer().getCentre().getX() + gameworld.getScreenWidth() / 2;
		int redBullScreenPosY = y - ( int ) gameworld.getPlayer().getCentre().getY() + gameworld.getScreenHeight() / 2;
		g.drawImage( image, redBullScreenPosX, redBullScreenPosY, gameworld.getScaledTileSize(), gameworld.getScaledTileSize(), null );

	}

	private void drawWorldMap( WorldMap worldMap, Graphics g ) {
		// iterate through worldmap map array and draw the tiles
		for( int row = 0, y = 0; row < gameworld.getMaxWorldRows(); row++, y += gameworld.getScaledTileSize() ) {
			for( int column = 0, x = 0; column < gameworld.getMaxWorldColumns(); column++, x += gameworld.getScaledTileSize() ) {
				int tile = worldMap.getMap()[ row ][ column ];

				// draw tiles relative to player position in the world
				// subtract the player position in the world from the tile position in the world and offset with the player screen position (centre)
				int tileScreenPosX = x - ( int ) gameworld.getPlayer().getCentre().getX() + gameworld.getScreenWidth() / 2;
				int tileScreenPosY = y - ( int ) gameworld.getPlayer().getCentre().getY() + gameworld.getScreenHeight() / 2;

				// only draw tiles that are within the boundary of the screen
				int screenBoundaryLeft = ( int ) gameworld.getPlayer().getCentre().getX() - ( gameworld.getScreenWidth() / 2 );
				int screenBoundaryRight = ( int ) gameworld.getPlayer().getCentre().getX() + ( gameworld.getScreenWidth() / 2 );
				int screenBoundaryUp = ( int ) gameworld.getPlayer().getCentre().getY() - ( gameworld.getScreenHeight() / 2 );
				int screenBoundaryDown = ( int ) gameworld.getPlayer().getCentre().getY() + ( gameworld.getScreenHeight() / 2 );

				if ( x + gameworld.getScaledTileSize() > screenBoundaryLeft && x - gameworld.getScaledTileSize() < screenBoundaryRight &&
					y + gameworld.getScaledTileSize() > screenBoundaryUp && y - gameworld.getScaledTileSize() < screenBoundaryDown ) {

					g.drawImage(worldMap.getTiles()[tile].getTile(),
							tileScreenPosX, tileScreenPosY,
							gameworld.getScaledTileSize(), gameworld.getScaledTileSize(),
							null);

				}
			}
		}
		try {
			drawBuilding ( g,
					ImageIO.read( getClass().getResourceAsStream( "/school/left-side-school.png" ) ),
					ImageIO.read( getClass().getResourceAsStream( "/school/centre-school.png" ) ),
					ImageIO.read( getClass().getResourceAsStream( "/school/right-side-school.png" ) ),
					80 * gameworld.getScaledTileSize(), gameworld.getMaxWorldRows() * gameworld.getScaledTileSize() );

			// draw shop at end
			drawBuilding( g,
					ImageIO.read( getClass().getResourceAsStream( "/school/left-side-school.png" ) ),
					ImageIO.read( getClass().getResourceAsStream( "/school/shop-centre.png" ) ),
					ImageIO.read( getClass().getResourceAsStream( "/school/right-side-school.png" ) ),
					80 * gameworld.getScaledTileSize(), 5 * gameworld.getScaledTileSize() );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	private void drawBuilding ( Graphics g, BufferedImage buildingLeft, BufferedImage buildingCentre, BufferedImage buildingRight, int worldX, int worldY ) {
		int screenPosX = worldX - ( int ) gameworld.getPlayer().getCentre().getX() + gameworld.getScreenWidth() / 2;
		int screenPosY = worldY - ( int ) gameworld.getPlayer().getCentre().getY() + gameworld.getScreenHeight() / 2;

		g.drawImage( buildingLeft, screenPosX - 145, screenPosY, 144, 144, null );
		g.drawImage( buildingCentre, screenPosX, screenPosY, 144, 144, null );
		g.drawImage( buildingRight, screenPosX + 145, screenPosY, 144, 144, null );
	}

	private void drawPlayer( Graphics g ) {
		g.drawImage( gameworld.getPlayer().getCurrentImage(),
				gameworld.getScreenWidth() / 2,
				gameworld.getScreenHeight() / 2,
				gameworld.getScaledTileSize(),
				gameworld.getScaledTileSize(),
				null );
	}

	/*public int getMaxScreenColumns () { return maxScreenColumns; }
	public int getMaxScreenRows () { return maxScreenRows; }
	public int getScaledTileSize () { return scaledTileSize; }
	public int getScreenHeight() { return screenHeight; }
	public int getScreenWidth() { return screenWidth; }*/
}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
