package es.deusto.ingenieria.sd.auctions.server.services;

import java.time.LocalDate;

import SpringBoot.SpringBootApplication;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;

//TODO: Implement Singleton Pattern
public class LoginAppService {
			
	public User login(String email, String password) {
		//TODO: Get User using DAO and check 		
		//check if user in Googl
		System.out.println("Inside LOGIN APP SERVICE");
		SpringBootApplication spr = new SpringBootApplication();
		if( spr.checkEmail(email) == false)
		{
			System.out.println("Incorrect email");
			System.out.println(email);
			return null;
		}
		if(spr.verifyPassword(email, password) == false)
		{
			System.out.println("Incorrect password");
			return null;
		}
		
		User user = new User();		
		user.setEmail(email);
		//user.setNickname("Thomas");		
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setPassword(sha1);
		
		
		return user;
//		if (user.getEmail().equals(email) && user.checkPassword(password)) {		
//			return user;
//		} else {
//			return null;
//		}
		
	}
	public User register(TypeOfAccount accountType, String email, String password, String name, LocalDate birthdate, float weight, float height, int heart_rate_max, int heart_rate_rest) {
			
		//TODO 
		//
		//
			User usuario = new User();
			usuario.setTypeOfAccount(accountType);
			System.out.println("TYPE OF ACCOUNT: "+ accountType);
			usuario.setEmail(email);
			usuario.setPassword(password);
			usuario.setNickname(name);
			usuario.setBirthDate(birthdate);
			usuario.setWeight(weight);
			usuario.setHeight(height);
			usuario.setMaxHeartRate(heart_rate_max);
			usuario.setRestHeartRate(heart_rate_rest);
			return usuario;
		}
}