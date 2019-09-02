package br.com.andrergomes.avaliacao.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("avaliacaoImagem");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
