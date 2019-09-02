package br.com.andrergomes.avaliacao.dao.jpa;

import javax.persistence.EntityManager;

import br.com.andrergomes.avaliacao.dao.IDao;

public class AbstractJpaDao<T> implements IDao<T> {

	private EntityManager manager;

	public AbstractJpaDao(EntityManager manager) {
		this.manager = manager;
	}

	public void salvar(T t) {
		this.manager.persist(t);
	}

	public void alterar(T t) {
		this.manager.merge(t);
	}

	public void remover(T t) {
		this.manager.remove(t);
	}

	public EntityManager getManager() {
		return this.manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
