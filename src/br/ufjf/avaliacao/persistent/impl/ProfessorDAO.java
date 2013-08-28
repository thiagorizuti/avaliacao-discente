package br.ufjf.avaliacao.persistent.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.ufjf.avaliacao.model.Professor;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IProfessorDAO;

public class ProfessorDAO extends GenericoDAO implements IProfessorDAO{

	@SuppressWarnings("finally")
	@Override
	public Professor retornaProfessor(String email, String senha) throws HibernateException, Exception {
		Professor professor = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Professor.class, "professor")
					.add(Restrictions.eq("professor.email", email))
					.add(Restrictions.eq("professor.senha", senha))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			professor = (Professor) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return professor;
		}
	}
}
