package com.episousse.service;

import java.io.File;
import java.io.IOException;

public interface ImageConverterService {
	
	byte[] convertImageToGray(File uploadedImage) throws IOException;
}
