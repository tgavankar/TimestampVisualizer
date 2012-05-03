package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

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
import visualize.FileObject;

public class ExportAsPDFAction implements ActionListener{

	MasterPanel panel;
	
	public ExportAsPDFAction(MasterPanel m) 
	{
		panel = m;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			JCheckBox includeTitle = new JCheckBox("Include Page Title");
			JTextField title = new JTextField(32);
			
			JCheckBox includeDate = new JCheckBox("Show Today's Date");
			
			JCheckBox includeHash = new JCheckBox("Include Hash Value");
			JTextField hash = new JTextField(32);
			
			JCheckBox includeName = new JCheckBox("Include Expert Name");
			JTextField name = new JTextField(32);
			
			JCheckBox includeFiles = new JCheckBox("Include File List");
			
			JPanel exportPanel = new JPanel();
			exportPanel.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			c.gridx = 0;
			c.gridy = 0;
			/*exportPanel.add(includeTitle, c);
			
			c.gridx++;
			exportPanel.add(title, c);
			
			c.gridx = 0;
			c.gridy++;*/
			exportPanel.add(includeDate, c);
			
			c.gridx = 0;
			c.gridy++;
			exportPanel.add(includeHash, c);
			
			c.gridx++;
			exportPanel.add(hash, c);
			
			c.gridx = 0;
			c.gridy++;
			exportPanel.add(includeName, c);
			
			c.gridx++;
			exportPanel.add(name, c);
			
			c.gridx = 0;
			c.gridy++;
			exportPanel.add(includeFiles, c);
			
			c.gridx++;
			exportPanel.add(new JLabel("Warning: May slow down export significantly"), c);
			
			int result = JOptionPane.showConfirmDialog(null, exportPanel, "PDF Export options", JOptionPane.OK_CANCEL_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				String savename = saveFile(new Frame(), "Save...", ".", "chart.pdf");
				if(!savename.equals(".null")) { // User hit cancel
					ArrayList<ExportData> data = new ArrayList<ExportData>();
									
					if(panel.numCharts == 1) {
						data.add(new ExportData(panel.createChart(panel.view[0], true), "", makeDescription(panel.view[0], includeDate.isSelected(), includeHash.isSelected() ? hash.getText() : null, includeName.isSelected() ? name.getText() : null, includeFiles.isSelected())));
					}
					else if(panel.numCharts == 2) {
						data.add(new ExportData(panel.createChart(panel.view[1], true), "", makeDescription(panel.view[1], includeDate.isSelected(), includeHash.isSelected() ? hash.getText() : null, includeName.isSelected() ? name.getText() : null, includeFiles.isSelected())));
						data.add(new ExportData(panel.createChart(panel.view[2], true), "", makeDescription(panel.view[2], includeDate.isSelected(), includeHash.isSelected() ? hash.getText() : null, includeName.isSelected() ? name.getText() : null, includeFiles.isSelected())));
					}
					
					try {
						create(new FileOutputStream(new File(savename)), data);
						JPanel exportCompletePanel = new JPanel();
						exportCompletePanel.add(new JLabel("PDF Export Complete"));
						JOptionPane.showConfirmDialog(null, exportCompletePanel, "Export Complete", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private String makeDescription(DateRange dr, boolean date, String hash, String name, boolean files) {
		StringBuilder sb = new StringBuilder();
		if(date) {
			sb.append("Generated on: ");
			DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
			sb.append(df.format(new Date()));
			sb.append("\n");
		}
		
		if(name != null) {
			sb.append("Expert(s): ");
			sb.append(name);
			sb.append("\n");
		}
		
		if(hash != null) {
			sb.append("Source Drive Hash: ");
			sb.append(hash);
			sb.append("\n");
		}
		
		sb.append("\nData Filter: \n");
		sb.append(dr.toString());
		sb.append("\n\n");
		
		if(files) {
			sb.append("Included file list: \n");
			ArrayList<FileObject> filtered = dr.filterFiles(panel.allFiles);
			for(FileObject f : filtered) {
				sb.append(f.getOrigLine());
				sb.append("\n");
			}
		}
		
		return sb.toString();
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
