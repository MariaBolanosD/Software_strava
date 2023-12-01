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
	
	 
	 private Factory()
	 {
		 GoogleGateway.start();
		 System.out.println("Factory constructor");
		 facebookGateway = new FacebookGateway();
		 googleGateway = GoogleGateway.getRequester();
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
	
	public void setFacebookPortIp(String port, String ip)
	{
		//FacebookGateway facebookGateway = new FacebookGateway();
		//facebookGateway.setFacebookPortIp(port, ip);
		System.out.println("facebook" + port + ip);
		((FacebookGateway )this.facebookGateway).setFacebookPortIp(port, ip);
		//this.facebookGateway = facebookGateway;
	}
	
}
