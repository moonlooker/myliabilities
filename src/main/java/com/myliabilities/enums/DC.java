package com.myliabilities.enums;

/**
 * 收支标志
 * @author Pactera
 * 2020年5月25日
 */
public enum DC {

	INCOME(0,"收"),DRAW(1,"支"),LIABILITIES(2,"债"),HOPE(3,"希望");
	
	private int value;
	private String name;
	
	private DC(int value, String name) {
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
		DC[] tmp = DC.values();
		for(DC dc : tmp) {
			if(dc.getValue() == value) {
				return dc.getName();
			}
		}
		return String.valueOf(value);
	}
	
	public static DC get(int value) {
		DC[] tmp = DC.values();
		for(DC dc : tmp) {
			if(dc.getValue() == value) {
				return dc;
			}
		}
		return null;
	}
}
