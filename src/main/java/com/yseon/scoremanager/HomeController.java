package com.yseon.scoremanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "main";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		UserDB db = new UserDB();
		String htmlString = db.selectData();
		model.addAttribute("list", htmlString);
		return "list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {		
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAction(Locale locale, Model model,
			@RequestParam("student_name") String sName,
			@RequestParam("midscore") String sMidscore,
			@RequestParam("finscore") String sFinscore) {
		double iMidScore = Double.parseDouble(sMidscore);
		double iFidScore = Double.parseDouble(sFinscore);
		SimpleDateFormat createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created= createTime.format(Calendar.getInstance().getTime());
		UserDB db = new UserDB();
		db.insertData(sName, iMidScore, iFidScore, created);
		model.addAttribute("notice", "데이터가 입력되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		UserDB db = new UserDB();
		db.createTable();
		model.addAttribute("notice", "테이블이 생성되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMethod(Locale locale, Model model, @RequestParam("idx") int idx) {
		UserDB db = new UserDB();
		ScoreList selectData = db.detailsData(idx);
		
		if (selectData != null) {
			model.addAttribute("idx", selectData.idx);
			model.addAttribute("student_name", selectData.name);
			model.addAttribute("midscore", selectData.midScore);
			model.addAttribute("finscore", selectData.finScore);
		}
		return "update";
	}

	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model
			, @RequestParam("idx") int idx
			, @RequestParam("student_name") String sName
			, @RequestParam("midscore") String middleScoreString
			, @RequestParam("finscore") String finalScoreString) {
		double middleScore = Double.parseDouble(middleScoreString);
		double finalScore = Double.parseDouble(finalScoreString);
		SimpleDateFormat updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updated = updateTime.format(Calendar.getInstance().getTime());

		UserDB db = new UserDB();
		db.updateData(idx, sName, middleScore, finalScore, updated);
		
		model.addAttribute("notice", "데이터가 수정되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteMethod(Locale locale, Model model, @RequestParam("idx") int idx) {
		UserDB db = new UserDB();
		db.deleteData(idx);
			model.addAttribute("notice", "데이터가 삭제되었습니다.");
		return "message";
	}
	
}
