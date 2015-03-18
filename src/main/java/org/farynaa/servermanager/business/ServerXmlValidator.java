package org.farynaa.servermanager.business;

import java.io.File;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.farynaa.servermanager.business.exception.internal.InvalidSchemaFileErrorException;
import org.farynaa.servermanager.business.exception.internal.XMLParserInitErrorException;
import org.farynaa.servermanager.business.exception.validation.XMLValidationErrorException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author devil
 *
 */
public class ServerXmlValidator {

	private static final String SERVER_XSD_RESOURCE_NAME = "server.xsd";
	private String serverSpecFilename;
	
	public static void validate(String serverSpecFilename) {
		new ServerXmlValidator(serverSpecFilename).processValidation();
	}
	
	public ServerXmlValidator(String serverSpecFilename) {
		this.serverSpecFilename = serverSpecFilename;
	}
	
	private void processValidation() {
		DocumentBuilder parser = createParser();		
		Document serverSpecXMLDocument = createXMLDocumentFromSpecFile(parser);

		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = loadSchemaFile(schemaFactory);

		validateDocumentAgainstSchema(serverSpecXMLDocument, schema);
	}

	private void validateDocumentAgainstSchema(Document document, Schema schema) {
		Validator validator = schema.newValidator();

	    try {
			validator.validate(new DOMSource(document));
		} catch (Exception e) {
			throw new XMLValidationErrorException();
		}
	}

	private Schema loadSchemaFile(SchemaFactory factory) {
		InputStream serverSchemaInputStream = ServerXmlValidator.class.getClassLoader().getResourceAsStream(SERVER_XSD_RESOURCE_NAME);
		Source schemaFile = new StreamSource(serverSchemaInputStream);
		
		try {
			Schema schema = factory.newSchema(schemaFile);
			return schema;
		} catch (SAXException e) {
			throw new InvalidSchemaFileErrorException(e);
		}
	}

	private DocumentBuilder createParser() {
		try {
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return parser;
		} catch (ParserConfigurationException e) {
			throw new XMLParserInitErrorException(e);
		}
	}

	private Document createXMLDocumentFromSpecFile(DocumentBuilder parser) {
		try {
			Document document = parser.parse(new File(serverSpecFilename));
			return document;
		} catch (Exception e) {
			throw new XMLParserInitErrorException(e);
		}
	}
}
