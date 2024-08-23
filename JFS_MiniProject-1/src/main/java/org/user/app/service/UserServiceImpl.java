package org.user.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.app.model.User;
import org.user.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public User addUser(User user) {
		
		return this.userrepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return this.userrepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		// TODO Auto-generated method stub
		return this.userrepository.findById(id);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return this.userrepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		this.userrepository.deleteById(id);
		
	}

}
