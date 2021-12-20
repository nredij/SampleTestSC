package com.sc.utilities.enums;

public enum SCCommonOptions {
 
	GENDER_MALE("Male"),
	GENDER_FEMALE("Female"),
	SELF("Self"),
	SON("Son"),
	DAUGHTER("Daughter"),
	BROTHER("Brother"),
	SISTER("Sister"),
	FRIEND("Friend"),
	RELATIVE("Relative")
;
	
	
	private String enumValue;

	SCCommonOptions(String value) {
		this.enumValue = value;
	}
	SCCommonOptions() {
		
	}
	
	public String getText() {
		return this.enumValue;
	}
}
