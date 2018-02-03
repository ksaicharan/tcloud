package tcloud.msg.user;

import tcloud.msg.model.User;

public interface UserService {

	/**
	 * 
	 * @param userId
	 * @return
	 */
	User get(String userId);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	void addUser(User user);
}
