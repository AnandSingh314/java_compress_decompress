/**
 * 
 */
package com.example.pocs.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Anand Singh <email: avsmips@gmail.com>
 *
 */
public class ITextCompressionPoc {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String filename = "PersonsTemplate.pdf";
		File source = new File("U:\\temp\\"+filename);
		File destination = new File("U:\\temp\\compressed-"+filename);
		try {
			compress(source, destination);			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally{
			System.out.println("Compression Completed...!!!");
		}
		
	}

	public static void compress(File source, File destination ) throws IOException, DocumentException{
		PdfReader pdfReader = new PdfReader(new FileInputStream(source));
		pdfReader.removeFields();
		pdfReader.removeUnusedObjects();
		PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(destination));
		stamper.setFullCompression();
		stamper.close();
	}
	
}
