package br.com.andrergomes.avaliacao.dao;

public interface IDao<T> {

	void salvar(T paramT);

	void alterar(T paramT);

	void remover(T paramT);
}
