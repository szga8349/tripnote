package com.lenovo.tripnote.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.export.PdfHeaderFooter;
import com.lenovo.tripnote.service.CommonService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.util.TimeUtils;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/common")
@Log4j
public class CommonController {

	@Resource
	private CommonService commonService;
	@Resource
	private TTripnoteService tTripnoteService;

	@RequestMapping(value = "/upload/{model}/image", method = RequestMethod.POST)
	public @ResponseBody void uploadImage(@PathVariable String model, HttpServletRequest request,HttpServletResponse response) {
		ResultVo vo = new ResultVo();
		try {
			Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
			MultipartHttpServletRequest multipartRequest = null;
			if(request instanceof MultipartHttpServletRequest){
				 multipartRequest = (MultipartHttpServletRequest)request;
			}
			else if(request instanceof ShiroHttpServletRequest){
				ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;
				CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
				 multipartRequest = commonsMultipartResolver
						.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());
				
			}
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			vo.setCode(Result.SUCESSFUL);
			String uploadurl = commonService.upload(account, files,model);
			if(uploadurl!=null){//上传成功将url返回给客户端 按照客户端要求返回
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				//response.setHeader("Content-type", "application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				// 这句话的意思，使得放入流的数据是utf8格式
				result.put("link",uploadurl);
				ps.write(result.toString().getBytes("UTF-8"));
				ps.close();
			}else{
				JSONObject result = new JSONObject();
				response.setContentType("application/json;charset=UTF-8");
				OutputStream ps = response.getOutputStream();
				ps.write(result.toString().getBytes("UTF-8"));
				ps.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			vo.setCode(Result.FAUL);
			vo.setMessage(e.getMessage());
		}

	}
	@RequestMapping(value = "/export/{id}")
	public  void export(@PathVariable String id,HttpServletResponse response){
		TTripNote tripnote = tTripnoteService.getByKey(Integer.valueOf(id));
		TTripNoteDetailResultVo detail = tTripnoteService.getDetailByKey(Integer.valueOf(id));
			try {
				Document document = getPdfDocument("fileName.pdf",response);
				setHeader(document,tripnote);
				setScheduleTrip(document,detail);
				document.close();
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private void setScheduleTrip(Document document,TTripNoteDetailResultVo detail) throws DocumentException{
		PdfPTable table = new PdfPTable(4); 
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        List<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f, 1f,1f,1f};
        table.setWidths(columnWidths);
        PdfPCell cells1[]= new PdfPCell[4];
        //单元格
        cells1[0] = new PdfPCell(new Paragraph("11121"));//单元格内容
        cells1[1] = new PdfPCell(new Paragraph("11121"));//单元格内容
        cells1[2] = new PdfPCell(new Paragraph("11121"));//单元格内容
        cells1[3] = new PdfPCell(new Paragraph("11121"));//单元格内容
        cells1[0].setPaddingLeft(20);//左填充20
        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        PdfPRow row1 = new PdfPRow(cells1);
        listRow.add(row1);
        document.add(table);
	}
	
	
	private void setHeader(Document document,TTripNote tripnote) throws DocumentException, IOException{
	    //设置标题
        document.addTitle(tripnote.getTitle());
        document.open();
        //设置title
        Image image1 = Image.getInstance(/*tripnote.getImageurl()!=null?tripnote.getImageurl():*/"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png");
        //设置图片位置的x轴和y周
        //image1.setAbsolutePosition(100f, 550f);
        //设置图片的宽度和高度
        image1.scaleAbsolute(500, 300);
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titFont = new Font(bfChinese, 20, Font.NORMAL);
        Paragraph header = new Paragraph(tripnote.getTitle(), titFont);
        image1.setAlignment(Image.ALIGN_CENTER);
        header.setAlignment(1);
        header.add(image1);
        document.add(header);
        
        header = new Paragraph(TimeUtils.getDateString(tripnote.getStartDate(), "yyyy.MM.dd")+"-"+TimeUtils.getDateString(tripnote.getEndDate(), "yyyy.MM.dd"), titFont);
        header.setAlignment(1);
        document.add(header);
        
        Paragraph line = new Paragraph("------行程路线-----", titFont);
        line.setAlignment(1);
        document.add(line);
        Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		line = new Paragraph((account!=null?account.getLoginName():"系统默认")+"为您定制行程",titFont);
		line.setAlignment(1);
        document.add(line);    
        Paragraph blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
        
	}
	private Document getPdfDocument (String fileName,HttpServletResponse response) throws DocumentException, IOException{
		    Document document = new Document();
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/pdf;charset=utf-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
	        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
	        writer.setStrictImageSequence(true); 
	        PdfHeaderFooter footer = new PdfHeaderFooter();
	        Subject subject = SecurityUtils.getSubject();
			BAccount account = (BAccount) subject.getPrincipal();
	        footer.setHeader((account!=null?account.getLoginName():"系统默认")+"为您定制行程");
	        writer.setPageEvent(footer);
	        return document;
	}
	
	public static void printNoCache(HttpServletResponse resp ,String str,Charset charset,boolean gzip){
        byte[] bb = str.getBytes(charset);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        try {
            resp.getOutputStream().write(bb);
            resp.flushBuffer();
        } catch (IOException e) {
            log.fatal(e);
        }
    }
}
