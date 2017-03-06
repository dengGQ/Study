package com.dgq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2Pdf {
	// OpenOffice的安装目录
	private static String OpenOffice_HOME = "C:/Program Files (x86)/OpenOffice 4/program/";
	// 启动服务的命令
	private static String command = "soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";

	private static Process process = null;

	/**
	 * 核心转pdf方法
	 * 
	 * @param sourcefile
	 *            被转文件
	 * @param targetfile
	 *            目标文件即pdf文件
	 * @return
	 */
	public static void convertTo(File sourcefile, File targetfile) {
		try {
			// 启动方法
			if (process == null) {
				process = startOpenOffice();
			}

			// 连接openoffice的端口必须和启动服务的端口一致,
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(
					8100);
			connection.connect();
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			// 转换,传入源文件和目标文件;
			converter.convert(sourcefile, targetfile);
			connection.disconnect();
		} catch (ConnectException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动openOffice服务
	 */
	public static Process startOpenOffice() {
		// 启动OpenOffice的服务的完整命令
		String fullCommand = OpenOffice_HOME + command;

		try {
			return Runtime.getRuntime().exec(fullCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * writeFile为pdf文件时调用该方法将文件直接写出
	 * 
	 * @param writeFile
	 *            文件的二进制数据
	 * @param response
	 */
	public static void outputPdf(Blob writeFile, HttpServletResponse response) {
		try {
			InputStream in = writeFile.getBinaryStream();
			byte[] b = new byte[2048];
			int len;
			while ((len = in.read(b)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * writeFile非pdf文件时调用该方法将文件的二进制数据转成file类型
	 * 
	 * @param writeFile
	 *            文件的二进制数据
	 * @param fileName
	 *            文件的名称
	 * @param exName
	 *            文件的类型
	 * @param response
	 */
	public static File blobToFile(Blob writeFile, String fileName, String exName) {
		File sourcefile = new File(fileName + "." + exName);
		try {
			InputStream in = writeFile.getBinaryStream();
			byte[] buf = new byte[2048];
			int len;
			FileOutputStream out = new FileOutputStream(sourcefile);
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.flush();
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sourcefile;

	}

	/**
	 * 将传入的文件装成pdf 支持类型(office,txt)
	 * 
	 * @param sourcefile
	 *            被转文件
	 * @param fileName
	 *            文件名称
	 * @param response
	 */
	public static void fileToPdf(File sourcefile, String fileName,
			HttpServletResponse response) {
		// 调用Office2Pdf的静态方法将传入的文件转成pdf类型的文件
		File pdffile = new File(fileName + ".pdf");
		convertTo(sourcefile, pdffile);
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(pdffile);
			byte[] b = new byte[2048];
			int len;
			while ((len = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File pdffile = new File("E:\\wer.pdf");
		File sourcefile = new File(
				"C:\\Users\\Administrator\\Desktop\\员工信息表- 邓国泉.doc");
		convertTo(sourcefile, pdffile);
	}
}