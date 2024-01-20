package es.deusto.ingenieria.sd.auctions.server.services;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.SessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

public class SportAppService {
	
	 private static SportAppService instance;
		
		
		public SportAppService(){}
		 
		 public static SportAppService getInstance()
		 {
		      if (instance == null) {
		          instance = new SportAppService();
		      }
		      return instance;
		 } 
	
	public List<Challenge> getChallenges() {	
		return ChallengeDAO.getInstance().findAll();
	}

	public List<Session> getSessions(User user) {
				
		if(user != null)
		{
			return SessionDAO.getInstance().getSessionsForUser(user);
		}
		return null;
	}
	
	public Challenge makeChallenge(String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport, boolean distanceorTime)
	{
		Challenge challenge = new Challenge();
		challenge.setName(name);
		challenge.setStartDate(startDate);
		challenge.setEndDate(endDate);
		challenge.setTarget(target);
		challenge.setSport(sport);
		challenge.setDistanceorTime(distanceorTime);
		
		ChallengeDAO.getInstance().store(challenge);
		
		return challenge;
		
	}
	
	public  Session makeSession(String title, SportEnum sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration, User user) {
		Session session = new Session();
		session.setTitle(title);
		session.setDistance(distance);
		session.setSport(sport);
		session.setDuration(duration);
		session.setStartDate(startDate);
		session.setStartTime(starTime);
		session.setUser(user);
		
		SessionDAO.getInstance().store(session);
		
		return session;
	}
		
}
