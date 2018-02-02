package org.cloud.mircoservice.provider.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	 //读取Text文件内容
		public static String readTxtFile(String filePath) {
			String plays = "";
			try {
				String encoding = "GBK";
				File file = new File(filePath);
				if (file.isFile() && file.exists()) { // 判断文件是否存在
					InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);

					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						plays += lineTxt;
					}
					read.close();
				} else {
					System.out.println("找不到指定的文件");
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}
			return plays;
		}
	
	
	
	
   /* public ReadFile() {
    }*/
    /**
     * 读取某个文件夹下的所有文件
     */
    public  List<String> readfile(String filepath) throws FileNotFoundException, IOException {
       	List<String> fileList = new ArrayList<String>();
    	
            try {
                    File file = new File(filepath);
                    if (!file.isDirectory()) {
                             System.out.println("Path="+filepath+"是文件");
                             System.out.println("path=" + file.getPath());
                            //绝对路径
                              System.out.println("absolutepath=" + file.getAbsolutePath());
                            //文件名
                       
                            String fileName = file.getName();
                       fileName = new String (fileName.getBytes("GBK"),"GBK");
                            System.out.println("name=" +  fileName);
                            
                            fileList.add(file.getName());
                    } else if (file.isDirectory()) {
                            System.out.println("Path="+filepath+"是文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                            	//递归访问文件夹
                            	
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    
                                    if (!readfile.isDirectory()) {
                                            System.out.println("path=" + readfile.getPath());
                                            System.out.println("absolutepath="+ readfile.getAbsolutePath());
                                            System.out.println("name=" + readfile.getName());
                                            fileList.add(readfile.getName());
                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return fileList;
    }

    /**
     * 删除某个文件夹下的所有文件夹和文件
     */
    
    
   public boolean deletefile(String delpath)
                    throws FileNotFoundException, IOException {
            try {
                  File file = new File(delpath);
                    if (!file.isDirectory()) {
                            System.out.println("Path="+delpath+"是文件");
                            file.delete();
                    } else if (file.isDirectory()) {
                            System.out.println("Path="+delpath+"是文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File delfile = new File(delpath + "\\" + filelist[i]);
                                    if (!delfile.isDirectory()) {
                                            System.out.println("path=" + delfile.getPath());
                                            System.out.println("absolutepath="
                                                            + delfile.getAbsolutePath());
                                            System.out.println("name=" + delfile.getName());
                                            delfile.delete();
                                            System.out.println("删除文件成功");
                                    } else if (delfile.isDirectory()) {
                                            deletefile(delpath + "\\" + filelist[i]);
                                    }
                            }
                            file.delete();

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("deletefile()   Exception:" + e.getMessage());
            }
            return true;
    } 
/*//    
// public static void main(String[] args) {
//            try {
//                 List<String> list =  readfile("D:\\Debug\\log");
//                 System.out.println("-----------------------");
//                  for (String string : list) {
//					System.out.println(string);
//				}
//  
//                  // deletefile("D:/file");
//            } catch (FileNotFoundException ex) {
//            } catch (IOException ex) {
//            }
//            System.out.println("ok");
//    } 
*/
}