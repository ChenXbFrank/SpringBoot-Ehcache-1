package com.roncoo.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.example.bean.Info;
import com.roncoo.example.dao.InfoDao;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	@Autowired
	private InfoDao infoDao;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Info> list() {
		return infoDao.findAll();
	}
}
