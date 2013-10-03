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


public class QuestionariosController {
	
	QuestionarioDAO questionarioDAO = new QuestionarioDAO();
	List<Questionario> questionarios = (List<Questionario>) questionarioDAO.procuraTodos(Questionario.class, -1, -1);
	List<Pergunta> perguntas;
	Pergunta pergunta;
	
	
	@Command
	public void abreJanela(){
		Window window = (Window) Executions.createComponents(
                "/adicionarPergunta.zul", null, null);
		window.doModal();
	}

	@Command
	@NotifyChange("perguntas")
	public void adiciona(){
		perguntas.add(pergunta);
		pergunta = new Pergunta();
	}

	@Command
	public void cancela(@BindingParam("window") Window x) {
		pergunta = null;
		x.detach();
		Executions.sendRedirect("/criarQuestionario.zul");
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
	
	

}
