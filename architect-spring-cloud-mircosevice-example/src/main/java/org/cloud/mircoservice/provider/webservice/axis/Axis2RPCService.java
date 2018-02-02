package org.cloud.mircoservice.provider.webservice.axis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

 

public class Axis2RPCService {
    
    /** 
    * @Title: upload 
    * @Description: 文件数据处理 
    * @param b 
    * @param len 
    * @return    参数 
    */  
    public String upload(byte[] b ,int len){  
        FileOutputStream fos=null;  
        String path="";  
        try {  
            String dir=System.getProperty("user.dir");  
            File file=new File(dir + "/" + new Random().nextInt(1000) + ".jsp");  
            fos=new FileOutputStream(file);  
            fos.write(b , 0 ,len);  
            path=file.getAbsolutePath();  
            System.out.println("File path:" + path);  
        } catch (Exception e) {  
              
        } finally{  
            try {  
                fos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return path;  
    }  
      
    /** 
    * @Title: getArray 
    * @Description: 返回一维数组 
    * @param i      数组长度 
    * @return       参数 
    */  
    public int[] getArray(int i){  
        int[] arr=new int[i];  
        for (int j = 0; j < arr.length; j++) {  
            arr[j]=new Random().nextInt(100);  
        }  
        return arr;  
    }  
      
      
    /** 
    * @Title: getTwoArray 
    * @Description: 返回二维数组 
    * @return    参数 
    */  
    public String[][] getTwoArray(){  
        return new String[][]{{"北京","上海"},{"南京","苏州"},{"深圳","厦门"},{"西安","兰州"}};  
    }  
       
    /** 
    * @Title: getUser 
    * @Description: 返回JavaBean对象 
    * @return    参数 
    */  
   /* public User getUser(){  
        User user=new User();  
        user.setUserName("elgin");  
        user.setAge(26);  
        user.setEmail("3303335@qq.com");  
        return user;  
    }  */
}
