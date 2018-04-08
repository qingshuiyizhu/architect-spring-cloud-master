package org.cloud.mircoservice.provider.thread;

public class t {

	public static void main(String[] args) {

		
 
		
	}	
}

class Outputer{
	String xxx="";
	public void output(String name) {
		int len = name.length();
		synchronized(xxx) {
			for(int i=0;i<len;i++) {
				System.out.print(name.charAt(i));
				
			}
			System.out.println();
		}
		
	}
		
}
class Outputer1{
	 
	public void output(String name) {
		int len = name.length();
		synchronized(this) {
			for(int i=0;i<len;i++) {
				System.out.print(name.charAt(i));
				
			}
			System.out.println();
		}
		
	}
}
class Outputer3{
	String xxx="";
	public void output(String name) {
		int len = name.length();
		synchronized(xxx) {
			for(int i=0;i<len;i++) {
				System.out.print(name.charAt(i));
				
			}
			System.out.println();
		}
		
	}
}