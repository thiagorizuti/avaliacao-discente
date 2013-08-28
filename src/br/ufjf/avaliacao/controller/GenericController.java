package br.ufjf.avaliacao.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;

public class GenericController {
	
	protected Usuario usuario;
	protected Session session = Sessions.getCurrent();
	protected UsuarioBusiness usuarioBusiness;
	
	
	@Command
	public void exit(){
		session.invalidate();
		Executions.sendRedirect("/index.zul");
	}
}
