package com.accounting.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accounting.bo.FacebookProfile;
import com.accounting.constant.AccountingConstants;
import com.accounting.constant.AccountingConstants.ErrorCodes;
import com.accounting.enums.AccountingEnums.AuthenticateType;
import com.accounting.repository.UserRepository;
import com.accounting.service.FacebookService;
import com.accounting.service.UserService;
import com.accounting.stateless.security.TokenAuthenticationService;
import com.accounting.user.bo.User;
import com.accounting.user.bo.UserDevice;
import com.accounting.utils.CenesUtils;
import com.google.common.collect.Sets;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Value("${accounting.imageUploadPath}")
    private String imageUploadPath;

    @Value("${accounting.domain}")
    private String domain;

    @Value("${accounting.salt}")
    private String salt;

    @RequestMapping(value = "/api/users/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> createUser(HttpServletResponse httpServletResponse, @RequestBody User user) {

		User userInfo = null;
		
		UserDevice userDevice = null;
		if (user.getDeviceToken() != null) {
			userDevice = new UserDevice();
			userDevice.setDeviceToken(user.getDeviceToken());
			userDevice.setDeviceType(user.getDeviceType());
		}
		
		// Setting auth type in case it is not passed in the api
		if (user.getAuthType() == null) {
			if (user.getFacebookID() != null) {
				user.setAuthType(AuthenticateType.facebook);
			} else if (user.getEmail() != null) {
				user.setAuthType(AuthenticateType.email);
			}
			if (user.getAuthType() == null) {
				user.setErrorCode(ErrorCodes.AuthTypeNotPresent.getErrorCode());
				user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.AuthTypeNotPresent));
				return new ResponseEntity<User>(user, HttpStatus.PARTIAL_CONTENT);
			}
		}

		if (user.getAuthType().equals(AuthenticateType.email)) {
			if (user.getPassword() == null) {
				user.setErrorCode(ErrorCodes.PasswordNotPresent.getErrorCode());
				user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.PasswordNotPresent));
				return new ResponseEntity<User>(user, HttpStatus.PARTIAL_CONTENT);
			}
			if (user.getEmail() == null) {
				user.setErrorCode(ErrorCodes.EmailNotPresent.getErrorCode());
				user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.EmailNotPresent));
				return new ResponseEntity<User>(user, HttpStatus.PARTIAL_CONTENT);
			}

			System.out.println("[ Date : " + new Date() + " ] ,UserType : Email, Message : Email Type User");
			userInfo = userService.findUserByEmail(user.getEmail());
			if (userInfo == null) {
				System.out.println("[ Date : " + new Date() + " ] ,UserType : Email, Message : New signup request");
				try {
					user.setUsername(
							user.getFirstName().toLowerCase().replaceAll(" ", ".") + System.currentTimeMillis());
					user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), salt));
					user.setToken(establishUserAndLogin(httpServletResponse, user));
					userInfo = userService.saveUser(user);
				} catch (Exception e) {
					e.printStackTrace();
					user.setPassword(null);
					user.setErrorCode(ErrorCodes.EmailAlreadyTaken.getErrorCode());
					user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.EmailAlreadyTaken));
					return new ResponseEntity<User>(user, HttpStatus.OK);
				}
			} else {
				user.setPassword(null);
				user.setErrorCode(ErrorCodes.EmailAlreadyTaken.getErrorCode());
				user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.EmailAlreadyTaken));
				System.out.println("[ Date : " + new Date() + " ] ,UserType : Email, Message : Email Already Exists");
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
			System.out.println("[ Date : " + new Date() + " ] ,UserType : Email, Message : User Details -> "+ userInfo.toString());
			
			//Now saving User Device Info
			saveUserDevice(userDevice,userInfo);
			
			return new ResponseEntity<User>(userInfo, HttpStatus.ACCEPTED);
		} else if (user.getAuthType().equals(AuthenticateType.phone)) {

		} else if (user.getAuthType().equals(AuthenticateType.facebook)) {
			if (user.getFacebookAuthToken() == null) {
				user.setErrorCode(ErrorCodes.FacebookTokenNotPresent.getErrorCode());
				user.setErrorDetail(ErrorCodes.FacebookTokenNotPresent.toString());
				return new ResponseEntity<User>(user, HttpStatus.PARTIAL_CONTENT);
			}
			if (user.getFacebookID() == null) {
				user.setErrorCode(ErrorCodes.FacebookIdNotPresent.getErrorCode());
				user.setErrorDetail(ErrorCodes.FacebookIdNotPresent.toString());
				return new ResponseEntity<User>(user, HttpStatus.PARTIAL_CONTENT);
			}

			User fbUser = userService.findUserByFacebookId(user.getFacebookID());

			if (fbUser == null) {
				FacebookService facebookService = new FacebookService();
				FacebookProfile facebookProfile = facebookService.facebookProfile(user.getFacebookAuthToken());
				if (StringUtils.isEmpty(user.getEmail()) && !StringUtils.isEmpty(facebookProfile.getEmail())) {
					user.setEmail(facebookProfile.getEmail());
				}
				if (StringUtils.isEmpty(user.getFirstName()) && !StringUtils.isEmpty(facebookProfile.getName())) {
					user.setUsername(facebookProfile.getName());
				}
				if (user.getPhoto() == null || StringUtils.isEmpty(user.getPhoto())) {
					if (facebookProfile.getPicture() != null) {
						Map<String, Object> pictureMap = facebookProfile.getPicture();
						Map<String, String> dataMap = (Map<String, String>) pictureMap.get("data");
						user.setPhoto(dataMap.get("url"));
					}
				}

				if (StringUtils.isEmpty(user.getUsername())) {
					user.setUsername(
							user.getFirstName().toLowerCase().replaceAll(" ", ".") + System.currentTimeMillis());
				}

				User dbUser = userService.findUserByEmail(user.getEmail());
				if (dbUser != null) {
					user.setErrorCode(ErrorCodes.EmailAlreadyTaken.getErrorCode());
					user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.EmailAlreadyTaken));
					System.out.println(
							"[ Date : " + new Date() + " ] ,UserType : Facebook, Message : Email Already Exists");
					return new ResponseEntity<User>(user, HttpStatus.OK);
				} else {
					System.out.println("Saving new face book user");
				}
				// user = this.userService.updateUserFromFacebookProfile(user);
			} else {
				user = this.userService.updateUserFromFacebookProfile(fbUser);

			}
			user.setPassword(null);
			if (user.getUsername() == null) {
				String name = "";
				if (user.getFirstName() == null) {
					name = user.getEmail().split("@")[0];
				} else {
					name = user.getFirstName();
				}
				user.setUsername(name + System.currentTimeMillis());
			}
			if (httpServletResponse != null) {
				user.setToken(establishUserAndLogin(httpServletResponse, user));
			}

			// lets save the user
			userInfo = userService.saveUser(user);
			System.out.println("[ Date : " + new Date() + " ] ,UserType : Facebook, Message : User Details -> "+ userInfo.toString());
			
			//Now saving User Device Info
			saveUserDevice(userDevice,userInfo);
			
			return new ResponseEntity<User>(userInfo, HttpStatus.ACCEPTED);
		} else if (user.getAuthType() == AuthenticateType.google) {

			if (!StringUtils.isEmpty(user.getEmail())) {
				User dbUser = userService.findUserByEmail(user.getEmail());
				if (dbUser == null) {
					// user = this.userService.updateUserFromGoogleProfile(user);
					System.out.println("Saving new user using googleplus");
				} else {
					if (dbUser.getGoogleAuthToken() != null) {
						dbUser.setGoogleAuthToken(user.getGoogleAuthToken());
						if (user.getFirstName() != null && !StringUtils.isEmpty(user.getFirstName())
								&& !user.getFirstName().equalsIgnoreCase(dbUser.getFirstName())) {
							dbUser.setFirstName(user.getFirstName());
						}
						if (user.getPhoto() != null && !StringUtils.isEmpty(user.getPhoto())
								&& !user.getPhoto().equalsIgnoreCase(dbUser.getPhoto())) {
							dbUser.setPhoto(user.getPhoto());
						}
						// user = this.userService.updateUserFromGoogleProfile(googleUser);
						user = dbUser;
					} else {
						user.setErrorCode(ErrorCodes.EmailAlreadyTaken.getErrorCode());
						user.setErrorDetail(AccountingConstants.errorMessages.get(ErrorCodes.EmailAlreadyTaken));
						System.out.println(
								"[ Date : " + new Date() + " ] ,UserType : Google, Message : Email Already Exists");
						return new ResponseEntity<User>(user, HttpStatus.OK);
					}
				}
			}

			user.setPassword(null);

			if (user.getUsername() == null) {
				String name = "";
				if (user.getFirstName() == null) {
					name = user.getEmail().split("@")[0];
				} else {
					name = user.getFirstName();
				}
				user.setUsername(name + System.currentTimeMillis());
			}
			if (httpServletResponse != null) {
				user.setToken(establishUserAndLogin(httpServletResponse, user));
			}
			userInfo = userService.saveUser(user);
			System.out.println("[ Date : " + new Date() + " ] ,UserType : Google, Message : User Details -> "
					+ userInfo.toString());
			
			//Now saving User Device Info
			saveUserDevice(userDevice,userInfo);
			
			return new ResponseEntity<User>(userInfo, HttpStatus.ACCEPTED);
		}
		user.setErrorCode(ErrorCodes.InternalServerError.getErrorCode());
		user.setErrorDetail(ErrorCodes.InternalServerError.toString());
		return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    public void saveUserDevice(UserDevice userDevice,User user) {
    	
    	if (userDevice != null) {
    		userDevice.setUserId(user.getUserId());
    		userService.saveUserDeviceInfo(userDevice);
    	}
    }
    
	@ResponseBody
	@RequestMapping(value = "/api/user/update/", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {

		User dbUser = userService.findUserById(user.getUserId());
		dbUser.setFirstName(user.getFirstName());
		dbUser.setEmail(user.getEmail());
		if (user.getPhoto() != null && user.getPhoto().length() > 0) {
			dbUser.setPhoto(user.getPhoto());
		}
		dbUser.setGender(user.getGender());

		Map<String, Object> response = new HashMap<>();
		try {
			dbUser = userRepository.save(dbUser);
			response.put("success", true);
			response.put("data", dbUser);
			response.put("errorCode", 0);
			response.put("errorDetail", null);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("data", "");
			response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
			response.put("errorDetail", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/profile/upload", method = RequestMethod.POST)
	public ResponseEntity<User> uploadImages(@RequestParam("mediaFile") MultipartFile uploadfile,
			@RequestParam("userId") String userId) {

		User user = userRepository.findOne(Long.valueOf(userId));
		String dirPath = imageUploadPath.replaceAll("\\[userId\\]", userId);

		File file = new File(dirPath);
		if (file != null) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				for (File f : files) {
					if (f.isFile() && f.exists()) {
						f.delete();
						System.out.println("successfully deleted");
					} else {
						System.out.println("cant delete a file due to open or error");
					}
				}
			}
		}

		InputStream inputStream = null;
		OutputStream outputStream = null;
		String extension = uploadfile.getOriginalFilename().substring(
				uploadfile.getOriginalFilename().trim().lastIndexOf("."), uploadfile.getOriginalFilename().length());

		String fileName = UUID.randomUUID().toString() + extension;

		File f = new File(dirPath);
		if (!f.exists()) {
			try {
				f.mkdirs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File newFile = new File(dirPath + fileName);
		try {
			inputStream = uploadfile.getInputStream();

			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

			String profilePicUrl = "http://" + domain + "/assets/uploads/" + userId + "/profile/" + fileName;
			user.setPhoto(profilePicUrl);
			userService.saveUser(user);

		} catch (Exception e) {
			e.printStackTrace();
			user = new User();
			user.setErrorCode(HttpStatus.BAD_REQUEST.ordinal());
			user.setErrorDetail(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

    @RequestMapping(value = "/api/user/friends", method = RequestMethod.GET)
    @ResponseBody
    /*
	 * public ResponseEntity<List<UserFriend>>
	 * getUserFriends(@RequestParam("user_id") Long userId) {
	 * 
	 * List<UserFriend> friends = userFriendRepository.findByFriendId(userId, null);
	 * return new ResponseEntity<List<UserFriend>>(friends,HttpStatus.OK); }
	 */ 
    public ResponseEntity<List<User>> getUserFriends(@RequestParam("user_id") Long userId) {
		List<User> friends = null;
		friends = (List) userRepository.findAll();
		List<User> userFriends = new ArrayList<>();
		for (User user : friends) {
			if (user.getUserId().equals(userId)) {
				continue;
			}
			userFriends.add(user);
		}
		return new ResponseEntity<List<User>>(userFriends, HttpStatus.OK);
	}

    @RequestMapping(value = "/auth/forgetPassword", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getForgetPassword(@RequestParam("email") String email) {

		User user = null;
		Map<String, Object> response = new HashMap<>();
		try {
			if (email != null && !email.isEmpty()) {
				user = userService.findUserByEmail(email);
				if (user != null) {

					String resetPasswordToken = CenesUtils.getAlphaNumeric(40);
					user.setResetToken(resetPasswordToken);
					user.setResetTokenCreatedAt(new Date());
					user = userService.saveUser(user);

					response.put("success", true);
				} else {
					response.put("success", false);
					response.put("errorDetail", "Email does not exist");
				}
				response.put("errorCode", 0);
			} else {
				response.put("success", false);
				response.put("errorCode", 0);
				response.put("errorDetail", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", false);
			response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
			response.put("errorDetail", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

    @RequestMapping(value = "/auth/updatePassword/{email}/{password}", method = RequestMethod.GET)
    public ResponseEntity<User> updatePassword(@PathVariable("email") String email,
	    @PathVariable("password") String newPassword, User user) {
	Map<String, Object> response = new HashMap<>();
	try {
	    if (email != null && !email.isEmpty()) {
		user = userService.findUserByEmail(email);
		if (user != null) {
		    user.setPassword(new Md5PasswordEncoder().encodePassword(newPassword, salt));
		    user = userService.saveUser(user);
		    response.put("success", true);
		    response.put("data", user);
		} else {
		    response.put("success", false);
		    response.put("data", null);
		}
		response.put("errorCode", 0);
		response.put("errorDetail", null);

	    } else {
		response.put("success", false);
		response.put("data", null);
		response.put("errorCode", 0);
		response.put("errorDetail", null);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    response.put("success", false);
	    response.put("data", null);
	    response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
	    response.put("errorDetail", HttpStatus.INTERNAL_SERVER_ERROR.toString());
	}
	ResponseEntity<User> responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
	return responseEntity;
    }

    @RequestMapping(value = "/auth/validateResetToken", method = RequestMethod.GET)
    public User validateResetToken(String resetToken) {
	try {
	    User user = userService.findUserByResetToken(resetToken);
	    return user;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    private String establishUserAndLogin(HttpServletResponse response, User email) {
	// Find user, create if necessary
	org.springframework.security.core.userdetails.User user;

	user = new org.springframework.security.core.userdetails.User(email.getUsername(), UUID.randomUUID().toString(),
		Sets.<GrantedAuthority>newHashSet());
	// userService.addUser(user);
	com.accounting.stateless.security.UserAuthentication authentication = new com.accounting.stateless.security.UserAuthentication(
		user, email);
	String token = tokenAuthenticationService.addAuthentication(response, authentication);
	return token;
    }
}
