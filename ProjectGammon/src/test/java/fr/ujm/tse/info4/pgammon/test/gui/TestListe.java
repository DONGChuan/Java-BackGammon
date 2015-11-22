package fr.ujm.tse.info4.pgammon.test.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

import fr.ujm.tse.info4.pgammon.gui.Avatar;
import fr.ujm.tse.info4.pgammon.gui.JoueurCellRenderer;
import fr.ujm.tse.info4.pgammon.gui.MonochromeButton;
import fr.ujm.tse.info4.pgammon.gui.MonochromeListe;
import fr.ujm.tse.info4.pgammon.gui.SessionCellRenderer;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.ParametreJeu;
import fr.ujm.tse.info4.pgammon.models.Session;

public class TestListe {

	

	private static MonochromeListe<Player> listeJoueur;

	private static Vector<Player> js;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Test Design");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(816,638);

		Container panel = frame.getContentPane();
		panel.setLayout(null);

		panel.setBackground(Color.BLACK);
		
		
		Player jBlanc = new Player(1, "ben", Avatar.CHAT_JAUNE.getPath(),NiveauAssistant.NON_UTILISE);
		Player jNoir = new Player(2, "JM", Avatar.CHEVAL.getPath(), NiveauAssistant.COMPLET);
		
		ParametreJeu param = new ParametreJeu(0, 3, true, jBlanc, jNoir);
		ArrayList<Session> listSession;
		listSession = new ArrayList<>();
		
		listSession.add(new Session(1,param));
		listSession.add(new Session(2,param));
		
		MonochromeListe<Session> sessions = new MonochromeListe<>("Parties enregistrées",listSession,new SessionCellRenderer());
		sessions.setBounds(40, 50, 330, 450);
		panel.add(sessions);
		
		
		
		js  = new Vector<>();
		
		js.add(new Player(1, "ben", "beauGoss",NiveauAssistant.NON_UTILISE) );
		js.add(new Player(2, "JM", "null", NiveauAssistant.COMPLET));
		listeJoueur = new MonochromeListe<>("Coucou", js, new JoueurCellRenderer());

		listeJoueur.setBounds(100,100,300,400);
		panel.add(listeJoueur);
		
		
		MonochromeButton btn = new MonochromeButton("+");
		btn.setBounds(600,100,150,50);
		panel.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				js.add(new Player(2, "JM", "null", NiveauAssistant.COMPLET));
				listeJoueur.updateList(new JoueurCellRenderer());
			}
		});
		frame.setVisible(true);
	}
}
