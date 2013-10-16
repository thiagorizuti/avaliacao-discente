package br.ufjf.avaliacao.persistent.impl;

import java.util.List;

import org.hibernate.Query;

import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.ICursoDAO;

public class CursoDAO extends GenericoDAO implements ICursoDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> getTodosCursos(){
		try {
			Query query = getSession().createQuery("SELECT c FROM Curso AS c ORDER BY c.nomeCurso");
			
			List<Curso> cursos = query.list();
			getSession().close();
			
			if(cursos!=null)
				return cursos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
