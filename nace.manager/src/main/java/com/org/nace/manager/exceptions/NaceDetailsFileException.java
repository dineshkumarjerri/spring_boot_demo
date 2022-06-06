package com.org.nace.manager.exceptions;

public class NaceDetailsFileException extends RuntimeException {

	
	private String message;
	 private String recordTye;
	 

	public NaceDetailsFileException() {}
 
   public NaceDetailsFileException(String msg, String className)
   {
       super(msg);
       this.message = msg;
       this.recordTye = className;
   }

	 public NaceDetailsFileException(String msg) {
		   super(msg);
	       this.message = msg;
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
