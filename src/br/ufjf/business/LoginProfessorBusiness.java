package br.ufjf.business;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import br.ufjf.model.Professor;
import br.ufjf.model.Usuario;
import br.ufjf.persistent.impl.ProfessorDAO;
import br.ufjf.persistent.impl.UsuarioDAO;

public class LoginProfessorBusiness {

	
	ProfessorDAO professorDAO;
	
	public Professor loginProfessor(String email, String senha) throws HibernateException, Exception {
		Professor professor;
		professorDAO = new ProfessorDAO();
		professor = professorDAO.retornaProfessor(email, senha);

		if (professor != null) {
			HttpSession session = (HttpSession) (Executions.getCurrent())
					.getDesktop().getSession().getNativeSession();
			session.setAttribute("professor", professor);
		} else {
			professor = new Professor();
			Messagebox.show("Usuário ou Senha inválidos!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return professor;
	}
}
