package com.prem.workplace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * User: premkumar.bhaskal
 * Date: 2/22/13
 * Time: 11:45 AM
 */
public class TestClass {


	public static void main(String[] s) {
		TestClass testClass = new TestClass();
//		testClass.generateData();
		testClass.gen();
	}

	// data generator.
	private void generateData() {
		File file = new File("D://testData.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);

			String line;
			for (int i=1;i<=200;i++) {
				line = "";
				line += i + "," + i + ",";
				line += "name_" + i + ",";
				line += "03/22/2013 00:00:00,";
				line += "3.14";
				out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	private void gen() {
		File file = new File("D://testData.txt");
		PrintWriter out = null;
		int million = 1000000;
		int val;
		try {
			out = new PrintWriter(file);

			String line;
			for (int i=1;i<=30000000;i++) {
				val = i % million;
				line = "";
				line += val + "," ;
				line += "03/22/2013 00:00:00,";
				line += "3.14";
				out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
}
