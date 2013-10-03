package br.ufjf.avaliacao.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.ufjf.avaliacao.model.Pergunta;
import br.ufjf.avaliacao.persistent.impl.PerguntaDAO;

public class CriarQuestionarioController extends GenericController{
	
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	private Pergunta pergunta;
	
	
	@Command
	public void abreJanela(){
		Window window = (Window) Executions.createComponents(
                "/adicionarPergunta.zul", null, null);
		window.doModal();
	}

	@Command
	@NotifyChange("perguntas")
	public void adiciona(){
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		perguntaDAO.salvar(pergunta);
		perguntas.add(pergunta);
		pergunta = new Pergunta();
	}

	@Command
	public void cancela(@BindingParam("window") Window x) {
		pergunta = new Pergunta();
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
	
	
}
