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

import com.wgh.model.MessageForm;
import com.wgh.model.UserInfo;
import com.wgh.tools.ChStr;

import java.io.*;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Xiaofeng
 */
@WebServlet(name = "Messages", urlPatterns = {"/Messages"})




public class Messages extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
		String action = request.getParameter("action");
        if ("loginRoom".equals(action)) {	//登录时，写入系统公告
            this.loginRoom(request, response);
        } else if ("sendMessage".equals(action)) {	//发送聊天信息
            this.sendMessages(request, response);
        } else if ("getMessages".equals(action)) {		//从XML文件中读取聊天信息
            this.getMessages(request, response);
        }
    }
    	// 获取聊天内容，并将页面重定向到显示聊天信息的页面
	public void getMessages(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
        response.setContentType("text/html;charset=GBK");
        request.setCharacterEncoding("GBK");
        ServletContext application = getServletContext();
        Vector sourceMessage=(Vector)application.getAttribute("message");
        HttpSession session=request.getSession();
        String msg="";
        for(int i=0;i<sourceMessage.size();i++){
            MessageForm f=(MessageForm)sourceMessage.get(i);
            if("true".equals(f.getIsPrivate())){
                String from=f.getFrom();
                String to=f.getTo();
                if(session.getAttribute("username").equals(to) || session.getAttribute("username").equals(from)){
                    msg += "<font color='red'>[私人对话]</font><font color='blue'><strong>" + f.getFrom() + "</strong></font><font color='#CC0000'>" + f.getFace() + "</font>对<font color='green'>[" + f.getTo() + "]</font>说：" + "<font color='" + f.getColor() + "'>" + f.getContent() + "</font>（" + f.getSendTime() + "）<br>";
                 }
            }else{
                if(f.getFrom().equals(f.getTo()) && "系统公告：".equals(f.getFrom())){
                    msg += f.getContent();
                }else{
                    msg += "<font color='blue'><strong>" + f.getFrom() + "</strong></font><font color='#CC0000'>" + f.getFace() + "</font>对<font color='green'>[" + f.getTo() + "]</font>说：" + "<font color='" + f.getColor() + "'>" + f.getContent() + "</font>（" + f.getSendTime() + "）<br>";
                }
            }
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("content.jsp").forward(request, response);
	}

	// 登录时，写入系统公告

	public void loginRoom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		request.setCharacterEncoding("GBK");
		ChStr chStr=new ChStr();
		HttpSession session = request.getSession();
		String username=chStr.toGBK(request.getParameter("username"));	//获得登录用户名
		UserInfo user=UserInfo.getInstance();		//获得UserInfo类的对象
		session.setMaxInactiveInterval(600);		//设置Session的过期时间为10分钟
		Vector vector=user.getList();
		boolean flag=true;		//标记是否登录的变量
		//判断用户是否登录
		if(vector!=null&&vector.size()>0){
			for(int i=0;i<vector.size();i++){
				if(user.equals(vector.elementAt(i))){
					PrintWriter out;
					try {
						out = response.getWriter();
						out.println("<script language='javascript'>alert('该用户已经登录');window.location.href='index.jsp';</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
					flag=false;
					break;
				}
			}
		}
		//保存用户信息
		if(flag){
			UserListener ul=new UserListener();					//创建UserListener的对象
			ul.setUser(username);								//添加用户
			user.addUser(ul.getUser());							//添加用户到UserInfo类的对象中
			session.setAttribute("user",ul);						//将UserListener对象绑定到Session中
			session.setAttribute("username",username);	//保存当前登录的用户名
			session.setAttribute("loginTime",new Date().toLocaleString());		//保存登录时间
		}
        ServletContext application=getServletContext();


        Vector sourceMessage=null;
        if(null!=application.getAttribute("message")){
            sourceMessage=(Vector)application.getAttribute("message");
        }else{
            sourceMessage=new Vector();
        }
        
        MessageForm messageForm=new MessageForm();
        
        messageForm.setFrom("系统公告："); //发言人
        messageForm.setTo("系统公告："); //接收者
        //messageForm.setContent("系统公告：<font color='gray'>" + username + "上线了！</font><br>"); //发言内容
        messageForm.setIsPrivate("false");			//是否为悄悄话
        sourceMessage.add(messageForm);
        application.setAttribute("message",sourceMessage);
        try {
            request.getRequestDispatcher("login_ok.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Messages.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	// 发送聊天信息
    public void sendMessages(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=GBK");
            request.setCharacterEncoding("GBK");
            ChStr chStr=new ChStr();
            Random random = new Random();
            MessageForm messageForm=new MessageForm();
            messageForm.setFrom(chStr.toUTF8(request.getParameter("from"))); //发言人
            messageForm.setFace(chStr.toUTF8(request.getParameter("face"))); //表情
            messageForm.setTo(chStr.toUTF8(request.getParameter("to"))); //接收者
            messageForm.setColor(request.getParameter("color")); //字体颜色
            messageForm.setContent(chStr.toUTF8(request.getParameter("content"))); //发言内容
            messageForm.setIsPrivate( request.getParameter("isPrivate"));			//是否为悄悄话
            messageForm.setSendTime( new Date().toLocaleString()); //发言时间
            ServletContext application = getServletContext();
            Vector sourceMessage=(Vector)application.getAttribute("message");
            sourceMessage.add(messageForm);
            application.setAttribute("message", sourceMessage);
            request.getRequestDispatcher("Messages?action=getMessages&nocache=" + random.nextInt(10000)).forward(request, response);


	}



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}

