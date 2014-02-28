/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclasses;
import java.awt.image.BufferedImage; 

import java.io.*; 

import java.awt.Image; 

import com.sun.image.codec.jpeg.JPEGCodec; 

import com.sun.image.codec.jpeg.JPEGImageEncoder; 



public class FileUpload 

{ 

public static void uploadImage(String imgFolderPath,String srcFileName,String dstFileName) throws IOException 

{ 

//判断文件夹image是否存在，若不存在则创建 

File folder = new File(imgFolderPath); 

System.out.println("function 'uploadFileTest2'-imgFolderPath'"+imgFolderPath); 

if(!folder.exists()) 

{ 

folder.mkdir(); 

System.out.println("maked a folder!"); 

} 



File _file = new File(srcFileName); //读入文件 

Image src = javax.imageio.ImageIO.read(_file); //构造Image对象 

int wideth=src.getWidth(null); //得到源图宽 

int height=src.getHeight(null); //得到源图长 

BufferedImage tag = new BufferedImage(wideth,height,BufferedImage.TYPE_INT_RGB); 

tag.getGraphics().drawImage(src,0,0,wideth,height,null); 

FileOutputStream out=new FileOutputStream(dstFileName); //输出到文件流 

JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); 

encoder.encode(tag); //JPEG编码 

out.close(); 

System.out.println("function 'uploadFileTest2'-status:A Image File Saved!"); 

} 

} 

//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Xiaofeng
// */
//@WebServlet(name = "UploadFile", urlPatterns = {"/UploadFile"})
//public class UploadFile extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP
//     * <code>GET</code> and
//     * <code>POST</code> methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /*
//             * TODO output your page here. You may use following sample code.
//             */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UploadFile</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UploadFile at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP
//     * <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP
//     * <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//}


//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
// 
//import org.apache.commons.fileupload.*;
//import java.util.*;
//import java.util.regex.*;
//import java.io.*;
//import org.apache.commons.fileupload.servlet.*;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//
//public class FileUpload extends HttpServlet { 
//	//BLOB
//
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		this.config = config;
//	}
//
//	private ServletConfig config = null;
//	
//	
//
//	private File tempPath = new File("D:\\upload\\temp\\"); // 用于存放临时文件的目录
//
//	public void destroy() {
//		config = null;
//		super.destroy();
//	}
//
//	
//	public void doPost(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		int id = -1;
//		
//		String uploadPath = config.getInitParameter("uploadPath"); // 用于存放上传文件的目录
//		
//		res.setContentType("text/html; charset=GB2312");
//		PrintWriter out = res.getWriter();
//		System.out.println(req.getContentLength());
//		System.out.println(req.getContentType());
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		// maximum size that will be stored in memory
//		factory.setSizeThreshold(4096);
//		// the location for saving data that is larger than getSizeThreshold()
//		factory.setRepository(tempPath);
//
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		// maximum size before a FileUploadException will be thrown
//		upload.setSizeMax(1000000);
//		try {
//			List fileItems = upload.parseRequest(req);
//			// assume we know there are two files. The first file is a small
//			// text file, the second is unknown and is written to a file on
//			// the server
//			Iterator iter = fileItems.iterator();
//
//			// 正则匹配，过滤路径取文件名
//			String regExp = ".+\\\\(.+)$";
//
//			// 过滤掉的文件类型
//			String[] errorType = { ".exe", ".com", ".cgi", ".jsp" };
//			Pattern p = Pattern.compile(regExp);
//			while (iter.hasNext()) {
//				FileItem item = (FileItem) iter.next();
//				if(item.isFormField()) {
//					if(item.getFieldName().equals("id")) {
//						id = Integer.parseInt(item.getString());
//					}
//				}
//				// 忽略其他不是文件域的所有表单信息
//				if (!item.isFormField()) {
//					String name = item.getName();
//					long size = item.getSize();
//					if ((name == null || name.equals("")) && size == 0)
//						continue;
//					Matcher m = p.matcher(name);
//					boolean result = m.find();
//					if (result) {
//						for (int temp = 0; temp < errorType.length; temp++) {
//							if (m.group(1).endsWith(errorType[temp])) {
//								throw new IOException(name + ": wrong type");
//							}
//						}
//						try {
//
//							// 保存上传的文件到指定的目录
//
//							// 在下文中上传文件至数据库时，将对这里改写
//							item.write(new File(uploadPath + id + ".jpg"));
//
//							out.print(name + "&nbsp;&nbsp;" + size + "<br>");
//						} catch (Exception e) {
//							out.println(e);
//						}
//
//					} else {
//						throw new IOException("fail to upload");
//					}
//				}
//			}
//		} catch (IOException e) {
//			out.println(e);
//		} catch (FileUploadException e) {
//			out.println(e);
//		}
//
//	}
//
//	
//
//}
