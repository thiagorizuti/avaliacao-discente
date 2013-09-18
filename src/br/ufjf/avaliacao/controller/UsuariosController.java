package br.ufjf.avaliacao.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.CursoDAO;
import br.ufjf.avaliacao.persistent.impl.UsuarioDAO;

public class UsuariosController extends GenericController {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	private CursoDAO cursoDAO = new CursoDAO();
	private List<Usuario> usuarios = (List<Usuario>) usuarioDAO.procuraTodos(Usuario.class, -1, -1);
	private List<Curso> cursos = (List<Curso>) cursoDAO.procuraTodos(Curso.class, -1, -1);
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario)|| usuario.getTipoUsuario()!= 0) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
	@Command
	public void abreCadastro(){
		Window window = (Window) Executions.createComponents(
                "/cadastrarUsuario.zul", null, null);
		window.doModal();
	}
	
	@Command
	@NotifyChange("usuarios")
	public void exclui(@BindingParam("usuario") Usuario usuario) {
		usuarioDAO.exclui(usuario);
		usuarios.remove(usuario);
	}
	
	@Command
	@NotifyChange({"usuarios","usuario"})
	public void cadastra() throws HibernateException, Exception{
		usuarioDAO.salvar(usuario);
		usuarios.add(usuario);
		usuario = new Usuario();
	}

	@Command
	public void cancela(@BindingParam("window") Window x) {
		usuarioBusiness = null;
		usuario = null;
		x.detach();
		Executions.sendRedirect("/usuarios.zul");
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
	
}
