package com.jack.jackAdvanced.exception;


import com.jack.jackAdvanced.result.CodeMsg;

public class JackException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMsg cm;
	
	public JackException(CodeMsg cm) {
		super(cm.toString());
		this.cm = cm;
	}
	public CodeMsg getCm() {
		return cm;
	}

}
