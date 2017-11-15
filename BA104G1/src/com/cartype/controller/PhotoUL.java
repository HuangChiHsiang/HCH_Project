package com.cartype.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class PhotoUL {

	
	public byte[] getPictureByteArray(Part part) throws IOException {
		InputStream in = part.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i=in.read(buffer))!=-1){
			bos.write(buffer,0,i);
		}
		bos.close();
		in.close();
		return bos.toByteArray();
	
	}
}
