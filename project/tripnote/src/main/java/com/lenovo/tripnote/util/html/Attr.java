package com.lenovo.tripnote.util.html;

class Attr {
	private final String name;
	private final String value;
	private final char quoter;

	public Attr( String name, String value, char quoter ) {
		this.name = name;
		this.value = value;
		this.quoter = quoter;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public char getQuoter() {
		return quoter;
	}

}
