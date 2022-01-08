package main;

import elements.Cell;
import elements.Particle;
import elements.Wall;
import graphics.Plotter;
import process.Processor;

public class Main
{

	public static int L=60;
	public static int N=3600;
	public static boolean periodic = false;

	public static void main(String[] args)
	{
		if (!periodic)
		{
			new Wall (0, 0, L, 0);
			new Wall (L, 0, L, L);
			new Wall (L, L, 0, L);
			new Wall (0, L, 0, 0);
		}

		for (int i=0; i<N; i++)
		{
			new Particle (Math.random()*(L-2)+1, Math.random()*(L-2)+1, 1, Math.random()*2*Math.PI);
		}
		
		
		new Plotter();	
		Cell.MakeNetwork(L, Cell.Length);
		Processor.turnOn();				
	}

}
