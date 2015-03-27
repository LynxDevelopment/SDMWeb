package com.lynxspa.sdm.core.services;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersistenceService {
	private static Logger logger = Logger.getLogger(PersistenceService.class);
	
	protected @Autowired
	SessionFactory	sessionFactory;

	/**
	 * Crea una nueva sesión de hibernate. Es importante cerrarla manualmente.
	 * @return La nueva sesión de Hibernate
	 */
	public Session openSession() {
		return getHibernateSessionFactory().openSession();
	}
	
	/**
	 * @return la session factory configurada para Spring.
	 */
	public SessionFactory getHibernateSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * @return La sesión que utiliza actualmente Spring para el scope.
	 */
	public Session getHibernateSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Persiste un objeto en BBDD.
	 * 
	 * @param obj
	 */
	@Transactional
	public void persist(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		sessionFactory.getCurrentSession().persist(obj);
	}

	/**
	 * Guarda un objeto en BBDD.
	 * @param obj
	 */
	@Transactional
	public void save(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		sessionFactory.getCurrentSession().save(obj);
	}

	/**
	 * Actualiza un objeto en BBDD.
	 * @param obj
	 */
	@Transactional
	public void update(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		sessionFactory.getCurrentSession().update(obj);
	}

	/**
	 * Elimina un objeto físicamente de la BBDD.
	 * @param obj
	 */
	@Transactional
	public void delete(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		sessionFactory.getCurrentSession().delete(obj);
	}

	/**
	 * Devuelve un objeto del tipo _class<T> con la id _obj
	 * @param _class
	 * @param _obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> T get(Class<T> _class, Serializable _obj) {
		return (T) sessionFactory.getCurrentSession().get(_class, _obj);
	}

	/**
	 * @param _class
	 * @return Devuelve todos los objetos del tipo _class<T>
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public <T> List<T> get(Class<T> _class) {
		return createCriteria(_class).list();
	}

	/**
	 * @param query
	 * @return Devuelve un listado con el resultado de la query.
	 */
	@Transactional(readOnly = true)
	public Object get(String query) {
		return createQuery(query).list();
	}

	/**
	 * Crea una query de hibernate. El formato de la query acepta parámetros
	 * como el método estático format de la clase String.
	 * 
	 * @param fmt
	 * @param objects
	 * @return La query.
	 * 
	 * @see String#format(String, Object...)
	 */
	@Transactional
	public Query createQuery(String fmt, Object... objects) {
		return sessionFactory.getCurrentSession().createQuery(String.format(fmt, objects));
	}

	@Transactional
	public Query createQuery(String fmt, int first, int total, Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(String.format(fmt, objects));
		query.setFirstResult(first);
		query.setMaxResults(total);
		return query;
	}
	
	/**
	 * Crea un criteria para la clase.
	 * 
	 * @param _class
	 * @return
	 */
	@Transactional
	public Criteria createCriteria(Class<?> _class) {
		return sessionFactory.getCurrentSession().createCriteria(_class);
	}

	@Transactional
	public Object executeQueryForObject(String fmt, Object... objects) {
		return createQuery(fmt, objects).uniqueResult();
	}

	@Transactional
	public Object executeQueryForList(String fmt, Object... objects) {
		return createQuery(fmt, objects).list();
	}

	@Transactional
	public Object executeQueryForList(String fmt, int first, int total, Object... objects) {
		return createQuery(fmt,first, total, objects).list();
	}
	
	@Transactional
	public void saveOrUpdate(Object o) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(o);
		} catch (Throwable ex) {
			logger.error("Could not save object: " + o.toString() + "." , ex);
		}

	}
	
	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}
}
