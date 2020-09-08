package com.hm2.common.upload;

public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = 558206723001145339L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}