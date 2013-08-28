package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;

public class HomeProfessorController {
	
	private Usuario usuario;
	private Session session = Sessions.getCurrent();
	private UsuarioBusiness usuarioBusiness;
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario)|| usuario.getTipoUsuario()!= 1) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
}
