package br.ufjf.avaliacao.business;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.persistent.impl.DisciplinaDAO;

public class DisciplinaBusiness {
	
	public boolean cadastrado(String codDisciplina, String nomeDisciplina) throws HibernateException, Exception{
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		if (disciplinaDAO.retornaDisciplina(codDisciplina, nomeDisciplina) != null)
			return true;
		else return false;	
	}
	
	public boolean camposEmBranco (String codDisciplina, String nomeDisciplina){
		if (codDisciplina == null || nomeDisciplina == null || codDisciplina == " " || codDisciplina == " ")
			return true;
		else return false;
	}

}
