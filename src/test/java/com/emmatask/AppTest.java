package com.emmatask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest

{
	public static void main(String[] args)  {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		String dirName = dateFormat.format(new Date()).toString();
		String fileName = "F:/caiqiu" + "/" + dirName;
		
		new File(fileName).mkdirs();
		
	try{
		
	}catch(Exception e){
	    System.out.println(e.getMessage());
	}
//		file.mkdirs();
	}
}
