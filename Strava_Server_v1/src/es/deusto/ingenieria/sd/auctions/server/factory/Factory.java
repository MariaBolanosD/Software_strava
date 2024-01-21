package es.deusto.ingenieria.sd.auctions.server.factory;
import org.springframework.stereotype.Component;

import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.gateway.FacebookGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.MailSender;

@Component
public class Factory {

	 private static Factory instance;
	 
	 private Factory()
	 {	 }
	 
	 public static Factory getInstance()
	 {
	      if (instance == null) {
	          instance = new Factory();
	      }
	      return instance;
	 } 
	 
	public IGateway creategateway(TypeOfAccount type)
	{
		// calls IGateway
		switch (type) {
		case GOOGLE:
			System.out.println("GOOGLE");
			if(!GoogleGateway.isStarted())
			{
				GoogleGateway.start();				
			}
			return GoogleGateway.getRequester();
		case FACEBOOK:
			return new FacebookGateway();
		default:
			return null;
		}
	}
	
	public void getMailGateway(String email, String info)
	{
		new MailSender(email).sendMessage(info);

	}
	
}
