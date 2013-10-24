package br.ufjf.avaliacao.persistent.impl;

import java.util.List;

import org.hibernate.Query;

import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.model.Questionario;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IQuestionarioDAO;

public class QuestionarioDAO extends GenericoDAO implements IQuestionarioDAO {
	
	@SuppressWarnings("unchecked")
	public List<Questionario> retornaQuestinariosCurso(Curso curso) {
	try {
			Query query = getSession().createQuery("SELECT q FROM Questionario AS q WHERE q.curso = :curso");
			query.setParameter("curso", curso);
			
			List<Questionario> questionarios = query.list();
			
			getSession().close();
			
			if (questionarios!=null)
				return questionarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

