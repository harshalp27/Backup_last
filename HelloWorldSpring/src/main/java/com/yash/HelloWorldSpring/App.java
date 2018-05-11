package com.yash.HelloWorldSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml");

		HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		obj.printHello();
		
		HelloWorld obj1 = (HelloWorld) context.getBean("helloBean");
		obj.printHello();
	}
}