package com.cogu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cogu.model.FileModel;

@Controller
@RequestMapping("file")
public class FileController {

	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
	public ModelAndView fileUploadPage() {
		FileModel file = new FileModel();
		ModelAndView modelAndView = new ModelAndView("/all", "command", file);
		 modelAndView.setViewName("/all"); 
		return modelAndView;
	}

	@RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
	public void fileUpload(@Validated FileModel file, BindingResult bindingResult, HttpServletResponse rep)
			throws IOException {
		if (bindingResult.hasErrors()) {
			System.out.println("validation errors");
		} else {
			MultipartFile multipartFile = file.getFile();
			String uploadPath = "F:\\DataCode\\javawebcode\\SSMS\\WebRoot\\WEB-INF\\jsp" + File.separator;
			FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath + file.getFile().getOriginalFilename()));
			String fileName = multipartFile.getOriginalFilename();
			FileInputStream fis = new FileInputStream(uploadPath + fileName);
			OutputStream os = rep.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}
		    os.flush();  
			fis.close();
			os.close();
			/* req.setAttribute("imgcode",uploadPath + fileName); */
		}
		System.out.println("跳转");
	}

}
