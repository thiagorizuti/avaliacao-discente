package br.ufjf.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.model.Usuario;
import br.ufjf.persistent.impl.UsuarioDAO;
import br.ufjf.business.LoginUsuarioBusiness;

public class LoginUsuarioController {
	
	Usuario usuario = new Usuario();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Session session = Sessions.getCurrent();
	private Usuario usuarioCommon;
	private LoginUsuarioBusiness login = new LoginUsuarioBusiness();
	
	@Command
	public void submit() throws HibernateException, Exception {
		if (usuario != null && usuario.getEmail() != null
				&& usuario.getSenha() != null) {
			String senha = usuario.getSenha();
			usuario = login.loginUsuario(usuario.getEmail(), senha);
			if (usuario != null && usuario.getIdUsuario() > 0) {
				if (!usuario.isCoordenador())
					Executions.sendRedirect("/homeAluno.zul");
				else
					Executions.sendRedirect("/homeCoordenador.zul");
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

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario getUsuarioCommon() {
		return usuarioCommon;
	}

	public void setUsuarioCommon(Usuario usuarioCommon) {
		this.usuarioCommon = usuarioCommon;
	}
}
