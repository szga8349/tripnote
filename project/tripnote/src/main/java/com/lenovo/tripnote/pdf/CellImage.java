package com.lenovo.tripnote.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;

public class CellImage implements PdfPCellEvent {
	private Image image ;
    public CellImage(Image image){
    	this.image = image;
    }
	@Override
	public void cellLayout(PdfPCell arg0, Rectangle arg1, PdfContentByte[] arg2) {
		
		image.scaleAbsolute(arg1.getWidth(),200);
		arg0.addElement(image);
	}

}
