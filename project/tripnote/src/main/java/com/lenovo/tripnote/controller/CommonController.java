package com.lenovo.tripnote.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
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
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleHotelResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleRCityVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTrafficResultVo;
import com.lenovo.tripnote.export.PdfHeaderFooter;
import com.lenovo.tripnote.service.CommonService;
import com.lenovo.tripnote.service.TTripNoteScheduleService;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;
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
	@Resource
	private TTripnoteScheduleHotelService tTripnoteScheduleHotelService;
	@Resource
	private TTripNoteScheduleService tTripNoteScheduleService;
	
	private String[] num = new String[]{"一","二","三","四","五","六","日"};

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
				setScheduleTripDetail(document,detail);
				document.close();
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private void setScheduleTripDetail(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		 //table2
        PdfPTable table = new PdfPTable(2);
        Font titFont = new Font(bfChinese, 20, Font.NORMAL);
        //设置每列宽度比例   
        int[] width= {4,96};
        table.setWidths(width); 
        table.getDefaultCell().setBorder(0);
        com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.NORMAL);
        PdfPCell cell21 = new PdfPCell(new Paragraph("日程安排",FontChinese16));
        String imagePath2 = "http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
        Image image21 = Image.getInstance(imagePath2); 
        cell21.setBorder(0);
        table.addCell(image21);
        table.addCell(cell21); 
        document.add(table);
        //空行间距
        Paragraph blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
        Date start = detail.getStartDate();
        int j = detail.getTTripNoteSchedules().size();
       
        for(int m=0;m<j;m++){
     	   TTripNoteScheduleResultVo vo = detail.getTTripNoteSchedules().get(m);
     	   //获取日程明细信息
     	   vo = tTripNoteScheduleService.getDetailByKey(vo.getId());
     	   //交通连线
     	   Map<String,TTripnoteScheduleTrafficResultVo> trafficline = new HashMap<String,TTripnoteScheduleTrafficResultVo>();
     	   if(vo.getTraffics()!=null)
     	   for(TTripnoteScheduleTrafficResultVo traffic:vo.getTraffics()){
     		  trafficline.put(traffic.getStartScheduleType()+""+traffic.getStartScheduleTrip(),traffic);
     	   }
     	   
     	   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
     	   PdfPTable table1 = new PdfPTable(4);
     	   width = new int[]{10,15,15,60};
     	   table1.setWidths(width); 
     	   table1.getDefaultCell().setBorder(0);
     	   List<PdfPRow> listRow = table1.getRows();
     	   //单元格
    	   PdfPCell c0 = new PdfPCell(new Paragraph("D"+vo.getIndexdate(),titFont));//单元格内容
    	   c0.setBorder(0);
    	   PdfPCell c1 = new PdfPCell(new Paragraph(TimeUtils.getDateString(current, "MM/dd"),titFont));//单元格内容
    	   c1.setBorder(0);
    	   PdfPCell c2 = new PdfPCell(new Paragraph("周"+num[TimeUtils.getDayofweek(current)-1],titFont));//单元格内容
    	   c2.setBorder(0);
    	   PdfPCell c3 = new PdfPCell(new Paragraph(" ",titFont));//单元格内容
    	   c3.setBorder(0);
    	   PdfPCell cells1[]= new PdfPCell[]{c0,c1,c2,c3};
           PdfPRow row1 = new PdfPRow(cells1);
           listRow.add(row1);
           document.add(table1);
           blankRow1 = new Paragraph(18f, " ", titFont); 
           document.add(blankRow1);
           List<TTripnoteScheduleHotelResultVo> scheduleHotels = vo.getScheduleHotels();
           //打印入住酒店
           if(scheduleHotels!=null)
           for(TTripnoteScheduleHotelResultVo hotel:scheduleHotels){//设置入住酒店
        	   if(hotel.getCheckInType()==1){
        		   table1 = new PdfPTable(2);
             	   width = new int[]{5,95};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph(hotel.getNameCn(),titFont));//单元格内容
           	       c0.setBorder(0);
         	       imagePath2 = "http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
         	       image21 = Image.getInstance(imagePath2); 
         	       table1.addCell(image21);
         	       table1.addCell(c0);
                   document.add(table1);
                   
                   blankRow1 = new Paragraph(18f, " ", titFont); 
                   document.add(blankRow1);
                   
                   table1 = new PdfPTable(2);
             	   width = new int[]{50,50};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph("入住:"+"退房:",titFont));//单元格内容
           	       c0.setBorder(0);
           	       c1 = new PdfPCell(new Paragraph(" ",titFont));//单元格内容
        	       c1.setBorder(0);
         	       table1.addCell(c0);
         	       table1.addCell(c1);
                   document.add(table1);
                   
                   blankRow1 = new Paragraph(18f, " ", titFont); 
                   document.add(blankRow1);
                   
                   table1 = new PdfPTable(2);
             	   width = new int[]{50,50};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph(hotel.getIntroduction(),titFont));//单元格内容
           	       c0.setBorder(0);
           	       imagePath2 = hotel.getImageurl()!=null?hotel.getImageurl():"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
      	           image21 = Image.getInstance(imagePath2); 
         	       table1.addCell(c0);
         	       table1.addCell(image21);
                   document.add(table1);
                   
        	   }
           }
           
           //打印退房酒店
           if(scheduleHotels!=null)
           for(TTripnoteScheduleHotelResultVo hotel:scheduleHotels){
        	   if(hotel.getCheckInType()==2){//设置退房酒店
        		   table1 = new PdfPTable(2);
             	   width = new int[]{5,95};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph(hotel.getNameCn(),titFont));//单元格内容
           	       c0.setBorder(0);
         	       imagePath2 = "http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
         	       image21 = Image.getInstance(imagePath2); 
         	       table1.addCell(image21);
         	       table1.addCell(c0);
                   document.add(table1);
                   
                   blankRow1 = new Paragraph(18f, " ", titFont); 
                   document.add(blankRow1);
                   
                   table1 = new PdfPTable(2);
             	   width = new int[]{50,50};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph("入住:"+"退房:",titFont));//单元格内容
           	       c0.setBorder(0);
           	       c1 = new PdfPCell(new Paragraph(" ",titFont));//单元格内容
        	       c1.setBorder(0);
         	       table1.addCell(c0);
         	       table1.addCell(c1);
                   document.add(table1);
                   
                   blankRow1 = new Paragraph(18f, " ", titFont); 
                   document.add(blankRow1);
                   
                   TTripnoteScheduleHotel hoteldetail = tTripnoteScheduleHotelService.getByKey(hotel.getId());
                   table1 = new PdfPTable(2);
             	   width = new int[]{50,50};
             	   table1.setWidths(width); 
             	   table1.getDefaultCell().setBorder(0);
             	   listRow = table1.getRows();
             	   c0 = new PdfPCell(new Paragraph(hoteldetail.getIntroduction(),titFont));//单元格内容
           	       c0.setBorder(0);
           	       imagePath2 = hoteldetail.getImageurl();
      	           image21 = Image.getInstance(imagePath2); 
         	       table1.addCell(c0);
         	       table1.addCell(image21);
                   document.add(table1);
        	   }
           }
           
        }
	}
	/**设置行程
	 * @param document
	 * @param detail
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void setScheduleTrip(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(4); 
        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        List<PdfPRow> listRow = table.getRows();
        table.getDefaultCell().setBorder(0);
        //设置列宽
        float[] columnWidths = { 25f, 40f,10f,25f};
        table.setWidths(columnWidths);
        Date start = detail.getStartDate();
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titFont = new Font(bfChinese, 20, Font.NORMAL);
        int j = detail.getTTripNoteSchedules().size();
       for(int m=0;m<j;m++){
    	   TTripNoteScheduleResultVo vo = detail.getTTripNoteSchedules().get(m);
    	   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
           //单元格
    	   PdfPCell c0 = new PdfPCell(new Paragraph("D"+vo.getIndexdate()));//单元格内容
    	   c0.setBorder(0);
    	   StringBuffer title = new StringBuffer();
    	   int citys = vo.getCitys().size();
    	   for(int i=0;i<citys;i++){
    		   TTripnoteScheduleRCityVo ci = vo.getCitys().get(i);
    		   if(m==0)
    		      title.append("!!"+ci.getNameCn());
    		   else if(i!=citys-1){
    			   title.append(ci.getNameCn()+"-->"); 
    		   }else if(m==j-1){
    			   title.append("!!"+ci.getNameCn()); 
    		   }else{
    			   title.append(ci.getNameCn()); 
    		   }
    	   }
    	   PdfPCell c1 = new PdfPCell(new Paragraph(title.toString(),titFont));//单元格内容
    	   c1.setBorder(0);
    	   PdfPCell c2 = new PdfPCell(new Paragraph(TimeUtils.getDateString(current, "MM/dd")));//单元格内容
    	   c2.setBorder(0);
    	   PdfPCell c3 = new PdfPCell(new Paragraph("周"+num[TimeUtils.getDayofweek(current)-1],titFont));//单元格内容
    	   c3.setBorder(0);
           PdfPCell cells1[]= new PdfPCell[]{c0,c1,c2,c3};
           PdfPRow row1 = new PdfPRow(cells1);
           listRow.add(row1);
        }
        document.add(table);
        //空行间距
        Paragraph blankRow1 = new Paragraph(40f, " ", titFont); 
        document.add(blankRow1);
       
        //table2
        PdfPTable table2 = new PdfPTable(2);
        //设置每列宽度比例   
        int width21[] = {4,96};
        table2.setWidths(width21); 
        table2.getDefaultCell().setBorder(0);
        com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.NORMAL);
        PdfPCell cell21 = new PdfPCell(new Paragraph("关于这次旅行",FontChinese16));
        String imagePath2 = "http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
        Image image21 = Image.getInstance(imagePath2); 
        cell21.setBorder(0);
        table2.addCell(image21);
        table2.addCell(cell21); 
        document.add(table2);
         //空行间距
        blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
        com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
        //table5
        PdfPTable table5 = new PdfPTable(1);
        PdfPCell cell51 = new PdfPCell(new Paragraph(detail.getIntroduction()+"XXX",FontChinese11));
        cell51.setBorder(0);
        
        String imagePath = /*tripnote.getImageurl()!=null?tripnote.getImageurl():*/"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
        Image image = Image.getInstance(imagePath); 
        cell21.setBorder(0);
        table5.addCell(image);
        table5.addCell(cell51);
        document.add(table5);
        blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
        
	}
	
	
	/**设置表头
	 * @param document
	 * @param tripnote
	 * @throws DocumentException
	 * @throws IOException
	 */
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
        //空行间距
        Paragraph blankRow1 = new Paragraph(40f, " ", titFont); 
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
