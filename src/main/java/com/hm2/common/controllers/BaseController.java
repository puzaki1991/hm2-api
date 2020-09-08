package com.hm2.common.controllers;

import com.hm2.common.persistencecustom.persistence.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.constants.ProjectConstant;
import com.hm2.common.exceptions.InvalidRequestException;
import com.hm2.common.exceptions.ProcessException;
import com.hm2.common.utils.DateUtils;

import java.util.Locale;

public class BaseController implements AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${default.locale}")
    public String localStr;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BaseService baseService;

    @Override
    public void setMessageSuccess(ResponseData<?> responseData) {

        responseData.setMessage(messageSource.getMessage("default.success", null, getMessageLocal()));
        responseData.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
    }

    @Override
    public void setMessageSuccess(ResponseData<?> responseData, String msg) {
        responseData.setMessage(msg);
        responseData.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
    }

    private void setMessageFail(ResponseData<?> responseData) {
        responseData.setMessage(messageSource.getMessage("default.fail", null, getMessageLocal()));
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
    }

    @Override
    public void setMessageSaveSuccess(ResponseData<?> responseData) {
        responseData.setMessage(messageSource.getMessage("default.save.success", null, getMessageLocal()));
        responseData.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
    }

    @Override
    public void setMessageSaveFail(ResponseData<?> responseData) {
        String errMsg = messageSource.getMessage("default.save.fail", null, getMessageLocal());
        responseData.setMessage(errMsg);
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
        logger.error(errMsg);

    }

    @Override
    public void setMessageDeleteSuccess(ResponseData<?> responseData) {
        responseData.setMessage(messageSource.getMessage("default.delete.success", null, getMessageLocal()));
        responseData.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
    }

    @Override
    public void setMessageDeleteFail(ResponseData<?> responseData) {
        String errMsg = messageSource.getMessage("default.delete.fail", null, getMessageLocal());
        responseData.setMessage(errMsg);
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
        logger.error(errMsg);
    }

    @Override
    public void setMessageUploadFileSuccess(ResponseData<?> responseData) {
        responseData.setMessage(messageSource.getMessage("default.uploadFile.success", null, getMessageLocal()));
        responseData.setStatus(ProjectConstant.ResponseStatus.SUCCESS);
    }

    @Override
    public void setMessageUploadFileFail(ResponseData<?> responseData) {
        String errMsg = messageSource.getMessage("default.uploadFile.fail", null, getMessageLocal());
        responseData.setMessage(errMsg);
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
        logger.error(errMsg);
    }

    @Override
    public void setMessageFail(ResponseData<?> responseData, InvalidRequestException e) {
        String message = "";
        if (ProjectConstant.ProjectLocale.TH.equalsIgnoreCase(localStr))
            message = e.getMessageTh();
        else message = e.getMessageEn();
        String errMsg = messageSource.getMessage(message, null, getMessageLocal());
        responseData.setMessage(errMsg);
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
//        logger.error("Error InvalidRequestException ==> ", e);
    }

    @Override
    public void setMessageFail(ResponseData<?> responseData, ProcessException e) {
        String message = "";
        if (ProjectConstant.ProjectLocale.TH.equalsIgnoreCase(localStr))
            message = e.getMessageTh();
        else message = e.getMessageEn();
        String errMsg = messageSource.getMessage(message, null, getMessageLocal());
        responseData.setMessage(errMsg);
        responseData.setStatus(ProjectConstant.ResponseStatus.FAILED);
//        logger.error("Error ProcessException ==> ", e);
    }

    @Override
    public void setMessageFail(ResponseData<?> responseData, Exception e) {
        setMessageFail(responseData);
        logger.error("Error Exception ==> ", e);
    }

    @Override
    public String getMessage(String msgCode) {
        if (StringUtils.isBlank(msgCode)) return  null;
        return messageSource.getMessage(msgCode, null, getMessageLocal());
    }

    public Locale getMessageLocal() {
        Locale locale = null;
        if ("en".equalsIgnoreCase(localStr))
            locale = Locale.US;
        else
            locale = DateUtils.LOCAL_TH;
        return locale;
    }

    public String setMessage(String msg){
        return  messageSource.getMessage(msg, null, getMessageLocal());
    }

}
