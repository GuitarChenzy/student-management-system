package com.cogu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cogu.model.Course;
import com.cogu.model.PageBean;
import com.cogu.model.Student;
import com.cogu.modelDTO.CourseDTO;
import com.cogu.service.CourseService;
import com.cogu.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/opencourse", method = RequestMethod.GET)
	public String openCourse(HttpServletRequest request) {
		if (request.getSession().getAttribute("code") != null) {
			return "/course";
		} else {
			return "../../index";
		}
	}

	@RequestMapping(value = "/getcourse", method = RequestMethod.POST)
	public void getStudent(@RequestParam("cno") int cno, HttpServletResponse rep) throws Exception {
		Course course = new Course();
		CourseDTO courseDTO = new CourseDTO();
		course = courseService.getCourseByCno(cno);
		int num = courseService.getStuCountByCno(cno);
		courseDTO.setCourse(course);
		courseDTO.setNum(num);
		JSONArray jsonArray = JSONArray.fromObject(courseDTO);
		ResponseUtil.write(rep, jsonArray);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public void getAllStudent(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, HttpServletResponse rep) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		List<CourseDTO> listdto = new Vector<CourseDTO>();
		List<Course> list = new Vector<Course>();
		list = courseService.getAllCourse(pageBean.getStart(), pageBean.getPageSize());
		int count = courseService.getCourseCount();
		for (Course c : list) {
			int num = courseService.getStuCountByCno(c.getCno());
			CourseDTO ct = new CourseDTO();
			ct.setCourse(c);
			ct.setNum(num);
			listdto.add(ct);
		}
		JSONArray jsonArray = JSONArray.fromObject(listdto);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", count);
		ResponseUtil.write(rep, result);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(Course course, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		try {
			if (courseService.saveCourse(course)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(Course course, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		try {
			if (courseService.updateCourse(course)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
	public void delete(@RequestParam("cno") int cno, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		try {
			System.out.println(cno);
			if (courseService.deleteCourse(cno)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getCoursesExcel", method = RequestMethod.GET)
	public void getStudentExcel(HttpServletResponse response) {
		List<CourseDTO> listdto = new ArrayList<CourseDTO>();
		List<Course> list = courseService.getAllCourse();
		for (Course c : list) {
			int num = courseService.getStuCountByCno(c.getCno());
			CourseDTO ct = new CourseDTO();
			ct.setCourse(c);
			ct.setNum(num);
			listdto.add(ct);
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("课程表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("课程编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("课程名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("课程学时");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("课程人数");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < listdto.size(); i++) {
			row = sheet.createRow((int) i + 1);
			CourseDTO stu = (CourseDTO) listdto.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue((int) stu.getCourse().getCno());
			row.createCell((short) 1).setCellValue(stu.getCourse().getCname());
			row.createCell((short) 2).setCellValue(stu.getCourse().getCperiod());
			row.createCell((short) 3).setCellValue((int) stu.getNum());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:/courses.xls");
			wb.write(fout);
			fout.close();
			String path = "C:/courses.xls";
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			System.out.println(buffer.length);
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
}
