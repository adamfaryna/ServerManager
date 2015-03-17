package org.farynaa.servermanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author devil
 *
 */
@Entity
@Table(schema = "data")
public class Server {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
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
		string += "name:" + getObjectToStringSafe(getName());
		return string;
	}
	
	private String getObjectToStringSafe(Object obj) {
		return obj == null ? "" : obj.toString();
	}
}
