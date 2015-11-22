package fr.ujm.tse.info4.pgammon.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import fr.ujm.tse.info4.pgammon.models.Player;

public class JoueurCellRenderer extends JPanel implements ListCellRenderer<Player> {
	private static final long serialVersionUID = 2419031075848394031L;
	JLabel label;
	ImageAvatar imgjoueur;
	Player joueur;
	public JoueurCellRenderer() {
		label = new JLabel();
		joueur = new Player();
		
		setLayout(null);

		setPreferredSize(new Dimension(100, 45));
		
		imgjoueur = new ImageAvatar(joueur.getImageSource());
		imgjoueur.setBounds(2, 6, 30, 30);
		//imgjoueur.setPreferredSize(new Dimension(30,30));
		add(imgjoueur);
		
		//label.setAlignmentX(CENTER_ALIGNMENT);
		//label.setAlignmentY(CENTER_ALIGNMENT);
		label.setBounds(40, 0, 250, 45);
		label.setFont(new Font("Arial",Font.BOLD,18));
		//label.setForeground(new Color(0xCCCCCC));
		add(label);
		
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list,
			Player j, int index, boolean isSelected, boolean cellHasFocus) {
		
		label.setForeground(new Color(0xCCCCCC));
		Color bgColor;
		if(isSelected)
			if(cellHasFocus)
				bgColor = new Color(0x333333);
			else
				bgColor = new Color(0x252525);
		else 
			if(cellHasFocus)
				bgColor = new Color(0x202020);
			else
				bgColor = new Color(0x111111);
		setBackground(bgColor);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0x555555)));
		label.setText(j.getPseudo());
		setAlignmentX(0.5f);
		joueur=j;
		imgjoueur.setPath(j.getImageSource());
		return this;
		/*
		Paint p;
		// Arriere plan
		p = new RadialGradientPaint(new Point2D.Double(100 / 2.0,
				100 / 2.0), 
				100,
                new float[] { 0.0f, 0.8f },
                new Color[] { new Color(0x333333), new Color(0x000000) },
                RadialGradientPaint.CycleMethod.NO_CYCLE);
		
		Graphics2D g = (Graphics2D) getGraphics();
		if(g!= null)
			g.fillRect(0, 0, 300, 100);
		CustomPanel panel = new CustomPanel(value.getPseudo());
		return panel;
		 */
	}

}
/*
class CustomPanel extends JPanel{
	Label label;
	
	public CustomPanel(String text) {
		label = new Label(text);

		label.setForeground(new Color(0xFFFFFF));
		build();
	}
	
	private void build(){
		add(label);
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create(); 
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		Paint p;
		int h = getHeight(); 
		int w = getWidth(); 
		
		// Arriere plan
		p = new RadialGradientPaint(new Point2D.Double(getWidth() / 2.0,
                getHeight() / 2.0), 
                getHeight(),
                new float[] { 0.0f, 0.8f },
                new Color[] { new Color(0x333333), new Color(0x000000) },
                RadialGradientPaint.CycleMethod.NO_CYCLE);
		
		g2.setPaint(p); 
		g2.fillRect(0, 0, w, h); 
		
		// Bordure
		p = new Color(0x808080);
		g2.setStroke(new BasicStroke(5.0f) );
		g2.setPaint(p); 
		g2.drawRect(2, 2, w - 5 , h - 5 );
		
		
		g2.dispose(); 
		
	}
}*/