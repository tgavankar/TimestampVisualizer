package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import panels.MasterPanel;
import visualize.DateRange;

public class ExportAsPDFAction implements ActionListener{

	MasterPanel panel;
	
	public ExportAsPDFAction(MasterPanel m) 
	{
		panel = m;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String savename = saveFile(new Frame(), "Save...", ".", "chart.pdf");
			if(!savename.equals(".null")) { // User hit cancel
				ArrayList<ExportData> data = new ArrayList<ExportData>();
				
				if(panel.numCharts == 1) {
					data.add(new ExportData(panel.createChart(panel.view[0]), "Graph", "Data Filter:\n" + panel.view[0]));
				}
				else if(panel.numCharts == 2) {
					data.add(new ExportData(panel.createChart(panel.view[1]), "Graph 1", "Data Filter:\n" + panel.view[1]));
					data.add(new ExportData(panel.createChart(panel.view[2]), "Graph 2", "Data Filter:\n" + panel.view[2]));
				}
				
				try {
					create(new FileOutputStream(new File(savename)), data);
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private class ExportData {
		public JFreeChart chart;
		public String title;
		public String description;
		
		public ExportData(JFreeChart chart, String title, String description) {
			this.chart = chart;
			this.title = title;
			this.description = description;
		}
	}
	
	private String saveFile(Frame f, String title, String defDir, String fileType) {
		FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
	    fd.setFile(fileType);
	    fd.setDirectory(defDir);
	    fd.setLocation(50, 50);
	    fd.setVisible(true);
	    return fd.getDirectory() + fd.getFile();
	}
	

	public void create(OutputStream outputStream, ArrayList<ExportData> data) throws DocumentException, IOException {
		Document document = null;
		PdfWriter writer = null;
		
		try {
			//instantiate document and writer
			document = new Document(PageSize.LETTER);
			writer = PdfWriter.getInstance(document, outputStream);
			
			//open document
			document.open();
			
			for(int i=0; i<data.size(); i++) {
				ExportData edata = data.get(i);
				JFreeChart chart = edata.chart;
				String titleText = edata.title;
				String descText = edata.description;

				Paragraph title = new Paragraph(new Chunk(titleText));
				title.setAlignment(Element.ALIGN_CENTER);
				//add text before
				document.add(title);
				
				//add image
				int width = 500;
				int height = 350;
				
				//create PdfContentByte
				//if you work with this object, you write to
				//the top most layer, meaning anything behind
				//will be clipped
				PdfContentByte contentByte = writer.getDirectContent();
				//create PdfTemplate from PdfContentByte
				PdfTemplate template = contentByte.createTemplate(width, height);
				//create Graphics2D from PdfTemplate
				Graphics2D g2 = 
					template.createGraphics(width, height, new DefaultFontMapper());
				//setup the drawing area
				Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
				//pass the Graphics2D and drawing area to JFreeChart
				chart.draw(g2, r2D, null);
				g2.dispose(); //always dispose this
				
				//create Image from PdfTemplate
				Image image = Image.getInstance(template);
				image.setAlignment(Image.MIDDLE);
				document.add(image);
				
				//add text after
				Paragraph desc = new Paragraph(new Chunk(descText));
				document.add(desc);
				
				
				document.newPage();
			}
			//release resources
			document.close();
			document = null;
			
			writer.close();
			writer = null;
		} catch(DocumentException de) {
			throw de;
		} finally {
			//release resources
			if(null != document) {
				try { document.close(); }
				catch(Exception ex) { }
			}
			
			if(null != writer) {
				try { writer.close(); }
				catch(Exception ex) { }
			}
		}
	}

}
