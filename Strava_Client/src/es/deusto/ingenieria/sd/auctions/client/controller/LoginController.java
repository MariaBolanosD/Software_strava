package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;

import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;


//This class implements Controller pattern.
public class LoginController {	
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails

	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean login(String email, String password) {
		try {
			this.token = this.serviceLocator.getService().login(email, password);			
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			this.token = -1;
			return false;
		} 
	}
	
	public void logout() {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
		}
	}

	
	public void Register(TypeOfAccount accountType, String email, String password, String name, LocalDate birthdate, float weight, float height, int heart_rate_max, int heart_rate_rest)
	{
		try {
			this.serviceLocator.getService().register(accountType, email, password, name, birthdate, weight, height, heart_rate_max, heart_rate_rest);
			
		} catch (RemoteException e) {
			System.out.println("# Error during register: " + e);
		}
		
		
	}
	public void Register(TypeOfAccount accountType, String email, String password, String name, LocalDate birthdate)
	{
		try {
			this.serviceLocator.getService().register(accountType, email, password, name, birthdate);
			
		} catch (RemoteException e) {
			System.out.println("# Error during register: " + e);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public long getToken() {
		return token;
	}
	
//	public Map<String, User> getUsers()
//	{
//		return this.serviceLocator.getService().getUsers();
//	}
//	
}