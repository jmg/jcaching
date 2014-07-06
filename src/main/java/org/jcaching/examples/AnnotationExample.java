package org.jcaching.examples;

import org.jcaching.config.Cacheable;

/*
 * 1) mvn clean package
 * Eclipse:
 * 2) -javaagent:target/jcaching-1.0-SNAPSHOT-jar-with-dependencies.jar
 * Terminal:
 * 2)<!-- java -javaagent:target/jcaching-1.0-SNAPSHOT-jar-with-dependencies.jar  org.jcaching.examples.AnnotationExamples
 * */

public class AnnotationExample {
	@Cacheable(timeout=3)
	public String a(String b,String c, Integer d){
		return ""+System.currentTimeMillis();
	}
	public static void main(String[] args) throws InterruptedException {
		AnnotationExample c = new AnnotationExample();
		String a=c.a("hola","v",1);
		System.out.println(a==c.a("hola","v",1));
		Thread.sleep(3100);
		System.out.println(a!=c.a("hola","v",1));
	}
	
}
