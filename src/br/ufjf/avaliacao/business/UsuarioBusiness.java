package br.ufjf.avaliacao.business;

import org.hibernate.HibernateException;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.UsuarioDAO;

public class UsuarioBusiness extends GenericBusiness {

	
	public boolean login(String email, String senha) throws HibernateException, Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.retornaUsuario(email, senha);

		if (usuario != null) {
			Session session = Sessions.getCurrent();
			session.setAttribute("usuario", usuario);
			return true;
		}

		return false;
	}

	public boolean checaLogin(Usuario usuario) throws HibernateException, Exception {
		if (usuario != null) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.retornaUsuario(usuario.getEmail(), usuario.getSenha());

			if (usuario != null) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cadastrado(String email, String nome) throws HibernateException, Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.retornaUsuario(nome)!=null || usuarioDAO.retornaUsuarioEmail(email)!=null){
			return true;
		}
		else return false;
	}
	
	
	public boolean cadastroValido(String nome, String email, String senha, Curso curso, Integer tipoUsuario){
		if(campoStrValido(nome) && campoStrValido(email) && campoStrValido(senha) && (curso!=null) && (tipoUsuario!=null))
			return true;
		else
			return false;
	}
}