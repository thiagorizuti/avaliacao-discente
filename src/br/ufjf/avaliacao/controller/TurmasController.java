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
import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Turma;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.DisciplinaDAO;
import br.ufjf.avaliacao.persistent.impl.TurmaDAO;
import br.ufjf.avaliacao.persistent.impl.UsuarioDAO;

public class TurmasController extends GenericController{
	
	private TurmaDAO turmaDAO = new TurmaDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Turma> turmas;
	private Turma turma = new Turma();
	private Disciplina disciplina = new Disciplina();
	private Usuario professor = new Usuario();
	private List<Disciplina> disciplinas = (List<Disciplina>) disciplinaDAO.procuraTodos(Disciplina.class, -1, -1);
	private List<Usuario> professores = (List<Usuario>) usuarioDAO.retornaProfessores();
	
	
	@Init
	public void testaLogado() throws HibernateException, Exception {
		usuario = (Usuario) session.getAttribute("usuario");
		usuarioBusiness = new UsuarioBusiness();
		if (!usuarioBusiness.checaLogin(usuario)|| usuario.getTipoUsuario()!= 0) {
			Executions.sendRedirect("/index.zul");
			usuario = new Usuario();
		}
	}
	
	@Command
	@NotifyChange({"turmas","turma"})
	public void cadastra() throws HibernateException, Exception{
		turmaDAO.salvar(turma);
		turmas.add(turma);
		turma = new Turma();
	}
	
	@Command
	public void exclui(@BindingParam("turma") Turma turma){
		turmaDAO.exclui(turma);
		turmas.remove(turma);
		
	}
	
	@Command
	@NotifyChange("turma")
	public void edita(@BindingParam("turma") Turma turma) {
		this.setTurma(turma);
		Window window = (Window) Executions.createComponents(
                "/edicaoTurma.zul", null, null);
		window.doModal();
    }
	
	@Command
	public void modifica () throws HibernateException, Exception{
		turma.setDisciplina(disciplinaDAO.retornaDisciplinaNome(disciplina.getNomeDisciplina()));
		turma.setProfessor(usuarioDAO.retornaUsuario(professor.getNome()));
		turmaDAO.editar(turma);
		Executions.sendRedirect("/turmas.zul");
		
	}
	
	

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Usuario> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Usuario> professores) {
		this.professores = professores;
	}
	
	
	
}
