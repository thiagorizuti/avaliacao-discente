package br.ufjf.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.ufjf.model.Usuario;
import br.ufjf.business.LoginUsuarioBusiness;

public class HomeCoordenadorController {
	
	private Usuario usuarioCommon;
	private Session session = Sessions.getCurrent();
	private LoginUsuarioBusiness loginUsuarioBusiness;
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuarioCommon = (Usuario) session.getAttribute("usuario");
		if (usuarioCommon != null) {
			loginUsuarioBusiness = new LoginUsuarioBusiness();
			usuarioCommon = loginUsuarioBusiness.loginUsuario(usuarioCommon.getEmail(),
					usuarioCommon.getSenha());
			if (usuarioCommon != null && (usuarioCommon.isCoordenador())) {
				return;
			}
		}
		usuarioErro();
	}

	private void usuarioErro() throws InterruptedException {
		Executions.sendRedirect("/index.zul");
		usuarioCommon = new Usuario();
	}
	
}