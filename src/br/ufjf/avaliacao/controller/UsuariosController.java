package br.ufjf.avaliacao.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.CursoDAO;
import br.ufjf.avaliacao.persistent.impl.UsuarioDAO;

public class UsuariosController extends GenericController {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	private CursoDAO cursoDAO = new CursoDAO();
	private List<Usuario> usuarios = (List<Usuario>) usuarioDAO.getTodosUsuarios();
	private List<Curso> cursos = (List<Curso>) cursoDAO.getTodosCursos();
	private boolean podeSelecionar = true;
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario) || usuario.getTipoUsuario()!= 0) {
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
	public void cadastra() throws HibernateException, Exception {
		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
		if(!usuarioBusiness.cadastroValido(usuario)) {
			Messagebox.show("Preencha todos os campos!");
		}
		else if(usuarioBusiness.cadastrado(usuario.getEmail(), usuario.getNome())){
			Messagebox.show("Nome e/ou email já cadastrado!");
		}
		else if(usuarioDAO.salvar(usuario)){
			usuarios.add(usuario);
			Messagebox.show("Usuario Cadastrado");
			usuario = new Usuario();
		}
	}

	@Command
	public void cancela(@BindingParam("window") Window x) {
		usuarioBusiness = null;
		usuario = null;
		x.detach();
		Executions.sendRedirect("/usuarios.zul");
	}
	
	@Command
	public void changeEditableStatus(@BindingParam("usuario") Usuario usuario) {
		usuario.setEditingStatus(!usuario.isEditingStatus());
		refreshRowTemplate(usuario);
	}
	
	public void refreshRowTemplate(Usuario usuario) {
		BindUtils.postNotifyChange(null, null, usuario, "editingStatus");
	}
	
	@Command
	public void confirm(@BindingParam("usuario") Usuario usuario) throws HibernateException, Exception {
		UsuarioBusiness business = new UsuarioBusiness();
		if (business.cadastroValido(usuario)){
			changeEditableStatus(usuario);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.editar(usuario);
			refreshRowTemplate(usuario);
		}
		else {
			Messagebox.show("Usuário já cadastrado ou inválido");
		}
	}
	
	@Command
	public void verifica(@BindingParam("tipo") Comboitem tipo, Combobox cmb){
		podeSelecionar = tipo.getIndex() != 2;
	}
	
	public boolean getPodeSelecionar(){
		return this.podeSelecionar;
	}
	
	public void setPodeSelecionar(boolean podeSelecionar){
		this.podeSelecionar = podeSelecionar;
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
