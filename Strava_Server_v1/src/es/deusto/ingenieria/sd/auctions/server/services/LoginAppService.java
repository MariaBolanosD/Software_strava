package es.deusto.ingenieria.sd.auctions.server.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import es.deusto.ingenieria.sd.auctions.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Session;
import es.deusto.ingenieria.sd.auctions.server.data.domain.User;
import es.deusto.ingenieria.sd.auctions.server.data.dto.SportEnum;
import es.deusto.ingenieria.sd.auctions.server.data.dto.TypeOfAccount;
import es.deusto.ingenieria.sd.auctions.server.factory.Factory;
import es.deusto.ingenieria.sd.auctions.server.gateway.GoogleGateway;
import es.deusto.ingenieria.sd.auctions.server.gateway.IGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {

	private static LoginAppService instance;

	public LoginAppService() {
	}

	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}
		return instance;
	}

	public User login(String email, String password) {

		// Get user from users
		User user = UserDAO.getInstance().find(email);
		if (user == null)
			return null;
		System.out.println(user.getTipoAut());
		IGateway gateway = Factory.getInstance().creategateway(user.getTipoAut());
		boolean b = gateway.verifyPassword(email, password);
		System.out.println("result: " + b);
		if (b == true)
			return user;
		else
			return null;
	}

	public boolean register(TypeOfAccount accountType, String email, String name, LocalDate birthdate, float weight,
			float height, int heart_rate_max, int heart_rate_rest) {

		if (Factory.getInstance().creategateway(accountType).getUserByEmail(email) == false)
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

		UserDAO.getInstance().store(usuario);

		return true;

	}

}