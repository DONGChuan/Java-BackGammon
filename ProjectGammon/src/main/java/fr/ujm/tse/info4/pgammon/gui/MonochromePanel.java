package fr.ujm.tse.info4.pgammon.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonochromePanel extends JPanel {
	
	private final int TITLE_HEIGHT = 30;
	
	private static final long serialVersionUID = 1L;
	private String _title = "";
	private JLabel title_lbl;
	
	public MonochromePanel(String title) {
		super();
		_title = title;
		build();

	}



	public MonochromePanel() {
		super();
		build();
	}
	
	private void build() {
		title_lbl = new JLabel(_title);
		title_lbl.setFont(new Font("Arial", Font.BOLD, 22));
		title_lbl.setForeground(new Color(0xB3B3B3));
		title_lbl.setAlignmentX(0);
		add(title_lbl);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		int h = getHeight(); 
		int w = getWidth(); 
		
		

		Paint p;
		
		p = new RadialGradientPaint(new Point2D.Double(getWidth() / 2.0,
                getHeight() / 2.0), 
                getHeight(),
                new float[] { 0.0f, 0.8f },
                new Color[] { new Color(0x333333), new Color(0x000000) },
                RadialGradientPaint.CycleMethod.NO_CYCLE);
		
		g2.setPaint(p); 
		
		
		Rectangle2D.Float r2d = new Rectangle2D.Float(0, TITLE_HEIGHT, w - 1, h - 1-TITLE_HEIGHT); 
		
		Shape clip = g2.getClip(); 
		g2.clip(r2d); 
		g2.fillRect(0, TITLE_HEIGHT, w, h-TITLE_HEIGHT); 
		
		// Bordure
		g2.setClip(clip); 
		p = new Color(0x808080);
		g2.setStroke(new BasicStroke(5.0f) );
		g2.setPaint(p); 
		g2.drawRect(2, TITLE_HEIGHT, w - 5 , h - 5 - TITLE_HEIGHT);
		

		//super.paintComponent(g2); 
		g2.dispose(); 
		
	}
	
	
}
