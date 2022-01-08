package elements;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Main;

public class Particle
{
	public double thetaDot=0;
	private double x, y, v, theta;
	public static ArrayList<Particle> all = new ArrayList<Particle>();
	public Particle(double x, double y, double v, double theta)
	{
		this.x=x;
		this.y=y;
		this.v=v;
		this.theta=theta;
		all.add(this);
	}
	public void moveX(double dX)
	{
		this.x+=dX;
		this.x+=Main.L;
		this.x=this.x%Main.L;
	}
	public void moveY(double dY)
	{
		this.y+=dY;
		this.y+=Main.L;
		this.y=this.y%Main.L;
	}
	public void setV(double v)
	{
		this.v=v;
	}
	public void setTheta(double theta)
	{
		this.theta=theta;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getV()
	{
		return v;
	}

	public double getTheta()
	{
		return theta;
	}
	public void Render(Graphics G)
	{
        int col = Color.HSBtoRGB((float) (this.getTheta()/(2*Math.PI)), 0.5f, 1); 
        Color color = new Color(col);
        G.setColor(color);
		G.fillOval((int)(x*((double)graphics.Plotter.SIZE/main.Main.L))-2,(int)((main.Main.L-y)*((double)graphics.Plotter.SIZE/main.Main.L))-2, 4, 4);

        col = Color.HSBtoRGB((float) (this.getTheta()/(2*Math.PI)), 1, 1); 
        color = new Color(col);
        G.setColor(color);
		G.drawOval((int)(x*((double)graphics.Plotter.SIZE/main.Main.L))-2,(int)((main.Main.L-y)*((double)graphics.Plotter.SIZE/main.Main.L))-2, 4, 4);
	}
}
