package SpringBoot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringBootApplication {
 
  private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);
 
  public static void main(String args[]) {
	 SpringBootController.start();
	 
	
	 ISpringBootController req = SpringBootController.getRequester();
	 
	 log.info("Adding user - response from requester: " + req.addUser());
	 log.info("********************************");

	 log.info("Get list users - response from requester: " + req.getListUsers());
	 
	 log.info("********************************");
	 
	 log.info("DELETE DELETE DELETE : " + req.deleteUserAll());
	
	 try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 
	 String asciiArtEnd = 
	            "  _____ _   _ _____   _____ _   _ ____  \n" +
	            " |_   _| | | | ____| | ____| \\ | |  _ \\ \n" +
	            "   | | | |_| |  _|   |  _| |  \\| | | | |\n" +
	            "   | | |  _  | |___  | |___| |\\  | |_| |\n" +
	            "   |_| |_| |_|_____| |_____|_| \\_|____/ \n";

	  System.out.println(asciiArtEnd);
	 
	  
  }	  
  
}  