package SpringBoot;

import javax.print.attribute.standard.RequestingUserName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringBootApplication {
 
  private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);
 
  ISpringBootController req;
  public static void main(String args[]) {

	  SpringBootApplication springBoot = new SpringBootApplication();
	  springBoot.MAIN();
  }	  
  
  public SpringBootApplication() {
	  	SpringBootController.start();	
	  	req = SpringBootController.getRequester();	   
  }
  
  public void end()
  {
	  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  }
  
  public void MAIN()
  {
		 SpringBootController.start();		 
			
		 req = SpringBootController.getRequester();
		 
		 verifyPassword("rebeca.cortazar@deusto.es", "Rebeca");
		 verifyPassword("rebeca.cortazar@deusto.es", "Roberto");
		 
		 log.info("********************************");

		 log.info("Get list users - response from requester: " + req.getListUsers());
		 
		 log.info("********************************");
		 
		 log.info("DELETE DELETE DELETE : " + req.deleteUserAll());
		
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
		  
		 
  }
  
  public boolean verifyPassword(String email, String password)
  {
	  log.info("Verify Password : "+password);
		 if(req.verifyPassword(email, password))
		 {
			 log.info("Password CORRECT");
			 return true;
		 }
		 else {
			 log.info("Password INCORRECT");
		}
		 return false;
  }
  
  public boolean checkEmail(String email)
  {
	  log.info("Checking if user in Google ...");
	  if(req.getUserByEmail() != null)
	  {
		  return true;
	  }
	  return false;
  }
  
}  
