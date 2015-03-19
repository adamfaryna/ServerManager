package org.farynaa.servermanager.business.model.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.farynaa.servermanager.business.exception.internal.XMLParseErrorException;
import org.farynaa.servermanager.business.model.AbstractServer;

/**
 * JAXB representation of {@link Server}.
 * 
 * @author adamfaryna@gmail.com
 */
@XmlRootElement(name = "server", namespace = "http://www.opsource.net/simpleapp")
public class ServerXML extends AbstractServer {

	private static final long serialVersionUID = 9129629164787090751L;

	public static ServerXML createFromFile(String serverSpecFilename) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerXML.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			File file = new File(serverSpecFilename);
			/*
			 * Validation of XML file is provided based on this POJO type.
			 */
			return (ServerXML) unmarshaller.unmarshal(file);
			
		} catch (JAXBException e) {
			throw new XMLParseErrorException(e);
		}
	}

	@XmlElement
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@XmlElement
	@Override
	public void setName(String name) {
		super.setName(name);
	}
}
