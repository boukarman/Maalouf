package com.episousse.web.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.episousse.dto.FileUploadBean;
import com.episousse.service.ImageConverterService;

@Controller
@RequestMapping("/image-converter")
public class ImageConverterController {
	
	@Autowired
	private ImageConverterService imageConverterService;
	
	@RequestMapping("/view")
	public String loadView(Model model) {
		model.addAttribute("command", new FileUploadBean());
		return "home";
	}
	
	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	public String uploadImage(@ModelAttribute("command") FileUploadBean fileUploadBean, HttpServletRequest request,
			Model model) throws IllegalStateException, IOException {
		ServletContext servletContext = request.getSession().getServletContext();
		String destination = servletContext.getRealPath("/images/"
				+ fileUploadBean.getUploadedImage().getOriginalFilename());
		File file = new File(destination);
		// if file doesnt exists, then create it
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		if (!file.exists())
			file.createNewFile();
		fileUploadBean.getUploadedImage().transferTo(file);
		byte[] result = imageConverterService.convertImageToGray(file);
		model.addAttribute("binaryResult", result);
		return "convertedImage";
	}
}
