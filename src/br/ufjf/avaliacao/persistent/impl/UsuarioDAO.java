package br.ufjf.avaliacao.persistent.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IUsuarioDAO;

public class UsuarioDAO extends GenericoDAO implements IUsuarioDAO {
	
	@SuppressWarnings("finally")
	@Override
	public Usuario retornaUsuario(String email, String senha) throws HibernateException, Exception {
		Usuario usuario = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Usuario.class, "usuario")
					.add(Restrictions.eq("usuario.email", email))
					.add(Restrictions.eq("usuario.senha", senha))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			usuario = (Usuario) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return usuario;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public Usuario retornaUsuario(String nome) throws HibernateException, Exception {
		Usuario usuario = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Usuario.class, "usuario")
					.add(Restrictions.eq("usuario.nome", nome))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			usuario = (Usuario) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return usuario;
		}
	}
	
	public List<Usuario> retornaProfessores() {
		List <Usuario> usuarios = (List<Usuario>) procuraTodos(Usuario.class, -1, -1);
		List <Usuario> professores = new ArrayList<Usuario>();
		for(Usuario usuario : usuarios) {  
			  if (usuario.getTipoUsuario() == 1)
					  professores.add(usuario);
		}  
		return professores;
	}

}
