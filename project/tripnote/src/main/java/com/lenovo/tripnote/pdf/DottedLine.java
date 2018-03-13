package com.lenovo.tripnote.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

/**pdf画虚线
 * @author shijy2
 *
 */
public class DottedLine implements PdfPCellEvent{

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            //取cell宽度一半
            float midle = (position.getWidth())/2;
            canvas.setLineDash(5f, 5f);
            canvas.rectangle(position.getLeft()+midle, position.getBottom(),
                0,position.getHeight());
            canvas.setRGBColorStroke(0x22,0xa9,0x8e);
            canvas.stroke();
        }
}
