package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.Queue;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(stringWriter.toString())));
            document.setXmlStandalone(false);

            Queue<Node> nodes = new LinkedList<>();
            nodes.add(document.getDocumentElement());
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        nodes.add(children.item(i));
                    }
                }

                Node child = node.getFirstChild();
                if (child != null && child.getNodeType() == Node.TEXT_NODE) {
                    String text = child.getTextContent();
                    String regex = ".*[\"'&<>]+.*";
                    if (text.matches(regex)) {
                        node.replaceChild(document.createCDATASection(text), child);
                    }
                }

                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName)) {
                    Comment commentNode = document.createComment(comment);
                    node.getParentNode().insertBefore(commentNode, node);

                }
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(sw));
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }

    @XmlRootElement(name = "first")
    static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}
