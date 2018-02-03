package tcloud.msg;

public interface WalletService {
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	double getBalance(String userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	double getAmountToBePayedOut(String userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	double getAmountToBeReceived(String userId);
	
	/**
	 * 
	 * @param userId
	 * @param amount
	 * @return
	 */
	boolean requestPayment(String toUserId, String fromUserId, double amount);
	
	/**
	 * 
	 * @param transactionId
	 * @return
	 */
	boolean makePayment(String transactionId);
}
