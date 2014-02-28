package com.wgh.tools;

import java.io.UnsupportedEncodingException;

public class ChStr {
public String toGBK(String str){
	try {
		str=new String(str.getBytes("ISO-8859-1"),"GBK");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str;
}
public String toUTF8(String str){
	try {
		str=new String(str.getBytes("ISO-8859-1"),"UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return str;
}
}
