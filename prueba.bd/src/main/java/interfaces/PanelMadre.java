package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelMadre extends  JPanel{
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			BufferedImage fondo = ImageIO.read(new File("./fotoclase.jpg"));
			
			g.drawImage(fondo, 0, 0, this.getWidth(), 
			this.getHeight(), new Color(0,0,0),null );
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}