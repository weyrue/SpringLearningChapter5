package com.zy.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
//@MultipartConfig
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filePath = req.getSession().getServletContext().getRealPath("/WEB-INF/file");
        String fileName = req.getParameter("fileName");
        if (fileName == null) return;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            File onlineFile = new File(filePath, fileName);
            inputStream = new BufferedInputStream(new FileInputStream(onlineFile));

//            byte[] buffer = new byte[inputStream.available()];
            byte[] buffer = new byte[1024];

            outputStream = new BufferedOutputStream(resp.getOutputStream());

//            resp.reset();

            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(onlineFile.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            resp.addHeader("Content-Length", "" + onlineFile.length());
            resp.setContentType("application/octet-stream");

            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            resp.sendRedirect(req.getSession().getServletContext().getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();

        }
    }
}
