package com.episousse.service;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class ImageConverterServiceImpl implements ImageConverterService {

	public byte[] convertImageToGray(File uploadedImage) throws IOException {
		// read "any" type of image
		BufferedImage bufferedImage = ImageIO.read(uploadedImage);
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(cs, null);
		BufferedImage image = op.filter(bufferedImage, null);

		// write it to byte array in-memory (jpg format)
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		return baos.toByteArray();
	}

}
