package org.farynaa.servermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dozer.DozerBeanMapper;
import org.farynaa.servermanager.model.AbstractServer;
import org.farynaa.servermanager.model.xml.ServerXML;

/**
 * @author devil
 *
 */
@Entity
@Table(schema = "data")
public class Server extends AbstractServer {

	public static Server createFromXML(ServerXML serverXML) {
		return new DozerBeanMapper().map(serverXML, Server.class);
	}
	
	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return super.getId();
	}

	@Column(nullable = false)
	@Override
	public String getName() {
		return super.getName();
	}
}
