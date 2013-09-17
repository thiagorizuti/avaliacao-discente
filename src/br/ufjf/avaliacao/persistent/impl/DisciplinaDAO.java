package br.ufjf.avaliacao.persistent.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IDisciplinaDAO;

public class DisciplinaDAO extends GenericoDAO implements IDisciplinaDAO{
	
	@SuppressWarnings("finally")
	@Override
	public Disciplina retornaDisciplinaCod(String codDisciplina) throws HibernateException, Exception {
		Disciplina disciplina = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Disciplina.class, "disciplina")
					.add(Restrictions.eq("disciplina.codDisciplina", codDisciplina))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			disciplina = (Disciplina) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return disciplina;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public Disciplina retornaDisciplinaNome(String nomeDisciplina) throws HibernateException, Exception {
		Disciplina disciplina = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Disciplina.class, "disciplina")
					.add(Restrictions.eq("disciplina.nomeDisciplina", nomeDisciplina))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			disciplina = (Disciplina) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return disciplina;
		}
	}
	
}
