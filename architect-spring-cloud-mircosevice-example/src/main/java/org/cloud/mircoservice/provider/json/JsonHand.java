package org.cloud.mircoservice.provider.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonHand {
	/*  转Json字符串 
	 * String str = JSON.toJSONString(resource);
       System.out.println(str);
	 */
    public static void main(String args[]){
        try {
        	//创建JSON解析器
            JsonParser parser=new JsonParser();  
             //创建JsonObject对象  json文件
             // JsonObject object=(JsonObject) parser.parse(new FileReader("configs/test.json")); 
            JsonObject object=(JsonObject) parser.parse("{\"cat\":\"it\",\"pop\":true}");
            //将json数据转为为String型的数据
            System.out.println("cat="+object.get("cat").getAsString());
            //将json数据转为为boolean型的数据
            System.out.println("pop="+object.get("pop").getAsBoolean()); 
          //得到为json的数组
            JsonArray array=object.get("language").getAsJsonArray();    
            for(int i=0;i<array.size();i++){
                System.out.println("---------------");
                JsonObject subObject=array.get(i).getAsJsonObject();
                System.out.println("id="+subObject.get("id").getAsInt());
                System.out.println("name="+subObject.get("name").getAsString());
                System.out.println("ide="+subObject.get("ide").getAsString());
            } 
             
        } catch (Exception e) {
            e.printStackTrace();
      }
        }
    }
 