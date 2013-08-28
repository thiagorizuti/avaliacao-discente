package br.ufjf.avaliacao.business;

import org.hibernate.HibernateException;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.model.Professor;
import br.ufjf.avaliacao.persistent.impl.ProfessorDAO;
 
public class ProfessorBusiness{
	
	public boolean login(String email, String senha) throws HibernateException, Exception {
		Professor professor;
		ProfessorDAO professorDAO = new ProfessorDAO();
		professor = professorDAO.retornaProfessor(email, senha);

		if (professor != null) {
			Session session = Sessions.getCurrent();
			session.setAttribute("professor", professor);
			return true;
		}

		return false;
	}

	public boolean checaLogin(Professor professor) throws HibernateException, Exception {
		if (professor != null) {
			ProfessorDAO professorDAO = new ProfessorDAO();
			professor = professorDAO.retornaProfessor(professor.getEmail(), professor.getSenha());

			if (professor != null) {
				return true;
			}
		}
	
		return false;
	}
}

