package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import process.Processor;
import javax.swing.JSeparator;

public class Settings extends JPanel
{
	private static final long serialVersionUID = 1253960844168626902L;

	public static JLabel lblTime = null;
	public Settings ()
	{
		double L = Plotter.SIZE;
		setLayout(null);
		setBounds((int)L/2-8, (int)L/2-8, (int)L/2-12, (int)L/2-12);
			
		JLabel lblNewLabel = new JLabel("Epsilon(ε):");
		lblNewLabel.setBounds(6, 23, 69, 16);
		add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(120, 17, 79, 28);
		add(spinner);
		SpinnerNumberModel model1 = new SpinnerNumberModel(Processor.epsilon, 0, 10, 0.01);  
		spinner.setModel(model1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 45, 374, 12);
		add(separator);
		
		JLabel lblAlpha = new JLabel("Alpha(α):");
		lblAlpha.setBounds(6, 63, 61, 16);
		add(lblAlpha);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(120, 57, 79, 28);
		add(spinner_1);
		SpinnerNumberModel model2 = new SpinnerNumberModel(Processor.alpha, 0, 1, 0.001);  
		spinner_1.setModel(model2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(6, 83, 374, 12);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(6, 6, 374, 12);
		add(separator_2);
		
		JLabel lblGp = new JLabel("gp:");
		lblGp.setBounds(6, 98, 61, 16);
		add(lblGp);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(120, 92, 79, 28);
		add(spinner_2);
		SpinnerNumberModel model3 = new SpinnerNumberModel(Processor.gp, 0, 100, 0.1);  
		spinner_2.setModel(model3);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 118, 374, 12);
		add(separator_3);
		
		JLabel lblGw = new JLabel("gw:");
		lblGw.setBounds(6, 134, 61, 16);
		add(lblGw);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(120, 128, 79, 28);
		add(spinner_3);
		SpinnerNumberModel model4 = new SpinnerNumberModel(Processor.gw, 0, 100, 0.1);  
		spinner_3.setModel(model4);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(6, 155, 374, 12);
		add(separator_4);
		
		JLabel lblDt = new JLabel("dT(×10^-3):");
		lblDt.setBounds(6, 174, 81, 16);
		add(lblDt);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(120, 168, 79, 28);
		add(spinner_4);
		SpinnerNumberModel model5 = new SpinnerNumberModel(Processor.dT*1000, 0.001, 50, 0.001);  
		spinner_4.setModel(model5);
		
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(6, 197, 374, 12);
		add(separator_5);
		
		JLabel lblDat2 = new JLabel("L: "+main.Main.L);
		lblDat2.setBounds(6, 216, 80, 16);
		add(lblDat2);
		
		JLabel lblDat1 = new JLabel("N: "+main.Main.N);
		lblDat1.setBounds(120, 216, 80, 16);
		add(lblDat1);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(6, 233, 374, 12);
		add(separator_6);
		
		lblTime = new JLabel("Time: "+Processor.time);
		lblTime.setBounds(6, 252, 150, 16);
		add(lblTime);
		
	    DecimalFormat df = new DecimalFormat("#.######");
		JLabel lblDat3 = new JLabel("ρ: "+df.format((double)main.Main.N/Math.pow(main.Main.L, 2)));
		lblDat3.setBounds(120, 252, 80, 16);
		add(lblDat3);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(6, 269, 374, 12);
		add(separator_7);
		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Processor.epsilon = ((Number)(spinner.getValue())).doubleValue();
				Processor.alpha   = ((Number)(spinner_1.getValue())).doubleValue();
				Processor.gp      = ((Number)(spinner_2.getValue())).doubleValue();
				Processor.gw      = ((Number)(spinner_3.getValue())).doubleValue();
				Processor.dT      = ((Number)(spinner_4.getValue())).doubleValue()*0.001;
			}
		});
		btnNewButton.setBounds(1, 280, 200, Plotter.SIZE-280);
		add(btnNewButton);
		
	}
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		Render(G);
	}
	public void Render(Graphics G)
	{
		G.setColor(Color.white);
		G.fillRect(0, 0, this.getWidth(), 280);
		G.setColor(Color.LIGHT_GRAY);
		G.fillRect(0, 280, this.getWidth(), this.getHeight()-285);
		G.setColor(Color.black);
		G.drawRect(0, 0, this.getWidth(), this.getHeight());

	}
}
