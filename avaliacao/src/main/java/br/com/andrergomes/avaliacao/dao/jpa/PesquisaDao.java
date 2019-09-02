package br.com.andrergomes.avaliacao.dao.jpa;

import javax.persistence.EntityManager;

import br.com.andrergomes.avaliacao.model.Pesquisa;

public class PesquisaDao extends AbstractJpaDao<Pesquisa> {

	public PesquisaDao(EntityManager manager) {
		super(manager);
	}
}
