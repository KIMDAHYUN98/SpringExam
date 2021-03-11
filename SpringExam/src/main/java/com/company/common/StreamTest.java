package com.company.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class StreamTest {

	public static void main(String[] args) throws Exception {
		
		/*
		 * FileReader fr = new FileReader("D:\\Temp\\sample.txt"); 
		 * int c; while ((c =
		 * fr.read()) != -1) { 
		 * System.out.println((char)c); 
		 * } 
		 * fr.close();
		 */
		
		//BufferedReader br = new BufferedReader(new FileReader("D:\\Temp\\sample.txt"));
		BufferedInputStream br = new BufferedInputStream(new FileInputStream("D:\\Temp\\Hydrangeas.jpg"));
		BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("D:\\Temp\\Hydrangeas2.jpg"));
		
		int cnt;
		byte[] b = new byte[100];
		
		while(true) {
			cnt = br.read(b); // byte 수
			if(cnt == -1) {
				break;
			}
			bw.write(b);
		}
		br.close();
		bw.close();
	}

}
