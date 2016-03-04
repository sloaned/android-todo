package com.catalystdevworks.todo.utility;

public class ImagePackage {

	private int id;
	private String fileName;
	private byte[] newImage;

	public ImagePackage(){
	}
	
	public ImagePackage(String fileName, byte[] newImage){
		this.fileName = fileName;
		this.newImage = newImage;	
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getNewImage() {
		return newImage;
	}
	
	public void setNewImage(byte[] newImage) {
		this.newImage = newImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	

}
