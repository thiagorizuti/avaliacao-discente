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
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.persistent.impl.DisciplinaDAO;
import br.ufjf.avaliacao.business.DisciplinaBusiness;

public class DisciplinasController extends GenericController{
	
		private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		private List<Disciplina> disciplinas = (List<Disciplina>) disciplinaDAO.getTodasDisciplinas();
		private Disciplina disciplina = new Disciplina();
		
		@Init
		public void init() throws HibernateException, Exception {
			testaPermissao(0);
		}
		
		@Command
		public void abreCadastro(){
			Window window = (Window) Executions.createComponents(
	                "/cadastrarDisciplina.zul", null, null);
			window.doModal();
		}
		
		@Command
		@NotifyChange("disciplinas")
		public void exclui(@BindingParam("disciplina") Disciplina disciplina) {
			DisciplinaBusiness disciplinaBusiness = new DisciplinaBusiness();
			if (!disciplinaBusiness.disciplinaUsada(disciplina)) {
				disciplinaDAO.exclui(disciplina);
				disciplinas.remove(disciplina);
				Messagebox.show("Disciplina Excluida");
			}
			else
				Messagebox.show("Impossível excluir. A disciplina está associada a alguma turma.");			
		}
		
		@Command
		public void changeEditableStatus(@BindingParam("disciplina") Disciplina disciplina) {
			disciplina.setEditingStatus(!disciplina.isEditingStatus());
			refreshRowTemplate(disciplina);
		}
		
		@Command
		public void confirm(@BindingParam("disciplina") Disciplina disciplina) throws HibernateException, Exception {
			DisciplinaBusiness business = new DisciplinaBusiness();
			if (business.cadastroValido(disciplina.getCodDisciplina(), disciplina.getNomeDisciplina()) && !business.cadastrado(disciplina.getCodDisciplina(), disciplina.getNomeDisciplina())) {
				changeEditableStatus(disciplina);
				DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				disciplinaDAO.editar(disciplina);
				refreshRowTemplate(disciplina);
			}
			else {
				Messagebox.show("Disciplina já cadastrada ou inválida");
			}
		}
		
		public void refreshRowTemplate(Disciplina disciplina) {
			BindUtils.postNotifyChange(null, null, disciplina, "editingStatus");
		}
		
		@Command
		@NotifyChange({"disciplinas","disciplina"})
		public void cadastra() throws HibernateException, Exception{
			DisciplinaBusiness disciplinaBussines = new DisciplinaBusiness();
			if (!disciplinaBussines.cadastroValido(disciplina.getCodDisciplina(), disciplina.getNomeDisciplina())) {
				Messagebox.show("Preencha todos os campos!");
			}
			else if (disciplinaBussines.cadastrado(disciplina.getCodDisciplina(),disciplina.getNomeDisciplina())) {
					Messagebox.show("Disciplina já cadastrada!");
					disciplina = new Disciplina();
			} 
			else if (disciplinaDAO.salvar(disciplina)){
					disciplinas.add(disciplina);
					Messagebox.show("Disciplina Cadastrada");
					disciplina = new Disciplina();
			}	
		}
		
		@Command
		public void cancela(@BindingParam("window") Window x) {
			disciplina = null;
			x.detach();
			Executions.sendRedirect("/disciplinas.zul");
		}
		
		
		public List<Disciplina> getDisciplinas() {
			return disciplinas;
		}
		public void setDisciplinas(List<Disciplina> disciplinas) {
			this.disciplinas = disciplinas;
		}
		public Disciplina getDisciplina() {
			return disciplina;
		}
		public void setDisciplina(Disciplina disciplina) {
			this.disciplina = disciplina;
		}		
		
}
