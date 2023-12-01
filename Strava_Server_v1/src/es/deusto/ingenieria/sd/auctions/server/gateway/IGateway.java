package es.deusto.ingenieria.sd.auctions.server.gateway;

public interface IGateway {
	 public boolean getUserByEmail(String email);
	 public boolean verifyPassword(String email, String password);
}
