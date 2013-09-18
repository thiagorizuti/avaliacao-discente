package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import br.ufjf.avaliacao.business.UsuariosBusiness;
import br.ufjf.avaliacao.model.Usuario;


public class HomeAlunoController extends GenericController {
	
	Usuario usuario = new Usuario();
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuariosBusiness();
		if (!usuarioBusiness.checaLogin(usuario)|| usuario.getTipoUsuario()!= 2) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
