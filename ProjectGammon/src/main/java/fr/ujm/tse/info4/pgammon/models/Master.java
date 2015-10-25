//
//  @ Projet : Project Gammon
//  @ Fichier : Master.java
//  @ Date : 12/12/2012
//  @ Auteurs : DONG Chuan, BONNETTO Benjamin, FRANCON Adrien, POTHELUNE Jean-Michel
//
//

package fr.ujm.tse.info4.pgammon.models;

import java.util.ArrayList;
import java.util.Calendar;
import fr.ujm.tse.info4.pgammon.controleur.ControleurPrincipal;

public class Master
{
	@SuppressWarnings("unused")
	private static Master master;
	private int idSession;
	private ArrayList<Session> listSession;
	private ControleurPrincipal controleurPrincipal;
	
	public Master() {
		Calendar date = Calendar.getInstance();
		idSession = 10000*date.get(Calendar.MONTH)
				+ 1000*date.get(Calendar.DATE)
				+ 100*date.get(Calendar.HOUR)
				+ 10*date.get(Calendar.MINUTE)
				+ date.get(Calendar.SECOND);
		controleurPrincipal = new ControleurPrincipal(this);
		listSession = new ArrayList<Session>();
	}
	
	public static void main(String[] args) {
		master = new Master();
	}
	
	public void launchSession(ParametreJeu parametreJeu){
		if (isSessionLaunchable()){
			listSession.add(new Session(idSession,parametreJeu));
		}
		try {
			GestionDeSession gestion = GestionDeSession.getGestionDeSession();
			gestion.setListSession(listSession);
		} catch (Exception e) {
			
		}
	}
	
	public void addSession(Session session){
		if (isSessionLaunchable()){
			listSession.add(session);
		}	
	}
	
	public void stopSession(int id) {
		if(listSession.size()!=0) {
			for (Session session : listSession) {
				if(session.getIdSession() == id) {
					listSession.remove(session);
					break;
				}
			}	
		}	
	}

	public boolean isSessionLaunchable() {	
		if (listSession.size() != 1 )
			return true;
		return false;
	}
	
	public Session getSession() {
		// a modifier pour le multi THREAD
		return listSession.get(listSession.size()-1);
	}

	public ControleurPrincipal getControleurPrincipal() {
		return controleurPrincipal;
	}

	public void setControleurPrincipal(ControleurPrincipal controleurPrincipal) {
		this.controleurPrincipal = controleurPrincipal;
	}

}