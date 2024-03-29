package es.deusto.ingenieria.sd.auctions.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public boolean register(TypeOfAccount accountType, String email, String name, LocalDate birthdate, float weight, float height, int heart_rate_max, int heart_rate_rest)throws RemoteException;

	public long login(String email, String password) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<ChallengeDTO> getChallenges() throws RemoteException;
	
	public List<ChallengeDTO> getAcceptedChallenges(long token) throws RemoteException; 
	
	public List<SessionDTO> getSessions(long token) throws RemoteException;
	
	public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport, boolean distanceorTime)throws RemoteException;
	
	public boolean makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration )throws RemoteException;
	
	public void acceptChallenge(long token, String name, LocalDate start_date, SportEnum sport) throws RemoteException;

}