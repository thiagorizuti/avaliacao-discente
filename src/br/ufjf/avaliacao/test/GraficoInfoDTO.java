package br.ufjf.avaliacao.test;

import org.zkoss.zul.PieModel;
import org.zkoss.zul.SimplePieModel;

public class GraficoInfoDTO {
	
	public static PieModel getModel(){
        PieModel model = new SimplePieModel();
        model.setValue("Ótimo", new Double(220));
        model.setValue("Bom", new Double(240));
        model.setValue("Regular", new Double(110));
        model.setValue("Ruim", new Double(410));
        model.setValue("Péssimo", new Double(210));
        return model;
    }
}
