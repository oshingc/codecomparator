package pe.com.codecomparator.model.command.infrastructure.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.command.infrastructure.UserCommandInfrastructureService;
import pe.com.codecomparator.persistence.UserMapper;

public class UserCommandInfrastructureServiceImpl implements
		UserCommandInfrastructureService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void createUser(User user) {
		userMapper.createUser(user.getT_username(), user.getT_password(), user.getT_email());
	}

}
