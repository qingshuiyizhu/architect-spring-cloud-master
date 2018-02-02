package org.cloud.mircoservice.provider.file;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.util.StringUtils;

public class WriterFile {
	/*	写入文件
	 * fileName 文件路径
	 * fileContent 文件路径
	 */
  	public static  void writeFile(String fileName, String fileContent) {
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "GBK");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  	
    /** 
     * 保存文件(文件不存在会自动创建). 
     *  
     * @param path 文件路径 
     * @param content 文件内容 
     * @param encoding 编码(UTF-8/gb2312/...) 
     * @throws Exception 
     */  
    public static void saveFile(String path, String content, String encoding) throws Exception {  
        FileOutputStream fileOutputStream = null;  
        BufferedOutputStream bw = null;  
        try {  
            // 获得文件对象  
            File f = new File(path);  
            // 默认编码UTF-8  
            encoding = (StringUtils.isEmpty(encoding)) ? "GBK" : encoding;  
            // 如果路径不存在,则创建  
            if (!f.getParentFile().exists()) {  
                f.getParentFile().mkdirs();  
            }  
            // 开始保存文件  
            fileOutputStream = new FileOutputStream(path);  
            bw = new BufferedOutputStream(fileOutputStream);  
            bw.write(content.getBytes(encoding));  
        } catch (Exception e) {  
                     System.out.println("保存文件错误.path=" + path + ",content=" + content + ",encoding=" + encoding+"   "+e);  
            throw e;  
        } finally {  
            if (bw != null) {  
                try {  
                    bw.close();  
                } catch (IOException e1) {  
                }  
            }  
            if (fileOutputStream != null) {  
                try {  
                    fileOutputStream.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }
}
