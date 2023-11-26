package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;

public class Controller {
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		
		public Controller(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator; 
		}
		
		public List<ChallengeDTO> getChallenges()
		{
			try {
				return this.serviceLocator.getService().getChallenges();
			} catch (RemoteException e) {
				System.out.println("# Error getting all challenges: " + e);
				return null;
			}
		}
		
		public List<SessionDTO> getSessions(long token) {
			try {
				return this.serviceLocator.getService().getSessions(token);
			} catch (RemoteException e) {
				System.out.println("# Error getting sessions of a user: " + e);
				return null;
			}
		}
		
		public List<ChallengeDTO> getAcceptedChallenges(long token)
		{
			try {
				return this.serviceLocator.getService().getAcceptedChallenges(token);
			} catch (RemoteException e) {
				System.out.println("# Error getting accepted challenges of a user: " + e);
				return null;
			}
			//return null;
			
		}
		
		public boolean makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport,
				boolean distanceorTime)
		{
			try {
				 this.serviceLocator.getService().makeChallenge(token, name, startDate, endDate, target, sport, distanceorTime);
				 return true;
			} catch (RemoteException e) {
				System.out.println("# Error making challenge: " + e);
				return false;
			}
			
		}
		
		
		
		public boolean makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration) {
			try {
				 this.serviceLocator.getService().makeSession(token, title, sport, distance, startDate, starTime, duration);
				 return true;
			} catch (RemoteException e) {
				System.out.println("# Error making session: " + e);
				return false;
			}
			
		}
		
		public void acceptChallenge(long token, String name, LocalDate start_date, SportEnum sport)
		{
			try {
				 this.serviceLocator.getService().acceptChallenge(token, name, start_date, sport);
			} catch (RemoteException e) {
				System.out.println("# Error accepting challenge: " + e);
			}
			
		}
		
		
		public ServiceLocator getServiceLoc() {
			return this.serviceLocator;
		}
					
	}