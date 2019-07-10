/*===================================================
     DOM01.java
     - 로컬 XML → java 프로그램(Console 기준)
==================================================*/
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class DOM01
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
         // XML 파일을 메모리에 로드 → XML DOM 형성
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document xmlObj = null;
         
         // ※ 주의 : VEHICLES.xml 파일을 프로젝트명 하위에 복사&붙여넣기 할 것.
         String url = "VEHICLES.xml";
         xmlObj = builder.parse(url);
         
         // 읽어들인 XML 파일의 루트 엘리먼트 접근
         Element root  = xmlObj.getDocumentElement();
         
         //테스트
         //System.out.println(root.getNodeName());
         //--==>>VEHICLES
         
         //특정 하위 엘리먼트 접근 → 이름을 기준으로 접근
         //『getElementByTagName()』는
         //태그 이름을 가지고 자식(자손) 노드 접근 메소드
         NodeList vehicleNodeList = root.getElementsByTagName("VEHICLE");
         
         
         
         
         // ※ NodeList 객체에 들어있는 Node의 갯수를
         //    『getLength()』 메소드를 통해 확인할 수 있다.
         for (int i=0; i<vehicleNodeList.getLength();i++)
         {
            //※ 『item()』 메소드는 인덱스에 의한 노드 접근 메소드
            Node vehicleNode= vehicleNodeList.item(i);
         
            //※ 캐스트 연산자를 이용하면
            //   Node 객체를 Element 객체로 반환하는 것이 가능하다.
            //   Node는 상위 자료형, Element 는 하위 자료형이기 때문에 가능한 것이다.
            Element vehicleElement = (Element)vehicleNode;
      /*
       
            NodeList makeNodeList = vehicleElement.getElementsByTagName("MAKE");
            Node makeNode = makeNodeList.item(0);
            Element makeElement = (Element)makeNode;
            
            //테스트
            //System.out.println(makeElement.getNodeName());
            //--==>> MAKE
            System.out.printf("%s : %s %n"
                          ,makeElement.getNodeName()
                          ,makeElement.getTextContent());
            
            NodeList modelNodeList = vehicleElement.getElementsByTagName("MODEL");
            Node modelNode = modelNodeList.item(0);
            Element modelElement = (Element)modelNode; 
            
            // 테스트
            //System.out.println(modelElement.getNodeName());
            //--==>>Model>
            
            System.out.printf("%s : %s %n"
                    ,modelElement.getNodeName()
                    ,modelElement.getTextContent());
                  
            
            System.out.printf("%s : %s %n","PICTURE",getText(vehicleElement,"PICTURE"));
	*/
            
            // 특정 엘리먼트의 텍스트 데이터를 얻는
            // 사용자 정의 메소드 getText() 호출
            
            System.out.printf("%s %s %s %s %s %n"
                     ,getText(vehicleElement,"MAKE")
                     ,getText(vehicleElement,"MODEL")
                     ,getText(vehicleElement,"YEAR")
                     ,getText(vehicleElement,"PICTURE")
                     ,getText(vehicleElement,"STYLE"));
            
         }
         
         
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
   }
   
   private static String getText(Element parent, String tagName)
   {
      String result = "";
      
      // 특정 태그 이름을 가진 객체의 첫 번째 자식 노드를 얻는 과정
      Node node = parent.getElementsByTagName(tagName).item(0);
      Element element = (Element)node;
      
      // 특정 엘리먼트의 자식 노드(Text Node)의 값(nodeValue)를 얻는 과정
      result = element.getChildNodes().item(0).getNodeValue();
      
      return result;
      
   }
}