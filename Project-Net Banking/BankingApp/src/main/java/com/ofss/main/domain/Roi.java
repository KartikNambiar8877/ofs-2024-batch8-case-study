package com.ofss.main.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roi")
public class Roi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROI_ID")
    private int roiId;

    @Column(name = "ROI_account_no")
    private int roiAccountNo;

    @Column(name = "ROI_AccountType")
    private String roiAccountType;

    @Column(name = "Validity")
    private int validity;

    @Column(name = "ROI")
    private double roi;

	public int getRoiId() {
		return roiId;
	}

	public void setRoiId(int roiId) {
		this.roiId = roiId;
	}

	public int getRoiAccountNo() {
		return roiAccountNo;
	}

	public void setRoiAccountNo(int roiAccountNo) {
		this.roiAccountNo = roiAccountNo;
	}

	public String getRoiAccountType() {
		return roiAccountType;
	}

	public void setRoiAccountType(String roiAccountType) {
		this.roiAccountType = roiAccountType;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}

	public Roi(int roiId, int roiAccountNo, String roiAccountType, int validity, double roi) {
		super();
		this.roiId = roiId;
		this.roiAccountNo = roiAccountNo;
		this.roiAccountType = roiAccountType;
		this.validity = validity;
		this.roi = roi;
	}

	public Roi() {
		super();
	}

	@Override
	public String toString() {
		return "Roi [roiId=" + roiId + ", roiAccountNo=" + roiAccountNo + ", roiAccountType=" + roiAccountType
				+ ", validity=" + validity + ", roi=" + roi + "]";
	}
    
    
}
