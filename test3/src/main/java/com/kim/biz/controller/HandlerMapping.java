package com.kim.biz.controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {  // 싱글톤 패턴이 유지됨
							   // 객체의 무분별한 생성을 막기 위해, 사용될 때마다 새로운 객체가 생성되는 것이 아님
	// input: 어떤 요청에 대해 == String
	// output: 무슨 컨트롤러를 반환해야하는지(객체를 제공해야하는지) == Controller 
//	private Map<String,Controller> mappings; // 의존관계 -> DI => 생성자 주입
//	
//	public HandlerMapping() { // 생성자로 new 하는 과정
//		mappings=new HashMap<String,Controller>();
//		mappings.put("/login.do",new LoginController());
//		mappings.put("/main.do",new MainController());
//	}
//	
//	public Controller getController(String command) {
//		return mappings.get(command);
//	}
}
