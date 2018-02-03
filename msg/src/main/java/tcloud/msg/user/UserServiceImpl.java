package tcloud.msg.user;

import java.util.HashMap;
import java.util.Map;

import tcloud.msg.model.User;

public class UserServiceImpl implements UserService{

	// holds all users in the
	Map<String, User> userMap = new HashMap<String, User>();
		
	public User get(String userId) {
		if(userId != null && !userId.isEmpty()) {
			User user = userMap.get(userId);
			if(user == null) {
				System.out.println("User not found in the system. UserID = " + userId); // use log4j
			}
			return user;
		}
		return null;
	}

	public void addUser(User user) {
		if(user != null) {
			userMap.put(user.getUserId(), user);
		}
	}
	
	
	

}
