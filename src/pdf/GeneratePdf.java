package pdf;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bean.Transaction;

public class GeneratePdf {
	
	public static ByteArrayOutputStream  generatePdf(ArrayList<Transaction> a)
	{
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try
		{
			long account_id=000000000;
			for(Transaction t : a)
			{
				if(t.getWs_src_acct_id() == t.getWs_tgt_acct_id())
				{
			     account_id =t.getWs_src_acct_id();
			     break;
				}
				else if(t.getWs_op().equalsIgnoreCase("T"))
				{
					if(t.getWs_amt()<0)
					{
						account_id =t.getWs_src_acct_id();
						break;
					}
					else if(t.getWs_amt()>0)
					{
						account_id =t.getWs_tgt_acct_id();
						break;
					}
				}
			}
			String filename = new String(Long.toString(account_id));
			Document document = new Document();
        	//String file_path = "./" + filename + "_trasactions.pdf";
			//String file_path = "./a.pdf";
//			String basePath = new File("").getAbsolutePath();
//			System.out.println(basePath);
//		    basePath = basePath.concat("\\WebContent\\GeneratedPDFs\\"+filename+"_trasactions.pdf");
			//String basePath = "C:\\Users\\hp\\git\\TCS_Bank\\TCS\\WebContent\\"+filename+"_trasactions.pdf";
			//String basePath = "F:\\TCS_GeneratedPDFs\\"+filename+"_trasactions.pdf";
			PdfWriter.getInstance(document,bout);
			document.open();
			
			Paragraph paragraph = new Paragraph();
			paragraph.setAlignment(1);
			Font font = new Font();
			font.setColor(0,0,0);
			font.setStyle(1);
			Font font1 = new Font();
			font1.setColor(255,0,0);
			font1.setStyle(1);
			Font font2 = new Font();
			font2.setColor(255,0,0);
			font2.setStyle(1);
			font2.setSize(30);
			paragraph.setFont(font2);
			paragraph.add("SMALL FINANCE BANK LTD.");
			paragraph.add("\n");
			paragraph.add("\n");
			document.add(paragraph);
			Paragraph paragraph2 = new Paragraph();
		    paragraph2.setAlignment(1);
			paragraph2.setFont(font);
			paragraph2.add("Account Statement");
			paragraph2.add("\n");
			paragraph2.add("\n");
			paragraph2.add("Account Id: " + filename);
			paragraph2.add("\n");
			paragraph2.add("\n");
			paragraph2.add("\n");
			paragraph2.add("\n");
			  
			document.add(paragraph2);
			
			BaseColor bc =new BaseColor(255,204,0);
			PdfPTable table = new PdfPTable(6);  
			PdfPCell c1 = new PdfPCell(Phrase.getInstance(1,"Transaction ID" ,font));
			c1.setVerticalAlignment(3);
			//c1.setHorizontalAlignment(1);
			c1.setPaddingRight(-2);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			c1 = new PdfPCell(Phrase.getInstance(2,"Operation" ,font));
			c1.setVerticalAlignment(3);
			c1.setHorizontalAlignment(1);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			
			c1 = new PdfPCell(Phrase.getInstance(3,"Source ID" ,font));
			c1.setVerticalAlignment(3);
			c1.setHorizontalAlignment(1);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			c1 = new PdfPCell(Phrase.getInstance(4,"Target ID" ,font));
			c1.setVerticalAlignment(3);
			c1.setHorizontalAlignment(1);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			c1 = new PdfPCell(Phrase.getInstance(5,"DATE(YYYY/MM/DD)" ,font));
			c1.setVerticalAlignment(3);
			c1.setHorizontalAlignment(1);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			c1 = new PdfPCell(Phrase.getInstance(6,"Amount" ,font));
			c1.setVerticalAlignment(3);
			c1.setHorizontalAlignment(1);
			c1.setBackgroundColor(bc);
			table.addCell(c1);
			table.setHeaderRows(1);
			
			for(Transaction t : a)
			{ 
				c1 = new PdfPCell(new Phrase(Long.toString(t.getWs_trxn_id())));
				c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
				if(t.getWs_op().equalsIgnoreCase("T"))
					c1 = new PdfPCell(new Phrase("Transfer"));
				else if(t.getWs_op().equalsIgnoreCase("W"))
					c1 = new PdfPCell(new Phrase("Withdraw"));
				else if(t.getWs_op().equalsIgnoreCase("D"))
					c1 = new PdfPCell(new Phrase("Deposit"));
				c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(Long.toString(t.getWs_src_acct_id())));
				c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(Long.toString(t.getWs_tgt_acct_id())));
				c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
			    Date date = new Date(t.getWs_trxn_date().getTime());
			    String s = new SimpleDateFormat("yyyy/MM/dd").format(date);
			    c1 = new PdfPCell(new Phrase(s));
			    c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase(Long.toString(t.getWs_amt())));
				c1.setVerticalAlignment(1);
				c1.setHorizontalAlignment(1);
				table.addCell(c1);
			}
			document.add(table);
			Paragraph paragraph1 = new Paragraph();
			paragraph1.setAlignment(2);;
			paragraph1.setFont(font);
			paragraph1.add("\n");
			paragraph1.add("\n");
			paragraph1.add("\n");
			paragraph1.add("~ SMALL FINANCE BANK. LTD.");
			paragraph1.add("\n");
			paragraph1.add("Thank you for banking with us.");
			paragraph1.add("\n");
			paragraph1.add("Copyright © 2020 SMALL FINANCE BANK. LTD.");
			paragraph1.add("\n");
			paragraph1.add("All rights are reserved.");
			document.add(paragraph1);
			document.close();
			System.out.println("done");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return bout;
	}

	public static void main(String args[])
	{
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		ArrayList<Transaction> t = new ArrayList<>();
		
		Transaction a = new Transaction();
		a.setWs_trxn_id(100000000);
		a.setWs_src_acct_id(100000031);
		a.setWs_tgt_acct_id(100000001);
		a.setWs_amt(-10000);
		a.setWs_op("T");
		a.setWs_trxn_date(ts);
		
		for(int i = 0 ; i < 50 ; i++)
		{
			t.add(a);
		}
		
		GeneratePdf.generatePdf(t);
		
//		String basePath = new File("").getAbsolutePath();
//	    basePath = basePath.concat("\\WebContent\\GeneratedPDFs");
//	    System.out.println(basePath);
	}


}
