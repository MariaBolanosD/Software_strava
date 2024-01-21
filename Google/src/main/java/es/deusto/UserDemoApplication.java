package es.deusto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import es.deusto.dao.UserRepository;
import es.deusto.model.User;


@SpringBootApplication
public class UserDemoApplication {
	
	private static final Logger log= LoggerFactory.getLogger(UserDemoApplication.class);
	 
	@Value("${spring.mail.host}")		
	private String host;
	@Value("${spring.mail.port}")		
	private int port;
	@Value("${spring.mail.username}")
    private String sender;
	@Value("${spring.mail.password}")
	private String password;
	 

    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class, args);
        
          }
    
    @Bean
    CommandLineRunner demo(UserRepository repository) {
      return (args) -> {
          // INIT data ... some Users
    	    	  
    	  User rebeca = new User();
    	  rebeca.setEmail("rebeca");
    	  rebeca.setPassword("Rebeca");
    	  User roberto = new User();
    	  roberto.setEmail( "rcarba@deusto.es");
    	  roberto.setPassword("Roberto"); 
    	  User user1 = new User();
    	  user1.setEmail("thomas.e2001@gmail.com");
    	  user1.setPassword("$thomas,");
    	  User user2 = new User();
    	  user2.setEmail("sample@gmail.com");
    	  user2.setPassword("hello");
    	  User user3 = new User();
    	  user3.setEmail("maria.bolanos@opendeusto.es");
    	  user3.setPassword("maria");
    	  repository.save(rebeca);
    	  repository.save(roberto);
    	  repository.save(user1);
    	  repository.save(user2);
    	  repository.save(user3);
    	  
    	  log.info(user3.getEmail());   
    	  
        log.info("Sample users created"); 
    	  
    	log.info("USER Services AVAILABLE ...");
      };
    }
}
    
