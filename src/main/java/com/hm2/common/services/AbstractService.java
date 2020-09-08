package com.hm2.common.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.constants.ProjectConstant;
import com.hm2.common.exceptions.InvalidRequestException;
import com.hm2.common.exceptions.ProcessException;
import com.hm2.common.utils.UserLoginUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public abstract class AbstractService<I, O> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String processBy;
	private Date processDate;
	private String status = ProjectConstant.ResponseStatus.SUCCESS;
	private String message;
	
	public String getProcessBy() {
		return processBy;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setSuccess(String status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	protected abstract void validate(I req) throws InvalidRequestException;
	protected abstract O doProcess(I req) throws ProcessException;
	protected abstract void logging(I req, String ip);
	
	@Transactional
	public ResponseData<O> process(I req, HttpServletRequest httpReq) {
		this.processBy = UserLoginUtils.getCurrentUsername();
		this.processDate = new Date();
		
		try {
			String ip = this.getClientIp(httpReq);
			
			logger.info("process class : {}, ip : {}, processBy : {}, processDate : {}", 
					this.getClass().getSimpleName(), 
					ip,
					processBy,
					processDate);
			
			validate(req);
			O response = doProcess(req);
			logging(req, ip);

			ResponseData<O> res = new ResponseData<O>();
			res.setData(response);
			res.setMessage(message);
			res.setStatus(status);
			return res;
		} catch (InvalidRequestException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseData<O> res = new ResponseData<O>();
			res.setMessage(e.getMessage());
			res.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
			return res;
		} catch (ProcessException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseData<O> res = new ResponseData<O>();
			res.setMessage(e.getMessage());
			res.setStatus(ProjectConstant.ResponseStatus.SUCCESS);

			return res;
		}
		
	}
	
    public String getClientIp(HttpServletRequest httpReq) {

        String remoteAddr = "";

        if (httpReq != null) {
            remoteAddr = httpReq.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = httpReq.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
    
}
