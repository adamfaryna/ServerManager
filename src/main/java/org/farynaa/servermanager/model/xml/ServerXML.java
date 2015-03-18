package org.farynaa.servermanager.model.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.farynaa.servermanager.business.exception.internal.XMLParseErrorException;
import org.farynaa.servermanager.model.AbstractServer;

/**
 * @author devil
 *
 */
@XmlRootElement
public class ServerXML extends AbstractServer {

	public static ServerXML createFromFile(String serverSpecFilename) {
		try {
			ServerXML serverXML = new ServerXML();
			JAXBContext jaxbContext = JAXBContext.newInstance(ServerXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			File file = new File(serverSpecFilename);
			jaxbMarshaller.marshal(serverXML, file);
			return serverXML;
			
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
