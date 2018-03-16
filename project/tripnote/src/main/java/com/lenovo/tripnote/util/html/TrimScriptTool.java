package com.lenovo.tripnote.util.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class TrimScriptTool extends TagSyntaxAnalyzer {

	public TrimScriptTool( Writer out ) {
		super( out );
	}

	@Override
	protected Tag onTagOpenStart( String tagName ) {
		Tag tag = super.onTagOpenStart( tagName );
		if ( tag != null && (
				"frame".equalsIgnoreCase( tagName ) 
				||"iframe".equalsIgnoreCase( tagName )
				||"script".equalsIgnoreCase( tagName )
				) ) {
			tag.setIgnore( true );
		}
		return tag;
	}

	@Override
	protected void onAttr( String attrValue, String attrName, char valueQuoter ) {
		if ( !attrName.startsWith( "on" ) && !attrValue.startsWith( "javascript:" ) && !attrValue.startsWith( "<javascript" ) && !attrValue.startsWith( "<script" )) {
			super.onAttr( attrValue, attrName, valueQuoter );
		}
	}

	public static void main( String args[] ) throws FileNotFoundException,
			IOException {
		// String text = StreamUtils.readText( new FileInputStream( new File(
		// "C:\\Users\\kongkp1\\Desktop\\TMP\\test.html" ) ) );
		String text = "111";//StreamUtils.readText( new FileInputStream( new File( "C:\\Users\\kkp\\Desktop\\界面参考\\test2.html" ) ) );

		File outFile = new File( "C:\\Users\\kkp\\Desktop\\界面参考\\out.html" );
		FileWriter writer = new FileWriter( outFile );
		try {
			TrimScriptTool ttt = new TrimScriptTool( writer );
			ttt.scan( text );
		} finally {
			writer.close();
		}
	}
}
