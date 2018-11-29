package com.jf2jdbc.ban;

import java.util.Arrays;

import com.jfinal.core.Controller;

public class GLYController extends Controller {
	public void index(){
		String path = getViewPath();
		System.out.println(path);
		render("guanliyuanform.html");

	}
	
	public void myadd(){
		System.out.println("进来了");
		GuanLiYuan modle = getModel(GuanLiYuan.class,"modle");
		System.out.println(Arrays.toString(modle._getAttrNames()));
//		System.out.println(Arrays.toString(GuanLiYuan.me._getAttrNames()));
		System.out.println(modle.getStr("guanliyuanming"));
		modle.save();
		redirect("/guanliyuan");
		
	}
}
