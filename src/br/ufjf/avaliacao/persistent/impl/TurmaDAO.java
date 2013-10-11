package br.ufjf.avaliacao.persistent.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.ITurmaDAO;

public class TurmaDAO extends GenericoDAO implements ITurmaDAO {
	
	
	@Override
	public Turma retornaTurma(String letraTurma, String semestre, Disciplina disciplina) throws HibernateException, Exception {
		try {
			Query query = 
				getSession().createQuery("SELECT turma FROM Turma AS turma LEFT JOIN FETCH u.usuario JOIN FETCH u.disciplina WHERE u.letraTurma = :letraTurma AND u.semestre = :semestre AND u.disciplina = :disciplina");
			query.setParameter("letraTurma",letraTurma);
			query.setParameter("semestre", semestre);
			query.setParameter("disciplina", disciplina);
			
			Turma turma = (Turma) query.uniqueResult();
			
			getSession().close();
			
			if (turma != null)
				return turma;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
