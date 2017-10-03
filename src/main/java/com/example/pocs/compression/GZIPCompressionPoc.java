/**
 * 
 */
package com.example.pocs.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Anand Singh <email: avsmips@gmail.com>
 *
 */
public class GZIPCompressionPoc {
	public static void main(String[] args) {
		String file = "forest.jpg";
		File source = new File("U:\\temp\\"+file);
		File destination = new File("U:\\temp\\Compressed-"+file);
		try {
			compress(source, destination);
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally{
			System.out.println("Compression Completed...!!!");
		}
		
		File newSource = new File("U:\\temp\\Compressed-"+file);
		File newDestination = new File("U:\\temp\\Decompressed-"+file);
		try {
			decompress(newSource, newDestination);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			System.out.println("Decompression Completed...!!!");
		}
	}
	
	public static void compress(File sourceFile, File destinationFile)
			throws IOException {
		byte[] buffer = new byte[1024];
		FileInputStream fileInputStream = new FileInputStream(sourceFile);
		FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
		
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
		
		int read;
		while ((read = fileInputStream.read(buffer))!= -1) {
			gzipOutputStream.write(buffer, 0, read);
		}
		gzipOutputStream.flush();
		gzipOutputStream.close();
		fileOutputStream.close();
		fileInputStream.close();
	}
	
	public static void decompress(File sourceFile, File destinationFile)
			throws IOException {
		byte[] buffer = new byte[1024];
		FileInputStream fileInputStream = new FileInputStream(sourceFile);
		GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
		FileOutputStream fileOutputStream = new FileOutputStream(
				destinationFile);

		int read;
		while ((read = gzipInputStream.read(buffer)) != -1) {
			fileOutputStream.write(buffer, 0, read);
		}
		fileOutputStream.flush();
		fileOutputStream.close();
		gzipInputStream.close();
		fileInputStream.close();
	}
}
