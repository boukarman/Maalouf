package com.episousse.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadBean {
	
	private MultipartFile uploadedImage;

	public MultipartFile getUploadedImage() {
		return uploadedImage;
	}

	public void setUploadedImage(MultipartFile uploadedImage) {
		this.uploadedImage = uploadedImage;
	}
}
