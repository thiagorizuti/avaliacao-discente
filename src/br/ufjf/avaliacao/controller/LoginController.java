package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.business.UsuariosBusiness;
import br.ufjf.avaliacao.model.Usuario;



public class LoginController {
	
	Usuario usuario = new Usuario();
	private Session session = Sessions.getCurrent();
	private UsuariosBusiness usuarioBusiness = new UsuariosBusiness();
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuarioBusiness = new UsuariosBusiness();
		usuario = (Usuario) session.getAttribute("usuario");
		if (usuarioBusiness.checaLogin(usuario)) {
			if (usuario.getTipoUsuario() == 0) {
				Executions.sendRedirect("/homeCoordenador.zul");
			}
			else if (usuario.getTipoUsuario() == 1){
				Executions.sendRedirect("/homeProfessor.zul");
			}
			else if (usuario.getTipoUsuario() == 2){
				Executions.sendRedirect("/homeAluno.zul");
			}
		}
		else {
			usuario = new Usuario();
		}
	}
	
	@Command
	public void submit() throws HibernateException, Exception {
		
		if (usuario != null && usuario.getEmail() != null && usuario.getSenha() != null) {
			if (usuarioBusiness.login(usuario.getEmail(), usuario.getSenha())) {
				usuario = (Usuario) session.getAttribute("usuario");
				if (usuario.getTipoUsuario() == 0) {
					Executions.sendRedirect("/homeCoordenador.zul");
				}
				else if (usuario.getTipoUsuario() == 1){
					Executions.sendRedirect("/homeProfessor.zul");
				}
				else if (usuario.getTipoUsuario() == 2){
					Executions.sendRedirect("/homeAluno.zul");
				}
			}	
			else {
				Messagebox.show("Usuário ou Senha inválidos!", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}

	
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
