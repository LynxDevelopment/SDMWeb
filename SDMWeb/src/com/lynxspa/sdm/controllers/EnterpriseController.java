package com.lynxspa.sdm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/list")
public class EnterpriseController {

	
   public EnterpriseController() {
		System.out.println("EnterpriseController");
	}
   
   @RequestMapping(method = RequestMethod.GET)
   public ModelAndView enterpriseList3() {
	   System.out.println("listado3");
	   return new ModelAndView("enterpriseList");
   }
//   
//   @RequestMapping(value = "/enterprise/list", method = RequestMethod.GET)
//   public ModelAndView enterpriseList() {
//	   System.out.println("listado");
//	   return new ModelAndView("enterpriseList");
//   }
//
//   @RequestMapping(value = "/list")
//   public ModelAndView enterpriseList2() {
//	   System.out.println("listado2");
//	   return new ModelAndView("enterpriseList");
//   }
//   
//   
//  
//   
//   @RequestMapping(value = "/enterprise/detail", method = RequestMethod.GET)
//   public ModelAndView enterpriseDetail() {
//	   System.out.println("detalles");
//	   return new ModelAndView("enterpriseDetail");
//   }
}