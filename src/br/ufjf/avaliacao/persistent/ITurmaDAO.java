package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;

public interface ITurmaDAO {

	public Turma retornaTurma(String letraTurma, String semestre,
			Disciplina disciplina) throws HibernateException, Exception;

}
