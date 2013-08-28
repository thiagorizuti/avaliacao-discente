package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Usuario;


public interface IUsuarioDAO {
	public Usuario retornaUsuario (String email, String senha) throws HibernateException, Exception;
}
