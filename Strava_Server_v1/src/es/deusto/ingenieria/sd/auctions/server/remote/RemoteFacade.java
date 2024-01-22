package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.dao.SessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionAssembler;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.factory.Factory;
import es.deusto.ingenieria.sd.auctions.server.services.LoginAppService;
import es.deusto.ingenieria.sd.auctions.server.services.SportAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, User> serverState = new HashMap<>();

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		User user = LoginAppService.getInstance().login(email, password);
		
		//If login() success user iis stored in the Server State
		if (user != null) {
			
			// *********** ELIMINAR DESPUES DE COMPILAR UNA VEZ *************************************************************
			this.serverState.clear();
			// *********** ELIMINAR DESPUES DE COMPILAR UNA VEZ *************************************************************
			
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
			throw new RemoteException("Token error (" + token + ")!");
		}
	}
	
	@Override
	public List<ChallengeDTO> getChallenges() throws RemoteException {
		System.out.println(" * RemoteFacade getChallenges");
		
		List<Challenge> challenges = SportAppService.getInstance().getChallenges();
		//System.out.println(challenges);
		if (challenges != null) {
			//Convert domain object to DTO
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getChallenges() fails!");
		}
	}

	// TO DO
	@Override
	public List<SessionDTO> getSessions(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getSessions('" + serverState.get(token).getEmail() + "')");

		List<Session> sessions = SportAppService.getInstance().getSessions(serverState.get(token));
		
		System.out.println("sessions : " + sessions);
		if (sessions != null) {
			//Convert domain object to DTO
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		} else {
			sessions = new ArrayList<Session>();
			System.out.println("sessions is null");
			//throw new RemoteException("getSessions() fails!");
			return SessionAssembler.getInstance().sessionToDTO(sessions);
		}
	}
	
	@Override
	public List<ChallengeDTO> getAcceptedChallenges(long token) throws RemoteException {
		System.out.println(" * RemoteFacade getUserAcceptedChallenges('" + serverState.get(token).getEmail() + "')");
		
		List<Challenge> challenges = serverState.get(token).getChallenges();
		
		if (challenges != null) {
			System.out.println(challenges);
			return ChallengeAssembler.getInstance().challengeToDTO(challenges);
		} else {
			throw new RemoteException("getAcceptedChallenges() fails!");
		}
	}

	@Override
	public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport,
			boolean distanceorTime) {
		if(serverState.get(token) != null)
		{	
			// send email to subscribe challenge to user
			Challenge challenge = SportAppService.getInstance().makeChallenge(name, startDate, endDate, target, sport, distanceorTime);
			serverState.get(token).addChallenge(challenge);
			
			// send email by using MailSender
			String infoString = "You have susbcribed to a new challenge: \n" + challenge.toString();
			Factory.getInstance().getMailGateway(serverState.get(token).getEmail(), infoString );
			return true;
		}
		else {				
			return false;
		}
		
	}

	@Override
	public boolean makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate,
			LocalTime starTime, double duration) {
		
		if(token >= 0)
		{
			serverState.get(token).addSession(SportAppService.getInstance().makeSession(title, sport, 
					distance, startDate, starTime, duration, serverState.get(token)));
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
		return LoginAppService.getInstance().register(accountType, email, name, birthdate, weight, height, heart_rate_max, heart_rate_rest);
		
	}

	@Override
	public void acceptChallenge(long token, String name, LocalDate start_date, SportEnum sport) {
		for(Challenge eachChall : SportAppService.getInstance().getChallenges())
		{
			System.out.println(eachChall.getName() + " == " + name);
			if((eachChall.getName().compareTo(name) == 0))
			{
				System.out.println(eachChall);
				serverState.get(token).addChallenge(eachChall);
				break;
				
			}
		}
	}


}