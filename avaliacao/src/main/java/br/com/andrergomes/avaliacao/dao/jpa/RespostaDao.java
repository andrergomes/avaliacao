package br.com.andrergomes.avaliacao.dao.jpa;

import javax.persistence.EntityManager;

import br.com.andrergomes.avaliacao.model.Resposta;

public class RespostaDao extends AbstractJpaDao<Resposta> {

	public RespostaDao(EntityManager manager) {
		super(manager);
	}
}
