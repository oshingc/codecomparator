package pe.com.codecomparator.model.command.infrastructure.impl;

import pe.com.codecomparator.domain.User;
import pe.com.codecomparator.model.command.infrastructure.UserCommandInfrastructureService;
import pe.com.codecomparator.persistence.UserMapper;

public class UserCommandInfrastructureServiceImpl implements
		UserCommandInfrastructureService {

	private UserMapper userMapper;

	@Override
	public void createUser(User user) {
		userMapper.createUser(user.getT_username(), user.getT_password());
	}

}
