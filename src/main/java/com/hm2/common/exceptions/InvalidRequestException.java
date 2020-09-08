package com.hm2.common.exceptions;

import lombok.Data;

@Data
public class InvalidRequestException extends Exception {

    private static final long serialVersionUID = 5601149194215346000L;
    private String messageEn;
    private String messageTh;
    private String msg;

    public InvalidRequestException(String message) {
        super(message);
        this.messageEn = message;
        this.messageTh = message;
        this.msg = message;
    }

    public InvalidRequestException(String messageEn, String messageTh) {
        super(messageEn);

        this.messageEn = messageEn;
        this.messageTh = messageTh;
    }

}
