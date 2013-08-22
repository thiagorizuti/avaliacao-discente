package br.ufjf.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.business.LoginProfessorBusiness;
import br.ufjf.model.Professor;
import br.ufjf.persistent.impl.ProfessorDAO;

public class LoginProfessorController {

	Professor professor = new Professor();
	ProfessorDAO professorDAO = new ProfessorDAO();
	private Session session = Sessions.getCurrent();
	Professor professorCommmon;
	LoginProfessorBusiness login = new LoginProfessorBusiness();
	
	@Command
	public void submit() throws HibernateException, Exception {
		if (professor != null && professor.getEmail() != null
				&& professor.getSenha() != null) {
			String senha = professor.getSenha();
			professor = login.loginProfessor(professor.getEmail(), senha);
			if (professor != null && professor.getIdProfessor() > 0) {
				Executions.sendRedirect("/homeProfessor.zul"); 
			}
		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public ProfessorDAO getProfessorDAO() {
		return professorDAO;
	}

	public void setProfessorDAO(ProfessorDAO professorDAO) {
		this.professorDAO = professorDAO;
	}

	public Professor getProfessorCommmon() {
		return professorCommmon;
	}

	public void setProfessorCommmon(Professor professorCommmon) {
		this.professorCommmon = professorCommmon;
	}
	
}
