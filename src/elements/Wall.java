package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Wall
{

	private double x1, y1, x2, y2, normalX, normalY;
	public static ArrayList<Wall> all = new ArrayList<Wall>();
	public Wall(double x1, double y1, double x2, double y2)
	{
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
		
		normalX = y2-y1;
		normalY = x1-x2;
		double mag = Math.sqrt(normalX*normalX + normalY*normalY);
		normalX/=mag;
		normalY/=mag;
		all.add(this);
	}
	public double getX1()
	{
		return x1;
	}
	public double getX2()
	{
		return x2;
	}
	public double getY1()
	{
		return y1;
	}
	public double getY2()
	{
		return y2;
	}
	public double getNormalX()
	{
		return normalX;
	}
	public double getNormalY()
	{
		return normalY;
	}
	public double getDistanceFrom (double x, double y)
	{
		if (x1==x2)
		{
			return Math.abs(x1-x);
		}
		else if (y1==y2)
		{
			return Math.abs(y1-y);
		}
		else
		{
			/*
			double m =self.getX2()/self.getX1();
			double b = start.getX2()-m*start.getX1();
			double tmp = Math.abs(m*point.getX1()+point.getX2()-b);
			return tmp/Math.sqrt(m*m+1);
			*/
		}
		return 0;
	}
	
	public void Render(Graphics G)
	{
		G.setColor(Color.DARK_GRAY);
		G.drawLine((int) (x1*((double)graphics.Plotter.SIZE/main.Main.L)), (int)(y1*((double)graphics.Plotter.SIZE/main.Main.L)),
					(int)(x2*((double)graphics.Plotter.SIZE/main.Main.L)), (int)(y2*((double)graphics.Plotter.SIZE/main.Main.L)));
	}

}
