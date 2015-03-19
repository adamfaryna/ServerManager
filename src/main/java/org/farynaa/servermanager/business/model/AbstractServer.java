package org.farynaa.servermanager.business.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.farynaa.servermanager.business.model.entity.Server;

/**
 * Abstract server representation.
 * 
 * @author adamfaryna@gmail.com
 */
public abstract class AbstractServer implements Serializable {

	private static final long serialVersionUID = -6824242982082058644L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Server)) {
			return false;
		}

		Server otherServer = (Server) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(
				getId(), otherServer.getId()).build();
	}

	@Override
	public int hashCode() {
		 return new HashCodeBuilder(7, 31).append(getId()).build();
	}
	
	@Override
	public String toString() {
		String string = "id: " + getObjectToStringSafe(getId());
		string += " name:" + getObjectToStringSafe(getName());
		return string;
	}
	
	private String getObjectToStringSafe(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}
