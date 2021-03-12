package com.example.Agriculture.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "user")
public class DAOUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	private String image;
	@Column
	@NotBlank
	private String email;
	@Column
	private String date_of_birth;
	@Column
	private String gender;
	@Column
	@NotBlank
	private Long phone;
	@Column
	@NotBlank
	private String address;
	@Column
	@NotBlank
	private Long aadhaarNo;
	@Column
	@NotBlank
	@JsonIgnore
	private String password;

	public  DAOUser(long id, String username, String image, @NotBlank String email, String date_of_birth, String gender,
			@NotBlank Long phone, @NotBlank String address, @NotBlank Long aadhaarNo, @NotBlank String password) {
		super();
		this.id = id;
		this.username = username;
		this.image = image;
		this.email = email;
		this.date_of_birth = date_of_birth;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.aadhaarNo = aadhaarNo;
		this.password = password;
	}

	public  DAOUser(String username, @NotBlank String email, @NotBlank Long phone, @NotBlank String address,
			@NotBlank Long aadhaarNo, @NotBlank String password) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.aadhaarNo = aadhaarNo;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", aadhaarNo=" + aadhaarNo + "]";
	}

	public DAOUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(Long aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
