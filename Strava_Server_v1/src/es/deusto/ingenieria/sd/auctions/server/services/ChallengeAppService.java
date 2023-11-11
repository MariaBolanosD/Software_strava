package es.deusto.ingenieria.sd.auctions.server.services;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

public class ChallengeAppService {

	private List<Challenge> challenges = new ArrayList<>();
	
	public ChallengeAppService() {
		this.initilizeData();
	}
	
	@SuppressWarnings("deprecation")
	private void initilizeData() {
		
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		user0.setBirthDate(new Date(1985, 2, 25 ));
		user0.setWeight(86);
		user0.setHeight(184.25);
		user0.setMaxHeartRate(150);
		user0.setRestHeartRate(70);
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		user1.setPassword("hqc`}3Hb");
		user0.setBirthDate(new Date(1989, 5, 2 ));
		user0.setWeight(98);
		user0.setHeight(193.50);
		user0.setMaxHeartRate(147);
		user0.setRestHeartRate(79);
		
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
		
		
		// Create Sessions
		Session sesion1 = new Session();
		sesion1.setTitle("sesion1");
		sesion1.setSport(SportEnum.Cycling);
		sesion1.setDistance(70);
		sesion1.setStartDate(LocalDate.of(2017, 5, 30 ));
		sesion1.setStartTime(LocalTime.of(12, 20));
		sesion1.setDuration(30);
		sesion1.setUser(user0);
		
		Session sesion2 = new Session();
		sesion2.setTitle("sesion1");
		sesion2.setSport(SportEnum.Running);
		sesion2.setDistance(40);
		sesion2.setStartDate(LocalDate.of(2017, 5, 30 ));
		sesion2.setStartTime(LocalTime.of(15, 30));
		sesion2.setDuration(45);
		sesion2.setUser(user1);
		
		this.challenges.add(running1);
		this.challenges.add(cycling1);
	}
	
	public List<Challenge> getChallenges() {
		//TODO: Get all the categories using DAO Pattern		
		return this.challenges;
	}

	public List<Session> getSessions(User user) {
		//TODO: Get all the categories using DAO Pattern		
		if(user != null)
		{
			return user.getSessions();
		}
		return null;
	}
	
	// TO DO
	public boolean makeChallenge()
	{
		
		
		return false;
	}
	
	// TO DO
	public boolean makeSession(User user)
	{
		return false;
	}
	
}
