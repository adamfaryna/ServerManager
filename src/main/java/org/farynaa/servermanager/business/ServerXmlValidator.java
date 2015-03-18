package org.farynaa.servermanager.business;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.farynaa.servermanager.business.exception.internal.InvalidSchemaFileErrorException;
import org.farynaa.servermanager.business.exception.internal.XMLParseErrorException;
import org.farynaa.servermanager.business.exception.internal.XMLParserInitErrorException;
import org.farynaa.servermanager.business.exception.validation.XMLValidationErrorException;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

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
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setValidating(false);
		parserFactory.setNamespaceAware(true);
		
		InputStream serverSchemaInputStream = ServerXmlValidator.class.getClassLoader().getResourceAsStream(SERVER_XSD_RESOURCE_NAME);
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = null;
		
		try {
			schema = schemaFactory.newSchema(new Source[] {new StreamSource(serverSchemaInputStream)});
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		parserFactory.setSchema(schema);
		
		
		
		SAXParser parser = null;
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XMLReader xmlReader = null;
		try {
			xmlReader = parser.getXMLReader();
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xmlReader.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception) throws SAXException {
				
			}
			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				throw new XMLParseErrorException(exception);
			}
			@Override
			public void error(SAXParseException exception) throws SAXException {
				throw new XMLParseErrorException(exception);
			}
		});
		
		try {
			xmlReader.parse(new InputSource(serverSpecFilename));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DocumentBuilder parser = createParser();		
//		Document serverSpecXMLDocument = createXMLDocumentFromSpecFile(parser);
//
//		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//		Schema schema = loadSchemaFile(schemaFactory);
//
//		validateDocumentAgainstSchema(serverSpecXMLDocument, schema);
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
