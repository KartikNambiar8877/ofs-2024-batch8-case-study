package com.ofss.main.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name="cheques")
public class Cheques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cheque_id")
    private Integer chequeId;

    @Column(name = "chk_account_id")
    private Integer chkAccountId;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "chk_slip_id")
    private Integer chkSlipId;

    // Getters and Setters

    public Integer getChequeId() {
        return chequeId;
    }

    public void setChequeId(Integer chequeId) {
        this.chequeId = chequeId;
    }

    public Integer getChkAccountId() {
        return chkAccountId;
    }

    public void setChkAccountId(Integer chkAccountId) {
        this.chkAccountId = chkAccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Integer getChkSlipId() {
        return chkSlipId;
    }

    public void setChkSlipId(Integer chkSlipId) {
        this.chkSlipId = chkSlipId;
    }

    // Constructors

    public Cheques(Integer chequeId, Integer chkAccountId, String status, Double amount, String payeeName, Integer chkSlipId) {
        super();
        this.chequeId = chequeId;
        this.chkAccountId = chkAccountId;
        this.status = status;
        this.amount = amount;
        this.payeeName = payeeName;
        this.chkSlipId = chkSlipId;
    }

    public Cheques() {
        super();
    }

    @Override
    public String toString() {
        return "Cheques [chequeId=" + chequeId + ", chkAccountId=" + chkAccountId + ", status=" + status + ", amount="
                + amount + ", payeeName=" + payeeName + ", chkSlipId=" + chkSlipId + "]";
    }
}
