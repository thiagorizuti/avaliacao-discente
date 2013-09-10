package br.ufjf.avaliacao.test;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.PieModel;

public class ResultadosController {

	
	PieModel model;
	
	@Init
	public void init() {
		model = GraficoInfoDTO.getModel();
	}
	
	public PieModel getModel() {
        return model;
    }
}
