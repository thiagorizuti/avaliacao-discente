package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;




import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;


public class LoginUsuarioController {
	
	Usuario usuario = new Usuario();
	private Session session = Sessions.getCurrent();
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	@Command
	public void submit() throws HibernateException, Exception {
		if (usuario != null && usuario.getEmail() != null && usuario.getSenha() != null) {
			
			if (usuarioBusiness.login(usuario.getEmail(), usuario.getSenha())) {
				if (!usuario.isCoordenador())
					Executions.sendRedirect("/homeAluno.zul");
				else
					Executions.sendRedirect("/homeCoordenador.zul");
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
