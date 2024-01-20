package es.deusto.ingenieria.sd.auctions.server.test;

import java.util.Calendar;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.MainProgram;
import es.deusto.ingenieria.sd.auctions.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.SessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

public class LocalDataBaseTest {

	public static void main(String[] args) {		
		//Initialize DB
		MainProgram.initDB();
		
		List<Session> session = null;
		List<Challenge> challenge = null;
		List<User> users = null;
		
		//Retreiving objects from DB
		try {
			System.out.println(" - Retreiving all sessions ...");
			session = SessionDAO.getInstance().findAll();			
			System.out.println("\t- " + session.size() + " sessions retreived!");
			session.forEach(cat -> System.out.println("\t\t- " + cat));
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all sessions: " + ex.getMessage());
		}

		try {
			System.out.println(" - Retreiving all challenge ...");
			challenge = ChallengeDAO.getInstance().findAll();						
			System.out.println("\t- " + challenge.size() + " challenge retreived!");
			challenge.forEach(art -> System.out.println("\t\t- " + art));	
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all challenge: " + ex.getMessage());
		}
			
		try {
			System.out.println(" - Retreiving all users ...");
			users = UserDAO.getInstance().findAll();
			System.out.println("\t- " + users.size() + " users retreived!");
			users.forEach(usr -> System.out.println("\t\t- " + usr));
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving all users: " + ex.getMessage());
		}

		try {
			if (session != null && !session.isEmpty()) {
				String name = session.get(0).getTitle();
				System.out.println(" - Retreiving a category by named '" + name + "' ...");
				Session category = SessionDAO.getInstance().find(name);
				
				if (category != null) {
					System.out.println("\t- Session '" + name + "' has " + category.toString());		
				}
			}
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving a Session by name: " + ex.getMessage());
		}
			
		try {
			if (challenge != null && !challenge.isEmpty()) {
				String name = challenge.get(0).getName();
				System.out.println(" - Retreiving a challenge by named '" + name + "' ...");
				Session category = SessionDAO.getInstance().find(name);
				
				if (category != null) {
					System.out.println("\t- challenge '" + name + "' has " + category.toString());		
				}
			}
		} catch (Exception ex) {
			System.out.println("\t$ Retreiving a challenge by name: " + ex.getMessage());
		}

			
		if (users != null && !users.isEmpty()) {
			System.out.println(" - Retreiving an user by email '" + users.get(0).getEmail() + "' ...");
			User user = UserDAO.getInstance().find(String.valueOf(users.get(0).getEmail()));
			
			if (user != null) {
				System.out.println("\t- " + user);
			}				
		}
		
		
		
		try {
			//Clean DB
			session = SessionDAO.getInstance().findAll();
			System.out.println(" - Deleting all sessions ...");
			SessionDAO.getInstance().delete(session.get(0));		
			
			System.out.println(" - Retreiving all session ...");
			session = SessionDAO.getInstance().findAll();
			System.out.println("\t- " + session.size() + " session retreived!");
			
			challenge = ChallengeDAO.getInstance().findAll();
			System.out.println(" - Deleting all challenge ...");
			ChallengeDAO.getInstance().delete(challenge.get(0));		
			
			System.out.println(" - Retreiving all challenge ...");
			challenge = ChallengeDAO.getInstance().findAll();
			System.out.println("\t- " + challenge.size() + " challenge retreived!");
			
			users = UserDAO.getInstance().findAll();
			System.out.println(" - Deleting all users ...");
			//UserDAO.getInstance().delete(users.get(0));
			users.forEach(usr -> UserDAO.getInstance().delete(usr));			
			
			System.out.println(" - Retreiving all users ...");
			users = UserDAO.getInstance().findAll();
			System.out.println("\t- " + users.size() + " users retreived!");
			session = SessionDAO.getInstance().findAll();
			System.out.println("\t- " + session.size() + " session retreived!");
			
		} catch (Exception ex) {
			System.out.println("\t$ Testing DAO: " + ex.getMessage());
		}
	}
}