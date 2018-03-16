package com.lenovo.tripnote.util.html;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;



public class HTMLUtils {
	public static String trimScripts( String html ) {
		if ( html == null ) {
			return null;
		}
		CharArrayWriter out = new CharArrayWriter();
		TrimScriptTool tjt = new TrimScriptTool( out );
		tjt.scan( html );
		return out.toString();
	}

	public static String trimTags( String html ) {
		if ( html == null ) {
			return null;
		}
		CharArrayWriter out = new CharArrayWriter();
		TrimTagsTool tjt = new TrimTagsTool( out );
		tjt.scan( html );
		return out.toString();
	}

	public static void main( String args[] ) throws FileNotFoundException,
			IOException {
		
		String html = FileUtils.readFileToString(new File( "C:\\Users\\shijy2\\Desktop\\ttt.txt" ),"utf-8");
		System.out.println( HTMLUtils.trimTags( html ) );
		System.out.println( HTMLUtils.trimScripts( html ) );
	}
}
