package com.myliabilities.enums;

public enum LiabilitiesStatusEnum {
	
	WAIT(0,"待付"),SETTLE(1,"结清");
	
	private int value;
	private String name;
	
	private LiabilitiesStatusEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue( int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName( String name) {
		this.name = name;
	}
	
	public static String getName(int value) {
		LiabilitiesStatusEnum[] tmp = LiabilitiesStatusEnum.values();
		for(LiabilitiesStatusEnum t : tmp) {
			if(t.getValue() == value) {
				return t.getName();
			}
		}
		return String.valueOf(value);
	}
	
	public static LiabilitiesStatusEnum get(int value) {
		LiabilitiesStatusEnum[] tmp = LiabilitiesStatusEnum.values();
		for(LiabilitiesStatusEnum t : tmp) {
			if(t.getValue() == value) {
				return t;
			}
		}
		return null;
	}
}
