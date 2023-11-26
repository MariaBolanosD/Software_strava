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
		//clear as we are not doing logout
		this.serverState.clear();
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
			throw new RemoteException("Login fails_!");
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
		System.out.println(" * RemoteFacade getSessions('" + serverState.get(token).getEmail() + "')");

		//Get Articles using BidAppService

		List<Session> sessions = serverState.get(token).getSessions();
		
		if (sessions != null) {
			//Convert domain object to DTO
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		} else {
			throw new RemoteException("getSessions() fails!");
		}
	}
	
	@Override
	public List<ChallengeDTO> getAcceptedChallenges(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getUserAcceptedChallenges('" + serverState.get(token).getEmail() + "')");
		
		List<Challenge> challenges = serverState.get(token).getChallenges();
		
		if (challenges != null) {
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getAcceptedChallenges() fails!");
		}
	}

	@Override
	public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport,
			boolean distanceorTime) {
		// TODO Auto-generated method stub
		if(serverState.get(token) != null)
		{	
			// send email to subscribe challenge to user
			serverState.get(token).addChallenge(sportAppService.makeChallenge(name, startDate, endDate, target, sport, distanceorTime));
			return true;
		}
		else {				
			return false;
		}
		
	}

	@Override
	public boolean makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration) {
		// TODO Auto-generated method stub
		
		if(token >= 0)
		{
			
//			Session session = new Session();
//			session.setTitle(title);
//			session.setDistance(distance);
//			session.setSport(sport);
//			session.setDuration(duration);
//			session.setStartDate(startDate);
//			session.setStartTime(starTime);
//			
//			serverState.get(token).addSession(session);
//			return true;
			serverState.get(token).addSession(sportAppService.makeSession(title, sport, distance, startDate, starTime, duration));
			return true;
		}
		
		return false;
	}
	
	// TO DO
	public void updateChallenges()
	{
		
	}

	@Override
	public boolean register(TypeOfAccount accountType, String email, String name, LocalDate birthdate, float weight, float height,
			int heart_rate_max, int heart_rate_rest) {

		
		System.out.println(" * RemoteFacade Register(): " + email);
		
		//Perform login() using LoginAppService
		return loginService.register(accountType, email, name, birthdate, weight, height, heart_rate_max, heart_rate_rest);
		
	}

	@Override
	public void acceptChallenge(long token, String name, LocalDate start_date, SportEnum sport) {
		for(Challenge eachChall : sportAppService.getChallenges())
		{
			if((eachChall.getName() == name)
					&& (eachChall.getStartDate() == start_date)
						&& (eachChall.getSport() == sport)) 
			{
				serverState.get(token).addChallenge(eachChall);
				break;
				
			}
		}
	}


}