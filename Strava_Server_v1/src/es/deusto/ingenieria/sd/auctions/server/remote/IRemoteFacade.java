package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public boolean register(String email, String password, String name, LocalDate birthdate, float weight, float height, Integer heart_rate_max, Integer heart_rate_rest);
	
	public long login(String email, String password) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<ChallengeDTO> getChallenges() throws RemoteException;
	
	public List<SessionDTO> getSessions(long token) throws RemoteException;
	
	public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport, boolean distanceorTime);
	
	public boolean makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration );
	
//	public boolean makeBid(long token, int article, float amount) throws RemoteException;

//	public float getUSDRate() throws RemoteException;

//	public float getGBPRate() throws RemoteException;	
}