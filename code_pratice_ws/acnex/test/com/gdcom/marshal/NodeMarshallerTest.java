package com.gdcom.marshal;

import com.gdcom.elements.XmlAttribute;
import com.gdcom.elements.XmlElement;
import com.gdcom.tree.Node;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NodeMarshallerTest {

	XmlMarshaller xmlMarshaller = new NodeMarshaller();

	XmlElement rootElement = new XmlElement("root", "val");
	Node rootNode = new Node(rootElement, 0, null);


	@Test(expected = MarshalException.class)
	public void testMarshalExceptionOnInvalidObject() throws Exception {
		Object object = new Object();
		xmlMarshaller.marshal(object);
	}

	@Test(expected = MarshalException.class)
	public void testMarshalExceptionOnNonRootNode() throws Exception {
		XmlElement xmlElement = new XmlElement("nonRoot", "val");
		Node otherNode = new Node(xmlElement, 1, rootNode);

		xmlMarshaller.marshal(otherNode);
	}

	@Test
	public void testMarshalRootNode() throws Exception {
		String expectedString = "<root>val</root>";
		String actualString = xmlMarshaller.marshal(rootNode);

		assertEquals("the encoded xml is not proper", expectedString, actualString);
	}

	/**
	 *
	 <rootNode>
	 <elem1 id="1">
	 <name value="prem">
	 <surn>bhaskal</surn>
	 <middle>kumar</middle>
	 </name>
	 <sex>M</sex>
	 </elem1>
	 <rootNode>
	 */
	@Test
	public void testMarshalNodeCombination1() throws Exception {

		// form all the elements.
		XmlElement rootElement = new XmlElement("rootNode", null);
		XmlAttribute idAttribute = new XmlAttribute("id", "1");
		XmlElement element1 = new XmlElement("elem1", null, Arrays.asList(idAttribute));

		XmlAttribute valueAttribute = new XmlAttribute("value", "prem");
		XmlElement nameElement = new XmlElement("name", null, Arrays.asList(valueAttribute));

		XmlElement surnElement = new XmlElement("surn", "bhaskal");
		XmlElement middleElement = new XmlElement("middle", "kumar");
		XmlElement sexElement = new XmlElement("sex", "M");

		Node rootNode = new Node(rootElement, 0, null);
		Node element1Node = new Node(element1, 1, rootNode);
		Node nameNode = new Node(nameElement, 2, element1Node);
		Node surnNode = new Node(surnElement, 3, nameNode);
		Node middleNode = new Node(middleElement, 3, nameNode);
		Node sexNode = new Node(sexElement, 2, element1Node);

		rootNode.addChildNode(element1Node);
		element1Node.addChildNode(nameNode);
		element1Node.addChildNode(sexNode);

		nameNode.addChildNode(surnNode);
		nameNode.addChildNode(middleNode);

		String actualXml = "<rootNode>\n" +
				"<elem1 id=\"1\">\n" +
				"<name value=\"prem\">\n" +
				"<surn>bhaskal</surn>\n" +
				"<middle>kumar</middle>\n" +
				"</name>\n" +
				"<sex>M</sex>\n" +
				"</elem1>\n" +
				"</rootNode>";

		String expectedXml = xmlMarshaller.marshal(rootNode);

//		System.out.println(expectedXml);

		assertEquals("the encoded xml is not proper", actualXml, expectedXml);
	}

}
