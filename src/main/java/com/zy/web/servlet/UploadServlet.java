package com.zy.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * <p>上传文件servlet</p>
 * 1. web.xml配置和注解配置二选一，本质上是一个servlet。<br/>
 * 2. 表单中必须设置为：method="post" enctype="multipart/form-data"。<br/>
 * 3. resp.setContentType("text/html;charset=UTF-8")，解决浏览器显示乱码问题。<br/>
 * 4. 使用DiskFileItemFactory和ServletFileUpload读取二进制文件。<br/>
 * 5. 复制文件可以用write方法也可以用流。<br/>
 *
 * @author Yi
 * @version 1.0
 * @since 2/27/2020
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        if (!ServletFileUpload.isMultipartContent(req)) {
            printWriter.println("表单的enctype属性不是multipart/form-data类型");
            return;
        }

        // 使用DiskFileItemFactory和ServletFileUpload读取二进制文件
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        //2097152=2 * 1024 * 1024=2MB
        servletFileUpload.setFileSizeMax(2097152L);
        //最大全部文件大小
        //10485760=10 * 1024 * 1024=10MB
        servletFileUpload.setSizeMax(10485760L);

        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            List<FileItem> fileItemList = servletFileUpload.parseRequest(req);

            for (FileItem fileItem : fileItemList) {
                String filePath = req.getSession().getServletContext().getRealPath("/WEB-INF/file");
                String fileName = UUID.randomUUID() + fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
                File file = new File(filePath, fileName);
                if (!file.getParentFile().exists()) {
                    if (file.getParentFile().mkdir()) {
                        System.out.println("创建目录： " + file + "成功！");
                    } else {
                        System.out.println("创建目录： " + file + "失败！");
                        printWriter.println("上传失败！");
                        return;
                    }
                }

//                fileItem.write(file);
                /*
                 * 使用流上传文件
                 */
                outputStream = new BufferedOutputStream(new FileOutputStream(file));
                inputStream = new BufferedInputStream(fileItem.getInputStream());
                int length;
                byte[] buffer = new byte[1024];
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer);
                }

                System.out.println(fileName + "上传成功！");

                printWriter.println("上传成功！上传文   件为:" + fileName +
                        "<br/>保存的地址为" + filePath + "！！<br/>" +
                        "<a href='download?fileName=" + fileName + "'>下载</a><br/>" +
                        "<a href='html/UploadFiles.html'>返回上传</a><br/>" +
                        "<a href='index.html'>返回首页</a><br/>");

                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
