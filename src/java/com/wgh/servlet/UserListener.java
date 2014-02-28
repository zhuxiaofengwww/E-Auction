/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wgh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wgh.model.UserInfo;
import javax.servlet.http.HttpSessionBindingEvent;
/**
 *
 * @author Xiaofeng
 */
@WebServlet(name = "UserListener", urlPatterns = {"/UserListener"})


public class UserListener implements
		javax.servlet.http.HttpSessionBindingListener {
	private String user;
	private UserInfo container = UserInfo.getInstance();	//获得UserInfo类的对象

	public UserListener() {
		user = "";
	}

	// 设置在线监听人员
	public void setUser(String user) {
		this.user = user;
	}

	// 获取在线监听
	public String getUser() {
		return this.user;
	}

	// 当Session有对象加入时执行的方法
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("上线用户：" + this.user);

	}

	// 当Session有对象移除时执行的方法
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("下线用户：" + this.user);
		if (user != "") {
			container.removeUser(user);
		}
	}
}

