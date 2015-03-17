package org.farynaa.servermanager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.farynaa.servermanager.exception.InvalidSchemaFileException;
import org.farynaa.servermanager.exception.XMLValidationErrorException;
import org.farynaa.servermanager.exception.internal.XMLParserConfigurationException;
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
		
		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new StreamSource(serverSchemaInputStream);
		Schema schema = null;
		try {
			schema = factory.newSchema(schemaFile);
		} catch (SAXException e1) {
			throw new InvalidSchemaFileException();
		}
		return schema;
	}

	private DocumentBuilder createParser() {
		DocumentBuilder parser = null;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLParserConfigurationException(e);
		}
		return parser;
	}

	private Document createXMLDocumentFromSpecFile(DocumentBuilder parser) {
		Document document = null;
		try {
			document = parser.parse(new File(serverSpecFilename));
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return document;
	}
	

	
	
	
}
