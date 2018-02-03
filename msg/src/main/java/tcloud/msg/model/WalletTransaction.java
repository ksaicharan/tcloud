package tcloud.msg.model;

import java.util.UUID;

public class WalletTransaction {
	private WalletEnum operation;
	private String transactionId;
	private double amount;
	private String from; // userId
	private String to; // userId
	private long transactionTime; // utc time stamp
	
	public WalletTransaction(WalletEnum operation, double amount, String from, String to, long timestamp) {
		this.operation = operation;
		this.amount = amount;
		this.from = from;
		this.to= to;
		this.transactionTime = timestamp;
		this.transactionId = UUID.randomUUID().toString(); // auto generated transactionId
	}

	public WalletEnum getOperation() {
		return operation;
	}

	public double getAmount() {
		return amount;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public long getTransactionTime() {
		return transactionTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
