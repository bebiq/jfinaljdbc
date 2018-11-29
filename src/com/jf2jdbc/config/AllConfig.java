package com.jf2jdbc.config;

import com.jf2jdbc.ban.GLYController;
import com.jf2jdbc.ban.GuanLiYuan;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class AllConfig extends JFinalConfig {
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
		 
	}
	
	
	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		PropKit.use("res/configtext.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP);
	}
	
	//创建C3p0Plugin对象 也是通过这个方法返回一个C3p0连接池
	public static C3p0Plugin createC3p0Plugin(){
		return  new C3p0Plugin(PropKit.get("jdbcUrl").trim(), PropKit.get("user").trim(), PropKit.get("password").trim(),PropKit.get("driverClass").trim());
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("/guanliyuan", GLYController.class, "/jsp/guanliyuan");
	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin  c3p0 = createC3p0Plugin();
		me.add(c3p0);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0);
		me.add(arp);
		arp.setDialect(new SqlServerDialect());//设置方言 SQLSERVER的方言
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());//设置忽略大小写
		
		arp.addMapping("guanliyuan", "guanliyuanid", GuanLiYuan.class);
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
