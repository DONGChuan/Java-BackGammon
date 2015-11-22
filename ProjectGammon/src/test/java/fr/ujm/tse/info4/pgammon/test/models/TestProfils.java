package fr.ujm.tse.info4.pgammon.test.models;

import fr.ujm.tse.info4.pgammon.models.Player;
import fr.ujm.tse.info4.pgammon.models.NiveauAssistant;
import fr.ujm.tse.info4.pgammon.models.Profils;

public class TestProfils {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		System.out.println("Lancement du test de la Partie");
		
		Profils tmp = Profils.getProfils();
				
		tmp.ajouter("DONG Chuan","Image DONG",NiveauAssistant.NON_UTILISE);
		tmp.ajouter("BONNETTO Benjamin","Image Ben",NiveauAssistant.COMPLET);
		tmp.ajouter("FRANCON Adrien","Image Adrien",NiveauAssistant.COMPLET);
		tmp.ajouter("POTHELUNE Jean-Michel","Image JM",NiveauAssistant.SIMPLE);
		tmp.afficher();
		
		System.out.println("Supprimer");
		tmp.supprimer(tmp.getList().get(0));
		tmp.afficher();
		
		System.out.println("Modifier Image Source");
		tmp.modifierImageSource("XXXXXX", tmp.getList().get(0));
		tmp.afficher();
		
		System.out.println("Modifier Pseudo");
		tmp.modifierPseudo("JOJO",tmp.getList().get(0));
		tmp.afficher();
	
		
		/**
		 * 
		 * Tester StatustiqueJoueur
		 * 
		 */
		
		Player A1 = tmp.getList().get(0);//JOJO
		Player A2 = tmp.getList().get(1);//Adrien
		Player A3 = tmp.getList().get(2);//JM
		// 3 fois avec A2, 2 fois avec A3
		tmp.getList().get(0).getStat().ajouterAdversaire(A2);
		tmp.getList().get(0).getStat().ajouterAdversaire(A2);
		tmp.getList().get(0).getStat().ajouterAdversaire(A2);
		tmp.getList().get(0).getStat().ajouterAdversaire(A3);
		tmp.getList().get(0).getStat().ajouterAdversaire(A3);
		//Gagner 2 times,Perdre 3 times
		tmp.getList().get(0).getStat().ajouterVictoire();
		tmp.getList().get(0).getStat().ajouterVictoire();
		tmp.getList().get(0).getStat().ajouterDefaite();
		tmp.getList().get(0).getStat().ajouterDefaite();
		tmp.getList().get(0).getStat().ajouterDefaite();
		//Affichier les resultats
		System.out.println("NbVictoire "+tmp.getList().get(0).getStat().getNbVictoires());
		System.out.println("NbDefaites "+tmp.getList().get(0).getStat().getNbDefaites());
		System.out.println("PartiesJouees "+tmp.getList().get(0).getStat().getPartiesJouees());
		System.out.println("Pourcentage "+tmp.getList().get(0).getStat().getPourcentageVictoire());
		System.out.println("EnnemiFavoirs "+tmp.getList().get(0).getStat().getEnnemiFavoris());
		
		tmp.sauvegarder();
		
		
		
	}
}
