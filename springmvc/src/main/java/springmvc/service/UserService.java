package springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import springmvc.utility.utility;
import org.springframework.stereotype.Service;

import springmvc.dao.UserDao;
import springmvc.model.User;


@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	public int createUser(User user)
	{
		return this.userDao.saveUser(user);
	}
	
	public User validateUser(String email, String password) {
		
		User user = userDao.findByEmail(email);
		
		if(user != null && user.getPassword().equals(utility.generate_Hash(password))) 
			return user;
		
		return null;
	}
	
	public boolean isEmailUnique(String email) {
		return userDao.isEmailUnique(email) == true;
    }
}
