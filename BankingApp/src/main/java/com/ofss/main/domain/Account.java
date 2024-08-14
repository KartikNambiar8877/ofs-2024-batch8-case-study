package com.ofss.main.domain;

import jakarta.persistence.*;
import java.math.*;
import java.sql.Date;

@Entity
@Table(name = "account_info")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")
    private long accountNo;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "min_balance")
    private double minBalance;

    @Column(name = "account_status")
    private String status;

    @Column(name = "branch_no")
    private int branchNo;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "acc_customer_id", nullable = false)
    private int customerID;

    @Column(name = "validity")
    private Integer validity;

    @Column(name = "overdraft_balance")
    private double overdraftBalance;

    @Column(name = "main_balance")
    private double mainBalance;

    // Constructors
    public Account(long accountNo, String accountType, double minBalance, String status, int branchNo, Date openingDate,
                   int customerID, Integer validity, double overdraftBalance, double mainBalance) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.minBalance = minBalance;
        this.status = status;
        this.branchNo = branchNo;
        this.openingDate = openingDate;
        this.customerID = customerID;
        this.validity = validity;
        this.overdraftBalance = overdraftBalance;
        this.mainBalance = mainBalance;
    }

    public Account() {
    }

    // Getters and setters
    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(int branchNo) {
        this.branchNo = branchNo;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public double getOverdraftBalance() {
        return overdraftBalance;
    }

    public void setOverdraftBalance(double overdraftBalance) {
        this.overdraftBalance = overdraftBalance;
    }

    public double getMainBalance() {
        return mainBalance;
    }

    public void setMainBalance(double mainBalance) {
        this.mainBalance = mainBalance;
    }

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + ", minBalance=" + minBalance
				+ ", status=" + status + ", branchNo=" + branchNo + ", openingDate=" + openingDate + ", customerID="
				+ customerID + ", validity=" + validity + ", overdraftBalance=" + overdraftBalance + ", mainBalance="
				+ mainBalance + "]";
	}
    
}
