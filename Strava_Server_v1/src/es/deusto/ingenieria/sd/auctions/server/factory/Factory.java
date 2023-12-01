package es.deusto.ingenieria.sd.auctions.server.factory;
import org.springframework.stereotype.Component;

import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.gateway.FacebookGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;

@Component
public class Factory {

	 private static Factory instance;
	 private final IGateway googleGateway;
	 private final IGateway facebookGateway;
	 //private final ISpringBootController controller;
	
	 
	 private Factory()
	 {
		 GoogleGateway.start();
		 System.out.println("Factory constructor");
		 facebookGateway = new FacebookGateway();
		 googleGateway = GoogleGateway.getRequester();
		 //controller = SpringBootController.getRequester();
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
			return googleGateway;
		case FACEBOOK:
			return facebookGateway;
		default:
			return null;
		}
	}
	
}
