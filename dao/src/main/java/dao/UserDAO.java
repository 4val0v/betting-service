package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	public boolean addUser(User u);
	public boolean deleteUser(String username);
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	
}
