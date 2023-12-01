package es.deusto.ingenieria.sd.auctions.server.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.factory.Factory;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
			
	private Map<String, User> users = new HashMap<String, User>(); 
	
	public LoginAppService()
	{
		this.initializeData();
	}
	
	public User login(String email, String password) {
		//TODO: Get User using DAO and check 		
//		//check if user in Google
//		System.out.println("Inside LOGIN APP SERVICE LOGIN");
		//SpringBootApplication spr = new SpringBootApplication();
//		TCPSocketClient tcp = new TCPSocketClient(email, password);
//		System.out.println("before checking email");
//		System.out.println("response: " + tcp.getResponse());
//		if( spr.checkEmail(email) == false && tcp.getResponse() == false)
//		{
//			System.out.println("Incorrect email");
//			System.out.println(email);
//			return null;
//		}
//		System.out.println("response: " + tcp.getResponse());
//		if(spr.verifyPassword(email, password) == false && tcp.getResponse() == false)
//		{
//			System.out.println("response inside: " + tcp.getResponse());
//			System.out.println("Incorrect password");
//			return null;
//		}
//		System.out.println("correct password LOginAPPSERVICES");
		// Get user from users
		User user = users.get(email);	
		if(user == null)
			return null;
		System.out.println(user.getTipoAut());
		IGateway gateway  = Factory.getInstance().creategateway(user.getTipoAut());
		boolean b = gateway.verifyPassword(email, password);
		System.out.println("result: " + b);
		if(b == true)
			 return user;
		else
			return null;
	}
	
	public boolean register(TypeOfAccount accountType, String email, String name, LocalDate birthdate ,float weight, float height, int heart_rate_max, int heart_rate_rest) {
//		if(accountType == TypeOfAccount.GOOGLE)
//		{			
//			System.out.println("Inside LOGIN APP SERVICE REGISTER GOOGLE");
//			SpringBootApplication spr = new SpringBootApplication();
//			if( spr.checkEmail(email) == false)
//			{
//				System.out.println("Incorrect email");
//				System.out.println(email);
//				return false;
//			}
//			System.out.println("Usuario existe en Google");
//		}
//		else { // FACEBOOK
//			
//			System.out.println("Inside LOGIN APP SERVICE REGISTER FACEBOOK");
//			TCPSocketClient tcpSC = new TCPSocketClient(email, null);
//			tcpSC.setEmailToCheck(email);
//			if( tcpSC.getResponse() == false)
//			{
//				System.out.println("Incorrect email");
//				System.out.println(email);
//				return false;
//			}
//			System.out.println("Usuario existe en Facebook");
//		}
				
		if(Factory.getInstance().creategateway(accountType).getUserByEmail(email) == false)
			return false;
		
		User usuario = new User();
		usuario.setTypeOfAccount(accountType);
		usuario.setEmail(email);
		usuario.setNickname(name);
		usuario.setBirthDate(birthdate);
		usuario.setWeight(weight);
		usuario.setHeight(height);
		usuario.setMaxHeartRate(heart_rate_max);
		usuario.setRestHeartRate(heart_rate_rest);
		
		users.put(email, usuario);
		
		return true;		
			
	}
	
	private void initializeData()
	{
		// Create Sessions
		Session sesion1 = new Session();
		sesion1.setTitle("sesion1");
		sesion1.setSport(SportEnum.Cycling);
		sesion1.setDistance(70);
		sesion1.setStartDate(LocalDate.of(2017, 5, 30 ));
		sesion1.setStartTime(LocalTime.of(12, 20));
		sesion1.setDuration(30);
		
		Session sesion2 = new Session();
		sesion2.setTitle("sesion1");
		sesion2.setSport(SportEnum.Running);
		sesion2.setDistance(40);
		sesion2.setStartDate(LocalDate.of(2017, 5, 30 ));
		sesion2.setStartTime(LocalTime.of(15, 30));
		sesion2.setDuration(45);
		
		
		User user0 = new User();
		//user0.setEmail("rebeca.cortazar@deusto.es");
		user0.setEmail("rebeca");
		user0.setNickname("Rebeca");
		user0.setTypeOfAccount(TypeOfAccount.GOOGLE);
		//user0.setPassword("$!9PhNz,");
		user0.setBirthDate(LocalDate.of(1985, 2, 25 ));
		user0.setWeight(86);
		user0.setHeight(184.25);
		user0.setMaxHeartRate(150);
		user0.setRestHeartRate(70);
		user0.addSession(sesion1);
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		//user1.setPassword("hqc`}3Hb");
		user1.setBirthDate(LocalDate.of(1989, 5, 2 ));
		user1.setTypeOfAccount(TypeOfAccount.GOOGLE);
		user1.setWeight(98);
		user1.setHeight(193.50);
		user1.setMaxHeartRate(147);
		user1.setRestHeartRate(79);
		user1.addSession(sesion2);
		
		User user2 = new User();
		user2.setEmail("hola@deusto.es");
		user2.setNickname("hola");		
		//user1.setPassword("hqc`}3Hb");
		user2.setBirthDate(LocalDate.of(1989, 5, 2 ));
		user2.setTypeOfAccount(TypeOfAccount.FACEBOOK);
		user2.setWeight(98);
		user2.setHeight(193.50);
		user2.setMaxHeartRate(147);
		user2.setRestHeartRate(79);
		user2.addSession(sesion2);
		
		users.put(user0.getEmail(), user0);
		users.put(user1.getEmail(), user1);
		users.put(user2.getEmail(), user2);
	}
	
}