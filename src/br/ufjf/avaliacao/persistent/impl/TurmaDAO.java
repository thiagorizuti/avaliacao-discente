package br.ufjf.avaliacao.persistent.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.ITurmaDAO;

public class TurmaDAO extends GenericoDAO implements ITurmaDAO {
	
	@SuppressWarnings("finally")
	@Override
	public Turma retornaTurma(String letraTurma, String semestre, Disciplina disciplina) throws HibernateException, Exception {
		Turma turma = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Turma.class, "turma")
					.add(Restrictions.eq("turma.letraTurma", letraTurma))
					.add(Restrictions.eq("turma.semestre", semestre))
					.add(Restrictions.eq("usuario.disciplina", disciplina))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			turma = (Turma) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return turma;
		}
	}
}
