package com.lenovo.tripnote.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class LenovoXMLWorkerHelper {
	
    public static class LenovoFontsProvider extends XMLWorkerFontProvider {
        public LenovoFontsProvider() {
            super(null, null);
        }

        public Font getFont(final String fontname, String encoding, float size, final int style) {  
            try {  
                BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//中文字体  
                Font contentFont = new Font(bfChinese, 12f, Font.NORMAL);
                return contentFont;
            } catch (Exception ex) {  
                return new Font(Font.FontFamily.UNDEFINED, size, style);  
            }  
        }  
    }

    public static ElementList parseToElementList(String html, String css) throws IOException {
        // CSS
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        if (css != null) {
            CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(css.getBytes()));
            cssResolver.addCss(cssFile);
        }

        // HTML
        LenovoFontsProvider fontProvider = new LenovoFontsProvider();
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.autoBookmark(false);

        // Pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, end);
        CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, htmlPipeline);

        // XML Worker
        XMLWorker worker = new XMLWorker(cssPipeline, true);
        XMLParser p = new XMLParser(worker);
        html = html.replace("<br>", "").replace("<hr>", "").replace("<img>", "").replace("<param>", "")
                .replace("<link>", "");
        p.parse(new ByteArrayInputStream(html.getBytes()));
        if(elements.size()==0){//不包含html标签
        	BaseFont bfChinese = null;
			try {
    			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
    		} catch (DocumentException | IOException e) {
    			e.printStackTrace();
    		}
			 html = html.replaceAll("<br>", "").replaceAll("\r\n", "").replaceAll("\n", "");
             Font contentFont = new Font(bfChinese, 12f, Font.NORMAL);
             elements.add(new Paragraph(html,contentFont));
        }
        return elements;
    }

}