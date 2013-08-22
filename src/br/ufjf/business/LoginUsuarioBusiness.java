package br.ufjf.business;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import br.ufjf.model.Usuario;
import br.ufjf.persistent.impl.UsuarioDAO;

public class LoginUsuarioBusiness {

	
	UsuarioDAO usuarioDAO;
	
	public Usuario loginUsuario(String email, String senha) throws HibernateException, Exception {
		Usuario usuario;
		usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.retornaUsuario(email, senha);

		if (usuario != null) {
			HttpSession session = (HttpSession) (Executions.getCurrent())
					.getDesktop().getSession().getNativeSession();
			session.setAttribute("usuario", usuario);
		} else {
			usuario = new Usuario();
			Messagebox.show("Usuário ou Senha inválidos!", "Error",
					Messagebox.OK, Messagebox.ERROR);
		}
		return usuario;
	}
}
