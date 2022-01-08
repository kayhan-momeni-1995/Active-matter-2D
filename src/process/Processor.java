package process;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import elements.Cell;
import elements.Particle;
import elements.Wall;
import main.Main;

public class Processor
{
	public static double dT=0.05;
	public static double epsilon=0 ,gp=2, gw=40, R=1, alpha=0.5;
	public static double time=0;
	public static void turnOn()
	{
		Random rand = new Random();
		FileWriter write2 = null;
		try {
			write2 = new FileWriter("PDF.dat");
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter printer2 = new PrintWriter( write2 );
		printer2.println("X,Y");
		boolean pdfFinish=false;
		for (time=0;; time+=dT)
		{
			if (Math.abs(time-700)<dT && time<700)
				PDF(printer2, 2);
			else if (Math.abs(time-720)<dT && time<720)
				PDF(printer2, 2);
			else if (Math.abs(time-740)<dT && time<740)
				PDF(printer2, 2);
			else if (Math.abs(time-760)<dT && time<760)
				PDF(printer2, 2);
			else if (Math.abs(time-780)<dT && time<780)
				PDF(printer2, 2);
			else if (Math.abs(time-800)<dT && time<800)
				PDF(printer2, 2);
			else if (Math.abs(time-820)<dT && time<820)
				PDF(printer2, 2);
			else if (Math.abs(time-840)<dT && time<840)
				PDF(printer2, 2);
			else if (Math.abs(time-860)<dT && time<860)
				PDF(printer2, 2);
			else if (Math.abs(time-880)<dT && time<880)
				PDF(printer2, 2);
			else if (Math.abs(time-900)<dT && time<900)
				PDF(printer2, 2);
			else if (Math.abs(time-920)<dT && time<920)
				PDF(printer2, 2);
			else if (Math.abs(time-940)<dT && time<940)
				PDF(printer2, 2);
			else if (Math.abs(time-960)<dT && time<960)
				PDF(printer2, 2);
			else if (Math.abs(time-980)<dT && time<980)
				PDF(printer2, 2);
			else if (Math.abs(time-1000)<dT && time<1000)
				PDF(printer2, 2);
			else if (time>1001 && !pdfFinish)
			{
				printer2.close();
				pdfFinish=true;
			}
			Cell.Update();
			for (int c1=0; c1<Cell.all.size(); c1++)
			{
				Cell cell1=Cell.all.get(c1);
				for (int c2=0; c2<cell1.neighbors.size(); c2++)
				{
					Cell cell2=cell1.neighbors.get(c2);
					for (int n=0; n<cell1.particles.size(); n++)
					{
						Particle p1 = cell1.particles.get(n);
						for (int i=0; i<cell2.particles.size(); i++)
						{
							Particle p2 = cell2.particles.get(i);
							if (p1!=p2)
							{
								double distance1 = p2.getX() - p1.getX();
								if (Math.abs(distance1) > (Main.L/2) && Main.periodic)
									distance1 = ((p2.getX()+Main.L/2)%Main.L) - ((p1.getX()+Main.L/2)%Main.L);
								double distance2 = p2.getY() - p1.getY();
								if (Math.abs(distance2) > (Main.L/2) && Main.periodic)
									distance2 = ((p2.getY()+Main.L/2)%Main.L) - ((p1.getY()+Main.L/2)%Main.L);
								double distance = Math.sqrt(distance1*distance1 + distance2*distance2);
								if (distance>R)
								{
								}
								
								else
								{
									distance1/=distance;
									distance2/=distance;
									double v1 = Math.cos(p1.getTheta());
									double v2 = Math.sin(p1.getTheta());
									double val=distance1*v2 - distance2*v1;
									double delta= gp* ((1-alpha)*Math.sin((p2.getTheta()-p1.getTheta())) + alpha*val) / Math.PI;									
									p1.thetaDot+=delta;

									v1 = Math.cos(p2.getTheta());
									v2 = Math.sin(p2.getTheta());
									distance1*=-1;
									distance2*=-1;
									val=distance1*v2 - distance2*v1;
									p2.thetaDot+= gp*((1-alpha)*Math.sin((p1.getTheta()-p2.getTheta())) + alpha*val) / Math.PI;
								}
							}
						}
					}
				}
			}
			for (int n=0; n<Particle.all.size(); n++)
			{
				
				Particle p = Particle.all.get(n);
				for (int j=0; j<Wall.all.size(); j++)
				{
					Wall w = Wall.all.get(j);
					double distance = w.getDistanceFrom(p.getX(), p.getY());
					if (distance > R)
					{
					}
					else
					{
						double normal1=w.getNormalX();
						double normal2=w.getNormalY();
						double v1 = Math.cos(p.getTheta());
						double v2 = Math.sin(p.getTheta());
						double delta = gw*(normal1*v2 - normal2*v1)/(distance*Math.PI);
						p.thetaDot+=delta;
					}
				}
				
				p.thetaDot += epsilon*rand.nextGaussian();
				double deltaX=p.getV()*Math.cos(p.getTheta())*dT;
				double deltaY=p.getV()*Math.sin(p.getTheta())*dT;
				p.moveX(deltaX);
				p.moveY(deltaY);
				p.setTheta(p.getTheta()+p.thetaDot*dT);
				p.thetaDot=0;
			}
		}
	}
	public static void PDF(PrintWriter printer, double dr)
	{
		for (int o=0; o<Particle.all.size(); o++)
		{
			Particle origin = Particle.all.get(o);
			double x = origin.getX();
			double y = origin.getY();
			double vx= origin.getV()*Math.cos(origin.getTheta());
			double vy= origin.getV()*Math.sin(origin.getTheta());
			for (int p=o+1; p<Particle.all.size(); p++)
			{
				Particle particle = Particle.all.get(p);
				double distance = Math.sqrt((particle.getX()-x)*(particle.getX()-x) + (particle.getY()-y)*(particle.getY()-y));
				if (distance > dr)
					continue;
			
				double ABx = particle.getX()-origin.getX();
				double ABy = particle.getY()-origin.getY();
				
				double innerProduct = vx*ABx + vy*ABy;
				double ABMag = Math.sqrt(ABx*ABx + ABy*ABy);
				double vMag  = Math.sqrt(vx*vx + vy*vy);
				double cosOfTheta = innerProduct/(ABMag*vMag);
				
				double firstComponentX    = ABMag*cosOfTheta*vx;
				double firstComponentY    = ABMag*cosOfTheta*vy;
				double firstComponentMag  = innerProduct;
				
				double secondComponentX   = ABx-firstComponentX;
				double secondComponentY   = ABy-firstComponentY;
				double secondComponentMag = Math.sqrt(secondComponentX*secondComponentX + secondComponentY*secondComponentY);
	
				if (ABMag>0.1 && (firstComponentMag*firstComponentMag + secondComponentMag*secondComponentMag)<0.01)
					System.out.println(ABx+","+ABy);

				printer.println(secondComponentMag+","+firstComponentMag);
				
			}
		}
	}
}
