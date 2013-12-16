package pe.com.codecomparator.model.query.infrastructure.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.query.infrastructure.UserQueryInfrastructureService;
import pe.com.codecomparator.persistence.UserMapper;

public class UserQueryInfrastructureServiceImpl implements
		UserQueryInfrastructureService {

	@Autowired
	private UserMapper userMapper;

	public User validateUser(User user) {
		System.out.println((userMapper != null) ? userMapper : "null");
		User _user = userMapper.getUser(user.getT_username(),
				user.getT_password());
		System.out.println((_user != null) ? _user : "null");
		return _user;
		// return new User();
		//
	}
	
	public User userExists(User user){
		
		User _user = userMapper.userExists(user.getT_username());
		return _user;
		
	}
	
	public User emailExists(User user){
		User _user = userMapper.emailExists(user.getT_email());
		return _user;
	}

}
