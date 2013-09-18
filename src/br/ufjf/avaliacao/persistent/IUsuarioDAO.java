package br.ufjf.avaliacao.persistent;

import org.hibernate.HibernateException;

import br.ufjf.avaliacao.model.Usuario;


public interface IUsuarioDAO {
	public Usuario retornaUsuario (String email, String senha) throws HibernateException, Exception;

	public Usuario retornaUsuario(String nome) throws HibernateException, Exception;

	Usuario retornaUsuarioEmail(String email) throws HibernateException, Exception;
}
