package com.accounting.user.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.accounting.bo.AccountingGeneral;
import com.accounting.bo.MyAccount;
import com.accounting.enums.AccountingEnums.AuthenticateType;


@Entity
@Table(name="users")
public class User extends AccountingGeneral{

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	
	
	@Column(unique=true,nullable=true)
	private String username;
	
	@Column(name="email")
	private String email;
	//@JsonIgnore
	@Column(nullable=true)
	private String password;
	
	
	@Column(name="facebook_id")
	private String facebookID;
	
	
	@Transient
	private AuthenticateType authType;
	
	
	@Column(name="facebook_auth_token")
	private String facebookAuthToken;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="reset_token")
	private String resetToken;
	
	@Column(name="reset_token_created_at")
	private Date resetTokenCreatedAt;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="city")
	private String city;
	
	@Column(name="age")
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name="created_by_id",insertable=false,updatable=false)
	public MyAccount myAccount;
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Transient
	private Boolean isNew;
	
	public String getToken() {
		return token;
	}

	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public AuthenticateType getAuthType() {
		return authType;
	}

	public void setAuthType(AuthenticateType authType) {
		this.authType = authType;
	}

	public String getFacebookAuthToken() {
		return facebookAuthToken;
	}

	public void setFacebookAuthToken(String facebookAuthToken) {
		this.facebookAuthToken = facebookAuthToken;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public Date getResetTokenCreatedAt() {
		return resetTokenCreatedAt;
	}

	public void setResetTokenCreatedAt(Date resetTokenCreatedAt) {
		this.resetTokenCreatedAt = resetTokenCreatedAt;
	}

	public MyAccount getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(MyAccount myAccount) {
		this.myAccount = myAccount;
	}
}
