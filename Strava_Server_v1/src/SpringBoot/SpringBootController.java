package SpringBoot;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.deusto.ingenieria.sd.auctions.server.data.domain.User;


@SpringBootApplication
@Service
public class SpringBootController implements ISpringBootController {
	private static final Logger log = LoggerFactory.getLogger(SpringBootController.class);
		
	private RestTemplate restTemplate;
	private static SpringBootController requester;
	
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
		SpringApplication.run(SpringBootController.class);	
	}
		
	public SpringBootController () { }
	
	@Autowired
	public void setRequester(SpringBootController r) {
		 requester = r;
	 }
	public static SpringBootController getRequester() {
		return requester;
	}
	@Autowired
	public void setRestTemplate (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/* *
	 * Interface implementation methods
	 * */
	
	public String getListUsers() {
		//Example of parameterized logging
		  log.info("This is the endpoint: {}:{}/user/all - Getting all users...", serverURL, String.valueOf(serverPort));
		  User[] users = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/all", User[].class);
		  log.info("user/all - All users info ...");
		  
		  if (users != null) {
			  	for (User u: users) {
			  		log.info(u.toString());
			  	}
		  }
		  return "getListUsers -  all users info on console (if not null) ";
	  }
	
	  public boolean getUserByEmail(String email) {
		  	 log.info(email);
			 log.info("This is the endpoint: " + serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}"); 
			 User user = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/email/{email}", User.class, Map.of("email", email));
			 //log.info("/user/email/{email} - This is User: " + user.getEmail());// + "name: " + user.getFirstName());
			 if (user != null)
			 {
				 return true;				 
			 }
			 return false;
	  }	 
	  
	  public String addUser() {
	  	  // POST for creating a new user - email must be unique
		  User newUser = new User();
		  newUser.setEmail("ikg@deusto.es");
		  		 
		  log.info ("Endpoint: user/create - Creating new users");
		  String response = restTemplate.postForObject(serverURL + ":" + String.valueOf(serverPort) +"/user/create", newUser, String.class);
		  log.info("The response from the server is: " + response);
		  
		  return "addUser - new user created";
	  }
	  
	  public String updateUser() {
	    // PUT - Change the name of a user
		  log.info("Endpoint: user/update/{id} - Change email of one User - rebeca.cortazar@deusto.es TO rebecacortazar@gmail.com ");
		  
		  User userS = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) +"/user/details/{id}", User.class, Map.of("id", "1"));
		  log.info("User for update: ") ; // + userS.getFirstName());
		  
		  User userP = new User();
		  userP.setEmail("rebecacortazar@gmail.com");
		  restTemplate.put(serverURL + ":" + String.valueOf(serverPort) +"/user/update/{id}", userP, Map.of("id", "1"));
		  
		  return "update user- getting user 1 and changing the email";
	  }
		 	  
	  public String deleteUser() {
		    // DELETE  - Remove User 3
			  log.info("Endpoint: user/delete/{id} - Removal of User 3");

			  User userS = restTemplate.getForObject(serverURL + ":" + String.valueOf(serverPort) + "/user/details/{id}", User.class, Map.of("id", "3"));
			  log.info("User to delete: email: " + userS.getEmail());
			  // The delete() in RestTemplate does not have a return; in case of need, exchange() can be programmed	  
			  restTemplate.delete(serverURL + ":" + String.valueOf(serverPort) + "/user/delete/{id}", Map.of("id", "3"));
			  return "delete User - does not have a return";
	  }
	  
	  public String deleteUserAll() {
		    // DELETE  - Remove All Users from the DB
			  log.info("Endpoint: user/delete/all - Removal of ALL Users");

			 // The delete() in RestTemplate does not have a return	  
			  restTemplate.delete(serverURL + ":" + String.valueOf(serverPort) + "/user/delete/all");
			  return "delete all users - this endpoint does not have a return";
	  }
	  
	  public boolean verifyPassword(String email, String password)
	  {
		  log.info("Endpoint: Checking email and password");
		  log.info(password);
		  User userS =restTemplate.getForObject(serverURL + ":" +String.valueOf(serverPort) + "/user/verifyPassword/{email}/{password}",  User.class, email,password);
		  if( userS != null)				  	 
		  {
			  return true;
		  }
		  return false;
	  }
}
