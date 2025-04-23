package com.TechConnect.JavaUtility;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;

public class RandomNumClass 
{
	public static int randomNum() throws EncryptedDocumentException, IOException
	  {
		  Random r = new Random();
		  int num = r.nextInt(100);
		  return num;
	  }
}
