package br.ufjf.avaliacao.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Messagebox;

import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.impl.CursoDAO;

public class TesteDeLeituraController {

	@Command("upload")
	public void upload(@BindingParam("evt") UploadEvent evt) {
		Media media = evt.getMedia();
		if (!media.getName().contains("csv")) {
			Messagebox
					.show("Este não é um arquivo válido! Apenas CSV são aceitos.");
			return;
		}
		try {
			BufferedReader in = new BufferedReader(media.getReaderData());
			String linha;
			Usuario usuario;
			CursoDAO cursoDAO = new CursoDAO();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			while ((linha = in.readLine()) != null) {
				String conteudo[] = linha.split(";");
				usuario = new Usuario(conteudo[2], conteudo[0], conteudo[1],
						cursoDAO.getCursoNome(conteudo[3]),
						Integer.parseInt(conteudo[4]));
				usuarios.add(usuario);
				System.out.println(conteudo[0]);
				System.out.println(conteudo[1]);
				System.out.println(conteudo[2]);
				System.out.println(conteudo[3]);
				System.out.println(conteudo[4]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
