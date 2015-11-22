package fr.ujm.tse.info4.pgammon.test.vues;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import fr.ujm.tse.info4.pgammon.gui.Avatar;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.vues.VueNouvelleSession;



public class TestVueNouvelleSession {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			JFrame frame = new JFrame("Test Menu");
			
			

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(816,638);
			Container panel = frame.getContentPane();
			panel.setLayout(new FlowLayout());
			
			Player jBlanc = new Player(1, "ben", Avatar.CHAT_JAUNE.getPath(),NiveauAssistant.NON_UTILISE);
			Player jNoir = new Player(2, "JM", Avatar.CHEVAL.getPath(), NiveauAssistant.COMPLET);
			
			//ParametreJeu param = new ParametreJeu(0, 3, true, jBlanc, jNoir);
			
			VueNouvelleSession nouvellesession = new VueNouvelleSession();
			
			nouvellesession.setJoueur1(jBlanc);
			nouvellesession.setJoueur2(jNoir);
			frame.setContentPane(nouvellesession);
			
			
			frame.setVisible(true);
			
	}

	

}
