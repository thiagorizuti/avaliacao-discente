package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;


import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.business.ProfessorBusiness;
import br.ufjf.avaliacao.model.Professor;


public class LoginProfessorController {

	Professor professor = new Professor();
	private Session session = Sessions.getCurrent();
	ProfessorBusiness professorBusiness = new ProfessorBusiness();
	
	@Command
	public void submit() throws HibernateException, Exception {
		if (professor != null && professor.getEmail() != null && professor.getSenha() != null) {
			if (professorBusiness.login(professor.getEmail(), professor.getSenha())){
				Executions.sendRedirect("/homeProfessor.zul"); 
			}
			else{
				Messagebox.show("Usuário ou Senha inválidos!", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
