package com.ofss.main.domain;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_details")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;
    
    @Column(name = "t_datetime")
    private Timestamp transactionDatetime;
    
    @Column(name = "t_amt")
    private double transactionAmount=0;
    
    @Column(name = "t_type")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "sender_acc_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_acc_id")
    private Account receiverAccount;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Timestamp getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Timestamp transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public Transaction(int senderAccId, int receiverAccId, long transactionId, Timestamp transactionDatetime,
			double transactionAmount, String transactionType, Account senderAccount, Account receiverAccount) {
		super();
		this.transactionId = transactionId;
		this.transactionDatetime = transactionDatetime;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
	}

	public Transaction() {
		super();
	}
    
    
    
}



