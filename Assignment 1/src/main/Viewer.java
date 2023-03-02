package main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
	}
//
//		//Draw Bullets
//		// change back
//		gameworld.getBullets().forEach((temp) ->
//		{
//			drawBullet((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);
//		});
//
//		//Draw Enemies
//		gameworld.getEnemies().forEach((temp) ->
//		{
//			drawEnemies((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), (int) temp.getWidth(), (int) temp.getHeight(), temp.getTexture(),g);
//
//	    });
//	}
//
//	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
//		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
//		try {
//			Image myImage = ImageIO.read(TextureToLoad);
//			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time
//			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31
//			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4 )*32); //slows down animation so every 10 frames we get another frame so every 100ms
//			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
	private void drawWorldMap( WorldMap worldMap, Graphics g ) {
		// iterate through worldmap map array and display each tile
		for( int row = 0, y = 0; row < worldMap.getRows(); row++, y += gameworld.getScaledTileSize() ) {
			for( int column = 0, x = 0; column < worldMap.getColumns(); column++, x += gameworld.getScaledTileSize() ) {
				int tile = worldMap.getMap()[ row ][ column ];
				g.drawImage( worldMap.getTiles()[tile].getTile(),
						x,y,
						gameworld.getScaledTileSize(), gameworld.getScaledTileSize(),
						null );
			}
		}
	}
//
////	private void drawBackground(Graphics g)
////	{
////		File TextureToLoad = new File("res/Game background.jpg");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
////		try {
////			Image myImage = ImageIO.read(TextureToLoad);
////			 g.drawImage(myImage, 0,0, 2500, 3500, 0 , 0, 1000, 1000, null);
////
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//
//	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g)
//	{
//		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
//		try {
//			Image myImage = ImageIO.read(TextureToLoad);
//			//64 by 128
//			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 63, 127, null);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//
	private void drawPlayer( Graphics g ) {
		g.drawImage( gameworld.getPlayer().getCurrentImage(),
				(int)gameworld.getPlayer().getCentre().getX(),
				(int)gameworld.getPlayer().getCentre().getY(),
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