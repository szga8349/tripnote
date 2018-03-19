package com.lenovo.tripnote.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;

/**
 * pdf画竖虚线
 * 
 * @author shijy2
 *
 */
public class DottedVerticalLine implements PdfPCellEvent {

	/**
	 * 
	 */
	private Location location = Location.NOMAL;

	public DottedVerticalLine(Location location) {
		this.location = location;
	}
	public DottedVerticalLine() {
		this(Location.NOMAL);
	}

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position, PdfContentByte[] canvases) {
		PdfContentByte canvas = canvases[1];
		// 取cell宽度一半
		float midle = (position.getWidth()) / 2;
		canvas.setLineDash(3f, 3f);
		if (location==Location.NOMAL) {
			canvas.rectangle(position.getLeft() + midle, position.getBottom(), 0, position.getHeight());
		}
		else if (location==Location.TOP) {
			float heigth =  position.getHeight()/2;
			canvas.rectangle(position.getLeft() + midle, position.getBottom(), 0, position.getHeight()-heigth-4f);
		}
		else if (location==Location.BOTTOM) {
			float heigth =  position.getHeight()/2;
			canvas.rectangle(position.getLeft() + midle, position.getBottom()+heigth, 0, position.getHeight()-heigth);
		}
		canvas.setRGBColorStroke(0x22, 0xa9, 0x8e);
		canvas.stroke();
	}

	public enum Location {
		TOP, BOTTOM, NOMAL;
	}
}
