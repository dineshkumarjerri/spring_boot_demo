package com.org.nace.manager.exceptions;

public class RecordAlreadyExistsException extends RuntimeException{
	
	
	 private String message;
	 private String recordTye;
	 

	public RecordAlreadyExistsException() {}
  
    public RecordAlreadyExistsException(String msg, String className)
    {
        super(msg);
        this.message = msg;
        this.recordTye = className;
    }

	 public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecordTye() {
		return recordTye;
	}

	public void setRecordTye(String recordTye) {
		this.recordTye = recordTye;
	}

}
