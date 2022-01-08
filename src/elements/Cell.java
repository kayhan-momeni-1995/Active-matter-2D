package elements;

import java.util.ArrayList;

import main.Main;

public class Cell
{
	private double x, y, dx, dy;
	public static double Length=5;
	public Cell right=null, left=null, up=null, down=null;
	public ArrayList<Cell> neighbors = new ArrayList<Cell>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
	public static ArrayList<Cell> all = new ArrayList<Cell>();
	public Cell(double x, double y, double dx, double dy)
	{
		this.x=x;
		this.y=y;
		this.dx=dx;
		this.dy=dy;
		all.add(this);
	}
	public static Cell getCellOf(double x, double y)
	{		
		if (x<0)
			x+=0.1;
		if (y<0)
			y+=0.1;
		if (x>Main.L)
			x-=0.1;
		if (y>Main.L)
			y-=0.1;
		for (int n=0; n<all.size(); n++)
		{
			Cell e = Cell.all.get(n);
			if (x>=e.x && y>=e.y && x<e.x+e.dx && y<e.y+e.dy)
			{
				return e;
			}
		}
		System.out.println("("+x+", "+y+")");
		return null;
	}
	public static void Update ()
	{
		for (int n=0; n<Cell.all.size(); n++)
		{
			Cell c = Cell.all.get(n);
			c.particles.clear();
		}
		for (int n=0; n<Particle.all.size(); n++)
		{
			Particle p = Particle.all.get(n);
			Cell c = Cell.getCellOf(p.getX(), p.getY());
			if (c!=null)
				c.particles.add(p);
			else
				System.out.println("error here");
		}
	}
	public static void MakeNetwork (double L, double r)
	{
		all.clear();
		int a = (int)((L/r));
		if (a!=(L/r))
			a++;
		for (int row=0; row<a; row++)
		{
			for (int colomn=0; colomn<a; colomn++)
			{
				new Cell(colomn*r, row*r, r, r);
			}
		}
		for (int i=0; i<Cell.all.size(); i++)
		{
			Cell c = Cell.all.get(i);
			
			Cell up=null;
			if (i-a >= 0)
				up=Cell.all.get(i-a);
			else if (Main.periodic)
				up=Cell.all.get(i+(a*(a-1)));
			c.up=up;
			
			Cell down=null;
			if (i+a < Cell.all.size())
				down=Cell.all.get(i+a);
			else if (Main.periodic)
				down=Cell.all.get(i-(a*(a-1)));
			c.down=down;
			
			Cell right=null;
			if (i%a != a-1)
				right=Cell.all.get(i+1);
			else if (Main.periodic)
				right=Cell.all.get(i-(a-1));
			c.right=right;
			
			Cell left=null;
			if (i%a != 0)
				left=Cell.all.get(i-1);
			else if (Main.periodic)
				left=Cell.all.get(i+(a-1));
			c.left=left;
		}
		for (int n=0; n<Cell.all.size(); n++)
		{
			Cell c = Cell.all.get(n);
			c.neighbors.add(c);
			if (c.down!=null)
				c.neighbors.add(c.down);
			if (c.right!=null)
				c.neighbors.add(c.right);
			if (c.right!=null && c.right.down!=null)
				c.neighbors.add(c.right.down);
			if (c.right!=null && c.right.up!=null)
				c.neighbors.add(c.right.up);
		}
	}
}
