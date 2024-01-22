package es.deusto.ingenieria.sd.auctions.server;

import java.rmi.Naming;
import java.time.LocalDate;
import java.time.LocalTime;

import es.deusto.ingenieria.sd.auctions.server.data.dao.ChallengeDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.SessionDAO;
import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Challenge;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.factory.Factory;
import es.deusto.ingenieria.sd.auctions.server.remote.IRemoteFacade;
import es.deusto.ingenieria.sd.auctions.server.remote.RemoteFacade;

public class MainProgram {
	
	@SuppressWarnings({ "removal", "deprecation" })
	public static void main(String[] args) {	
		
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		initDB();
		
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava Server v1 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static void initDB() {
		// TODO Auto-generated method stub
		try {
			// Create Sessions
			Session sesion1 = new Session();
			sesion1.setTitle("sesion1");
			sesion1.setSport(SportEnum.Cycling);
			sesion1.setDistance(70);
			sesion1.setStartDate(LocalDate.of(2017, 5, 30 ));
			sesion1.setStartTime(LocalTime.of(12, 20));
			sesion1.setDuration(30);
			
			Session sesion2 = new Session();
			sesion2.setTitle("sesion2");
			sesion2.setSport(SportEnum.Running);
			sesion2.setDistance(40);
			sesion2.setStartDate(LocalDate.of(2017, 5, 30 ));
			sesion2.setStartTime(LocalTime.of(15, 30));
			sesion2.setDuration(45);
			
			Session sesion3 = new Session();
			sesion3.setTitle("sesion3");
			sesion3.setSport(SportEnum.Running);
			sesion3.setDistance(50);
			sesion3.setStartDate(LocalDate.of(2017, 5, 30 ));
			sesion3.setStartTime(LocalTime.of(15, 30));
			sesion3.setDuration(60);
			
			SessionDAO.getInstance().store(sesion1);
			SessionDAO.getInstance().store(sesion2);
			SessionDAO.getInstance().store(sesion3);
			
			//Create Challenges
			Challenge cycling1 = new Challenge();
			cycling1.setName("BiciMes");
			cycling1.setSport(SportEnum.Cycling); 
			cycling1.setStartDate(LocalDate.of(2017, 5, 25 ) );
			cycling1.setEndDate(LocalDate.of(2017, 6, 25 ));
			cycling1.setTarget(200);
			cycling1.setDistanceorTime(true); //distance
			
			Challenge running1 = new Challenge();
			running1.setName("RunMes");
			running1.setSport(SportEnum.Running); 
			running1.setStartDate(LocalDate.of(2017, 5, 25 ) );
			running1.setEndDate(LocalDate.of(2017, 6, 25 ));
			running1.setTarget(200);
			running1.setDistanceorTime(false); //distance
			
			Challenge cha = new Challenge();
			cha.setName("ChallAccept");
			cha.setSport(SportEnum.Running); 
			cha.setStartDate(LocalDate.of(2017, 5, 25 ) );
			cha.setEndDate(LocalDate.of(2017, 6, 25 ));
			cha.setTarget(50);
			cha.setDistanceorTime(true); //distance
			
			ChallengeDAO.getInstance().store(cycling1);
			ChallengeDAO.getInstance().store(running1);
			ChallengeDAO.getInstance().store(cha);
			
			
			User user0 = new User();
			user0.setEmail("rebeca");
			user0.setNickname("Rebeca");
			user0.setTypeOfAccount(TypeOfAccount.GOOGLE);
			user0.setBirthDate(LocalDate.of(1985, 2, 25 ));
			user0.setWeight(86);
			user0.setHeight(184.25);
			user0.setMaxHeartRate(150);
			user0.setRestHeartRate(70);
			user0.addSession(sesion1);
			user0.addSession(sesion2);
			user0.addSession(sesion3);
			user0.addChallenge(cycling1);
			
			User user2 = new User();
			user2.setEmail("hola@deusto.es");
			user2.setNickname("hola");		
			user2.setBirthDate(LocalDate.of(1989, 5, 2 ));
			user2.setTypeOfAccount(TypeOfAccount.FACEBOOK);
			user2.setWeight(98);
			user2.setHeight(193.50);
			user2.setMaxHeartRate(147);
			user2.setRestHeartRate(79);
			
			UserDAO.getInstance().store(user0);
			UserDAO.getInstance().store(user2);
			
		} catch (Exception e) {
			System.out.println("\t$ Error storing data:" + e.getMessage());
		}
	}
		
		

}
	
