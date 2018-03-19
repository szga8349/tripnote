package com.lenovo.tripnote.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

/**pdf画水平实线
 * @author shijy2
 *
 */
public class SolidHorizontalLine implements PdfPCellEvent{
	private BaseColor color;
	
	public SolidHorizontalLine(BaseColor color){
		this.color = color;
	}

	@Override
	public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.LINECANVAS];
            canvas.setLineDash(0f, 0f,0f);
            //取cell宽度一半
            float bootom = position.getHeight()/3;
            canvas.rectangle(position.getLeft(), position.getBottom()+bootom,
                    position.getWidth(),1);
            canvas.setRGBColorStroke(color.getRed(),color.getGreen(),color.getBlue());
            canvas.stroke();
        }
}
