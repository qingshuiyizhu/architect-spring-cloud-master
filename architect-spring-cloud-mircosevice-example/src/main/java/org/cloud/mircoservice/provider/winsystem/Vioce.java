package org.cloud.mircoservice.provider.winsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Vioce {
	private String strsound = "Set Ws = CreateObject(\"Wscript.Shell\") \r\n Ws.Sendkeys ";
	private String stradd = "\"棷棷棷棷棷棷\"";
	private String strless = "\"棶棶棶棶棶棶棶\"";
	private String strquiet = "\"棴 \"";
	private File file;
	private BufferedWriter writer;
	{
		try {
			file = File.createTempFile("ylkz", ".vbs");
			file.deleteOnExit();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add() {
		try {
			writer.write(this.strsound + this.stradd);
			runCmd();
		} catch (IOException e) {
		  e.printStackTrace();
		}
 	}

	public void less() {
		try {
			writer.write(this.strsound + this.strless);
			runCmd();
		} catch (IOException e) {
		  e.printStackTrace();
		}

	}
	 
	public void quiet() {
		  try {
				writer.write(this.strsound + this.strquiet);
				runCmd();
			} catch (IOException e) {
			 	e.printStackTrace();
			}
		}
 	public void runCmd(){
		try {
	 		writer.flush();
			writer.close();
			Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
