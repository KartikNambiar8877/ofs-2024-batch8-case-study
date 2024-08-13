package com.ofss.main.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_details")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "f_name", nullable = false)
	private String fName;

	@Column(name = "l_name", nullable = false)
	private String lName;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "passwrd", nullable = false)
	private String password;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "address_line_3")
	private String addressLine3;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private int zip;

	@Column(name = "phone")
	private long phone;

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Column(name = "login_attempts")
	private int loginAttempts;

	@Column(name = "is_admin")
	private boolean isAdmin;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(long customerId, String fName, String lName, String username, String password, String addressLine1,
			String addressLine2, String addressLine3, String city, String state, int zip, long phone, String email,
			String status, int loginAttempts, boolean isAdmin) {
		super();
		this.customerId = customerId;
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.loginAttempts = loginAttempts;
		this.isAdmin = isAdmin;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", fName=" + fName + ", lName=" + lName + ", username=" + username
				+ ", password=" + password + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", addressLine3=" + addressLine3 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone="
				+ phone + ", email=" + email + ", status=" + status + ", loginAttempts=" + loginAttempts + ", isAdmin="
				+ isAdmin + "]";
	}

}
