package com.cogu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cogu.service.StudentService;
import com.cogu.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cogu.model.FileModel;
import com.cogu.model.PageBean;
import com.cogu.model.Student;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/getstudent", method = RequestMethod.POST)
	public void getStudent(@RequestParam("sno") String sno, HttpServletResponse rep) throws Exception {
		Student student = new Student();
		student = studentService.getStudentBySno(sno);
		JSONArray jsonArray = JSONArray.fromObject(student);
		ResponseUtil.write(rep, jsonArray);
	}

	@RequestMapping(value = "getAll", method = RequestMethod.POST)
	public void getAllStudent(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, HttpServletResponse rep) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		List<Student> list = new Vector<Student>();
		int count = studentService.getCount();
		list = studentService.getAllStudent(pageBean.getStart(), pageBean.getPageSize());
		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", count);
		ResponseUtil.write(rep, result);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(Student student, @RequestParam(value = "major", required = true) int pno,
			HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		try {
			if (studentService.saveStudent(student) && studentService.saveMajor(student.getSno(), pno)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(Student student, @RequestParam(value = "major", required = true) int pno,
			HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		System.out.println(pno);
		try {
			if (studentService.updateStudent(student) && studentService.updateMajor(student.getSno(), pno)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteStudent", method = RequestMethod.POST)
	public void delete(@RequestParam("sno") String sno, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		try {
			String[] idStr = sno.split(",");
			if (studentService.deleteStudent(idStr[0])) {
				studentService.deleteCourseBySno(idStr[0]);
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/getStudentsExcel", method = RequestMethod.GET)
	public void getStudentExcel(HttpServletResponse response) {
		List<Student> list = studentService.getAllStudent();
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("学号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("身份证号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("出生年月");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("入学年份");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Student stu = (Student) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getScard());
			row.createCell((short) 1).setCellValue(stu.getSname());
			row.createCell((short) 2).setCellValue(stu.getSsex());
			row.createCell((short) 3).setCellValue(stu.getScard());
			row.createCell((short) 4).setCellValue(stu.getStele());
			row.createCell((short) 5).setCellValue(stu.getSbirth());
			row.createCell((short) 6).setCellValue(stu.getSstudytime());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:/students.xls");
			wb.write(fout);
			fout.close();
			String path = "C:/students.xls";
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			if (!response.isCommitted()) {
				response.reset();
			}
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/openstudent", method = RequestMethod.GET)
	public String openCourse(HttpServletRequest request) {
		if (request.getSession().getAttribute("code") != null) {
			return "/student";
		} else {
			return "../../index";
		}
	}

}
