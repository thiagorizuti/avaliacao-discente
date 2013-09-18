package br.ufjf.avaliacao.business;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.persistent.impl.DisciplinaDAO;

public class DisciplinaBusiness extends GenericBusiness {
	
	public boolean cadastrado(String codDisciplina, String nomeDisciplina) throws HibernateException, Exception{
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		if ((disciplinaDAO.retornaDisciplinaCod(codDisciplina) != null || disciplinaDAO.retornaDisciplinaNome(nomeDisciplina) != null))
			return true;
		else return false;	
	}
	
	//Verifica a validade do cadastro todo de uma vez com a função campoStrValido da classe GenericBusiness
	public boolean cadastroValido (String codDisciplina, String nomeDisciplina){
		if (campoStrValido(nomeDisciplina) && campoStrValido(codDisciplina))
			return true;
		else return false;
	}
}
