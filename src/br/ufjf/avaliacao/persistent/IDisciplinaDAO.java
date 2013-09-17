package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Disciplina;

public interface IDisciplinaDAO {


	Disciplina retornaDisciplinaNome(String nomeDisciplina)
			throws HibernateException, Exception;

	Disciplina retornaDisciplinaCod(String codDisciplina)
			throws HibernateException, Exception;
	

}
