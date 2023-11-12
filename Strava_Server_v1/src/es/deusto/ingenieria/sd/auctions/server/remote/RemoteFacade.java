package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.auctions.server.services.ChallengeAppService;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();
	
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private ChallengeAppService challengeAppService = new ChallengeAppService();
	//private BidAppService bidService = new BidAppService();

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User user = loginService.login(email, password);
			
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
	}
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);
		
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
	@Override
	public List<ChallengeDTO> getChallenges() throws RemoteException {
		System.out.println(" * RemoteFacade getChallenges");
		
		//Get Categories using BidAppService
		List<Challenge> challenges = challengeAppService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}
	}

	@Override
	public List<SessionDTO> getSessions(UserDTO user) throws RemoteException {
		System.out.println(" * RemoteFacade getArticle('" + user.getEmail() + "')");

		//Get Articles using BidAppService
		
		List<Session> sessions = challengeAppService.getSessions(challengeAppService.getUsers().get(user.getEmail()));
		
		if (sessions != null) {
			//Convert domain object to DTO
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		} else {
			throw new RemoteException("getArticles() fails!");
		}
	}

	@Override
	public boolean makeChallenge(String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport,
			boolean distanceorTime, User user) {
		// TODO Auto-generated method stub
		
		Challenge challenge = new Challenge();
		challenge.setName(name);
		challenge.setStartDate(startDate);
		challenge.setEndDate(endDate);
		challenge.setTarget(target);
		challenge.setSport(sport);
		challenge.setDistanceorTime(distanceorTime);
		
		if(user != null)
		{
			// send email to subscribe challenge to user
			user.addChallenge(challenge);
			return true;
		}
		else {				
			return false;
		}
		
	}

	@Override
	public boolean makeSession(User user, String title, SportEnum sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration) {
		// TODO Auto-generated method stub
		
		if(user != null)
		{
			
			Session session = new Session();
			session.setTitle(title);
			session.setDistance(distance);
			session.setSport(sport);
			session.setDuration(duration);
			session.setStartDate(startDate);
			session.setStartTime(starTime);
			session.setUser(user);
		
			user.addSession(session);
			
			return true;
		}
		
		return false;
	}
	
	
	// TO DO
	public void updateChallenges()
	{
		
	}
	
	public Map<String, User> getUsers()
	{
		return this.challengeAppService.getUsers();
	}
	
//	@Override
//	public boolean makeBid(long token, int article, float amount) throws RemoteException {		
//		System.out.println(" * RemoteFacade makeBid article : " + article + " / amount " + amount);
//		
//		if (this.serverState.containsKey(token)) {						
//			//Make the bid using Bid Application Service
//			if (bidService.makeBid(this.serverState.get(token), article, amount)) {
//				return true;
//			} else {
//				throw new RemoteException("makeBid() fails!");
//			}
//		} else {
//			throw new RemoteException("To place a bid you must first log in");
//		}
//	}

//	@Override
//	public float getUSDRate() throws RemoteException {
//		System.out.println(" * RemoteFacade get USD rate");
//
//		//Get rate using BidAppService
//		float rate = bidService.getUSDRate();
//		
//		if (rate != -1) {
//			return rate;
//		} else {
//			throw new RemoteException("getUSDRate() fails!");
//		}
//	}

//	@Override
//	public float getGBPRate() throws RemoteException {
//		System.out.println(" * RemoteFacade get GBP rate");
//		
//		//Get rate using BidAppService
//		float rate = bidService.getGBPRate();
//		
//		if (rate != -1) {
//			return rate;
//		} else {
//			throw new RemoteException("getGBPRate() fails!");
//		}
//	}
}