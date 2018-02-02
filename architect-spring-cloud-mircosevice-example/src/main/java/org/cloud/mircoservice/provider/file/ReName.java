package org.cloud.mircoservice.provider.file;
import java.io.File;
public class ReName {
 
	 public static void main(String[] args) {  
  	        //要改的文件夹路径  
	        String path= "E:/ff";  
	        ReFileName(path,".jsp",".html");
	          
	    }  
	 /**
	     * @Title: ReFileName  
	     * @Description: TODO 批量修改文件名后缀
	     * @param @param path 要修改的文件目录路径
	     * @param @param oldFileName 原来的文件后缀如.txt
	     * @param @param newFileName 新的文件名后缀如.html
	     * @return void    返回类型
	     * @throws
	  */
	    private static void ReFileName(String path ,String oldFileName,String newFileName) {  
	        File file = new File(path);  
	        //得到文件夹下的所有文件和文件夹  
	        String[] list = file.list();  
	          
	        if(list!=null && list.length>0){  
	            for (String oldName : list) {  
	                File oldFile = new File(path,oldName);  
	                //判断出文件和文件夹  
	                if(!oldFile.isDirectory()){  
	                    //文件则判断是不是要修改的  
	                    if(oldName.contains(oldFileName)){  
	                        System.out.println(oldName);  
	                        String newoldName = oldName.substring(0, oldName.lastIndexOf("."))+newFileName;  
	                        System.out.println(newoldName);  
	                        File newFile = new File(path,newoldName);  
	                        boolean flag = oldFile.renameTo(newFile);  
	                        System.out.println(flag);  
	                    }  
	                }else{  
	                    //文件夹则迭代  
	                    String newpath=path+"/"+oldName;  
	                    ReFileName(newpath,oldFileName,newFileName);  
	                }  
	            }  
	        }  
	    }  
	/*
	 * 
	 * 首先需要获取文件名称，然后通过文件名称用substring(*,*)方法得到没后缀名的文件名。
	示例代码：
	String filename= "xxxx.tmp";
	File file = new File(filename);
	String getFilename = file.getFileName();
	String name = getFilename.substring(0,getFilename .lastIndexOf("."));//获取除后缀1位的名称
	getFilename.substring(getFilename .lastIndexOf("."));//这个是获取后缀名
	 */
	
	
}
