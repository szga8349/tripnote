package com.lenovo.tripnote.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import com.itextpdf.tool.xml.ElementList;
import com.lenovo.tripnote.entity.BAccount;
import com.lenovo.tripnote.entity.TTripNote;
import com.lenovo.tripnote.entity.vo.TTripNoteDetailResultVo;
import com.lenovo.tripnote.entity.vo.TTripNoteScheduleResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleHotelResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleRCityVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTrafficResultVo;
import com.lenovo.tripnote.entity.vo.TTripnoteScheduleTripResultVo;
import com.lenovo.tripnote.export.PdfHeaderFooter;
import com.lenovo.tripnote.pdf.DottedVerticalLine;
import com.lenovo.tripnote.pdf.LenovoXMLWorkerHelper;
import com.lenovo.tripnote.pdf.SolidHorizontalLine;
import com.lenovo.tripnote.pdf.SolidVerticalLine;
import com.lenovo.tripnote.service.CommonService;
import com.lenovo.tripnote.service.TTripNoteScheduleService;
import com.lenovo.tripnote.service.TTripnoteScheduleHotelService;
import com.lenovo.tripnote.service.TTripnoteService;
import com.lenovo.tripnote.util.TimeUtils;
import com.lenovo.tripnote.util.html.HTMLUtils;
import com.lenovo.tripnote.vo.Result;
import com.lenovo.tripnote.vo.ResultVo;

import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/tripnote/common")
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
	
	private String[] numImage = new String[]{"A.PNG","B.PNG","C.PNG","D.PNG","E.PNG","F.PNG","H.PNG","G.PNG","H.PNG","I.PNG","J.PNG","K.PNG","L.PNG","M.PNG","N.PNG","O.PNG","P.PNG",
			"Q.PNG","R.PNG","S.PNG","T.PNG","U.PNG","V.PNG","W.PNG","X.PNG","Y.PNG","Z.PNG"};
	
	private String imagePrefix = null;
	
	private float lineSpacing = 1.0f;
	
	private   BaseFont bfChinese;
	
	private  Font contentFont;
	
	private  Font firstTitleFont; 
	
	private  Font secondTitleFont; 
	
	private long cutLength = 200;
	
	private BaseColor grayBorder = new BaseColor(0xd9, 0xe4, 0xe2);
	
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
		
		TTripNoteDetailResultVo detail = tTripnoteService.getPdfDetailByKey(Integer.valueOf(id));
			try {
			   contentFont = new Font(bfChinese, 14, Font.NORMAL);
			   lineSpacing = 1.5f;
			   firstTitleFont = new Font(bfChinese, 24, Font.NORMAL);
			   //firstTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
			   secondTitleFont = new Font(bfChinese, 22, Font.NORMAL);
		       secondTitleFont.setColor(new BaseColor(0x22,0xa9,0x8e));
		       cutLength = 200;
			   if(imagePrefix==null)
				 imagePrefix = request.getRealPath("/")+File.separator+"pdf"+File.separator;
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
	/**
	 * 添加城际交通表格
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private void setTrafficTable(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
		  
		
		    PdfPTable tableTitle = new PdfPTable(2);
	       
	        //Font contentFont =  new Font(bfChinese, 12, Font.NORMAL);
	        //设置每列宽度比例   
	        int[] width= {8,92};
	        tableTitle.setWidths(width); 
	        tableTitle.setWidthPercentage(100); // 宽度100%填充
	        tableTitle.getDefaultCell().setBorder(0);
	        PdfPCell cell21 = new PdfPCell(new Paragraph("城际交通",firstTitleFont));
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
	        tableTitle.addCell(cell22);
	        tableTitle.addCell(cell21);  
	        document.add(tableTitle);
	        //空行间距
	       Paragraph blankRow1 = new Paragraph(10f, " "); 
	       document.add(blankRow1);
	        
		   PdfPTable table = new PdfPTable(4);
		   table.setWidths(new int[]{30,15,15,40});
	       //table.setTableEvent(new DottedHeader());
	       //table.getDefaultCell().setBorder(PdfPCell.);
		   //table.setWidthPercentage(92); // 宽度100%填充
	       
	       int height = 40;
	       PdfPCell cc2 = new PdfPCell(new Paragraph("出发/到达时间",contentFont));//单元格内容
	       cc2.setBackgroundColor(grayBorder);
	       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cc2.setBorderColor(grayBorder);
	       cc2.setBorderWidth(2);
	       cc2.setFixedHeight(height);
	       cc2.disableBorderSide(8);
	       table.addCell(cc2);
	       
	       cc2 = new PdfPCell(new Paragraph("班次号",contentFont));//单元格内容
	       cc2.setBackgroundColor(grayBorder);
	       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cc2.setBorderWidth(2);
	       cc2.setBorderColor(grayBorder);
	       cc2.setFixedHeight(height);
	       cc2.disableBorderSide(4);
	       cc2.disableBorderSide(8);
	       table.addCell(cc2);
	       
	       cc2 = new PdfPCell(new Paragraph("出发地",contentFont));//单元格内容
	       cc2.setBackgroundColor(grayBorder);
	       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cc2.setBorderColor(grayBorder);
	       cc2.setBorderWidth(2);
	       cc2.disableBorderSide(4);
	       cc2.disableBorderSide(8);
	       cc2.setFixedHeight(height);
	       table.addCell(cc2);
	       
	       cc2 = new PdfPCell(new Paragraph("到达地",contentFont));//单元格内容
	       cc2.setBackgroundColor(grayBorder);
	       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       cc2.setBorderColor(grayBorder);
	       cc2.setBorderWidth(2);
	       cc2.disableBorderSide(4);
	       cc2.setFixedHeight(height);
	       table.addCell(cc2);
	       //单元格
		   Font titFont = new Font(bfChinese, 22, Font.NORMAL);
		   titFont.setColor(0x22,0xa9,0x8e);
		   Date start = detail.getStartDate();
	       for(TTripNoteScheduleResultVo vo:detail.getTTripNoteSchedules()){
	    	   for(TTripnoteScheduleTripResultVo tripvo:vo.getScheduletrips()){
	    		   if(tripvo.getType()==7){//城际交通
	    			   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
	    			   Paragraph ph= new Paragraph("D"+vo.getIndexdate()+"\r\n",titFont);//单元格内容
	    			   Paragraph ph1 = new Paragraph(TimeUtils.getDateString(current, "MM.dd/"+"周"+num[TimeUtils.getDayofweek(current)-1]),contentFont);//单元格内容
	    			   ph.add(ph1);
	    			   cc2 = new PdfPCell(ph);//单元格内容
	    			   cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    		       cc2.setBorderWidth(2);
	    		       cc2.setBorderColor(grayBorder);
	    		       table.addCell(cc2);
	    		       
	    		       cc2 = new PdfPCell(new Paragraph(tripvo.getNameCn(),contentFont));//单元格内容
	    		       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    		       cc2.setBorderWidth(2);
	    		       cc2.setBorderColor(grayBorder);
	    		       table.addCell(cc2);
	    		       
	    		       cc2 = new PdfPCell(new Paragraph(tripvo.getStartName(),contentFont));//单元格内容
	    		       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    		       cc2.setBorderWidth(2);
	    		       cc2.setBorderColor(grayBorder);
	    		       table.addCell(cc2);
	    		       
	    		       cc2 = new PdfPCell(new Paragraph(tripvo.getEndName(),contentFont));//单元格内容
	    		       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    		       cc2.setBorderWidth(2);
	    		       cc2.setBorderColor(grayBorder);
	    		       table.addCell(cc2);
	    		   }
	    	   }
	       }
	       document.add(table);
	       
	       
	       
	       
	}
    /**
     * 添加酒店安排表
     * @throws DocumentException 
     * @throws IOException 
     * @throws  
     */
    private void setHotelTable(Document document,TTripNoteDetailResultVo detail) throws DocumentException, IOException{
    	

	    PdfPTable tableTitle = new PdfPTable(2);
       
        //Font contentFont =  new Font(bfChinese, 12, Font.NORMAL);
        //设置每列宽度比例   
        int[] width= {8,92};
        tableTitle.setWidths(width); 
        tableTitle.setWidthPercentage(100); // 宽度100%填充
        tableTitle.getDefaultCell().setBorder(0);
        PdfPCell cell21 = new PdfPCell(new Paragraph("酒店安排",firstTitleFont));
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
        tableTitle.addCell(cell22);
        tableTitle.addCell(cell21);  
        document.add(tableTitle);
        //空行间距
        Paragraph blankRow1 = new Paragraph(10f, " "); 
        document.add(blankRow1);
        
	   PdfPTable table = new PdfPTable(3);
       //table.setTableEvent(new DottedHeader());
       //table.getDefaultCell().setBorder(PdfPCell.);
	   table.setWidths(new int[]{30,20,50});
	   int height = 40;
       //BaseColor border = new BaseColor(0xd9, 0xe4, 0xe2);
       PdfPCell cc2 = new PdfPCell(new Paragraph("时间",contentFont));//单元格内容
       cc2.setBackgroundColor(grayBorder);
       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
       cc2.setBorderColor(grayBorder);
       cc2.setBorderWidth(2);
       cc2.disableBorderSide(8);
       cc2.setFixedHeight(height);
       table.addCell(cc2);
       
       cc2 = new PdfPCell(new Paragraph("城市",contentFont));//单元格内容
       cc2.setBackgroundColor(grayBorder);
       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
       cc2.setBorderWidth(2);
       cc2.setBorderColor(grayBorder);
       cc2.disableBorderSide(4);
       cc2.disableBorderSide(8);
       cc2.setFixedHeight(height);
       table.addCell(cc2);
       
       cc2 = new PdfPCell(new Paragraph("酒店",contentFont));//单元格内容
       cc2.setBackgroundColor(grayBorder);
       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
       cc2.setBorderColor(grayBorder);
       cc2.setBorderWidth(2);
       cc2.disableBorderSide(4);
       cc2.setFixedHeight(height);
       
       table.addCell(cc2);
       Font titFont = new Font(bfChinese, 22, Font.NORMAL);
	   titFont.setColor(0x22,0xa9,0x8e);
       Date start = detail.getStartDate();
       //保存以画酒店ID信息
       List<String> drawedHotel = new ArrayList<String>();
       for(TTripNoteScheduleResultVo vo:detail.getTTripNoteSchedules()){
    	   for(TTripnoteScheduleHotelResultVo tripvo:vo.getScheduleHotels()){
    		     if(!drawedHotel.contains(tripvo.getHotelId()+tripvo.getCheckInTime()+tripvo.getCheckOuTime())){//先前已经画了不需要再做重画操作
    			   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
    			   Paragraph ph= new Paragraph(tripvo.getCheckInTime()+"-"+tripvo.getCheckOuTime()+"\r\n",titFont);//单元格内容
    			   Integer offset = Integer.valueOf(tripvo.getCheckOuTime().substring(1));
    			   Date end = TimeUtils.getAfterDay(start, offset-1);
    			   Paragraph ph1 = new Paragraph(TimeUtils.getDateString(current, "MM.dd")+" - "+TimeUtils.getDateString(end, "MM.dd"),contentFont);//单元格内容
    			   ph.add(ph1);
    			   
    			   cc2 = new PdfPCell(ph);//单元格内容
    			   cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		       cc2.setBorderWidth(2);
    		       cc2.setBorderColor(grayBorder);
    		       table.addCell(cc2);
    		       
    		       cc2 = new PdfPCell(new Paragraph(tripvo.getCityName(),contentFont));//单元格内容
    		       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		       cc2.setBorderWidth(2);
    		       cc2.setBorderColor(grayBorder);
    		       table.addCell(cc2);
    		       
    		       cc2 = new PdfPCell(new Paragraph(tripvo.getNameCn(),contentFont));//单元格内容
    		       cc2.setHorizontalAlignment(Element.ALIGN_CENTER);
    		       cc2.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		       cc2.setBorderWidth(2);
    		       cc2.setBorderColor(grayBorder);
    		       table.addCell(cc2);
    		       drawedHotel.add(tripvo.getHotelId()+tripvo.getCheckInTime()+tripvo.getCheckOuTime());
    		     }
    	   }
       }
       
       document.add(table);
		
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
        Paragraph ph = this.removeHtml(tripnote.getRemarks(),contentFont);
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
     	   //vo = tTripNoteScheduleService.getDetailByKey(vo.getId());
     	   //交通连线
     	   Map<String,TTripnoteScheduleTrafficResultVo> trafficline = new HashMap<String,TTripnoteScheduleTrafficResultVo>();
     	   if(vo.getTraffics()!=null)
     	   for(TTripnoteScheduleTrafficResultVo traffic:vo.getTraffics()){
     		  trafficline.put(traffic.getStartScheduleType()+""+traffic.getStartScheduleTrip(),traffic);
     	   }
     	    Map<String,String> trafficMap = new HashMap<String,String>();
     	   //交通连线对应的起始节点名称
     	   if(vo.getScheduleHotels()!=null){
     		  for(TTripnoteScheduleHotelResultVo hotel:vo.getScheduleHotels()){
     			 trafficMap.put(hotel.getType()+""+hotel.getId(),hotel.getNameCn());
         	   }
     	   }
     	   if(vo.getScheduletrips()!=null){
     		  for(TTripnoteScheduleTripResultVo trip:vo.getScheduletrips()){
     			 trafficMap.put(trip.getType()+""+trip.getId(),trip.getNameCn());
         	   }
     	   }
     	   
     	   Date current = TimeUtils.getAfterDay(start, vo.getIndexdate()-1);
     	   //设置表头日程安排
     	   PdfPTable tabletop = new PdfPTable(3);
     	   width = new int[]{10,8,74};
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
           //行程简介不为空
           if(vo.getIntroduction()!=null && !StringUtils.isEmpty(vo.getIntroduction())){
        	   PdfPTable introduct = new PdfPTable(2);
        	   introduct.setWidths(new int[]{4,84});
        	   PdfPCell ccintrduct = new PdfPCell(this.removeHtml(vo.getIntroduction(), contentFont));//单元格内容
        	   ccintrduct.setBorder(0);
        	   ccintrduct.setLeading(lineSpacing, lineSpacing);
        	   PdfPCell emtp = new PdfPCell();//单元格内容
        	   emtp.setBorder(0);
        	   introduct.addCell(emtp);
        	   introduct.addCell(ccintrduct);
               table.addCell(emtp);
               table.addCell(introduct);
           }
           int count = 0;
           String key = "";
           // 设置入住酒店信息
           key = setPrintHotel(vo,table,0,count);
           if(!StringUtils.isEmpty(key))
           {
        	   count++;
           }
           TTripnoteScheduleTrafficResultVo traffic = trafficline.get(key);
           if(traffic!=null){
        	   count = setPrintTraffic(traffic,table,count,trafficMap);
           }
           List<TTripnoteScheduleTripResultVo> trips = vo.getScheduletrips();
           int size = trips.size();
           if(trips!=null)
        	 for(int i=0;i<size;i++){
        	   TTripnoteScheduleTripResultVo trip = trips.get(i);
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
               if(StringUtils.isEmpty(trip.getAddressInstrations()) || trip.getAddressInstrations().length()>cutLength){//没有简介或简介文字超过一定长度 采用上图下文字结构展示
            	        PdfPTable table1 = new PdfPTable(1);
						width = new int[] {100};
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						Paragraph ph = removeHtml(trip.getAddressInstrations(), contentFont);
						//ph.setFirstLineIndent(20f);
						PdfPCell c2 = new PdfPCell(ph);// 单元格内容
						c2.setBorder(0);
						c2.setLeading(lineSpacing, lineSpacing);
						c2.setVerticalAlignment(Element.ALIGN_LEFT);
						c2.setIndent(26);
						PdfPCell image2 = new PdfPCell();//单元格内容
						imagePath2 =  (trip.getImageurl()!=null && !"".equals(trip.getImageurl())) ?trip.getImageurl():"http://pic.rruu.com/img/user/pic/20151221/20151221110915578.png";
						Image image212 = Image.getInstance(imagePath2);
						//image2.setFixedHeight(3f);
						image2.setHorizontalAlignment(Element.ALIGN_TOP);
						image2.setVerticalAlignment(Element.ALIGN_LEFT);
						image2.setBorder(0);
						image2.addElement(image212);
						table1.addCell(image2);
						table1.addCell(c2);
						PdfPCell emty = new PdfPCell();
						if(i==size-1 && getEndHotel(vo))//最后一个 如果有晚间酒店 需要设置连线  没有的话 不设置连线
						     emty.setCellEvent(new SolidVerticalLine());
						else{
							 emty.setCellEvent(new SolidVerticalLine());
						}
						emty.setBorder(0);
						table.addCell(emty);
						table.addCell(table1);
               }
			  else {    //采用左文右图结构展示
				        PdfPTable  table1 = new PdfPTable(2);
						width = new int[] { 50, 50 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						PdfPCell c1 = new PdfPCell(removeHtml(trip.getAddressInstrations(), contentFont));// 单元格内容
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
						
						table1.addCell(c1);
						table1.addCell(image2);
						
						PdfPCell emty = new PdfPCell();
						if(i==size-1 && getEndHotel(vo))
						     emty.setCellEvent(new SolidVerticalLine());
						else{
							 emty.setCellEvent(new SolidVerticalLine());
						}
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
            	   count=setPrintTraffic(traffic,table,count,trafficMap);
               }
        	 }
           // 设置退房酒店信息
           setPrintHotel(vo,table,1,count);    
        }
        document.add(table);
	}
	/**判断是否有晚上酒店
	 * @param trip
	 * @return
	 */
	private boolean getEndHotel(  TTripNoteScheduleResultVo vo){
		for(TTripnoteScheduleHotelResultVo hotel:vo.getScheduleHotels()){
	     	   if(hotel.getCheckInType()==1){//设置退房酒店
	     		   return true;
	     	   }
		}return false;
	}
	private Paragraph removeHtml(String source,Font contentFont) throws IOException{
		Paragraph context = new Paragraph();
		if(source!=null){
			ElementList elementList = LenovoXMLWorkerHelper.parseToElementList(source, null);
			for (Element element : elementList) {
				if(element instanceof Paragraph){
					((Paragraph)element).setFont(contentFont);
				}
				context.add(element);
				context.add("\r\n");
			}
	    }
	    return context;
	}
	private String removeHtml(String source) throws IOException{
		if(source!=null){
			return HTMLUtils.trimTags(source);
		}
	    return source;
	}
	
	private int setPrintTraffic(TTripnoteScheduleTrafficResultVo traffic,PdfPTable  document,int count,Map<String,String> trafficMap)throws DocumentException, IOException{
		  
		   BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	       Font titFont = new Font(bfChinese, 16, Font.NORMAL);
	        //Font contentFont =  new Font(bfChinese, 12, Font.NORMAL);
	       
	       PdfPCell emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
	       emtp.setBorder(0);
	       emtp.setFixedHeight(40);
	       if(count!=0)
	         emtp.setCellEvent(new SolidVerticalLine());
	       //emtp.setCellEvent(new SolidHorizontalLine());
	       document.addCell(emtp);
	       emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
	       emtp.setBorder(0);
	       emtp.setFixedHeight(40);
	       emtp.setCellEvent(new SolidHorizontalLine(new BaseColor(0x22,0xa9,0x8e)));
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
	       //c0.disableBorderSide(4);
	       //c0.disableBorderSide(8);
	       //image.disableBorderSide(4);
	       //image.disableBorderSide(8);
	       //c0.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	       c0.setBorder(0);
	       c0.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       
	       //image.setHorizontalAlignment(Element.ALIGN_BOTTOM);
	       //c0.setBorderColor(new BaseColor(0x22,0xa9,0x8e));
	       //image.setBorderColor(new BaseColor(0x22,0xa9,0x8e));
	       image.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       table1.addCell(image);
	       table1.addCell(c0);
	       
	       PdfPCell image1 = new PdfPCell();
	       image1.addElement(Image.getInstance(imagePrefix+getImage(count)));
	       image1.setBorder(0);
	       //image1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	       image1.setVerticalAlignment(Element.ALIGN_TOP);
	       table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
	       image1.setCellEvent(new SolidVerticalLine());
	       document.addCell(image1);
           document.addCell(table1);
           
           emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
           emtp.setBorder(0);
           emtp.setCellEvent(new SolidVerticalLine());
           document.addCell(emtp);
           emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
           emtp.setBorder(0);
           document.addCell(emtp);
           
           if(traffic.getTrafficGuide()!=null){
			JSONArray jsonArray = JSONArray.fromObject(traffic.getTrafficGuide());
			int size = jsonArray.size();
			PdfPTable tabletraffic = new PdfPTable(2);
			tabletraffic.setWidths(new int[]{10,90});
			String start = traffic.getStartScheduleType()+""+traffic.getStartScheduleTrip();
			String startLine = trafficMap.get(start);
			{ //增加起点
				PdfPCell emty = new PdfPCell();//单元格内容
				emty.setBorder(0);
				emty.setCellEvent(new DottedVerticalLine());
				emty.setFixedHeight(35);
				tabletraffic.addCell(emty);
				PdfPCell content = new PdfPCell( new Paragraph(startLine,contentFont));//单元格内容
				content.setBorderColor(grayBorder);
				content.setBorderWidth(2);
				content.setVerticalAlignment(Element.ALIGN_MIDDLE);
				content.setFixedHeight(35);
				content.disableBorderSide(4);
				content.disableBorderSide(8);
				tabletraffic.addCell(content);
			}
			for (int i = 0; i < size; i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String instructions = object.getString("instructions");
				String travel_mode = object.getString("travel_mode");
				String distance = object.getJSONObject("distance").getString("text");
				String duration = object.getJSONObject("duration").getString("text");
				if (StringUtils.equalsIgnoreCase("TRANSIT", travel_mode)) {// 坐公交车
					JSONObject transit = object.getJSONObject("transit");
					// 下车车站名称
					String stopStation = transit.getJSONObject("arrival_stop").getString("name");
					String startStation = transit.getJSONObject("departure_stop").getString("name");
					// 车名称
					String transitName = transit.getJSONObject("line").getString("short_name");
					PdfPCell emty = new PdfPCell();//单元格内容
					emty.setBorder(0);
					emty.setCellEvent(new DottedVerticalLine());
					emty.setFixedHeight(35);
					tabletraffic.addCell(emty);
					PdfPCell content = new PdfPCell( new Paragraph("在  "+startStation+"  上车 ",contentFont));//单元格内容
					content.setBorderColor(grayBorder);
					content.setBorderWidth(2);
					content.setVerticalAlignment(Element.ALIGN_MIDDLE);
					content.setFixedHeight(35);
					content.disableBorderSide(4);
					content.disableBorderSide(8);
					tabletraffic.addCell(content);
					
				    emty = new PdfPCell();//单元格内容
					emty.setBorder(0);
					emty.setCellEvent(new DottedVerticalLine());
					emty.setFixedHeight(35);
					tabletraffic.addCell(emty);
					content = new PdfPCell( new Paragraph("乘坐  "+transitName+" 在 "+stopStation+"  下车      "+duration+"   "+distance,contentFont));//单元格内容
					content.setBorderColor(grayBorder);
					content.setBorderWidth(2);
					content.setVerticalAlignment(Element.ALIGN_MIDDLE);
					content.setFixedHeight(35);
					content.disableBorderSide(4);
					content.disableBorderSide(8);
					tabletraffic.addCell(content);
					continue;
				}
				// travel_mode DRIVING:驾车 TRANSIT:公交车 WALKING:步行

				PdfPCell emty = new PdfPCell();//单元格内容
				emty.setBorder(0);
				emty.setCellEvent(new DottedVerticalLine());
				emty.setFixedHeight(35);
				tabletraffic.addCell(emty);
				PdfPCell content = new PdfPCell(new Paragraph(removeHtml(instructions)+"   "+duration+"   "+distance,contentFont));//单元格内容
				content.setBorderColor(grayBorder);
				content.setBorderWidth(2);
				content.setVerticalAlignment(Element.ALIGN_MIDDLE);
				content.setFixedHeight(35);
				content.disableBorderSide(4);
				content.disableBorderSide(8);
				tabletraffic.addCell(content);
			 }
			String end = traffic.getEndScheduleType()+""+traffic.getEndScheduleTrip();
			String endLine = trafficMap.get(end);
			{//增加终点
				PdfPCell emty = new PdfPCell();//单元格内容
				emty.setBorder(0);
				emty.setCellEvent(new DottedVerticalLine());
				emty.setFixedHeight(35);
				tabletraffic.addCell(emty);
				PdfPCell content = new PdfPCell( new Paragraph(endLine,contentFont));//单元格内容
				content.setBorderColor(grayBorder);
				content.setBorderWidth(2);
				content.setVerticalAlignment(Element.ALIGN_MIDDLE);
				content.setFixedHeight(35);
				content.disableBorderSide(4);
				content.disableBorderSide(8);
				tabletraffic.addCell(content);
			}
			 PdfPCell exemty = new PdfPCell();//单元格内容
			 exemty.setCellEvent(new SolidVerticalLine());
			 exemty.setBorder(0);
			 document.addCell(exemty);
			 document.addCell(tabletraffic);
           }
   
           //document.addCell(emtp);
	       count++;
	       return count;
	}
	private String getImageHotel(int type){
		switch (type) {
		case 0:
			return "早上.PNG";
		case 1:
			return "晚上.PNG";
		default:
			return "早上.PNG";
		}
			
	}
	
	private String getImage(int count){
		return numImage[count];
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
		case 7:
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
       if(count!=0)//第一个是酒店 不需要画实线
        emtp.setCellEvent(new SolidVerticalLine());
       document.addCell(emtp);
       emtp = new PdfPCell( new Paragraph(18f, " ", titFont));//单元格内容
       emtp.setBorder(0);
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
 	           image1.addElement(Image.getInstance(imagePrefix+getImageHotel(type)));
 	           image1.setBorder(0);
 	           //image1.setCellEvent(new SolidVerticalLine());
 	           
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
    	       if(type==0)//早上的酒店下 需要画连线
    	         emty.setCellEvent(new SolidVerticalLine());
    	       document.addCell(emty);
               document.addCell(table1);
               //TTripnoteScheduleHotel hoteldetail = tTripnoteScheduleHotelService.getByKey(hotel.getId());
               if(StringUtils.isEmpty(hotel.getIntroduction()) || hotel.getIntroduction().length()>cutLength){
						table1 = new PdfPTable(1);
						width = new int[] { 100 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						c0 = new PdfPCell(this.removeHtml(hotel.getIntroduction(), contentFont));// 单元格内容
						c0.setBorder(0);
						c0.setLeading(lineSpacing, lineSpacing);
						c0.setIndent(26);
						imagePath2 = hotel.getImageurl();
						image21 = Image.getInstance(imagePath2);
						table1.addCell(image21);
						table1.addCell(c0);
						PdfPCell emty1 = new PdfPCell();
						emty1.setBorder(0);
						if(type==0)//早上的酒店下 需要画连线
							emty1.setCellEvent(new SolidVerticalLine());
						document.addCell(emty1);
						document.addCell(table1);
               }else{
						table1 = new PdfPTable(2);
						width = new int[] { 50, 50 };
						table1.setWidths(width);
						table1.getDefaultCell().setBorder(0);
						c0 = new PdfPCell(this.removeHtml(hotel.getIntroduction(), contentFont));// 单元格内容
						c0.setBorder(0);
						c0.setIndent(26);
						c0.setLeading(lineSpacing, lineSpacing);
						imagePath2 = hotel.getImageurl();
						image21 = Image.getInstance(imagePath2);
						table1.addCell(c0);
						table1.addCell(image21);
						PdfPCell emty1 = new PdfPCell();
						if(type==0)//早上的酒店下 需要画连线
							emty1.setCellEvent(new SolidVerticalLine());
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
    	   c0.setHorizontalAlignment(Element.ALIGN_LEFT);
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
       
    	setTrafficTable(document,detail);
    	
    	blankRow1 = new Paragraph(10f, " "); 
	    document.add(blankRow1);
	       
		setHotelTable(document,detail);
		 //空行间距
        blankRow1 = new Paragraph(40f, " ", titFont); 
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
        Paragraph ph = this.removeHtml(detail.getIntroduction(),contentFont);  
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
