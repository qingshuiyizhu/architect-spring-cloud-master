package org.com.dev.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.com.dev.service.AsyncTaskService;

public class SocketUtils {
	 /**  
     * 数组转对象  
     * @param bytes  
     * @return  
     */  
    public static Object toObject (byte[] bytes) {      
        Object obj = null;      
        try {        
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);        
            ObjectInputStream ois = new ObjectInputStream (bis);        
            obj = ois.readObject();      
            ois.close();   
            bis.close();   
        } catch (IOException ex) {        
            ex.printStackTrace();   
        } catch (ClassNotFoundException ex) {        
            ex.printStackTrace();   
        }      
        return obj;    
    }   
       
	  
	public static void sendData(byte[] bytes,String ip,int port) {
	 	try {
			 	DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName(ip),port);
			 	AsyncTaskService.DS.send(dp);// 发送完毕
				System.out.println("向客户端发送指令成功");
			} catch (Exception e) {
				System.out.println("向客户端发送指令失败");
				e.printStackTrace();
			}

		}
 
	  /**
     *      
     * @description     将byte 数组压缩
     * @param bt
     * @return      
     */
    public static byte[] compress(byte[] bt){
        //将byte数据读入文件流
        ByteArrayOutputStream bos=null;
        GZIPOutputStream gzipos=null;
        try {
            bos=new ByteArrayOutputStream();
            gzipos=new GZIPOutputStream(bos);
            gzipos.write(bt);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeStream(gzipos);
            closeStream(bos);
        }
        return bos.toByteArray();
    }

    /**
     *      
     * @description     解压缩byte数组
     * @param bt
     * @return      
     */
    public static byte[] unCompress(byte[] bt){
        //byte[] unCompress=null;
        ByteArrayOutputStream byteAos=null;
        ByteArrayInputStream byteArrayIn=null;
        GZIPInputStream gzipIn=null;
        try {
            byteArrayIn=new ByteArrayInputStream(bt);
            gzipIn=new GZIPInputStream(byteArrayIn);
             byteAos=new ByteArrayOutputStream();
            byte[] b=new byte[4096];
            int temp = -1;
            while((temp=gzipIn.read(b))>0){
                byteAos.write(b, 0, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            closeStream(byteAos);
            closeStream(gzipIn);
            closeStream(byteArrayIn);
        }
        return byteAos.toByteArray();
    }
	


	/**
	 * 
	 * @description 关闭数据流
	 * @param oStream
	 * 
	 */
	public static void closeStream(Closeable oStream) {
		if (null != oStream) {
			try {
				oStream.close();
			} catch (IOException e) {
				oStream = null;// 赋值为null,等待垃圾回收
				e.printStackTrace();
			}
		}
	}
}
