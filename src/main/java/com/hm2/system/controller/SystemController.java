
package com.hm2.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;
import com.hm2.common.utils.AppUtils;
import com.hm2.common.utils.DateUtils;
import com.hm2.system.entities.LoginLog;
import com.hm2.system.services.SystemService;
import com.hm2.system.vo.MessageVo;
import com.hm2.system.vo.SystemVo;
import com.hm2.ums.critrria.UserCriteria;
import com.hm2.ums.entities.User;
import com.hm2.ums.repositories.user.UserRepository;

@RestController
@RequestMapping("/system")
public class SystemController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Value("${host}")
	private String host;
	@Value("${host2}")
	private String host2;
	@Value("${host3}")
	private String host3;
	@Value("${host4}")
	private String host4;

	@Value("${timezone}")
	private String timezone;

	@Value("${default.locale}")
	private String defaultLocale;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SystemService systemService;

//	@Autowired
//	private SettingService settingService;
	
	@GetMapping("/get-loginlog-all")
	public ResponseData<List<LoginLog>> getLoginLogAll(@RequestParam String username, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {
		ResponseData<List<LoginLog>> responseData = new ResponseData<List<LoginLog>>();
		try {
			UserCriteria criteria = new UserCriteria();
			criteria.setUsername(username);
			criteria.setPage(page);
			criteria.setPageSize(pageSize);
			logger.info("Method getLoginLogAll criteria => {}", criteria.toString());
			responseData = systemService.searchLoginLog(criteria);
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/default-setting")
	public ResponseData<SystemVo> defaultSetting(HttpServletRequest httpRequest) {
		ResponseData<SystemVo> responseData = new ResponseData<>();
		try {
			SystemVo vo = new SystemVo();
			vo.setTimeZone(timezone);
			vo.setDate(new Date());
			vo.setDateStr(DateUtils.formatDate(new Date(), DateUtils.DD_MM_YYYY_HHMMSS) + "  | (dd/MM/yyyy HH:mm:ss)");
			vo.setIp(AppUtils.getClientIp(httpRequest));
			responseData.setData(vo);

			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/default-locale")
	public ResponseData<String> defaultLocale() {
		ResponseData<String> responseData = new ResponseData<>();
		try {
			responseData.setData(defaultLocale);
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/default-message/{localStr}")
	public ResponseData<MessageVo> defaultMessage(@PathVariable String localStr, HttpServletRequest httpRequest) {
		ResponseData<MessageVo> responseData = new ResponseData<>();
		try {
			Locale locale = null;
			if ("en".equalsIgnoreCase(localStr))
				locale = Locale.US;
			else
				locale = DateUtils.LOCAL_TH;

			MessageVo vo = new MessageVo();
			vo.setSuccess(messageSource.getMessage("default.success", null, locale));
			vo.setFail(messageSource.getMessage("default.fail", null, locale));
			vo.setSaveSuccess(messageSource.getMessage("default.save.success", null, locale));
			vo.setSaveFail(messageSource.getMessage("default.save.fail", null, locale));
			vo.setDeleteSuccess(messageSource.getMessage("default.delete.success", null, locale));
			vo.setDeleteFail(messageSource.getMessage("default.delete.fail", null, locale));
			vo.setUploadSuccess(messageSource.getMessage("default.uploadFile.success", null, locale));
			vo.setUploadFail(messageSource.getMessage("default.uploadFile.fail", null, locale));
			responseData.setData(vo);

			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}
	
	@PostMapping("/log-login")
	public ResponseData<LoginLog> defaultMessage(@RequestBody LoginLog loginLog) {
		ResponseData<LoginLog> responseData = new ResponseData<>();
		try {
			responseData.setData((LoginLog) systemService.saveBaseEntity(loginLog));
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	} 

	@GetMapping("/env-profile-active")
	public ResponseEntity<List<String>> envProfileActive(){
		String[] active = environment.getActiveProfiles();
		List<String> data = new ArrayList<>();
		data.add(active[0]);
		data.add(host);
		data.add(host2);
		data.add(host3);
		data.add(host4);
		return ResponseEntity.ok(data);
	}

	@GetMapping("/test-paging")
	public ResponseEntity findAll(@RequestParam("offset") int offset, @RequestParam("limit") int limit){
		Page<User> page = userRepository.findAll(PageRequest.of(offset, limit));
		return new ResponseEntity(page, HttpStatus.OK);
	}

}
