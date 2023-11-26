package SpringBoot;

public interface ISpringBootController {
	 public String getListUsers();
	 public boolean getUserByEmail(String email);
	 public String addUser();
	 public String updateUser();
	 public String deleteUser();
	 public String deleteUserAll();
	 public boolean verifyPassword(String email, String password);
}
