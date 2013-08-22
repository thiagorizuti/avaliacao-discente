package br.ufjf.persistent;

import org.hibernate.HibernateException;

import br.ufjf.model.Professor;

public interface IProfessorDAO {

	Professor retornaProfessor(String email, String senha)
			throws HibernateException, Exception;

}
