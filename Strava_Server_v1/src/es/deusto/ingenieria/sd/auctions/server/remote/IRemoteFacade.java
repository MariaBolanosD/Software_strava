package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.dto.UserDTO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public long login(String email, String password) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<ChallengeDTO> getChallenges() throws RemoteException;
	
	public List<SessionDTO> getSessions(UserDTO user) throws RemoteException;
	
	public boolean makeChallenge(String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport, boolean distanceorTime, User user);
	
	public boolean makeSession(User user, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration );
	
	public Map<String, User> getUsers();
	
//	public boolean makeBid(long token, int article, float amount) throws RemoteException;

//	public float getUSDRate() throws RemoteException;

//	public float getGBPRate() throws RemoteException;	
}