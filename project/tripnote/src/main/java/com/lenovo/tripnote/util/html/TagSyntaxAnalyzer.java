package com.lenovo.tripnote.util.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public abstract class TagSyntaxAnalyzer extends TagLexcalAnalyzer {
	protected final List<Tag> tagStack = new ArrayList<Tag>();
	protected Tag currentTag = null;
	protected Writer out = null;

	protected char lastChar = 0;

	public TagSyntaxAnalyzer( Writer out ) {
		this.out = out;
	}

	@Override
	protected void afterScan() {
		// TODO 处理收尾工作，补全没有匹配的标签
	}

	@Override
	protected void onChar( char ch ) {
		if ( currentTag != null && currentTag.isIgnore() ) {
			return;
		}

		try {
			// 省略点空白字符(注：不考虑JAVASCRIPT情况)
			if ( !Character.isWhitespace( ch ) || !Character.isWhitespace( lastChar ) ) {
				out.write( ch );
			}
			lastChar = ch;
		} catch ( IOException e ) {
		}

	}

	@Override
	protected Tag onTagOpenStart( String tagName ) {
		if ( currentTag != null && currentTag.isIgnore() ) {
			return null;
		}

		currentTag = new Tag( tagName, Tag.TYPE_OPEN );
		tagStack.add( currentTag );
		return currentTag;
	}

	@Override
	protected void onTagOpenEnd( String tagName ) {
		if ( currentTag.isIgnore() ) {
			return;
		}

		outputOpenTagFront( tagName );
		try {
			if ( "br".equalsIgnoreCase( tagName ) ) {
				releaseTopTag();
				out.write( "/>" );
			} else {
				out.write( '>' );
			}
		} catch ( IOException e ) {
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

		try {
			outputOpenTagFront( tagName );
			out.write( "/>" );
		} catch ( IOException e ) {
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

		if ( "br".equalsIgnoreCase( tagName ) ) {
			try {
				// 当作一个独立的<br/>
				out.write( "<br/>" );
			} catch ( IOException e ) {
			}
		} else if ( currentTag != null && currentTag.getName().equals( tagName ) ) {
			try {
				out.write( "</" );
				out.write( tagName );
				out.write( '>' );
			} catch ( IOException e ) {
			}
			releaseTopTag();
		} else {
			// 没有与之匹配的开始TAG，那么丢弃关闭TAG
		}
	}

	@Override
	protected void onAttr( String attrValue, String attrName, char valueQuoter ) {
		if ( currentTag.isIgnore() ) {
			return;
		}
		currentTag.addAttr( attrName, attrValue, valueQuoter );
	}

	private void outputOpenTagFront( String tagName ) {
		try {
			out.write( '<' );
			out.write( tagName );
			List<Attr> attrs = currentTag.getAttrs();
			for ( Attr attr : attrs ) {
				char quoter = attr.getQuoter();
				out.write( ' ' );
				out.write( attr.getName() );
				out.write( '=' );
				out.write( quoter == 0 ? '"' : quoter );
				out.write( attr.getValue() );
				out.write( quoter == 0 ? '"' : quoter );
			}
		} catch ( IOException e ) {
		}
	}

	protected void releaseTopTag() {
		int size = tagStack.size();
		tagStack.remove( --size );
		currentTag = size > 0 ? tagStack.get( size - 1 ) : null;
	}

	public static void main( String args[] ) throws FileNotFoundException,
			IOException {
		// String text = StreamUtils.readText( new FileInputStream( new File(
		// "C:\\Users\\kongkp1\\Desktop\\TMP\\test.html" ) ) );
		String text = "fff";//StreamUtils.readText( new FileInputStream( new File( "C:\\Users\\kkp\\Desktop\\界面参考\\test2.html" ) ) );

		File outFile = new File( "C:\\Users\\kkp\\Desktop\\界面参考\\out.html" );

		BufferedWriter writer = new BufferedWriter( new FileWriter( outFile ) );
		try {
			TagSyntaxAnalyzer ttt = new TagSyntaxAnalyzer( writer ) {
			};
			long cur = System.currentTimeMillis();
			ttt.scan( text );
			System.out.println( System.currentTimeMillis() - cur );
		} finally {
			writer.close();
		}
	}
}
