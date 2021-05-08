package com.cogu.controller;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cogu.model.PageBean;
import com.cogu.model.StuCourse;
import com.cogu.model.Student;
import com.cogu.modelDTO.AllCoursesDTO;
import com.cogu.modelDTO.CnameAndCnoDTO;
import com.cogu.modelDTO.StudentBaseDTO;
import com.cogu.service.StudentAllService;
import com.cogu.service.StudentService;
import com.cogu.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("studentAll")
public class StudentAllController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentAllService studentAllService;

	@RequestMapping(value = "/getStudentBase")
	public void getStudentInfo(@RequestParam("sno") String sno, HttpServletResponse rep) throws Exception {
		StudentBaseDTO studentBaseDTO = new StudentBaseDTO();
		Student student = studentService.getStudentBySno(sno);
		String pname = studentAllService.getPnameBySno(sno);
		String pdept = studentAllService.getPdeptBySno(sno);
		int num = studentAllService.getStuCourseCountBySno(sno);
		studentBaseDTO.setStudent(student);
		studentBaseDTO.setPname(pname);
		studentBaseDTO.setPdept(pdept);
		studentBaseDTO.setNum(num);
		JSONArray jsonArray = JSONArray.fromObject(studentBaseDTO);
		ResponseUtil.write(rep, jsonArray);
	}

	@RequestMapping(value = "/getAllCourses", method = RequestMethod.POST)
	public void getStudentAllCourses(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String size, @RequestParam("sno") String sno,
			HttpServletResponse rep) throws Exception {
		int pag = 0;
		if (Integer.parseInt(page) == 0) {
			pag = Integer.parseInt(page) + 1;
		} else {
			pag = Integer.parseInt(page);
		}
		PageBean pageBean = new PageBean(pag, Integer.parseInt(size));
		List<AllCoursesDTO> listallcourses = new Vector<AllCoursesDTO>();
		List<StuCourse> listuCourse = new Vector<StuCourse>();
		listuCourse = studentAllService.getStuCoursesBySno(sno, pageBean.getStart(), pageBean.getPageSize());
		String sname = studentAllService.getSnameBySno(sno);
		int num = listuCourse.size();
		String[] cname;
		cname = new String[num];
		for (int i = 0; i < num; i++) {
			cname[i] = studentAllService.getCnameByCno(listuCourse.get(i).getCno());
			CnameAndCnoDTO cnameAndCnoDTO = new CnameAndCnoDTO();
			cnameAndCnoDTO.setCname(cname[i]);
			cnameAndCnoDTO.setCno(listuCourse.get(i).getCno());
			AllCoursesDTO alldto = new AllCoursesDTO(listuCourse.get(i), cnameAndCnoDTO, sname);
			listallcourses.add(alldto);
		}
		JSONArray jsonArray = JSONArray.fromObject(listallcourses);
		JSONObject result = new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", num);
		ResponseUtil.write(rep, result);
	}

	@RequestMapping(value = "/getCourses", method = RequestMethod.POST)
	public void getCourse(@RequestParam("sno") String sno, HttpServletResponse rep) throws Exception {
		rep.setContentType("text/html;charset=utf8");
		rep.setCharacterEncoding("utf-8");
		List<CnameAndCnoDTO> list = studentAllService.getCourses(sno);
		JSONArray jsonArray = JSONArray.fromObject(list);
		ResponseUtil.write(rep, jsonArray);
	}

	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public void saveCourse(StuCourse stuCourse, HttpServletResponse req) {
		req.setContentType("text/html;charset=utf8");
		try {
			if (studentAllService.saveCourseBySno(stuCourse)) {
				ResponseUtil.write(req, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteCourse",method = RequestMethod.POST)
	public void deleteCourse(@RequestParam("sno") String sno, @RequestParam("cno") int cno,
			@RequestParam("psenior") String psenior, HttpServletResponse rep) {
		rep.setContentType("text/html;charset=utf8");
		try {
			if (studentAllService.deleteCourseBySno_Cno_Psenior(sno, cno, psenior)) {
				ResponseUtil.write(rep, "success");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
