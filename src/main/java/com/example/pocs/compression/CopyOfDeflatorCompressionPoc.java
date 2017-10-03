package com.example.pocs.compression;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.log4j.Logger;

public class CopyOfDeflatorCompressionPoc {
	private static final Logger LOG = Logger
			.getLogger(CopyOfDeflatorCompressionPoc.class);

	public static void main(String[] args) {
		File source = new File("U:\\temp\\PersonsTemplate.pdf");
		File destination = new File("U:\\temp\\CompressedFile.pdf");
		try {
			FileInputStream fileInputStream = new FileInputStream(source);
			FileOutputStream fileOutputStream = new FileOutputStream(destination);
		
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally{
			System.out.println("Compression Completed...!!!");
		}

	}

	public static byte[] compress(byte[] data) throws IOException {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated
													// code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		LOG.debug("Original: " + data.length / 1024 + " Kb");
		LOG.debug("Compressed: " + output.length / 1024 + " Kb");
		return output;
	}

	public static byte[] decompress(byte[] data) throws IOException,
			DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();
		LOG.debug("Original: " + data.length);
		LOG.debug("Compressed: " + output.length);
		return output;
	}
}