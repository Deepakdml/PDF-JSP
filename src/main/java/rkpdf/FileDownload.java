package rkpdf;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FileDownloadServlet")
public class FileDownload extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = getServletContext().getRealPath("/AllPdf");
		System.out.print(savePath);// Path to the folder within your Eclipse project where files are stored
		String fileName = request.getParameter("pdf"); // Change this to the name of the file you want to download

		File file = new File(savePath + File.separator + fileName);
		if (file.exists()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			OutputStream out = response.getOutputStream();
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();

		} else {
			
			response.getWriter().println("File not found!");
		}
	}
}