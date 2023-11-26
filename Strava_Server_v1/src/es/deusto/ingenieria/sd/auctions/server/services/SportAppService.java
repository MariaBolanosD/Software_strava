package es.deusto.ingenieria.sd.auctions.server.services;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

public class SportAppService {

	private List<Challenge> challenges = new ArrayList<>();
	// private Map<String, User> users = new HashMap<String, User>(); 
	
	public SportAppService() {
		this.initilizeData();
	}
	
	@SuppressWarnings("deprecation")
	private void initilizeData() {
		
		//Create Challenges
		Challenge cycling1 = new Challenge();
		cycling1.setName("BiciMes");
		cycling1.setSport(SportEnum.Cycling); 
		cycling1.setStartDate(LocalDate.of(2017, 5, 25 ) );
		cycling1.setEndDate(LocalDate.of(2017, 6, 25 ));
		cycling1.setTarget(200);
		cycling1.setDistanceorTime(true); //distance
		
		Challenge running1 = new Challenge();
		running1.setName("RunMes");
		running1.setSport(SportEnum.Running); 
		running1.setStartDate(LocalDate.of(2017, 5, 25 ) );
		running1.setEndDate(LocalDate.of(2017, 6, 25 ));
		running1.setTarget(200);
		running1.setDistanceorTime(true); //distance
		
		
		this.challenges.add(running1);
		this.challenges.add(cycling1);
		
//		this.users.put(user0.getEmail(), user0);
//		this.users.put(user1.getEmail(), user1);
		
	}
	
//	public Map<String, User> getUsers()
//	{
//		return users;
//	}

	
	public List<Challenge> getChallenges() {
		//TODO: Get all the categories using DAO Pattern		
		return this.challenges;
	}

//	public List<Session> getSessions(User user) {
//		//TODO: Get all the categories using DAO Pattern		
//		if(user != null)
//		{
//			return user.getSessions();
//		}
//		return null;
//	}
	
	// TO DO
	public Challenge makeChallenge(String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport, boolean distanceorTime)
	{
		Challenge challenge = new Challenge();
		challenge.setName(name);
		challenge.setStartDate(startDate);
		challenge.setEndDate(endDate);
		challenge.setTarget(target);
		challenge.setSport(sport);
		challenge.setDistanceorTime(distanceorTime);
		
		
		
		
		challenges.add(challenge);
		
		return challenge;
		
	}
	
	public  Session makeSession(String title, SportEnum sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration) {
		Session session = new Session();
		session.setTitle(title);
		session.setDistance(distance);
		session.setSport(sport);
		session.setDuration(duration);
		session.setStartDate(startDate);
		session.setStartTime(starTime);
		
		return session;
	}
	
//	// TO DO
//	public boolean makeSession(Long token, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration )
//	{
//		if(server != null)
//		{
//			
//			Session session = new Session();
//			session.setTitle(title);
//			session.setDistance(distance);
//			session.setSport(sport);
//			session.setDuration(duration);
//			session.setStartDate(startDate);
//			session.setStartTime(starTime);
//			
//		
//			user.addSession(session);
//			
//			return true;
//		}
//		
//		return false;
//	}
	
	
}
