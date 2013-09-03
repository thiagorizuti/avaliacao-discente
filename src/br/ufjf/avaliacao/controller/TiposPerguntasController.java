package br.ufjf.avaliacao.controller;

import br.ufjf.avaliacao.model.TipoPergunta;
import br.ufjf.avaliacao.persistent.impl.TipoPerguntaDAO;

import java.util.List;

public class TiposPerguntasController {
	
	private TipoPergunta tipoPergunta = new TipoPergunta();
	private TipoPerguntaDAO tipoPerguntaDAO = new TipoPerguntaDAO();
	private List<TipoPergunta> lista = (List<TipoPergunta>) tipoPerguntaDAO.procuraTodos(TipoPergunta.class, 0, 10);
	
	public void lista(){
		
	}

	public TipoPergunta getTipoPergunta() {
		return tipoPergunta;
	}

	public void setTipoPergunta(TipoPergunta tipoPergunta) {
		this.tipoPergunta = tipoPergunta;
	}

	public List<TipoPergunta> getLista() {
		return lista;
	}

	public void setLista(List<TipoPergunta> lista) {
		this.lista = lista;
	}
	
	
}
