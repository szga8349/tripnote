package com.lenovo.tripnote.util.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class TrimTagsTool extends TrimScriptTool {

	private boolean ignoreBlank = true;

	public TrimTagsTool( Writer out ) {
		super( out );
	}

	@Override
	protected Tag onTagOpenStart( String tagName ) {
		Tag tag = super.onTagOpenStart( tagName );
		if ( tag != null && ("style".equalsIgnoreCase( tagName )) ) {
			tag.setIgnore( true );
		}
		return tag;
	}

	@Override
	protected void onChar( char ch ) {
		if ( ignoreBlank && Character.isWhitespace( ch ) ) {
			return;
		} else {
			super.onChar( ch );
		}
	}

	@Override
	protected void onTagOpenEnd( String tagName ) {
		if ( currentTag.isIgnore() ) {
			return;
		}
		if ( "br".equalsIgnoreCase( tagName ) ) {
			releaseTopTag();
		}
	}

	@Override
	protected void onTagCloseQuickly( String tagName ) {
		if ( currentTag.isIgnore() ) {
			if ( tagName.equalsIgnoreCase( currentTag.getName() ) ) {
				releaseTopTag();
			}
			return;
		}
		releaseTopTag();
	}

	@Override
	protected void onTagClose( String tagName ) {
		if ( currentTag != null && currentTag.isIgnore() ) {
			if ( tagName.equalsIgnoreCase( currentTag.getName() ) ) {
				releaseTopTag();
			}
			return;
		}

		if ( !"br".equalsIgnoreCase( tagName ) && (currentTag != null && currentTag.getName().equals( tagName )) ) {
			releaseTopTag();
		}
	}

	public static void main( String args[] ) throws FileNotFoundException,
			IOException {
		String text = "eee";//StreamUtils.readText( new FileInputStream( new File( "C:\\Users\\kongkp1\\Desktop\\TMP\\test.html" ) ) );
		// String text = StreamUtils.readText( new FileInputStream( new File(
		// "C:\\Users\\kkp\\Desktop\\界面参考\\test2.html" ) ) );

		File outFile = new File( "out.html" );
		FileWriter writer = new FileWriter( outFile );
		try {
			TrimTagsTool ttt = new TrimTagsTool( writer );
			ttt.scan( text );
		} finally {
			writer.close();
		}
	}
}
