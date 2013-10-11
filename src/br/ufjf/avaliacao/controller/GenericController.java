package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
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
	
	public void testaLogado() throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario)) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
	public void testaPermissao(int tipoUsuario) throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (usuarioBusiness.checaLogin(usuario)) {
			if (usuario.getTipoUsuario() != tipoUsuario) {
				Executions.sendRedirect("/home.zul");
			}
		}
		else{
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
	public String getMenu() {
		usuario = (Usuario) session.getAttribute("usuario");
		int tipoUsuario = usuario.getTipoUsuario();
		if (tipoUsuario == 0)
			return "/menuCoordenador.zul";
		if (tipoUsuario == 1)
			return "/menuProfessor.zul";
		return "/menuAluno.zul";
	} 
	
	@Command
	public void exit(){
		session.invalidate();
		Executions.sendRedirect("/index.zul");
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
