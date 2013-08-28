package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;

public class HomeProfessorController extends GenericController  {
	
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
