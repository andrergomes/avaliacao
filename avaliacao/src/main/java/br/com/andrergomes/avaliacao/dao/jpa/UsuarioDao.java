package br.com.andrergomes.avaliacao.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andrergomes.avaliacao.model.Usuario;

public class UsuarioDao extends AbstractJpaDao<Usuario> {

	public UsuarioDao(EntityManager manager) {
		super(manager);
	}

	@SuppressWarnings("unchecked")
	public Usuario pesquisarPorLogin(String login) {
		Query query = getManager().createQuery("select u from Usuario as u where u.login = :paramLogin");

		query.setParameter("paramLogin", login);

		List<Usuario> usuarios = query.getResultList();

		return !usuarios.isEmpty() ? usuarios.get(0) : null;
	}
}
