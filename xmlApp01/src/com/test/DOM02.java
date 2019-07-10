/*
 * 	로컬XML → java 프로그램(Console 기반)
 * 
 */

package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM02
{
	
	
	 public static void main(String[] args)
	   {
	      // 1. XML 파일을 메모리에 로드 → XML DOM 형성
	      // 2. 루트 엘리먼트 접근
	      // 3. 특정 하위 엘리먼트 접근 → 위치, 이름을 기준으로 접근
	      // 4. 텍스트 노드(속성 노드)를 접근 → 데이터 획득
	      // 5. 결과 출력
	      
	      try
	      {
	    	  //XML 파일을 메모리에 로드하여 XML DOM 형성
	    	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	  DocumentBuilder builder = factory.newDocumentBuilder();
	    	  Document xmlObj = null;
	    	  
	    	  // memberList.xml 파일을 프로젝트명 하위에 복사&부여넣기 할 것
	    	  String url = "memberList.xml";
	    	  xmlObj = builder.parse(url);
	    	  
	    	  //루트 엘리먼트 접근
	    	  Element root = xmlObj.getDocumentElement();
	    	  
	    	  // 특정 하위 엘리먼트 접근
	    	  NodeList memberInfoNodeList = root.getElementsByTagName("memberInfo");
	    	  
	    	  // NodeList 객체에 들어있는 Node의 갯수 확인 → getLength()
	    	  for(int i=0 ; i<memberInfoNodeList.getLength(); i++)
	    	  {
	    		  Node memberInfoNode = memberInfoNodeList.item(i);
	    		  
	    		  Element memberInfoElement = (Element)memberInfoNode;
	    		  
	    		  System.out.printf("%s %s %n"
	    				  , getText(memberInfoElement, "name")
	    				  , getText(memberInfoElement, "telephone"));
	    		  
	    		 NodeList curriculumnNodeList = memberInfoElement.getElementsByTagName("curriculumn");
	    		 
	    		 if(curriculumnNodeList.getLength()>0)
	    		 {
	    			 Node curriculumnNode = curriculumnNodeList.item(0);
	    			 Element curriculumnElement = (Element)curriculumnNode;
	    			 
	    			 //방법 1
	    			 /*
		    		 NodeList subNodeList = curriculumnElement.getElementsByTagName("sub");
		    		
		    		 for (int m = 0; m < subNodeList.getLength(); m++)
					{
		    			 Node subNode = subNodeList.item(m);
		    			 Element subElement = (Element)subNode;
		    			 System.out.printf("%s ", subElement.getTextContent());
		    			 
					}
		    		 System.out.println();
		    		 */
		    		 
		    		 //방법 2
	    			 NodeList subNodeList = curriculumnElement.getChildNodes();
	    			 for (int m = 0; m < subNodeList.getLength(); m++)
	    			 {
	    				 Node subNode = subNodeList.item(m);
	    				 
	    				 if(subNode.getNodeType()==1)
	    				 {
	    					 Element subElement = (Element)subNode;
		    				 System.out.printf("%s ", subElement.getTextContent());
	    					 
	    				 }
	    				 
	    			 }
			    	 System.out.println();
	    			 
	    			 
	    		 }
	    		 
	    		 		  
	    	  }
	    	  
	      
	    	  
	      } catch (Exception e)
	      {
	         System.out.println(e.toString());
	      }
	      

	   }
	 
	 
	 private static String getText(Element parent, String tagName)
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
