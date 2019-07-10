/*
 * 	로컬XML → java 프로그램(Console 기반)
 */


package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM04
{
	
	 public static void main(String[] args)
	 {
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			String url = "RSS.xml";
			xmlObj = builder.parse(url);
			
			Element root = xmlObj.getDocumentElement();
			
			// title 뽑아내기
			Node itemNode = root.getElementsByTagName("item").item(0);
			Element itemElement = (Element)itemNode;
			
			System.out.printf("%s%n%n", XMLDOM.getText(itemElement, "title"));
			
			//기상전망 뽑아내기
			Node wfNode = root.getElementsByTagName("wf").item(0);
			Element wfElement = (Element)wfNode;
			
			System.out.println("[기상전망]--------------------------------------");
			System.out.printf("%s%n%n", wfElement.getTextContent());
			
			// 도시별 기상 예보 뽑아내기
			System.out.println("[육상날씨]--------------------------------------");
			
			NodeList locationNodeList = root.getElementsByTagName("location");
			
			for (int i = 0; i < locationNodeList.getLength(); i++)
			{
				Node locationNode = locationNodeList.item(i);
				Element locationElement = (Element)locationNode;
				
				System.out.printf("도시 : %s%n"
						, XMLDOM.getText(locationElement, "city"));
				
				System.out.println("---------------------------------------------");
				
				NodeList dataNodeList = locationElement.getElementsByTagName("data");
				
				for (int j = 0; j < dataNodeList.getLength(); j++)
				{
					Node dataNode = dataNodeList.item(j);
					Element dataElement = (Element)dataNode;
					
					System.out.printf("         %s / %s / %s~%s / %s%n"
							, XMLDOM.getText(dataElement, "tmEf")
							, XMLDOM.getText(dataElement, "wf")
							, XMLDOM.getText(dataElement, "tmn")
							, XMLDOM.getText(dataElement, "tmx")
							, XMLDOM.getText(dataElement, "reliability"));
					
				}
				
				System.out.println("---------------------------------------------");
		
				
			}
					
					
					
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		} 
	 }

}
