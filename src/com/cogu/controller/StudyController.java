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

import com.cogu.model.PageBean;
import com.cogu.model.Student;
import com.cogu.model.Study;
import com.cogu.modelDTO.StudyDTO;
import com.cogu.service.StudyService;
import com.cogu.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller()
@RequestMapping("study")
public class StudyController {

	@Autowired
	private StudyService studyService;

	@RequestMapping(value = "/openstudy", method = RequestMethod.GET)
	public String openStudy(HttpServletRequest request) {
		if (request.getSession().getAttribute("code") != null) {
			return "/study";
		} else {
			return "../../index";
		}
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.POST)
	public void getAllStudy(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, HttpServletResponse rep) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(size));
		List<Study> list = new Vector<Study>();
		List<StudyDTO> listdto = new ArrayList<StudyDTO>();
		list = studyService.getAllStudy(pageBean.getStart(), pageBean.getPageSize());
		for (Study s : list) {
			StudyDTO sto = new StudyDTO();
			sto.setSno(s.getSno());
			sto.setTerm(s.getTerm().substring(0, 4));
			if (s.getRegister() == 1) {
				sto.setRegister("是");
			} else {
				sto.setRegister("否");
			}
			if (s.getGraduate() == 1) {
				sto.setGraduate("是");
			} else {
				sto.setGraduate("否");
			}
			if (s.getStudying() == 1) {
				sto.setStudying("是");
			} else {
				sto.setStudying("否");
			}
			if (s.getRemain() == 1) {
				sto.setRemain("是");
			} else {
				sto.setRemain("否");
			}
			listdto.add(sto);
		}
		int num = studyService.getAllStudyCount();
		JSONArray jsonArray = JSONArray.fromObject(listdto);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", num);
		ResponseUtil.write(rep, result);
	}

	@RequestMapping(value = "/getAllBySno", method = RequestMethod.POST)
	public void getAllStudyBySno(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, HttpServletResponse rep,
			@RequestParam(value = "sno") String sno) throws Exception {
		int pag = 0;
		if (Integer.parseInt(page) == 0) {
			pag = Integer.parseInt(page) + 1;
		} else {
			pag = Integer.parseInt(page);
		}
		PageBean pageBean = new PageBean(pag, Integer.parseInt(size));
		List<StudyDTO> listdto = new ArrayList<StudyDTO>();
		List<Study> list = new Vector<Study>();
		list = studyService.getAllStudyBySno(sno, pageBean.getStart(), pageBean.getPageSize());
		for (Study s : list) {
			StudyDTO sto = new StudyDTO();
			sto.setSno(s.getSno());
			sto.setTerm(s.getTerm().substring(0, 4));
			if (s.getRegister() == 1) {
				sto.setRegister("是");
			} else {
				sto.setRegister("否");
			}
			if (s.getGraduate() == 1) {
				sto.setGraduate("是");
			} else {
				sto.setGraduate("否");
			}
			if (s.getStudying() == 1) {
				sto.setStudying("是");
			} else {
				sto.setStudying("否");
			}
			if (s.getRemain() == 1) {
				sto.setRemain("是");
			} else {
				sto.setRemain("否");
			}
			listdto.add(sto);
		}
		int num = studyService.getAllStudyCountBySno(sno);
		JSONArray jsonArray = JSONArray.fromObject(listdto);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", num);
		ResponseUtil.write(rep, result);
	}

	public boolean checkTerm(StudyDTO studydto) {
		boolean flag = true;
		String term = studydto.getTerm().substring(0, 4);
		List<String> list = new ArrayList<String>();
		list = studyService.getTermsBySno(studydto.getSno());
		for (String s : list) {
			System.out.println(s);
			if (s.substring(0, 4).equals(term)) {
				flag = false;
			}
		}
		return flag;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveStudy(StudyDTO studydto, HttpServletResponse rep) {
		rep.setContentType("text/html;charset=utf8");
		Study study = new Study();
		System.out.println(studydto.getTerm().substring(0, 4));
		try {
			if (checkTerm(studydto)) {
				study.setSno(studydto.getSno());
				study.setTerm(studydto.getTerm().substring(0, 4));
				if(studydto.getRegister() == "是"){
					study.setRegister(1);
				}else{
					study.setRegister(0);
				}
				if(studydto.getGraduate() == "是"){
					study.setGraduate(1);
				}else{
					study.setGraduate(0);
				}
				if(studydto.getStudying() == "是"){
					study.setStudying(1);
				}else{
					study.setStudying(0);
				}
				if(studydto.getRemain() == "是"){
					study.setRemain(1);
				}else{
					study.setRemain(0);
				}
				if (studyService.save(study)) {
					ResponseUtil.write(rep, "success");
				}
			} else {
				ResponseUtil.write(rep, "fail");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateStudy(StudyDTO studydto, HttpServletResponse rep) {
		rep.setContentType("text/html;charset=utf8");
		Study study = new Study();
		System.out.println(studydto.getTerm().substring(0, 4));
		try {
			if (checkTerm(studydto)) {
				study.setSno(studydto.getSno());
				study.setTerm(studydto.getTerm().substring(0, 4));
				if(studydto.getRegister() == "是"){
					study.setRegister(1);
				}else{
					study.setRegister(0);
				}
				if(studydto.getGraduate() == "是"){
					study.setGraduate(1);
				}else{
					study.setGraduate(0);
				}
				if(studydto.getStudying() == "是"){
					study.setStudying(1);
				}else{
					study.setStudying(0);
				}
				if(studydto.getRemain() == "是"){
					study.setRemain(1);
				}else{
					study.setRemain(0);
				}
				System.out.println(study);
				if (studyService.save(study)) {
					ResponseUtil.write(rep, "success");
				}
			} else {
				ResponseUtil.write(rep, "fail");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteStudy", method = RequestMethod.POST)
	public void deleteStudy(@RequestParam("sno") String sno, @RequestParam("term") String term,
			HttpServletResponse rep) {
		rep.setContentType("text/html;charset=utf8");
		try {
			if (studyService.delete(sno, term)) {
				ResponseUtil.write(rep, "success");
			} else {
				ResponseUtil.write(rep, "fail");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/getStudyExcel", method = RequestMethod.GET)
	public void getStudentExcel(HttpServletResponse response) {
		List<Study> list = studyService.getALlStudy();
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
		cell.setCellValue("是否注册");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("是否毕业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("是否肆业");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("是否休学");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("学期");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Study stu = (Study) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getSno());
			if (list.get(i).getRegister() == 1) {
				row.createCell((short) 1).setCellValue("是");
			} else {
				row.createCell((short) 1).setCellValue("否");
			}
			if (list.get(i).getGraduate() == 1) {
				row.createCell((short) 2).setCellValue("是");
			} else {
				row.createCell((short) 2).setCellValue("否");
			}
			if (list.get(i).getStudying() == 1) {
				row.createCell((short) 3).setCellValue("是");
			} else {
				row.createCell((short) 3).setCellValue("否");
			}
			if (list.get(i).getRemain() == 1) {
				row.createCell((short) 4).setCellValue("是");
			} else {
				row.createCell((short) 4).setCellValue("否");
			}
			row.createCell((short) 5).setCellValue(stu.getTerm().substring(0, 4));
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:/study.xls");
			wb.write(fout);
			fout.close();
			String path = "C:/study.xls";
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
