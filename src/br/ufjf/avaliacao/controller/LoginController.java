package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;





import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.UsuarioDAO;


public class LoginController {
	
	Usuario usuario = new Usuario();
	private Session session = Sessions.getCurrent();
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	
	
	@Command
	public void submit() throws HibernateException, Exception {
		
		if (usuario != null && usuario.getEmail() != null && usuario.getSenha() != null) {
			if (usuarioBusiness.login(usuario.getEmail(), usuario.getSenha())) {
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuario = usuarioDAO.retornaUsuario(usuario.getEmail(),usuario.getSenha());
				if (usuario.getTipoUsuario()== 0) {
					Executions.sendRedirect("/homeCoordenador.zul");
				}
				if (usuario.getTipoUsuario()== 1){
					Executions.sendRedirect("/homeProfessor.zul");
				}
				if (usuario.getTipoUsuario()== 2){
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
