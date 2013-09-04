package br.ufjf.avaliacao.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;

import br.ufjf.avaliacao.business.UsuarioBusiness;
import br.ufjf.avaliacao.model.Disciplina;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.DisciplinaDAO;

public class DisciplinasController extends GenericController{
	
		private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		private List<Disciplina> disciplinas;
		private Disciplina disciplina = new Disciplina();
		private String nomeDisciplina;
		
		@Init
		public void init() throws HibernateException, Exception{
			testaLogado();
			lista();
		}
		
		public void lista(){
			disciplinas = (List<Disciplina>) disciplinaDAO.procuraTodos(Disciplina.class, -1, -1);
		}
		
		
		@Command
		@NotifyChange("disciplinas")
		public void cadastra() throws HibernateException, Exception{
			if (nomeDisciplina == null){
				Messagebox.show("Digite o nome da disciplina!");
			}
			else{
				disciplina.setNomeDisciplina(nomeDisciplina);
				if (cadastrado(disciplina.getNomeDisciplina())){
					Messagebox.show("Disciplina já cadastrada!");
				}
				else{
					if (disciplinaDAO.salvar(disciplina)){
						lista();
						nomeDisciplina = null;
						Messagebox.show("Cadastrado!");

					}
				}
			}
		}
		
		@Command
		@NotifyChange("disciplinas")
		public void exclui(@BindingParam("disciplina") Disciplina disciplina) {
			disciplinaDAO.exclui(disciplina);
			disciplinas.remove(disciplina);
		}
		
		public boolean cadastrado(String nomeDisciplina) throws HibernateException, Exception{
			if (disciplinaDAO.retornaDisciplina(nomeDisciplina) != null){
				return true;
			}
			else return false;	
		}
		
		public void testaLogado() throws HibernateException, Exception {
			usuario = (Usuario) session.getAttribute("usuario");
			usuarioBusiness = new UsuarioBusiness();
			if (!usuarioBusiness.checaLogin(usuario)|| usuario.getTipoUsuario()!= 0) {
				Executions.sendRedirect("/index.zul");
				usuario = new Usuario();
			}
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

		public String getNomeDisciplina() {
			return nomeDisciplina;
		}

		public void setNomeDisciplina(String nomeDisciplina) {
			this.nomeDisciplina = nomeDisciplina;
		}
		
		
}
