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
		
		public void makeChallenge(long token, String name, LocalDate startDate, LocalDate endDate, float target, SportEnum sport,
				boolean distanceorTime)
		{
			try {
				 this.serviceLocator.getService().makeChallenge(token, name, startDate, endDate, target, sport, distanceorTime);
			} catch (RemoteException e) {
				System.out.println("# Error making challenge: " + e);
			}
			
		}
		
		
		
		public void makeSession(long token, String title, SportEnum sport, double distance, LocalDate startDate, LocalTime starTime, double duration) {
			try {
				 this.serviceLocator.getService().makeSession(token, title, sport, distance, startDate, starTime, duration);
			} catch (RemoteException e) {
				System.out.println("# Error making session: " + e);
			}
			
		}
		
		
		public ServiceLocator getServiceLoc() {
			return this.serviceLocator;
		}
					
	}