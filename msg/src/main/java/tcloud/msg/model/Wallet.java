package tcloud.msg.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Wallet {
	private String walletId;
	private double balance;
	
	// all fulfilled transactions. to be sorted by utc timestamps
	private TreeSet<WalletTransaction> walletTransactions = new TreeSet<WalletTransaction>();


	// all fulfilled transactions. to be sorted by utc timestamps
	private TreeSet<WalletTransaction> payOutTransactions = new TreeSet<WalletTransaction>();
	
	// these needs to be paid out
	private Map<String, WalletTransaction> payOutRequests = new HashMap<String, WalletTransaction>();
	
	// these needs to be received
	private Map<String, WalletTransaction> requestedPayments = new HashMap<String, WalletTransaction>();
	
	public Wallet(String walletId, double balance) {
		this.walletId = walletId;
		this.balance = balance;
	}
	
	public String getWalletId() {
		return walletId;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public Collection<WalletTransaction> getPayOutRequests(){
		return payOutRequests.values();
	}
	
	public Collection<WalletTransaction> getRequestedPayments(){
		return requestedPayments.values();
	}
	
	public void addPayOutTx(WalletTransaction transaction) {
		this.payOutRequests.put(transaction.getTransactionId(), transaction);
	}
	
	public void addRequestedTx(WalletTransaction transaction) {
		this.requestedPayments.put(transaction.getTransactionId(), transaction);
	}
}
