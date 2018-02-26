package com.lenovo.tripnote.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.itextpdf.text.BaseColor;
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
import com.lenovo.tripnote.entity.TTripnoteScheduleHotel;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleHotelResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleRCityVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTrafficResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripResultVo;
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
	
	private String imagePrefix = null;
	
	private float lineSpacing = 1.0f;
	
	private   BaseFont bfChinese;
	
	private  Font contentFont;
	
	private  Font firstTitleFont; 
	
	private  Font secondTitleFont; 
	
	private long cutLength = 200;
	
	public CommonController(){
         try {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         contentFont = new Font(bfChinese, 14, Font.NORMAL);
         firstTitleFont = new Font(bfChinese, 24, Font.NORMAL);
         firstTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
         secondTitleFont = new Font(bfChinese, 22, Font.NORMAL);
         secondTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
	}
    


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
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/export/{id}")
	public  void export(@PathVariable String id,HttpServletRequest request,HttpServletResponse response){
		TTripNote tripnote = tTripnoteService.getByKey(Integer.valueOf(id));
		TTripNoteDetailResultVo detail = tTripnoteService.getDetailByKey(Integer.valueOf(id));
			try {
			   contentFont = new Font(bfChinese, 14, Font.NORMAL);
			   lineSpacing = 1.5f;
			   firstTitleFont = new Font(bfChinese, 24, Font.NORMAL);
			   firstTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
			   secondTitleFont = new Font(bfChinese, 22, Font.NORMAL);
		       secondTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
		       cutLength = 200;
			   if(imagePrefix==null)
				 imagePrefix = request.getRealPath("/")+File.separator+"static"+File.separator;
				Document document = getPdfDocument(tripnote.getTitle()+".pdf",response);
				setHeader(document,tripnote);
				setScheduleTrip(document,detail);
				setScheduleTripDetail(document,detail);
				setRemark(document,tripnote);
				setEndImage(document);
				document.close();
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private void setEndImage(Document document) throws DocumentException, IOException{
		
		//table2
	     PdfPTable table5 = new PdfPTable(1);
	   //设置每列宽度比例   
	     int width[] = {100};
	     table5.setWidths(width);
	     //table5.setWidthPercentage(100); // 宽度100%填充
	     table5.getDefaultCell().setBorder(0);
	     String imagePath2 = imagePrefix+"END_结束语.PNG";
	     PdfPCell cell51 = new PdfPCell();
	     cell51.setBorder(0);
	     Image image21 = Image.getInstance(imagePath2); 
	     cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
	     cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
	     cell51.addElement(image21);
	     table5.addCell(cell51);
	     document.add(table5);
	}
	private void setRemark(Document document,TTripNote tripnote) throws DocumentException, IOException{
		Font titFont = new Font(bfChinese, 14, Font.NORMAL);
		Paragraph blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
		//table2
        PdfPTable table2 = new PdfPTable(2);
        //设置每列宽度比例   
        int width21[] = {8,90};
        table2.setWidths(width21); 
        table2.getDefaultCell().setBorder(0);
        table2.setWidthPercentage(100); // 宽度100%填充
        PdfPCell cell21 = new PdfPCell(new Paragraph("行程备注",firstTitleFont));
        String imagePath2 = imagePrefix+"icon_行程安排.PNG";
        Image image21 = Image.getInstance(imagePath2); 
        cell21.setBorder(0);
        table2.addCell(image21);
        table2.addCell(cell21); 
        document.add(table2);
      /*   //空行间距
        Paragraph blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);*/
        //com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
        //table5
        PdfPTable table5 = new PdfPTable(1);
        Paragraph ph = new Paragraph(this.removeHtml(tripnote.getRemarks()),contentFont);
        PdfPCell cell51 = new PdfPCell(ph);
        cell51.setLeading(lineSpacing, lineSpacing);
        cell51.setBorder(0);
        cell51.setIndent(26);
        table5.addCell(cell51);
        document.add(table5);
	}
	private void setScheduleTripDetail(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		 //table2
        PdfPTable table = new PdfPTable(2);
       
        //Font contentFont =  new Font(bfChinese, 12, Font.NORMAL);
        //设置每列宽度比例   
        int[] width= {8,92};
        table.setWidths(width); 
        table.setWidthPercentage(100); // 宽度100%填充
        table.getDefaultCell().setBorder(0);
        PdfPCell cell21 = new PdfPCell(new Paragraph("日程安排",firstTitleFont));
        String imagePath2 = imagePrefix+"icon_标题.PNG";
        Image image21 = Image.getInstance(imagePath2); 
        cell21.setBorder(0);
        
        PdfPCell cell22 = new PdfPCell();
        cell22.addElement(image21);
        cell22.setBorder(0);
        cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell22);
        table.addCell(cell21); 
  
        //空行间距
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
     	   
     	   PdfPTable tabletop = new PdfPTable(3);
     	   width = new int[]{6,8,78};
     	   tabletop.setWidths(width); 
     	   ArrayList<PdfPRow> rows = tabletop.getRows();
     	   Font titFont = new Font(bfChinese, 22, Font.NORMAL);
     	   titFont.setColor(0x22,0xa9,0x8e);
     	   //单元格
    	   PdfPCell cc0 = new PdfPCell(new Paragraph("D"+vo.getIndexdate(),titFont));//单元格内容
    	   cc0.setBorder(0);
    	   titFont = new Font(bfChinese, 13, Font.NORMAL);
     	   titFont.setColor(0x72,0x7f,0x90);
    	   PdfPCell cc1 = new PdfPCell(new Paragraph(TimeUtils.getDateString(current, "MM/dd"),titFont));//单元格内容
    	   cc1.setBorder(0);
    	   titFont = new Font(bfChinese, 13, Font.NORMAL);
     	   titFont.setColor(0x72,0x7f,0x90);
    	   PdfPCell cc2 = new PdfPCell(new Paragraph("周"+num[TimeUtils.getDayofweek(current)-1],titFont));//单元格内容
    	   cc2.setBorder(0);
    	   
    	   cc1.setVerticalAlignment(Element.ALIGN_BOTTOM);
    	   cc1.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   cc2.setHorizontalAlignment(Element.ALIGN_LEFT);
    	   cc2.setVerticalAlignment(Element.ALIGN_BOTTOM);
    	   cc0.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	  
    	   rows.add(new PdfPRow(new PdfPCell[]{cc0,cc1,cc2}));
    	   tabletop.setHorizontalAlignment(Element.ALIGN_MIDDLE);
           PdfPCell tt1 = new PdfPCell();//单元格内容
           tt1.setBorder(0);
           tt1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
           table.addCell(tt1);
           table.addCell(tabletop);
           
           int count = 1;
           String key = "";
           // 设置入住酒店信息
           key = setPrintHotel(vo,table,1,count);
           if(!StringUtils.isEmpty(key))
           {
        	   count++;
           }
           TTripnoteScheduleTrafficResultVo traffic = trafficline.get(key);
           if(traffic!=null){
        	   count = setPrintTraffic(traffic,table,count);
           }
           List<TTripnoteScheduleTripResultVo> trips = vo.getScheduletrips();
           if(trips!=null)
        	 for(TTripnoteScheduleTripResultVo trip:trips){
        	   PdfPTable tablename = new PdfPTable(2);
           	   width = new int[]{5,95};
           	   tablename.setWidths(width); 
               tablename.getDefaultCell().setBorder(0);
               PdfPCell c0 = new PdfPCell(new Paragraph(trip.getNameCn(),secondTitleFont));//单元格内容
         	   c0.setBorder(0);
         	   
         	   PdfPCell image = new PdfPCell();//单元格内容
     	       image.setBorder(0);
       	       imagePath2 = imagePrefix+getPoiImage(trip.getType());//"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
       	       image21 = Image.getInstance(imagePath2); 
       	       image21.setAlignment(1);
       	       image.addElement(image21);
       	       
       	       image.setVerticalAlignment(Element.ALIGN_MIDDLE);
       	       c0.setVerticalAlignment(Element.ALIGN_MIDDLE);
 	           tablename.addCell(image);
 	           tablename.addCell(c0);

       	       
       	       PdfPCell image1 = new PdfPCell();
	           image1.addElement(Image.getInstance(imagePrefix+getImage(count)));
	           image1.setBorder(0);
	           
	           table.addCell(image1);
       	       table.addCell(tablename);
       	       
              /* blankRow1 = new Paragraph(18f, " ", titFont); 
               document.add(blankRow1);*/
               if(StringUtils.isEmpty(trip.getAddressInstrations()) || trip.getAddressInstrations().length()>cutLength){
            	        PdfPTable table1 = new PdfPTable(1);
						width = new int[] {100};
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						
						Paragraph ph = new Paragraph(removeHtml(trip.getAddressInstrations()), contentFont);
						//ph.setFirstLineIndent(20f);
						PdfPCell c2 = new PdfPCell(ph);// 单元格内容
						c2.setBorder(0);
						c2.setLeading(lineSpacing, lineSpacing);
						c2.setVerticalAlignment(Element.ALIGN_LEFT);
						c2.setIndent(26);
						
						PdfPCell image2 = new PdfPCell();//单元格内容
						 
						imagePath2 =  trip.getImageurl()!=null?trip.getImageurl():"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
						
						Image image212 = Image.getInstance(imagePath2);
						//image2.setFixedHeight(3f);
						image2.setHorizontalAlignment(Element.ALIGN_TOP);
						image2.setVerticalAlignment(Element.ALIGN_LEFT);
						image2.setBorder(0);
						image2.addElement(image212);
						table1.addCell(image2);
						table1.addCell(c2);

						PdfPCell emty = new PdfPCell();
						emty.setBorder(0);
						
						table.addCell(emty);
						table.addCell(table1);
               }
			  else {
				        PdfPTable  table1 = new PdfPTable(2);
						width = new int[] { 50, 50 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						PdfPCell c1 = new PdfPCell(new Paragraph(removeHtml(trip.getAddressInstrations()), contentFont));// 单元格内容
						c1.setBorder(0);
						c1.setLeading(lineSpacing, lineSpacing);
						c1.setVerticalAlignment(Element.ALIGN_LEFT);
						c1.setIndent(26);
						PdfPCell image2 = new PdfPCell();//单元格内容
						 
						imagePath2 =  trip.getImageurl()!=null?trip.getImageurl():"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
						
						Image image212 = Image.getInstance(imagePath2);
						//image2.setFixedHeight(3f);
						image2.setHorizontalAlignment(Element.ALIGN_TOP);
						image2.setVerticalAlignment(Element.ALIGN_LEFT);
						image2.setBorder(0);
						image2.addElement(image212);
						table1.addCell(image2);
						table1.addCell(c1);

						PdfPCell emty = new PdfPCell();
						emty.setBorder(0);
						table.addCell(emty);
						table.addCell(table1);
               }
     	       count++;
               /* blankRow1 = new Paragraph(18f, " ", titFont); 
               document.add(blankRow1);
               */
               key = trip.getType()+""+trip.getId();
               traffic = trafficline.get(key);
               if(traffic!=null){
            	   count=setPrintTraffic(traffic,table,count);
               }
               
        	 }
           // 设置退房酒店信息
           setPrintHotel(vo,table,0,count);    
        }
        document.add(table);
	}
	private String removeHtml(String source){
		if(source!=null){
			return source.replaceAll("<br>", "").replaceAll("\r\n", "").replaceAll("\n", "");
		}
		return source;
	}
	
	private int setPrintTraffic(TTripnoteScheduleTrafficResultVo traffic,PdfPTable  document,int count)throws DocumentException, IOException{
		  
		   BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	       Font titFont = new Font(bfChinese, 16, Font.NORMAL);
	        //Font contentFont =  new Font(bfChinese, 12, Font.NORMAL);
	       PdfPCell emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
	       emtp.setBorder(0);
           document.addCell(emtp);
           document.addCell(emtp);
		   PdfPTable table1 = new PdfPTable(2);
    	   int[] width = new int[]{5,95};
    	   table1.setWidths(width); 
    	   table1.getDefaultCell().setBorder(0);
    	   Paragraph ph = new Paragraph(getTrafficName(traffic.getTrafficType())+"/"+"约"+traffic.getDistance()+" 米"+"/预计用时:"+traffic.getSpendTime()+" 秒",titFont);
    	   //ph.setAlignment(Element.ALIGN_BOTTOM);
    	   PdfPCell c0 = new PdfPCell(ph);//单元格内容
  	       PdfPCell image = new PdfPCell();//单元格内容
  	       image.setBorder(0);
	       String imagePath2 = imagePrefix+getTrafficImage(traffic.getTrafficType());//"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
	       Image image21 = Image.getInstance(imagePath2); 
	       image.addElement(image21);
	       
	       c0.disableBorderSide(4);
	       c0.disableBorderSide(8);
	       image.disableBorderSide(4);
	       image.disableBorderSide(8);
	       //c0.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	       c0.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       //image.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	       c0.setBorderColor(new BaseColor(0x22,0xa9,0x8e));
	       image.setBorderColor(new BaseColor(0x22,0xa9,0x8e));
	       image.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       table1.addCell(image);
	       table1.addCell(c0);
	       
	       PdfPCell image1 = new PdfPCell();
	       image1.addElement(Image.getInstance(imagePrefix+getImage(count)));
	       image1.setBorder(0);
	       //image1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	       image1.setVerticalAlignment(Element.ALIGN_TOP);
	       table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	       document.addCell(image1);
           document.addCell(table1);
           emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
           emtp.setBorder(0);
           document.addCell(emtp);
           document.addCell(emtp);
	       count++;
	       return count;
	}
	private String getImage(int count){
		switch (count) {
		case 1:
			return "A.PNG";
		case 2:
			return "B.PNG";
		case 3:
			return "C.PNG";
		case 4:
			return "D.PNG";
		case 5:
			return "E.PNG";
		case 6:
			return "F.PNG";
		case 7:
			return "G.PNG";
		case 8:
			return "H.PNG";
		case 9:
			return "I.PNG";
		default:
			return null;
		}
	}

	private String getTrafficName(Integer type){
		switch (type) {
		case 1:
			return "公共交通";
		case 2:
			return "汽车";
		case 3:
			return "步行";
		case 4:
			return "飞机";
		case 5:
			return "火车";
		case 6:
			return "轮渡";
		case 7:
			return "巴士";
		case 8:
			return "地铁";
			
		default:
			return null;
		}
	}
	private String getTrafficImage(Integer type){
		switch (type) {
		case 1:
			return "icon_公交.png";
		case 2:
			return "icon_汽车.png";
		case 3:
			return "icon_步行.png";
		case 4:
			return "icon_飞机.png";
		case 5:
			return "icon_火车.png";
		case 6:
			return "icon_轮渡.png";
		case 7:
			return "icon_巴士.png";
		case 8:
			return "icon_地铁.png";
			
		default:
			return null;
		}
	}
	
	private String getPoiImage(Integer type){
		switch (type) {
		case 1:
			return "icon_餐饮.png";
		case 2:
			return "icon_游览.png";
		case 3:
			return "icon_购物.png";
		case 4:
			return "icon_娱乐.png";
		case 5:
			return "icon_产品.png";
		case 6:
			return "icon_酒店.png";
		default:
			return null;
		}
	}
	
	private String setPrintHotel(TTripNoteScheduleResultVo vo,PdfPTable document,int type,int count) throws DocumentException, IOException{
	   BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
       Font titFont = new Font(bfChinese, 16, Font.NORMAL);
       
       PdfPCell emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
       emtp.setBorder(0);
       document.addCell(emtp);
       document.addCell(emtp);
    
       List<TTripnoteScheduleHotelResultVo> scheduleHotels = vo.getScheduleHotels();
		 //打印退房酒店
        if(scheduleHotels!=null)
        for(TTripnoteScheduleHotelResultVo hotel:scheduleHotels){
     	   if(hotel.getCheckInType()==type){//设置退房酒店
     		   PdfPTable table1 = new PdfPTable(2);
          	   int[] width = new int[]{5,95};
          	   table1.setWidths(width); 
          	   table1.getDefaultCell().setBorder(0);
          	   PdfPCell c0 = new PdfPCell(new Paragraph(hotel.getNameCn(),secondTitleFont));//单元格内容
        	   c0.setBorder(0);
      	       String imagePath2 = imagePrefix+getPoiImage(hotel.getType());//"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
      	       PdfPCell image = new PdfPCell();
      	       Image image21 = Image.getInstance(imagePath2); 
      	       image.addElement(image21);
      	       image.setBorder(0);
      	       c0.setHorizontalAlignment(Element.ALIGN_MIDDLE);
      	       image.setHorizontalAlignment(Element.ALIGN_MIDDLE);
      	       table1.addCell(image);
      	       table1.addCell(c0);
      	       
      	       PdfPCell image1 = new PdfPCell();
 	           image1.addElement(Image.getInstance(imagePrefix+getImage(count)));
 	           image1.setBorder(0);
      	       document.addCell(image1);
               document.addCell(table1);
                
               //blankRow1 = new Paragraph(18f, " ", titFont); 
               //document.add(blankRow1);
               table1 = new PdfPTable(2);
          	   width = new int[]{50,50};
          	   table1.setWidths(width); 
          	   table1.getDefaultCell().setBorder(0);
          	   c0 = new PdfPCell(new Paragraph("入住:"+hotel.getCheckInTime()+"      退房:"+hotel.getCheckOuTime(),titFont));//单元格内容
        	   c0.setBorder(0);
        	   PdfPCell c1 = new PdfPCell(new Paragraph(" ",titFont));//单元格内容
     	       c1.setBorder(0);
      	       table1.addCell(c0);
      	       table1.addCell(c1);
      	       PdfPCell emty=  new PdfPCell();
    	       emty.setBorder(0);
    	       document.addCell(emty);
               document.addCell(table1);
               TTripnoteScheduleHotel hoteldetail = tTripnoteScheduleHotelService.getByKey(hotel.getId());
               if(StringUtils.isEmpty(hoteldetail.getIntroduction()) || hoteldetail.getIntroduction().length()>cutLength){
						table1 = new PdfPTable(1);
						width = new int[] { 100 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						c0 = new PdfPCell(new Paragraph(this.removeHtml(hoteldetail.getIntroduction()), contentFont));// 单元格内容
						c0.setBorder(0);
						c0.setLeading(lineSpacing, lineSpacing);
						c0.setIndent(26);
						imagePath2 = hoteldetail.getImageurl();
						image21 = Image.getInstance(imagePath2);
						table1.addCell(image21);
						table1.addCell(c0);
						PdfPCell emty1 = new PdfPCell();
						emty1.setBorder(0);
						document.addCell(emty1);
						document.addCell(table1);
               }else{
						table1 = new PdfPTable(2);
						width = new int[] { 50, 50 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						c0 = new PdfPCell(new Paragraph(this.removeHtml(hoteldetail.getIntroduction()), contentFont));// 单元格内容
						c0.setBorder(0);
						c0.setIndent(26);
						c0.setLeading(lineSpacing, lineSpacing);
						imagePath2 = hoteldetail.getImageurl();
						image21 = Image.getInstance(imagePath2);
						table1.addCell(c0);
						table1.addCell(image21);
						PdfPCell emty1 = new PdfPCell();
						emty1.setBorder(0);
						document.addCell(emty1);
						document.addCell(table1);
               }
               emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
               emtp.setBorder(0);
               document.addCell(emtp);
               document.addCell(emtp);
               return hotel.getType()+""+hotel.getId();
     	   }
        }
        return "";
        
	}
	/**设置行程
	 * @param document
	 * @param detail
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void setScheduleTrip(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
		PdfPTable table = new PdfPTable(4); 
        //table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        List<PdfPRow> listRow = table.getRows();
        table.getDefaultCell().setBorder(0);
        //设置列宽
        float[] columnWidths = { 10f, 60f,10f,20f};
        table.setWidths(columnWidths);
        Date start = detail.getStartDate();
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titFont = new Font(bfChinese, 22, Font.NORMAL);
        int j = detail.getTTripNoteSchedules().size();
        
        for(int m=0;m<j;m++){
    	   TTripNoteScheduleResultVo vo = detail.getTTripNoteSchedules().get(m);
    	   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
           //单元格
    	   titFont.setColor(0x22,0xa9,0x8e);
    	   PdfPCell c0 = new PdfPCell(new Paragraph("D"+vo.getIndexdate(),titFont));//单元格内容
    	   //c0.setBorder(0);
    	   c0.setHorizontalAlignment(Element.ALIGN_CENTER);
    	   StringBuffer title = new StringBuffer();
    	   int citys = vo.getCitys().size();
    	   for(int i=0;i<citys;i++){
    		   TTripnoteScheduleRCityVo ci = vo.getCitys().get(i);
    		   if(m==0 && i==0){
    		      title.append(ci.getNameCn());
    		   }
    		   else if(m!=0 && i!=citys-1){
    			   title.append(ci.getNameCn()+"-->"); 
    		   }
    		   else if(m==j-1 && i==citys-1){
    			   title.append(ci.getNameCn()); 
    		   }
    		   else if(m!=0 && i==citys-1){
    			   title.append(ci.getNameCn()); 
    		   }
    		   else {
    			   title.append("-->"+ci.getNameCn()); 
    		   }
    	   }
    	   Font titFont2 = new Font(bfChinese, 16, Font.NORMAL);
    	   PdfPCell c1 = new PdfPCell(new Paragraph(title.toString(),titFont2));//单元格内容
    	   //c1.setBorder(0);
    	   c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    	   
    	   Font titFont1 = new Font(bfChinese, 13, Font.NORMAL);
    	   titFont1.setColor(0x27, 0x7f, 0x90);
    	   PdfPCell c2 = new PdfPCell(new Paragraph(TimeUtils.getDateString(current, "MM/dd"),titFont1));//单元格内容
    	  // c2.setBorder(0);
    	   c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   titFont1 = new Font(bfChinese, 13, Font.NORMAL);
    	   titFont1.setColor(0x27, 0x7f, 0x90);
    	   PdfPCell c3 = new PdfPCell(new Paragraph("周"+num[TimeUtils.getDayofweek(current)-1],titFont1));//单元格内容
    	  // c3.setBorder(0);
    	   c3.setHorizontalAlignment(Element.ALIGN_LEFT);
    	   
    	   c0.setFixedHeight(40);
    	   c1.setFixedHeight(40);
    	   c2.setFixedHeight(40);
    	   c3.setFixedHeight(40);
    	   
    	   c0.disableBorderSide(4);
    	   c0.disableBorderSide(8);
    	   c1.disableBorderSide(4);
    	   c1.disableBorderSide(8);
    	   c2.disableBorderSide(4);
    	   c2.disableBorderSide(8);
    	   c3.disableBorderSide(4);
    	   c3.disableBorderSide(8);
    	   BaseColor lightGrey = new BaseColor(0xeb,0xec,0xee);
    	   c0.setBorderColor(lightGrey);
    	   c2.setBorderColor(lightGrey);
    	   c3.setBorderColor(lightGrey);
    	   c1.setBorderColor(lightGrey);
    	   
    	   c0.setBorderWidth((float) 0.01);
    	   c1.setBorderWidth((float) 0.01);
    	   c2.setBorderWidth((float) 0.1);
    	   c3.setBorderWidth((float) 0.1);
    	   
    	   //c0.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   c0.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	   c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	   c2.setVerticalAlignment(Element.ALIGN_MIDDLE);
    	   c3.setVerticalAlignment(Element.ALIGN_MIDDLE);
           
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
        table2.setWidthPercentage(100); // 宽度100%填充
        int width21[] = {8,92};
        table2.setWidths(width21); 
        table2.getDefaultCell().setBorder(0);
       
        PdfPCell cell21 = new PdfPCell(new Paragraph("关于这次旅行",firstTitleFont));
        String imagePath2 = imagePrefix+"icon_标题.PNG";
        Image image21 = Image.getInstance(imagePath2); 
        //image21.setAlignment(Element.ALIGN_LEFT);
        PdfPCell cell22 = new PdfPCell();
        cell22.addElement(image21);
        cell22.setBorder(0);
        cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
        
        cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell21.setBorder(0); 
        PdfPCell cells1[]= new PdfPCell[]{cell22,cell21};
        PdfPRow row1 = new PdfPRow(cells1);
        table2.getRows().add(row1);
        //table2.addCell(cell21); 
        document.add(table2);
         //空行间距
        blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);
        //table5
        PdfPTable table5 = new PdfPTable(1);
        Paragraph ph = new Paragraph(this.removeHtml(detail.getIntroduction()),contentFont);  
        PdfPCell cell51 = new PdfPCell(ph);
        cell51.setBorder(0);
        cell51.setIndent(26);
        cell51.setLeading(lineSpacing,lineSpacing);
        String imagePath = /*tripnote.getImageurl()!=null?tripnote.getImageurl():*/"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
        PdfPCell imagecell = new PdfPCell(ph);
        Image image = Image.getInstance(imagePath); 
        cell21.setBorder(0);
        imagecell.addElement(image);
        imagecell.setBorder(0);
        table5.addCell(imagecell);
        table5.addCell(cell51);
        document.add(table5);
       /* blankRow1 = new Paragraph(18f, " ", titFont); 
        document.add(blankRow1);*/
        
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
        Image image1 = Image.getInstance(imagePrefix+"起始页_BG.PNG");
        //设置图片位置的x轴和y周
       /*float left = document.left();
        float top = document.top();*/
        image1.setAbsolutePosition(document.leftMargin(), document.top()-document.topMargin()-200);
        //设置图片的宽度和高度
        image1.scaleAbsolute(document.right()-document.left(), 250);
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titFont = new Font(bfChinese, 24, Font.NORMAL);
        titFont.setColor(0xff, 0xff, 0xff);
        Paragraph header = new Paragraph(tripnote.getTitle(), titFont);
        image1.setAlignment(Image.ALIGN_CENTER);
        header.setAlignment(1);
        document.add(image1);
        //空行间距
        Paragraph blankRow1 = new Paragraph(60f, " ", titFont); 
        document.add(blankRow1);
        
        document.add(header);
        blankRow1 = new Paragraph(10f, " ", titFont); 
        document.add(blankRow1);
        
        titFont = new Font(bfChinese, 13, Font.NORMAL);
        titFont.setColor(0x9d, 0xb4, 0xeb);
        header = new Paragraph(TimeUtils.getDateString(tripnote.getStartDate(), "yyyy.MM.dd")+"-"+TimeUtils.getDateString(tripnote.getEndDate(), "yyyy.MM.dd"), titFont);
        header.setAlignment(1);
        document.add(header);
        
        
        image1 = Image.getInstance(imagePrefix+"起始页_图片.PNG");
        //设置图片位置的x轴和y周
        /*float left = document.left();
        float top = document.top();*/
        image1.setAbsolutePosition((document.right()-document.rightMargin())/2, document.top()-document.topMargin()-240);
        //设置图片的宽度和高度
        image1.scaleAbsolute(80, 80);
        document.add(image1);
        
        blankRow1 = new Paragraph(140f, " ", titFont); 
        document.add(blankRow1);
        
        
        titFont = new Font(bfChinese, 16, Font.NORMAL);
        titFont.setColor(0x00, 0x15, 0x2f);
        Paragraph line = new Paragraph("------行程路线-----", titFont);
        line.setAlignment(1);
        document.add(line);
        Subject subject = SecurityUtils.getSubject();
		BAccount account = (BAccount) subject.getPrincipal();
		
		titFont = new Font(bfChinese, 13, Font.NORMAL);
	    titFont.setColor(0xa5, 0xae, 0xbf);
		line = new Paragraph((account!=null?account.getLoginName():"系统默认")+"为您定制行程",titFont);
		line.setAlignment(1);
        document.add(line);    
        //空行间距
/*        blankRow1 = new Paragraph(15f, " ", titFont); 
        document.add(blankRow1);*/
        
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
