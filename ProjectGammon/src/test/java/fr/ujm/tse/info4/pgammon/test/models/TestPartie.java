package fr.ujm.tse.info4.pgammon.test.models;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ujm.tse.info4.pgammon.models.DeSixFaces;
import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.ParametreJeu;
import fr.ujm.tse.info4.pgammon.models.Partie;
import fr.ujm.tse.info4.pgammon.models.Tablier;

public class TestPartie {

	/**
	 * Dans cette classe on va simuler l'interface graphique en appelant les
	 * méthode partie comme le ferais l'interface.
	 * 
	 * @param args
	 */
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {

		// comment récuperer ce que l'utilisateur saisie.

		System.out.println("Lancement du test de la Partie");

		// creation des parametre et joueur
		Player jBlanc = new Player(1, "ben", "beauGoss",
				NiveauAssistant.NON_UTILISE);
		Player jNoir = new Player(2, "JM", "null", NiveauAssistant.COMPLET);

		ParametreJeu param = new ParametreJeu(0, 3, true, jBlanc, jNoir);
		Partie p = new Partie(param);
		p.lancerPremierePartie();
		// partie lancer
		
		boolean deplacementValide = false;
		int caseDepart;
		int caseArriver;
		do {
			System.out.println("c'est au joueur"+p.getJoueurEnCour());
			do {
				afficherTablier(p.getTablier());
				afficherDe(p.getDeSixFaces());
				do {
					System.out.println("donner la case de départ");
					Scanner sc = new Scanner(System.in);
					caseDepart = sc.nextInt();

					System.out.println("donner la case d'arriver");
					Scanner sc2 = new Scanner(System.in);
					caseArriver = sc.nextInt();
					if (!p.jouerCoup(caseDepart, caseArriver))
						System.out.println("deplacement non valide");
					else
						deplacementValide = true;
				} while (!deplacementValide);
			} while (!p.siDesUtilises());
			
				p.changerTour();
			
			
		} while (!p.isPartieFini());
	}

	public static void afficherTablier(Tablier tab1) {

		System.out.println("TABLIER : ");
		for (int i = 0; i < tab1.getListeCase().size(); i++)
			System.out.println(tab1.getListeCase().get(i).getPosition() + " : "
					+ tab1.getListeCase().get(i).getCouleurDame() + " _ "
					+ tab1.getListeCase().get(i).getNbDame());

		System.out.println("CASE VICTOIRE : ");
		for (int i = 0; i < tab1.getCaseVictoire().size(); i++)
			System.out.println(tab1.getCaseVictoire().get(i).getPosition()
					+ " : " + tab1.getCaseVictoire().get(i).getCouleurDame()
					+ " _ " + tab1.getCaseVictoire().get(i).getNbDame());

		System.out.println("CASE BARRE : ");
		for (int i = 0; i < tab1.getCaseBarre().size(); i++)
			System.out.println(tab1.getCaseBarre().get(i).getPosition() + " : "
					+ tab1.getCaseBarre().get(i).getCouleurDame() + " _ "
					+ tab1.getCaseBarre().get(i).getNbDame());
	}

	public static void afficherDe(ArrayList<DeSixFaces> de) {
		System.out.println("DeSixFace : ");
		for (int i = 0; i < de.size(); i++)
			System.out.println(i + " : " + de.get(i).getValeur() + " "+ de.get(i).isUtilise());
	}

}
