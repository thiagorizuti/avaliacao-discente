package br.ufjf.avaliacao.persistent.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IDisciplinaDAO;

public class DisciplinaDAO extends GenericoDAO implements IDisciplinaDAO{
	
	@Override
	public Disciplina retornaDisciplinaCod(String codDisciplina) {
		try {
			Query query = 
				getSession().createQuery("SELECT disciplina FROM Disciplina AS disciplina WHERE u.codDisciplina = :codDisciplina");
			query.setParameter("codDisciplina", codDisciplina);
			
			Disciplina disciplina = (Disciplina) query.uniqueResult();
			
			getSession().close();
			
			if (disciplina!=null)
				return disciplina;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Disciplina retornaDisciplinaNome(String nomeDisciplina) throws HibernateException, Exception {
		try {
			Query query = 
				getSession().createQuery("SELECT disciplina FROM Disciplina AS disciplina WHERE u.nomeDisciplina = :nomeDisciplina");
			query.setParameter("nomeDisciplina", nomeDisciplina);
			
			Disciplina disciplina = (Disciplina) query.uniqueResult();
			
			getSession().close();
			
			if (disciplina!=null)
				return disciplina;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
