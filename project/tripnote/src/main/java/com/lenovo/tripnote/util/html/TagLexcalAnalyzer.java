package com.lenovo.tripnote.util.html;

public abstract class TagLexcalAnalyzer {

	protected void beforeScan() {
	}

	protected void afterScan() {
	}

	protected abstract void onChar( char ch );

	protected abstract Tag onTagOpenStart( String tagName );

	protected abstract void onTagOpenEnd( String tagName );

	protected abstract void onTagClose( String tagName );

	protected abstract void onTagCloseQuickly( String tagName );

	protected abstract void onAttr( String attrValue, String attrName, char valueQuoter );

	public void scan( CharSequence tagText ) {
		beforeScan();
		if ( tagText == null || tagText.length() == 0 )
			return;

		StringBuilder tagName = new StringBuilder();
		StringBuilder attName = new StringBuilder();
		StringBuilder attVal = new StringBuilder();
		char attrQuoter = 0;

		int i = 0, length = tagText.length(), state = PLAIN;
		out: while ( state != DONE ) {
			char ch = i < length ? tagText.charAt( i++ ) : 0;
			switch ( state ) {
				case PLAIN:
					if ( ch == '<' ) {
						state = OPEN_TAG_START;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						onChar( ch );
					}
					break;
				case OPEN_TAG_START:
					if ( ch == '/' ) {
						state = CLOSE_TAG_START;
					} else if ( ch == '!' ) {
						state = ANNOTATION_TAG_START;
					} else if ( isTagNameStartChar( ch ) ) {
						tagName.delete( 0, tagName.length() );
						tagName.append( ch );
						state = OPEN_TAG_NAME;
					} else if ( ch == '?' ) {
						state = TAG_SKIP;
					} else if ( ch == 0 ) {
						onChar( '<' );
						state = DONE;
					} else {
						onChar( '<' );
						onChar( ch );
						state = PLAIN;
					}
					break;
				case CLOSE_TAG_START:
					if ( isTagNameStartChar( ch ) ) {
						tagName.delete( 0, tagName.length() );
						tagName.append( ch );
						state = CLOSE_TAG_NAME;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = TAG_SKIP;
					}
					break;
				case ANNOTATION_TAG_START:
					if ( ch == '-' ) {
						state = ANNOTATION_TAG_START_1;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = TAG_SKIP;
					}
					break;
				case ANNOTATION_TAG_START_1:
					if ( ch == '-' ) {
						state = ANNOTATION;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = TAG_SKIP;
					}
					break;
				case ANNOTATION:
					if ( ch == '-' ) {
						state = ANNOTATION_END;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						// TODO notify annotation
					}
					break;
				case ANNOTATION_END:
					if ( ch == '-' ) {
						state = ANNOTATION_END_1;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = ANNOTATION;
					}
					break;
				case ANNOTATION_END_1:
					if ( ch == '>' ) {
						state = PLAIN;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = ANNOTATION;
					}
					break;
				case OPEN_TAG_NAME:
					if ( ch == '/' ) {
						onTagOpenStart( tagName.toString() );
						state = QUICK_CLOSE_TAG;
					} else if ( ch == '>' ) {
						String tag = tagName.toString();
						onTagOpenStart( tag );
						onTagOpenEnd( tag );
						state = PLAIN;
					} else if ( ch == 0 ) {
						onTagOpenStart( tagName.toString() );
						state = DONE;
					} else if ( Character.isWhitespace( ch ) ) {
						onTagOpenStart( tagName.toString() );
						state = ATTR_START;
					} else {
						tagName.append( ch );
					}
					break;
				case CLOSE_TAG_NAME:
					if ( ch == 0 ) {
						onTagClose( tagName.toString() );
						state = DONE;
					} else if ( ch == '>' ) {
						onTagClose( tagName.toString() );
						state = PLAIN;
					} else if ( Character.isWhitespace( ch ) ) {
						onTagClose( tagName.toString() );
						state = TAG_SKIP;
					} else {
						tagName.append( ch );
					}
					break;
				case TAG_SKIP:
					if ( ch == '>' ) {
						state = PLAIN;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						// dismiss illegal char
					}
					break;
				case ATTR_START:
					if ( ch == '/' ) {
						state = QUICK_CLOSE_TAG;
					} else if ( ch == '>' ) {
						onTagOpenEnd( tagName.toString() );
						state = PLAIN;
					} else if ( ch == 0 ) {
						state = DONE;
					} else if ( Character.isWhitespace( ch ) ) {
						break;
					} else {
						attName.delete( 0, attName.length() );
						attName.append( ch );
						state = ATTR;
					}
					break;
				case ATTR:
					if ( ch == '/' ) {
						onAttr( "", attName.toString(), attrQuoter );
						state = QUICK_CLOSE_TAG;
					} else if ( ch == '>' ) {
						onAttr( "", attName.toString(), attrQuoter );
						onTagOpenEnd( tagName.toString() );
						state = PLAIN;
					} else if ( ch == 0 ) {
						onAttr( "", attName.toString(), attrQuoter );
						state = DONE;
					} else if ( ch == '=' ) {
						attVal.delete( 0, attVal.length() );
						state = ATTR_VALUE_START;
					} else if ( Character.isWhitespace( ch ) ) {
						state = ATTR_VALUE_PRE_START;
					} else {
						attName.append( ch );
					}
					break;
				case ATTR_VALUE_PRE_START:
					if ( ch == '=' ) {
						state = ATTR_VALUE_START;
					} else if ( ch == '>' ) {
						onAttr( "", attName.toString(), attrQuoter );
						onTagOpenEnd( tagName.toString() );
						state = PLAIN;
					} else if ( ch == 0 ) {
						onAttr( "", attName.toString(), attrQuoter );
						state = DONE;
					} else if ( Character.isWhitespace( ch ) ) {
						break;
					} else if ( ch == '/' ) {
						onAttr( "", attName.toString(), attrQuoter );
						state = QUICK_CLOSE_TAG;
					} else {
						onAttr( "", attName.toString(), attrQuoter );
						attName.delete( 0, attName.length() );
						attName.append( ch );
						state = ATTR;
					}
					break;
				case ATTR_VALUE_START:
					if ( Character.isWhitespace( ch ) ) {
						break;
					} else if ( ch == 0 ) {
						onAttr( "", attName.toString(), attrQuoter );
						state = DONE;
					} else if ( ch == '>' ) {
						onAttr( "", attName.toString(), attrQuoter );
						onTagOpenEnd( tagName.toString() );
						state = PLAIN;
					} else if ( ch == '"' || ch == '\'' ) {
						attrQuoter = ch;
						attVal.delete( 0, attVal.length() );
						state = ATTR_VALUE;
					} else {
						attrQuoter = 0;
						attVal.delete( 0, attVal.length() );
						attVal.append( ch );
						state = ATTR_VALUE;
					}
					break;
				case ATTR_VALUE:
					if ( ch == 0 ) {
						onAttr( attVal.toString(), attName.toString(), attrQuoter );
						state = DONE;
					} else if ( ch == attrQuoter ) {
						onAttr( attVal.toString(), attName.toString(), attrQuoter );
						state = ATTR_START;
					} else if ( ch == '>' && attrQuoter == 0 ) {
						onAttr( attVal.toString(), attName.toString(), attrQuoter );
						onTagOpenEnd( tagName.toString() );
						state = PLAIN;
					} else if ( attrQuoter == 0 && Character.isWhitespace( ch ) ) {
						onAttr( attVal.toString(), attName.toString(), attrQuoter );
						state = ATTR_START;
					} else {
						attVal.append( ch );
					}
					break;
				case QUICK_CLOSE_TAG:
					if ( ch == '>' ) {
						onTagCloseQuickly( tagName.toString() );
						state = PLAIN;
					} else if ( ch == 0 ) {
						state = DONE;
					} else {
						state = ATTR_START;
					}
					break;
				case DONE:
					break out;
			}
		}

		afterScan();
	}

	private boolean isTagNameStartChar( char ch ) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}

	public final static int PLAIN = 0;

	public final static int OPEN_TAG_START = 1;

	public final static int OPEN_TAG_NAME = 2;

	public final static int CLOSE_TAG_START = 3;

	public final static int CLOSE_TAG_NAME = 4;

	public final static int ATTR = 5;

	public final static int ATTR_VALUE = 6;

	public final static int QUICK_CLOSE_TAG = 7;

	public final static int ATTR_VALUE_PRE_START = 8;

	public final static int ATTR_VALUE_START = 9;

	public final static int ATTR_START = 10;

	public final static int DONE = 11;

	public final static int TAG_SKIP = 12;

	public final static int ANNOTATION_TAG_START = 13;

	public final static int ANNOTATION_TAG_START_1 = 14;

	public final static int ANNOTATION = 15;

	public final static int ANNOTATION_END = 16;

	public final static int ANNOTATION_END_1 = 17;

}
