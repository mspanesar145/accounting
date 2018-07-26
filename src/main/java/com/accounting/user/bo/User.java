package com.accounting.user.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.accounting.bo.AccountingGeneral;
import com.accounting.bo.MyAccount;
import com.accounting.bo.Roles;
import com.accounting.enums.AccountingEnums.AuthenticateType;
import com.accounting.enums.AccountingEnums.UserType;


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
	
	@Column(name="google_id")
	private String googleId;
	
	@Column(name="google_auth_token")
	private String googleAuthToken;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private UserType type = UserType.user;
	
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
	
	@Transient
	private String deviceToken;
	
	@Transient
	private String deviceType;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	List<Roles> roles;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="created_by_id",insertable=false,updatable=false)
	public List<MyAccount> myAccounts = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",insertable=false,updatable=false)
	public List<UserDevice> userDevices = new ArrayList<>();
	
	
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

	public List<MyAccount> getMyAccounts() {
		return myAccounts;
	}

	public void setMyAccounts(List<MyAccount> myAccounts) {
		this.myAccounts = myAccounts;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getGoogleAuthToken() {
		return googleAuthToken;
	}

	public void setGoogleAuthToken(String googleAuthToken) {
		this.googleAuthToken = googleAuthToken;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public List<UserDevice> getUserDevices() {
		return userDevices;
	}

	public void setUserDevices(List<UserDevice> userDevices) {
		this.userDevices = userDevices;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
}
