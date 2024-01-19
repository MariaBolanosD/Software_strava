package es.deusto.ingenieria.sd.auctions.server.factory;
import org.springframework.stereotype.Component;

import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.gateway.FacebookGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;

@Component
public class Factory {

	 private static Factory instance;
	
	 
	 private Factory()
	 {
		 System.out.println("Factory constructor");
	 }
	 
	 public static Factory getInstance()
	 {
	      if (instance == null) {
	          instance = new Factory();
	      }
	      System.out.println("factory already exists");
	      return instance;
	 } 
	 
	public IGateway creategateway(TypeOfAccount type)
	{
		// calls IGateway
		switch (type) {
		case GOOGLE:
			System.out.println("GOOGLE");
			GoogleGateway.start();
			return GoogleGateway.getRequester();
		case FACEBOOK:
			FacebookGateway facebook = new FacebookGateway();
			// get port and ip from string
			facebook.setFacebookPortIp("8090", "127.0.0.1");
			return facebook;
		default:
			return null;
		}
	}
	
	
}
