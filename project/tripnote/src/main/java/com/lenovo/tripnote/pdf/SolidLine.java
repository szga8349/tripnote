package com.lenovo.tripnote.pdf;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

/**pdf画实线
 * @author shijy2
 *
 */
public class SolidLine implements PdfPCellEvent{

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(3f, 0f,0f);
            //取cell宽度一半
            float midle = (position.getWidth())/2;
            canvas.rectangle(position.getLeft()+midle, position.getBottom(),
                    0,position.getHeight());
            //canvas.setGrayStroke(2);
            canvas.stroke();
        }
}
