package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.business.ProfessorBusiness;
import br.ufjf.avaliacao.model.Professor;


public class HomeProfessorController {
	
	private Professor professor;
	private Session session = Sessions.getCurrent();
	private ProfessorBusiness professorBusiness;
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		professor = (Professor) session.getAttribute("professor");
		professorBusiness = new ProfessorBusiness();
		if (!professorBusiness.checaLogin(professor)) {
			Executions.sendRedirect("/index.zul");
			professor = new Professor();
		}
	}

}
