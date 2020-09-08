package com.hm2.lookup.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.controllers.BaseController;
import com.hm2.lookup.entities.Lookup;
import com.hm2.lookup.services.LookupService;

@RestController
@RequestMapping("/api/lookup")
public class LookupController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LookupService lookupService;

	@GetMapping("/get-by-type/{type}")
	public ResponseData<List<Lookup>> getByType(@PathVariable String type) {
		ResponseData<List<Lookup>> responseData = new ResponseData<List<Lookup>>();
		try {
			logger.info("method= {}, params={}, ", "getLookupValue", type);
			List<Lookup> result = lookupService.getLookupValue(type);
			responseData.setData(result);
			responseData.setRows(result.size());
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/get-by-type-value/{type}/{value}")
	public ResponseData<Lookup> getByTypeValue(@PathVariable String type, @PathVariable String value) {
		ResponseData<Lookup> responseData = new ResponseData<Lookup>();
		try {
			logger.info(" method= {}, params={} {} ", "getLookupValue", type, value);

			responseData.setData(lookupService.getLookupValue(type, value));
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}

	@GetMapping("/get-lookup-all")
	public ResponseData<List<Lookup>> getLookupAll() {
		ResponseData<List<Lookup>> responseData = new ResponseData<List<Lookup>>();
		try {
			logger.info("method= {} ", "getLookupAll");

			responseData.setData(lookupService.getLookupAll());
			setMessageSuccess(responseData);
		} catch (Exception e) {
			setMessageFail(responseData, e);
		}
		return responseData;
	}
}
