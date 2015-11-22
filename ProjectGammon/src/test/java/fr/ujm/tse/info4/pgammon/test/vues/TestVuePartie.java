package fr.ujm.tse.info4.pgammon.test.vues;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import fr.ujm.tse.info4.pgammon.controleur.ControleurPartie;
import fr.ujm.tse.info4.pgammon.gui.Avatar;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.ParametreJeu;
import fr.ujm.tse.info4.pgammon.models.Partie;

public class TestVuePartie {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test vue partie");

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(816,638);
		Container panel = frame.getContentPane();
		panel.setLayout(new FlowLayout());
		
		Player jBlanc = new Player(1, "ben", Avatar.CHAT_JAUNE.getPath(),NiveauAssistant.NON_UTILISE);
		Player jNoir = new Player(2, "JM", Avatar.CHEVAL.getPath(), NiveauAssistant.COMPLET);
		
		ParametreJeu param = new ParametreJeu(0, 3, true, jBlanc, jNoir);
		Partie p = new Partie(param);
		p.lancerPremierePartie();
		
		ControleurPartie controleurPartie = new ControleurPartie(p);
		
		frame.setContentPane(controleurPartie.getVuePartie());
		
		
		frame.setVisible(true);
	}

}
