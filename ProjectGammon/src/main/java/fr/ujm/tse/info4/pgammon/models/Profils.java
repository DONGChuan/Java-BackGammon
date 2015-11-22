// 
//
//  @ Projet : Project Gammon
//  @ Fichier : Profils.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//




package fr.ujm.tse.info4.pgammon.models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Profils
{
	public List<Player> joueurs = new ArrayList<Player>();
	private List<Element> listJoueurs;
	private static  Profils profil;
	
	private Profils()
	{
		
	}
	/**
	 * Constructeur singleton
	 * @return profil
	 */
	public static Profils getProfils()
	{
		if(profil == null){
			  profil = new Profils();
			  try {
					profil.charger();
				} catch (JDOMException e) {
					
					//todo message probleme
				}catch (IOException e){
					//todo message probleme
				}
		}   
		return profil; 
	}
	
	/**
	 * Sauvegarder les infos sur joueurs dans le dossier "sauvegarde" par XML
	 */
	public void sauvegarder()
	{
		Element racine = new Element("profils");
		Document document = new Document(racine);
		
		for(int i=0;i<joueurs.size();i++){
			joueurs.get(i).sauvegarder(racine);
		}
	
		try{
			
			File path = new File("sauvegarde");
			if(!path.exists()) 
				path.mkdirs();
			
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("sauvegarde/profils.xml"));
	   
		}catch(Exception e){
			System.out.println("Erreur d'enregistrement");
		}
	}
	/**
	 * Charger les infos sur joueurs par le fichier XML dans le dossier "sauvegarde"
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void charger() throws JDOMException, IOException
	{
		 SAXBuilder builder = new SAXBuilder();
		 //System.out.println((new File(".")).getAbsolutePath());
		 Document document = builder.build("sauvegarde/profils.xml");
		 Element racine = document.getRootElement();
		
		 listJoueurs = racine.getChildren("joueurs");
		 Iterator<Element> it = listJoueurs.iterator();
		 
		 while(it.hasNext()){
			 Player j = new Player();
			 j.charger(it.next());
			 joueurs.add(j);
		 }
		 
		 //Pour charger MAP<Joueur,Integer> dans Stat de chaque joueur
		 Iterator<Element> itStat = listJoueurs.iterator();
		 while(itStat.hasNext()){
			 Element e = itStat.next();//"e" est pour le premier child "joueurs"
			 Iterator<Element> itContre = e.getChild("statistiqueJoueur").getChild("nbrDePartieContreJoueur").getChildren("joueurs").iterator();
			 while(itContre.hasNext()){
				 Element c = itContre.next();//"c" est pour le deuxeme child "joueurs"
				 for(int i=0;i<joueurs.size();i++){
					 if(joueurs.get(i).getId()== Integer.valueOf(e.getAttributeValue("id")))//Chercher lequel joueur qu'on va charger leur MAP.
						 for(int j=0;j<joueurs.size();j++){
							 if(joueurs.get(j).getId()== Integer.valueOf(c.getAttributeValue("id"))){//Chercher lequel joueur est L'Adversaire
								 Player jcontre = joueurs.get(j);
								 joueurs.get(i).getStat().getNbrDePartieContreJoueur().put(jcontre,Integer.valueOf(c.getChildText("nbrPartie")));
							 }
						 }
				 }
			 }
		 }
	}
	/**
	 * Autres fonctions	 
	 */
	public void ajouter(String _pseudo,String _imageSource,NiveauAssistant _niveau){
		
		joueurs.add(new Player(joueurs.size()+1,_pseudo,_imageSource,_niveau));
		
	}
	
	public void modifierPseudo(String _pseudo,Player j){
		
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getId()==j.getId()){
				joueurs.get(i).setPseudo(_pseudo);
			}
		}
	}
	
	public void modifierImageSource(String _imageSource,Player j){
		
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getId()==j.getId()){
				joueurs.get(i).setImageSource(_imageSource);
			}
		}
	}
	
	public void supprimer(Player j)
	{
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getId() == j.getId()){
				joueurs.remove(i);
			}
		}
	}
	
	public void afficher(){
		for(int i=0;i<joueurs.size();i++){
				System.out.println(joueurs.get(i).getId()+" "+joueurs.get(i).getPseudo()+" "+joueurs.get(i).getImageSource()+" "+joueurs.get(i).getNiveauAssistant());
		}
	}
	
	public List<Player> getList(){
		return joueurs;
	}
	
	public Player getJoueur(int id){
		
		for(int i=0;i<joueurs.size();i++){
			if(joueurs.get(i).getId()==id)
				return joueurs.get(i);
		}
		return null;
	}
}
