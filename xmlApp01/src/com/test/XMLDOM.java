/*
 * 	XMLDOM.java
 * 
 * 
 */
package com.test;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLDOM
{
	 public static String getText(Element parent, String tagName)
	 {
		 String result= "";
		 
		 //특정 태그 이름을 가진 객체의 첫 번째 자식 노드 얻어내기
		 Node node = parent.getElementsByTagName(tagName).item(0);
		 Element element = (Element)node;
		 
		 //특정 엘리먼트의 자식 노드(텍스트노드)의 값 얻어내기
		 result = element.getChildNodes().item(0).getNodeValue();
		 
		 
		 return result;
		 
	 }
	

}
