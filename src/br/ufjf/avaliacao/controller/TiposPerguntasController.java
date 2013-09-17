package br.ufjf.avaliacao.controller;

import br.ufjf.avaliacao.model.TipoPergunta;
import br.ufjf.avaliacao.persistent.impl.TipoPerguntaDAO;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zhtml.Messagebox;

public class TiposPerguntasController {
	
	private TipoPerguntaDAO tipoPerguntaDAO = new TipoPerguntaDAO();
	private List<TipoPergunta> lista = (List<TipoPergunta>) tipoPerguntaDAO.procuraTodos(TipoPergunta.class, 0, 10);
	private TipoPergunta tipoPergunta;
	
	
	@Command
	public void deleta(int id){
		tipoPergunta=(TipoPergunta) tipoPerguntaDAO.procuraId(id, TipoPergunta.class);
		if (tipoPerguntaDAO.exclui(tipoPergunta)){
			Messagebox.show("Excluido com sucesso!");
		}
	}
	
	public List<TipoPergunta> getLista() {
		return lista;
	}

	public void setLista(List<TipoPergunta> lista) {
		this.lista = lista;
	}
	
	
	
}
