package br.ufjf.avaliacao.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.model.Pergunta;
import br.ufjf.avaliacao.model.Questionario;
import br.ufjf.avaliacao.persistent.impl.PerguntaDAO;
import br.ufjf.avaliacao.persistent.impl.QuestionarioDAO;


public class QuestionariosController extends GenericController{
	
	QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	PerguntaDAO perguntaDAO = new PerguntaDAO();
	List<Questionario> questionarios = (List<Questionario>) questionarioDAO.procuraTodos(Questionario.class, -1, -1);
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	private Pergunta pergunta = new Pergunta();
	private Questionario questionario = new Questionario();
	
	
	@Command
	public void abreJanela(){
		Window window = (Window) Executions.createComponents(
                "/criarQuestionario.zul", null, null);
		window.doModal();
	}

	@Command
	@NotifyChange({"perguntas","pergunta"})
	public void adicionaPergunta(){
		perguntas.add(pergunta);
		pergunta = new Pergunta();
	}
	
	@Command
	@NotifyChange({"perguntas","pergunta"})
	public void excluiPergunta(@BindingParam("pergunta")Pergunta pergunta){
		perguntas.remove(pergunta);
	}
	
	@Command
	@NotifyChange({"questionarios","questionario"})
	public void exclui(@BindingParam("questionario")Questionario questionario){
		perguntas = questionario.getPerguntas();
		perguntaDAO	.excluiLista(perguntas);	
		questionarioDAO.exclui(questionario);
		questionarios.remove(questionario);
	}

	@Command
	public void cancela(@BindingParam("window") Window x) {
		pergunta = null;
		x.detach();
		Executions.sendRedirect("/questionarios.zul");
	}
	
	@Command
	@NotifyChange({"perguntas","questionario"})
	public void cria(){
		questionarioDAO.salvar(questionario);
		for (Pergunta pergunta : perguntas){
			pergunta.setQuestionario(questionario);
		}
		perguntaDAO.salvarLista(perguntas);
		questionario = new Questionario();
		perguntas = new ArrayList<Pergunta>();
	}
	
	
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	
	public Pergunta getPergunta() {
		return pergunta;
	}
	
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	public QuestionarioDAO getQuestionarioDAO() {
		return questionarioDAO;
	}
	
	public void setQuestionarioDAO(QuestionarioDAO questionarioDAO) {
		this.questionarioDAO = questionarioDAO;
	}
	
	public List<Questionario> getQuestionarios() {
		return questionarios;
	}
	
	public void setQuestionarios(List<Questionario> questionarios) {
		this.questionarios = questionarios;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
	
	
	

}
