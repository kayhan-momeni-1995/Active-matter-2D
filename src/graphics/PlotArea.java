package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import elements.Particle;
import elements.Wall;
import process.Processor;

public class PlotArea extends JPanel
{

	private static final long serialVersionUID = -5636466379766114421L;

    
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		Render(G);
	}
    DecimalFormat df = new DecimalFormat("#.######");
	public void Render(Graphics G)
	{
		Settings.lblTime.setText("Time: "+df.format(Processor.time));
		G.setColor(Color.white);
		G.fillRect(0, 0, this.getWidth(), this.getHeight());
		G.setColor(Color.BLACK);
		G.drawRect(0, 0, this.getWidth(), this.getHeight());
		
		Wall w = null;
		for (int i = Wall.all.size()-1; i>=0; i--)
		{
			w=Wall.all.get(i);
			w.Render(G);
		}
		
		Particle s = null;
		for (int i = Particle.all.size()-1; i>=0; i--)
		{
			s=Particle.all.get(i);
			s.Render(G);
		}
		
		G.setColor(Color.BLACK);
		G.drawImage(Plotter.img, 0, 0, 64, 64, null);
       // DecimalFormat df = new DecimalFormat("#.####");
		//G.drawString("Time: "+df.format(Processor.time),2, main.Main.L*3+12);
	}
}
