package tcloud.msg.model;


public class User {
	private String userId;
	private Wallet wallet;
	
	public User(String userId, Wallet wallet) {
		this.userId = userId;
		this.wallet = wallet;
	}

	public String getUserId() {
		return userId;
	}

	public Wallet getWallet() {
		return wallet;
	}
	
	
}
