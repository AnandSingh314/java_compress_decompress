package com.example.pocs.compression;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

import org.apache.log4j.Logger;

public class DeflatorCompressionPoc {
	private static final Logger LOG = Logger
			.getLogger(DeflatorCompressionPoc.class);

	public static void main(String[] args) {
		String file = "AnandPAN.pdf.encrypted";
		File source = new File("U:\\temp\\"+file);
		File destination = new File("U:\\temp\\Deflator-"+file);
		try {
			compress(source, destination);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}finally{
			System.out.println("Compression Completed...!!!");
		}

	}

	public static void compress(File source , File destination) throws IOException{
		FileOutputStream fout = new FileOutputStream(destination);
		Deflater deflater = new Deflater();
		deflater.setLevel(9);
		DeflaterOutputStream dout = new DeflaterOutputStream(fout, deflater);
		FileInputStream f_in = new FileInputStream(source);
		byte[] buffer = new byte[1024];
		int read;
		while((read = f_in.read(buffer, 0, 1024))!= -1){
			dout.write(buffer);
		}
		f_in.close();
		dout.close();
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