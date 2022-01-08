package graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Plotter extends JFrame {

	/**
	 * 
	 */
	public static int SIZE = 0;
	private static final long serialVersionUID = -7185258066666349819L;
	private JPanel contentPane;
	public static PlotArea panel = null;
	public static Image img=null;

	@SuppressWarnings("deprecation")
	public Plotter()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = 600;
		double height = 600;
		SIZE = (int) Math.min(width, height) - 30;

	    try
	    {                
	        img = ImageIO.read(new File("colorRange.png"));
	    }
	    catch (IOException ex)
	    {
	    }
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, SIZE+210, SIZE+30);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new PlotArea();
		panel.setBounds(6, 6, SIZE, SIZE);
		contentPane.add(panel);
		
		Settings settings = new Settings();
		settings.setBounds(SIZE+5, 5, 200, SIZE);
		contentPane.add(settings);
		
		show();
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(true)
				{
					panel.repaint();
				}
			}
			
		});
		t.start();
	}
}
