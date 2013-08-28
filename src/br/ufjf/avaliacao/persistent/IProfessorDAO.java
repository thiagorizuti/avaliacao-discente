package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Professor;

public interface IProfessorDAO {

	Professor retornaProfessor(String email, String senha)
			throws HibernateException, Exception;

}
