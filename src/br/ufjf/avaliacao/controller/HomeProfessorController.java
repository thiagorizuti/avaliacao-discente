package br.ufjf.avaliacao.controller;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Usuario;

public class HomeProfessorController extends GenericController  {
	
	Usuario usuario = new Usuario();
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario) || usuario.getTipoUsuario()!= 1) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
	@Command
	public void consultarAva() {
		Window window = (Window)Executions.createComponents(
                "/avaliacoes.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void gerarAlgoritmos() {
		Window window = (Window)Executions.createComponents(
                "/algoritmos.zul", null, null);
        window.doModal();		
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
