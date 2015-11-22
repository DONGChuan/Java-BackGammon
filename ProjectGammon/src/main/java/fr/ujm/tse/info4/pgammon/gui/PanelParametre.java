package fr.ujm.tse.info4.pgammon.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PanelParametre extends MonochromePanel{
	/**
	 * Cette classe affiche les paramètres de partie que l'on pourra choisir
	 */
	
	private static final long serialVersionUID = -4599011779523529733L;
	
	private MonochromeIconButton plus_partie;
	private MonochromeIconButton moins_partie;
	private MonochromeIconButton infinite_partie;
	private MonochromeIconButton plus_temps;
	private MonochromeIconButton moins_temps;
	private MonochromeIconButton infinite_temps;
	
	public static final String imgparties 	= "images/parties.png";
	public static final String imgtime 	= "images/time.png";

	private ImageIcon iconeparties;
	private ImageIcon iconetime;
	
	private MonochromeLabel lab_parties;
	private MonochromeLabel lab_temps;
	
	private JLabel text_parties;
	private JLabel text_temps;
	
	private MonochromeCheckbox videau;
	
	
	private int nbParties;
	private int nbTemps;

	/**
	 * Constructeur de la classe
	 */
	public PanelParametre(){
		super("Parameters");
		
		build();
		
	}
	
	public void build(){
		
		//récupération de l'image
		try{
			iconeparties = new ImageIcon(imgparties);
			iconetime = new ImageIcon(imgtime);
		}catch(Exception err){
			System.err.println(err);
		}
		
		nbParties = 3;
		nbTemps = 30;
		
		text_parties = new JLabel();
		text_parties.setText("Score to achieve");
		text_parties.setForeground(new Color(0xCCCCCC));
		text_parties.setBounds(20, 30, 300, 50);
		add(text_parties);
		
		text_temps = new JLabel();
		text_temps.setText("Time limit per turn");
		text_temps.setForeground(new Color(0xCCCCCC));
		text_temps.setBounds(20, 150, 300, 50);
		add(text_temps);
		
		lab_parties = new MonochromeLabel(new Integer(nbParties).toString());
		lab_parties.setBounds(70, 80, 120, 40);
		add(lab_parties);
		
		lab_temps = new MonochromeLabel(new Integer(nbTemps).toString()+" s");
		lab_temps.setBounds(70, 200, 120, 40);
		add(lab_temps);
		
		
			plus_partie = new MonochromeIconButton(IconMonochromeType.SMALL_PLUS,"MonochromeIconButton","NOIR");
			plus_partie.setSizeSmall();
			plus_partie.setBounds(200, 80, plus_partie.getPreferredSize().width, plus_partie.getPreferredSize().height);
			add(plus_partie);
	
			moins_partie = new MonochromeIconButton(IconMonochromeType.SMALL_MOINS,"MonochromeIconButton","NOIR");
			moins_partie.setSizeSmall();
			moins_partie.setBounds(245, 80, moins_partie.getPreferredSize().width, moins_partie.getPreferredSize().height);
			add(moins_partie);

			infinite_partie = new MonochromeIconButton(IconMonochromeType.SMALL_INFINITE,"MonochromeIconButton","NOIR");
			infinite_partie.setSizeSmall();
			infinite_partie.setBounds(290, 80, infinite_partie.getPreferredSize().width, infinite_partie.getPreferredSize().height);
			add(infinite_partie);
			
			plus_temps = new MonochromeIconButton(IconMonochromeType.SMALL_PLUS,"MonochromeIconButton","NOIR");
			plus_temps.setSizeSmall();
			plus_temps.setBounds(200, 200, plus_temps.getPreferredSize().width, plus_temps.getPreferredSize().height);
			add(plus_temps);
			
			
			moins_temps = new MonochromeIconButton(IconMonochromeType.SMALL_MOINS,"MonochromeIconButton","NOIR");
			moins_temps.setSizeSmall();
			moins_temps.setBounds(245, 200, moins_temps.getPreferredSize().width, moins_temps.getPreferredSize().height);
			add(moins_temps);
			
			
			infinite_temps = new MonochromeIconButton(IconMonochromeType.SMALL_INFINITE,"MonochromeIconButton","NOIR");
			infinite_temps.setSizeSmall();
			infinite_temps.setBounds(290, 200, infinite_temps.getPreferredSize().width, infinite_temps.getPreferredSize().height);
			add(infinite_temps);
			
			videau = new MonochromeCheckbox("Doubling Cube");
			videau.setBounds(100, 270, 150, 50);
			videau.setSelected(true);
			add(videau);
			
			listenerplus_partie();
			listenermoins_partie();
			listenerinfinite_partie();
			listenerplus_Temps();
			listenermoins_Temps();
			listenerinfinite_Temps();
			
			
	}
	
	/**
	 * Méthode permettant d'afficher la valeur du temps en fonction du choix de l'utilisateur
	 * @param i un entier en paramètre qui sera le temps à afficher
	 */
	public void changerValeurNbTemps(int i){
		int j=i/60;		
		if(i == 0){
			lab_temps.setText("\u221E");
		}else if(i>60){
			lab_temps.setText(new Integer(i/60).toString()+"m "+new Integer(i-60*j)+"s");
		}
		else{
			lab_temps.setText(new Integer(i).toString()+" s");
		}
		
	}
	
	private void listenerplus_Temps()
	{
		plus_temps.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				nbTemps = nbTemps+15;
				changerValeurNbTemps(nbTemps);
			}
		});
	}
	
	private void listenermoins_Temps()
	{
		moins_temps.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nbTemps > 15){
					nbTemps = nbTemps - 15;
					changerValeurNbTemps(nbTemps);
				}
			}
		});
	}
	
	private void listenerinfinite_Temps()
	{
		infinite_temps.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				nbTemps = 0;
				changerValeurNbTemps(nbTemps);
			}
		});
	}
	
	/**
	 * permet de changer la valeur du nombre de partie en fonction des choix de l'uilisateur
	 * @param i un entier en paramètre du nombre de partie
	 */
	public void changerValeurNbPartie(int i){
		if(i == 0){
			lab_parties.setText("\u221E");
		}else{
			lab_parties.setText(new Integer(i).toString());
		}
		
		
	}
	
	private void listenerplus_partie()
	{
		plus_partie.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				nbParties ++;
				changerValeurNbPartie(nbParties);
			}
		});
	}
	
	private void listenermoins_partie()
	{
		moins_partie.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nbParties > 1){
					nbParties --;
					changerValeurNbPartie(nbParties);
				}
			}
		});
	}
	
	private void listenerinfinite_partie()
	{
		infinite_partie.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}		
			@Override
			public void mouseExited(MouseEvent e) {}			
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				nbParties = 0;
				changerValeurNbPartie(nbParties);
			}
		});
	}
	
	
	/**
	 * Getter du nombre de partie
	 * @return retourne le nombre de partie selectionné
	 */
	public int getNbParties() {
		return nbParties;
	}

	/**
	 * Getter du nombre de temps
	 * @return retourne le nombre de temps selectionné
	 */
	public int getNbTemps() {
		return nbTemps;
	}

	

	/**
	 * Getter du label de partie
	 * @return retourne le label de partie
	 */
	public MonochromeLabel getLab_parties() {
		return lab_parties;
	}

	
	/**
	 * Getter du label de temps
	 * @return retourne le label de temps
	 */
	public MonochromeLabel getLab_temps() {
		return lab_temps;
	}

	
	/**
	 * Getter du checkbox du videau
	 * @return retourne la valeur de la checkbox
	 */
	public MonochromeCheckbox getVideau() {
		return videau;
	}

	/**
	 * Setter du checkbox du videau
	 * @param videau change la valeur du checkbox
	 */
	public void setVideau(MonochromeCheckbox videau) {
		this.videau = videau;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(iconeparties.getImage(),10,75,this);
		g.drawImage(iconetime.getImage(),10,194,this);
	}

}
