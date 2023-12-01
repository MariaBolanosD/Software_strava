package es.deusto.ingenieria.sd.auctions.server.gateway;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.deusto.ingenieria.sd.auctions.server.data.domain.User;


@SpringBootApplication
@Service
public class GoogleGateway implements IGateway{

	public RestTemplate restTemplate;
	private static GoogleGateway requester;
	
	
	@Bean
	RestTemplate restTemplate() {
	      return new RestTemplate();
	}	
	
	// Host and port NOT hard-coded: Defined in application.properties
	@Value("${spring.server.url}")
	private String serverURL;
	
	@Value("${server.port}")
	private int serverPort;
	
	public static void start() {
		System.out.println("GOOGLE START");
		SpringApplication.run(GoogleGateway.class);	
	}
		
	
    public GoogleGateway() {
    }

	
	@Autowired
	public void setRequester(GoogleGateway r) {
		 requester = r;
	 }
	public static GoogleGateway getRequester() {
		return requester;
	}
	@Autowired
	public void setRestTemplate (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public boolean getUserByEmail(String email)
	{
		
		User user = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}", User.class, Map.of("email", email));
		 //log.info("/user/email/{email} - This is User: " + user.getEmail());// + "name: " + user.getFirstName());
		 if (user != null)
		 {
			 return true;				 
		 }
		 return false;
	}
	public boolean verifyPassword(String email, String password)
	{
		System.out.println("FIRSTTTT STARTTS");
		System.out.println(serverPort);
		System.out.println(serverURL);
		//requester = this.getRequester();
		if(restTemplate == null)
		{
			System.out.println("NULLLLLL");
			restTemplate = restTemplate();
		}
		System.out.println("Verifying password kjdhakjdhad");
		User userS =restTemplate.getForObject(serverURL + ":" +String.valueOf(serverPort) + "/user/verifyPassword/{email}/{password}",  User.class, email,password);
		if( userS != null)				  	 
		{
			  
			return true;
		}
		return false;
	}
}
