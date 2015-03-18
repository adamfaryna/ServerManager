package org.farynaa.servermanager.dao.impl;

import java.util.List;

import org.farynaa.servermanager.dao.ServerDAO;
import org.farynaa.servermanager.model.entity.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ServerDAOImpl implements ServerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Server> getAll() {
		return (List<Server>) getCurrentSession().createCriteria(Server.class).list();
	}
	
	@Override
	public int count() {
		Object result = getCurrentSession().createCriteria(Server.class).setProjection(Projections.rowCount()).uniqueResult();
		return ((Number) result).intValue();
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Server get(Long id) {
		return (Server) getCurrentSession().get(Server.class, id);
	}

	@Override
	public void save(Server server) {
		getCurrentSession().merge(server);
	}

	@Override
	public void delete(Server server) {
		getCurrentSession().delete(server);
	}
}
