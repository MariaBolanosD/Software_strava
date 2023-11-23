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
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;
import es.deusto.ingenieria.sd.auctions.server.services.SportAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();
	
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private SportAppService sportAppService = new SportAppService();
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
		List<Challenge> challenges = sportAppService.getChallenges();
		
		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}
	}

	@Override
	public List<SessionDTO> getSessions(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getArticle('" + serverState.get(token).getEmail() + "')");

		//Get Articles using BidAppService

		List<Session> sessions = serverState.get(token).getSessions();
		
		if (sessions != null) {
			//Convert domain object to DTO
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		} else {
			throw new RemoteException("getArticles() fails!");
		}
	}

	@Override
	public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, String sport,
			boolean distanceorTime) {
		// TODO Auto-generated method stub
		if(serverState.get(token) != null)
		{	
			// send email to subscribe challenge to user
			//serverState.get(token).addChallenge(sportAppService.makeChallenge(name, startDate, endDate, target, sport, distanceorTime));
			return true;
		}
		else {				
			return false;
		}
		
	}

	@Override
	public boolean makeSession(long token, String title, String sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration) {
		// TODO Auto-generated method stub
		
		if(token >= 0)
		{
			
			Session session = new Session();
			session.setTitle(title);
			session.setDistance(distance);
			//session.setSport(sport);
			session.setDuration(duration);
			session.setStartDate(startDate);
			session.setStartTime(starTime);
			
			serverState.get(token).addSession(session);
			return true;
		}
		
		return false;
	}
	
	
	// TO DO
	public void updateChallenges()
	{
		
	}

	@Override
	public boolean register(String accountType, String email, String password, String name, LocalDate birthdate, float weight, float height,
			int heart_rate_max, int heart_rate_rest) {
		if (email == null || password == null || name == null || birthdate == null || heart_rate_max >=0 || heart_rate_rest >= 0) {
	        return false;
	    }
		User usuario = new User();
		//usuario.setTypeOfAccount(accountType);
		System.out.println("TYPE OF ACCOUNT: "+ accountType);
		usuario.setEmail(email);
		usuario.setPassword(password);
		usuario.setNickname(name);
		usuario.setBirthDate(birthdate);
		usuario.setWeight(weight);
		usuario.setHeight(height);
		usuario.setMaxHeartRate(heart_rate_max);
		usuario.setRestHeartRate(heart_rate_rest);
		return true;
	}

	@Override
	public boolean register(String accountType, String email, String password, String name,
			LocalDate birthdate) {
		// TODO Auto-generated method stub
		return false;
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