package com.lynxspa.sdm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TestController {
	
	@RequestMapping("list.sdo")
	public String list(HttpServletRequest request, ModelMap model) {
		System.out.println("Pasando");
		model.put("valor", "Prueba");

		return "test.sdm";
	}

}
