package com.hm2.common.controllers;

import com.hm2.common.beans.ResponseData;
import com.hm2.common.exceptions.InvalidRequestException;
import com.hm2.common.exceptions.ProcessException;

public interface AbstractController {

    void setMessageSuccess(ResponseData<?> responseData);
    void setMessageSuccess(ResponseData<?> responseData, String msg);

    void setMessageSaveSuccess(ResponseData<?> responseData);
    void setMessageSaveFail(ResponseData<?> responseData);
    
    void setMessageDeleteSuccess(ResponseData<?> responseData);
    void setMessageDeleteFail(ResponseData<?> responseData);
    
    void setMessageUploadFileSuccess(ResponseData<?> responseData);
    void setMessageUploadFileFail(ResponseData<?> responseData);

    //=> handle
    void setMessageFail(ResponseData<?> responseData, InvalidRequestException e);
    void setMessageFail(ResponseData<?> responseData, ProcessException e);
    void setMessageFail(ResponseData<?> responseData, Exception e);

    String getMessage(String msgCode);
}
