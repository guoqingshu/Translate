package com.example.translate.Service;

import com.example.translate.Utils.getData;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class pullXML {

    /**
     * @description 将xml字符串转换成map
     * @param
     * @return Map
     */

    public  Map readStringXmlOut(String word) {
        String requestUrl = "http://www.webxml.com.cn/WebServices/TranslatorWebService.asmx/getEnCnTwoWayTranslator";

        //params用于存储要请求的参数
        Map params = new HashMap();

        params.put("Word",word);

        //调用httpRequest方法，这个方法主要用于请求地址，并加上请求参数
        getData gd=new getData();
        String xml = gd.httpRequest(requestUrl,params);
        //System.out.println(string);
        Map map = new HashMap();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();
            // 拿到根节点的名称
            //System.out.println("根节点：" + rootElt.getName());
            // 获取根节点下的子节点string
            Iterator iter = rootElt.elementIterator("string");
            //System.out.println(iter);
            // 遍历string节点
            int i=0;
            while (iter.hasNext()){
                Element node=(Element) iter.next();
               // System.out.println(node.getStringValue());
               if(i==0){
                    map.put("原文",node.getStringValue());
               }else {
                    map.put("译文",node.getStringValue());
               }
               i++;
            }
            System.out.println(map);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

//    public static void main(String[] args) {
//        pullXML px=new pullXML();
//        getData gd=new getData();
//        px.readStringXmlOut(gd.result("China"));
//    }
}
