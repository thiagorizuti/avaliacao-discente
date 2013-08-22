package br.ufjf.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.business.LoginProfessorBusiness;
import br.ufjf.model.Professor;

public class HomeProfessorController {
	
	private Professor professorCommon;
	private Session session = Sessions.getCurrent();
	private LoginProfessorBusiness loginProfessorBusiness;
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		professorCommon = (Professor) session.getAttribute("professor");
		if (professorCommon != null) {
			loginProfessorBusiness = new LoginProfessorBusiness();
			professorCommon = loginProfessorBusiness.loginProfessor(professorCommon.getEmail(),
					professorCommon.getSenha());
			if (professorCommon != null) {
				return;
			}
		}
		usuarioErro();
	}

	private void usuarioErro() throws InterruptedException {
		Executions.sendRedirect("/index.zul");
		professorCommon = new Professor();
	}

}
