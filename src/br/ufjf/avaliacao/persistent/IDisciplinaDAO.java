package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Disciplina;

public interface IDisciplinaDAO {


	Disciplina retornaDisciplina(String codDisciplina, String nomeDisciplina) throws HibernateException, Exception;
	

}
