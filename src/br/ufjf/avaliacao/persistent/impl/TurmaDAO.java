package br.ufjf.avaliacao.persistent.impl;

import java.util.List;

import org.hibernate.Query;

import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.ITurmaDAO;

public class TurmaDAO extends GenericoDAO implements ITurmaDAO {
	
	
	@Override
	public Turma retornaTurma(String letraTurma, String semestre, Disciplina disciplina) {
		try {
			Query query = 
				getSession().createQuery("SELECT turma FROM Turma AS turma LEFT JOIN FETCH turma.professor JOIN FETCH turma.disciplina WHERE turma.letraTurma = :letraTurma AND u.semestre = :semestre AND u.disciplina = :disciplina");
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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Turma> getTodasTurmas(){
		try {
			Query query = 
					getSession().createQuery("SELECT t FROM Turma AS t LEFT JOIN FETCH t.professor JOIN FETCH t.disciplina");
			List<Turma> turmas = query.list();
			
			getSession().close();
			
			if(turmas!=null)
				return turmas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
