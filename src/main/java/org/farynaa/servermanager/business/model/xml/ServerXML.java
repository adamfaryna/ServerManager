package org.farynaa.servermanager.business.model.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.farynaa.servermanager.business.exception.internal.XMLParseErrorException;
import org.farynaa.servermanager.business.exception.validation.console.PassedServerFileParameterNotExists;
import org.farynaa.servermanager.business.model.AbstractServer;
import org.farynaa.servermanager.business.model.entity.Server;
import org.h2.util.IOUtils;

/**
 * JAXB representation of {@link Server}.
 * 
 * @author adamfaryna@gmail.com
 */
@XmlRootElement(name = "server", namespace = "http://www.opsource.net/simpleapp")
public class ServerXML extends AbstractServer {

	private static final long serialVersionUID = 9129629164787090751L;

	public static ServerXML createFromFile(InputStream serverSpecFile) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerXML.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			/*
			 * Validation of XML file is provided based on this POJO type.
			 */
			return (ServerXML) unmarshaller.unmarshal(serverSpecFile);
			
		} catch (IllegalArgumentException e) {
			throw new PassedServerFileParameterNotExists();
			
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
