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

public class DOM03
{
	public static void main(String[] args)
	{
		
		try
		{
			  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    	  DocumentBuilder builder = factory.newDocumentBuilder();
	    	  Document xmlObj = null;
	    	  String url = "VEHICLES.xml";
	    	  xmlObj = builder.parse(url);
	    	  
	    	  Element root = xmlObj.getDocumentElement();
	    	  
	    	  NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
	    	  
	    	  System.out.println("---------------------------------------------------");
	    	  System.out.println("  NO	MAKE	MODEL	YEAR	STYLE        PRICE");
	    	  System.out.println("---------------------------------------------------");
	    	  
	    	  for (int i = 0; i <vehicleNodeList.getLength(); i++)
	    	  {
	    		  Node vehicleNode = vehicleNodeList.item(i);
	    		  Element vehicleElement = (Element)vehicleNode;
	    		  System.out.printf("%4s %6s %8s %7s %14s %6s %n"
	    				  , XMLDOM.getText(vehicleElement, "INVENTORY_NUMBER")
	    				  , XMLDOM.getText(vehicleElement, "MAKE")
	    				  , XMLDOM.getText(vehicleElement, "MODEL")
	    				  , XMLDOM.getText(vehicleElement, "YEAR")
	    				  , XMLDOM.getText(vehicleElement, "STYLE")
	    				  , XMLDOM.getText(vehicleElement, "PRICE"));
	   		  
	    		  // OPTIONS 태그 하위 엘리먼트 정보 출력 추가
				/*
				 * NodeList optionsNodeList = vehicleElement.getElementsByTagName("OPTIONS");
				 * Node optionNode = optionsNodeList.item(0); Element optionElement =
				 * (Element)optionNode;
				 */
	    		 Element optionElement = (Element)vehicleElement.getElementsByTagName("OPTIONS").item(0);
	    		 NodeList optionChildNodes = optionElement.getChildNodes();
	    		 System.out.println("Options ------------------------");
	    		 for (int a = 0; a < optionChildNodes.getLength() ; a++)
				{
					Node optionChildeNode = optionChildNodes.item(a);
					if(optionChildeNode.getNodeType()==1)
					{
						Element optionChildElement = (Element)optionChildeNode;
						System.out.printf("        %s : %s %n"
								, optionChildElement.getNodeName()
								, optionChildElement.getTextContent());
						
					}
				}
	    		 System.out.println("--------------------------------------------------");
	    		  
			}
	    		  
		} catch (Exception e)
		{
	         System.out.println(e.toString());
		}
	
	}
}
