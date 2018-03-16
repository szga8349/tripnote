package com.lenovo.tripnote.util.html;

import java.util.ArrayList;
import java.util.List;

class Tag {
	public static final byte TYPE_OPEN = 1;
	public static final byte TYPE_CLOSE = 2;
	public static final byte TYPE_QUICKCLOSE = 3;

	private final List<Attr> attrs = new ArrayList<Attr>();
	private final byte type;
	private final String name;
	private boolean ignore;

	public Tag( String name, byte type ) {
		this.name = name;
		this.type = type;
		this.ignore = false;
	}

	public Tag( String name, byte type, boolean ignore ) {
		this.name = name;
		this.type = type;
		this.ignore = ignore;
	}

	public List<Attr> getAttrs() {
		return attrs;
	}

	public void addAttr( String name, String value, char quoter ) {
		this.attrs.add( new Attr( name, value, quoter ) );
	}

	public byte getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore( boolean ignore ) {
		this.ignore = ignore;
	}

}
