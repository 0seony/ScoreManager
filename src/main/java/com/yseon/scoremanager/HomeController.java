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
		DBCommon<ScoreList> db = new DBCommon<ScoreList>("d:/tomcat/scorelist.db", "scoretable");
		ArrayList<ScoreList> scorelist = db.selectArrayList(new ScoreList());
		String htmlString = "";
		for(int i = 0; i<scorelist.size(); i++) {
			htmlString += "<tr>";
			htmlString += "<td>";
			htmlString += scorelist.get(i).idx;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += scorelist.get(i).name;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += scorelist.get(i).midScore;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += scorelist.get(i).finScore;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += scorelist.get(i).created;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += "<a href='update?idx=" + scorelist.get(i).idx +"'>수정</a>";
			htmlString += "</td>";
			htmlString += "</tr>";
		}
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
		int iMidScore = Integer.parseInt(sMidscore);
		int iFidScore = Integer.parseInt(sFinscore);
		SimpleDateFormat createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created= createTime.format(Calendar.getInstance().getTime());
		DBCommon<ScoreList> db = new DBCommon<ScoreList>("d:/tomcat/scorelist.db", "scoretable");
		db.insertData(new ScoreList(sName, iMidScore, iFidScore, created));
		model.addAttribute("notice", "데이터가 입력되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		DBCommon<ScoreList> db = new DBCommon<ScoreList>("d:/tomcat/scorelist.db", "scoretable");
		db.createTable(new ScoreList());
		model.addAttribute("notice", "테이블이 생성되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMethod(Locale locale, Model model, @RequestParam("idx") int idx) {
		DBCommon<ScoreList> db = new DBCommon<ScoreList>("d:/tomcat/scorelist.db", "scoretable");
		ScoreList selectStudent = db.detailsData(new ScoreList(), idx);
		
		if (selectStudent != null) {
			model.addAttribute("idx", selectStudent.idx);
			model.addAttribute("student_name", selectStudent.name);
			model.addAttribute("midscore", selectStudent.midScore);
			model.addAttribute("finscore", selectStudent.finScore);
		}
		return "update";
	}

	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model
			, @RequestParam("idx") int idx
			, @RequestParam("student_name") String sName
			, @RequestParam("midscore") String middleScoreString
			, @RequestParam("finscore") String finalScoreString) {
		int middleScore = Integer.parseInt(middleScoreString);
		int finalScore = Integer.parseInt(finalScoreString);
		SimpleDateFormat updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updated = updateTime.format(Calendar.getInstance().getTime());

		DBCommon<ScoreList> db = new DBCommon<ScoreList>("d:/tomcat/scorelist.db", "scoretable");
		db.updateData(new ScoreList(idx, sName, middleScore, finalScore, updated));
		
		model.addAttribute("notice", "데이터가 수정되었습니다.");
		return "message";
	}
	
}
