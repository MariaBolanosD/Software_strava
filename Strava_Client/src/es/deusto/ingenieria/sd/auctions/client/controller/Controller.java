package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;

import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SessionDTO;

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
		
		public List<SessionDTO> getSessions(User user) {
			try {
				return this.serviceLocator.getService().getSessions(user);
			} catch (RemoteException e) {
				System.out.println("# Error getting sessions of a user: " + e);
				return null;
			}
		}
		
	}