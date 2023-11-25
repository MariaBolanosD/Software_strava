package SpringBoot;

public interface ISpringBootController {
	 public String getListUsers();
	 public String getUserByEmail();
	 public String addUser();
	 public String updateUser();
	 public String deleteUser();
	 public String deleteUserAll();
	 public boolean validateUser();
}
