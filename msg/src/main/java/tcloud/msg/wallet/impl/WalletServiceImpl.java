package tcloud.msg.wallet.impl;

import java.util.Collection;

import tcloud.msg.WalletService;
import tcloud.msg.model.User;
import tcloud.msg.model.WalletEnum;
import tcloud.msg.model.WalletTransaction;
import tcloud.msg.user.UserService;

public class WalletServiceImpl implements WalletService{
	
	private final UserService userService;
	
	public WalletServiceImpl(UserService userService) {
		this.userService = userService;
	}
	
	
	public double getBalance(String userId) {
		User user = this.userService.get(userId);
		if(user != null) {
			return user.getWallet().getBalance();
		}
		return 0; // throw exception if user not found
	}

	public double getAmountToBePayedOut(String userId) {
		double amount = 0;
		User user = this.userService.get(userId);
		if(user != null) {
			Collection<WalletTransaction> transactions = user.getWallet().getPayOutRequests();
			for(WalletTransaction transaction : transactions) {
				amount += transaction.getAmount();
			}
		}
		return amount;
	}

	public double getAmountToBeReceived(String userId) {
		double amount = 0;
		User user = this.userService.get(userId);
		if(user != null) {
			Collection<WalletTransaction> transactions = user.getWallet().getRequestedPayments();
			for(WalletTransaction transaction : transactions) {
				amount += transaction.getAmount();
			}
		}
		return amount;
	}

	public boolean requestPayment(String toUserId, String fromUserId, double amount) {
		if(toUserId.equals(fromUserId)) return false;
		
		User fromUser = userService.get(fromUserId);
		User toUser = userService.get(toUserId);
		if(fromUser != null && toUser != null) {
			WalletTransaction transaction = new WalletTransaction(WalletEnum.PAY_REQUEST, amount, fromUserId, toUserId, System.currentTimeMillis());
			fromUser.getWallet().addPayOutTx(transaction);
			toUser.getWallet().addRequestedTx(transaction);
			return true;
		}
		return false;
	}


	public boolean makePayment(String transactionId) {
		// TODO Auto-generated method stub
		return false;
	}

}
