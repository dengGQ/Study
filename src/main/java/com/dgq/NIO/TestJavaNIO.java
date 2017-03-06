package com.dgq.NIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

public class TestJavaNIO {
	public static void main(String[] args) {
		
		ArrayList<Object> arrayList = new ArrayList<>();
		
		arrayList.get(0);
		FileChannel channel = null;
		try {
			RandomAccessFile rf = new RandomAccessFile("E:\\WorkSpace\\Nio.txt", "rw");
			
			channel = rf.getChannel();
			
			System.out.println("posi---"+channel.position());
			
			String data = "mdsafafagagagagagag"+System.currentTimeMillis();
			
			ByteBuffer buffer = ByteBuffer.allocate(data.getBytes().length);
			System.out.println(data.getBytes().length);
			buffer.put(data.getBytes());
			
			buffer.flip();
			channel.position(2L);
			while(buffer.hasRemaining()){
				channel.write(buffer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				System.out.println("posi---"+channel.position());
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void test2(){
		try {
			RandomAccessFile fromFis = new RandomAccessFile("E:\\WorkSpace\\Nio.txt", "rw");
			FileChannel fromFileChannel = fromFis.getChannel();
			
			RandomAccessFile toFis = new RandomAccessFile("E:\\WorkSpace\\Nio1.txt", "rw");
			FileChannel toFilechannel = toFis.getChannel();
			
			long count = fromFileChannel.size();
		
	System.out.println(count);		
	
			//fromFileChannel.transferTo(0, count, toFilechannel);
			toFilechannel.transferFrom(fromFileChannel, 0, count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	public static void test(){
		FileChannel fc = null;
		try {
			//获取通道
			FileInputStream fis = new FileInputStream("E:\\WorkSpace\\Nio.txt");
			fc = fis.getChannel();

			//创建缓冲器
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			int readBytes = fc.read(byteBuffer);
			
			while(readBytes != -1){
				byteBuffer.flip();
				while(byteBuffer.hasRemaining()){
					System.out.print((char)byteBuffer.get());
				}
				
				byteBuffer.clear();
				fc.read(byteBuffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
