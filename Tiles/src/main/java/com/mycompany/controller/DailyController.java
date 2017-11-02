package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mycompany.mapper.DailyMapper;
import com.mycompany.vo.Daily;

@Controller
public class DailyController {

	@Autowired
	private DailyMapper dailyMapper;

	@RequestMapping(value = "/daily", method = RequestMethod.GET)
	public String index(Model model) {
		// ���۷κ��� ����Ʈ�� �޾ƿ�.
		List<Daily> daily = dailyMapper.getList();

		// ���� ���� ���������� �����͸� ����
		model.addAttribute("daily", daily);
		return "daily/index";
	}

	@RequestMapping(value = "/daily/new", method = RequestMethod.GET)
	public String newDaily() {
		return "daily/new";
	}

	@RequestMapping(value = "/daily", method = RequestMethod.POST)
	public String create(@ModelAttribute Daily daily) {
		dailyMapper.create(daily);
		return "redirect:/daily";
	}

	@RequestMapping(value = "/daily/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, Model model) {
		// id ���� ���� �����͸� ������
		Daily daily = dailyMapper.getdaily(id);
		// �� �������� �����͸� ����(key/value ����)
		model.addAttribute("daily", daily);
		return "daily/edit";
	}

	@RequestMapping(value = "/daily/update", method = RequestMethod.POST)
	public String update(@ModelAttribute Daily daily) {
		dailyMapper.update(daily);
		return "redirect:/daily";
	}

	@RequestMapping(value = "/daily/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		dailyMapper.delete(id);
		return "redirect:/daily";
	}

	@RequestMapping(value = "/daily/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		Daily daily = dailyMapper.getdaily(id);
        model.addAttribute("daily", daily);
		return "daily/show";
	}
}