package rkpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
@WebServlet("/FileUploadServlet")
public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FileUpload() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.print("I am the post method");
		Part file = request.getPart("pdfFile");

		String pdfFileName = file.getSubmittedFileName();
		System.out.println("Submitted file : " + pdfFileName);

		String uploadPath = "C:/Users/Deepak Kumar/eclipse-workspace/rkpdf/src/main/webapp/AllPdf/" + pdfFileName;

		System.out.println("Upload path : " + uploadPath);
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();

			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
			System.out.println("DB connected");
			PreparedStatement pst = con.prepareStatement("insert into pdfrk(filename) values(?)");
			pst.setString(1, pdfFileName);

			int row = pst.executeUpdate();

			if (row > 0) {
				System.out.println("Pdf added succesfully");
			} else {
				System.out.println("Pdf not added succesfully");
			}
			con.close();
			response.sendRedirect("index.jsp?message=Image Uploaded Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong");
		}
	}
}
