package com.hm2.ums.controllers;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;
import com.hm2.common.exceptions.InvalidRequestException;
import com.hm2.common.exceptions.ProcessException;
import com.hm2.common.utils.UserLoginUtils;
import com.hm2.ums.critrria.UserCriteria;
import com.hm2.ums.entities.User;
import com.hm2.ums.services.UserSerivce;
import com.hm2.ums.vo.EmailVo;
import com.hm2.ums.vo.UserProfileVo;
import com.hm2.ums.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ums/user")
public class UserController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserSerivce userSerivce;

	@GetMapping("/profile") 
	public ResponseData<UserProfileVo> profile() {
		ResponseData<UserProfileVo> responseData = new ResponseData<UserProfileVo>();

		try {
			UserProfileVo userProfile = UserLoginUtils.getCurrentUser();
			String username = UserLoginUtils.getCurrentUsername();
			List<String> roles = UserLoginUtils.getGrantedAuthorityList();
			User user = userSerivce.findByUsername(username);
			
			userProfile.setUsername(username);
			userProfile.setRoles(roles);
			userProfile.setAccessMenu(user.getAccessMenu());
			
			responseData.setData(userProfile);
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/")
	public ResponseData<List<User>> getUserAll() {
		ResponseData<List<User>> responseData = new ResponseData<>();
		try {
			responseData.setData(userSerivce.getUserAll());
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/search")
	public ResponseData<List<User>> searchUser(@RequestParam String username,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
		ResponseData<List<User>> responseData = new ResponseData<List<User>>();
		try {
			UserCriteria criteria = new UserCriteria();
			criteria.setUsername(username);
			criteria.setPage(page);
			criteria.setPageSize(pageSize);
			responseData = userSerivce.searchUser(criteria);
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/{id}")
	public ResponseData<User> findByUserId(@PathVariable long id) {
		ResponseData<User> responseData = new ResponseData<>();
		try {
			responseData.setData(userSerivce.findByUserId(id));
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@PostMapping("/register")
	public ResponseData<UserVo> register(@RequestBody UserVo userVo) {
		ResponseData<UserVo> responseData = new ResponseData<>();
		try {
			logger.info("Method register ==> {}", userVo.toString());
			if (StringUtils.isBlank(userVo.getUser().getUsername()))
				throw new InvalidRequestException("USR_UN0");
			if (StringUtils.isBlank(userVo.getUser().getPassword()))
				throw new InvalidRequestException("UST_PASS0");
			responseData.setData(userSerivce.save(userVo));
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			logger.error("Method register err");
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@PostMapping("/generate-user")
	public ResponseData<UserVo> generateUser(@RequestBody UserVo userVo) {
		ResponseData<UserVo> responseData = new ResponseData<>();
		try {
			logger.info("Method register ==> {}", userVo.toString());
			if (StringUtils.isBlank(userVo.getUser().getUsername()))
				throw new InvalidRequestException("USR_UN0");
			if (StringUtils.isBlank(userVo.getUser().getPassword()))
				throw new InvalidRequestException("UST_PASS0");
			String userCode = "";
	            boolean notUniq = true;
	            while (notUniq) {
	            	userCode = userSerivce.generateUserCode();
	                User entity = userSerivce.findByUser(userCode);
	                if (entity == null) {
	                    notUniq = false;
	                }
	            }
	        userVo.getUser().setUserCode(userCode);   
			responseData.setData(userSerivce.save(userVo));
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			logger.error("Method register err");
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/generate-password/{password}")
	public ResponseData<String> generatePasswordByRequest(@RequestParam String password) {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			if (StringUtils.isBlank(password))
				throw new InvalidRequestException("UST_PASS0");
			responseData.setData(new BCryptPasswordEncoder().encode(password));
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/generate-password")
	public ResponseData<String> generatePassword() {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			logger.info("do generate password ...");
			responseData.setData(userSerivce.generatePassword());
			setMessageSuccess(responseData);
			logger.info("do generate success");
		} catch (Exception e) {
			setMessageFail(responseData, e);
			logger.info("do generate fail");
		}
		return responseData;
	}

	@DeleteMapping("/{id}")
	public ResponseData<User> deleteUser(@PathVariable Long id) {
		ResponseData<User> responseData = new ResponseData<>();
		try {
			userSerivce.deleteUser(id);
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@PostMapping("/change-password")
	public ResponseData<?> changePassword(@RequestBody UserVo userVo) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			if (StringUtils.isBlank(userVo.getPassword()))
				throw new InvalidRequestException("UST_PASS0");
			if (StringUtils.isBlank(userVo.getTel()))
				throw new InvalidRequestException("USR_TEL0");
			userSerivce.changePassword(userVo);
			setMessageSuccess(responseData);
			logger.info("Method changePassword process success {}", userVo.getTel());
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (ProcessException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			setMessageFail(responseData, e);
			logger.error("Method changePassword process fail ", userVo.getTel());
		}
		return responseData;
	}

	@PostMapping("/forgot-password")
	public ResponseData<?> forgotPassword(@RequestBody EmailVo vo) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			userSerivce.forgotPassword(vo);
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/find-forgot-password-key/{key}")
	public ResponseData<User> findForgotPasswordKey(@PathVariable String key) {
		ResponseData<User> responseData = new ResponseData<>();
		try {
			if (StringUtils.isBlank(key))
				throw new InvalidRequestException("USR_KEY0");
			responseData.setData(userSerivce.findForgotPasswordKey(key));
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/has-user-by-tel/{tel}")
	public ResponseData<Boolean> checkUserByTel(@PathVariable String tel) {
		ResponseData<Boolean> responseData = new ResponseData<>();
		try {
			if (StringUtils.isBlank(tel))
				throw new InvalidRequestException("USR_TEL0");
			User user = userSerivce.findByUsername(tel);
			responseData.setData(user!=null);
			setMessageSuccess(responseData);
		} catch (InvalidRequestException e) {
			setMessageFail(responseData, e);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}
	
}