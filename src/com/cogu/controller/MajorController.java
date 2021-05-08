package com.cogu.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.cogu.model.Major;
import com.cogu.model.PageBean;
import com.cogu.modelDTO.MajorDTO;
import com.cogu.service.MajorService;
import com.cogu.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("major")
public class MajorController {

	@Autowired
	private MajorService majorService;

	@RequestMapping(value = "/openmajor", method = RequestMethod.GET)
	public String openCourse(HttpServletRequest request) {
		if (request.getSession().getAttribute("code") != null) {
			return "/major";
		} else {
			return "../../index";
		}
	}

	@RequestMapping(value = "/getmajor", method = RequestMethod.POST)
	public void getStudent(@RequestParam("pno") int pno, HttpServletResponse rep) throws Exception {
		Major major = new Major();
		MajorDTO majorDTO = new MajorDTO();
		major = majorService.getMajorByPno(pno);
		int num = majorService.getStuCountByPno(pno);
		majorDTO.setMajor(major);
		majorDTO.setNum(num);
		JSONArray jsonArray = JSONArray.fromObject(majorDTO);
		ResponseUtil.write(rep, jsonArray);
	}

	@RequestMapping(value = "getAll", method = RequestMethod.POST)
	public void getAllStudent(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, HttpServletResponse rep) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		List<MajorDTO> listdto = new Vector<MajorDTO>();
		List<Major> list = new Vector<Major>();
		list = majorService.getAllMajor(pageBean.getStart(), pageBean.getPageSize());
		int count = majorService.getMajorCount();
		for (Major m : list) {
			int num = majorService.getStuCountByPno(m.getPno());
			MajorDTO mt = new MajorDTO();
			mt.setMajor(m);
			mt.setNum(num);
			listdto.add(mt);
		}
		JSONArray jsonArray = JSONArray.fromObject(listdto);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", count);
		ResponseUtil.write(rep, result);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(Major major, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		try {
			if (majorService.saveMajor(major)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(Major major, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		try {
			if (majorService.updateMajor(major)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteMajor", method = RequestMethod.POST)
	public void delete(@RequestParam("pno") int pno, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		req.setCharacterEncoding("utf-8");
		try {
			if (majorService.deleteMajor(pno)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/getmajors", method = RequestMethod.POST)
	public void getAllMajor(HttpServletResponse rep) throws Exception {
		rep.setContentType("text/html;charset=utf8");
		rep.setCharacterEncoding("utf-8");
		List<Major> list = majorService.getAllMajor();
		JSONArray jsonArray = JSONArray.fromObject(list);
		ResponseUtil.write(rep, jsonArray);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getMajorsExcel", method = RequestMethod.GET)
	public void getStudentExcel(HttpServletResponse response) {
		List<MajorDTO> listdto = new Vector<MajorDTO>();
		List<Major> list = majorService.getAllMajor();
		for (Major m : list) {
			int num = majorService.getStuCountByPno(m.getPno());
			MajorDTO mt = new MajorDTO();
			mt.setMajor(m);
			mt.setNum(num);
			listdto.add(mt);
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("专业表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("专业编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("专业名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("专业所在院系");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("专业人数");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < listdto.size(); i++) {
			row = sheet.createRow((int) i + 1);
			MajorDTO stu = (MajorDTO) listdto.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getMajor().getPno());
			row.createCell((short) 1).setCellValue(stu.getMajor().getPname());
			row.createCell((short) 2).setCellValue(stu.getMajor().getPdept());
			row.createCell((short) 3).setCellValue(stu.getNum());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:/major.xls");
			wb.write(fout);
			fout.close();
			String path = "C:/major.xls";
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

}
