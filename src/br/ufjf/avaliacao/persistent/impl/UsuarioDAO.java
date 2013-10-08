package br.ufjf.avaliacao.persistent.impl;

import java.util.List;

import org.hibernate.Query;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IUsuarioDAO;

public class UsuarioDAO extends GenericoDAO implements IUsuarioDAO {
	
	@Override
	public Usuario retornaUsuario(String email, String senha) {
		try {
			Query query = getSession().createQuery("SELECT u FROM Usuario AS u LEFT JOIN FETCH u.curso WHERE u.email = :email AND u.senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			
			Usuario usuario = (Usuario) query.uniqueResult();
	
			getSession().close();
			
			if(usuario!=null)
				return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@SuppressWarnings("finally")
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
	}*/
	
	@Override
	public Usuario retornaUsuario(String nome) {
		try {
			Query query = getSession().createQuery("SELECT u FROM Usuario AS u LEFT JOIN FETCH u.curso WHERE u.nome = :nome");
			query.setParameter("nome", nome);
			
			Usuario usuario = (Usuario) query.uniqueResult();
			getSession().close();
			
			if (usuario!=null)
				return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@SuppressWarnings("finally")
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
	}*/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> retornaProfessores() {
		try {
			Query query = getSession().createQuery("SELECT u FROM Usuario AS u LEFT JOIN FETCH u.curso WHERE u.tipoUsuario = :tipoUsuario");
			query.setParameter("tipoUsuario", 1);
			
			List<Usuario> professores = query.list();
			getSession().close();
			
			if (professores!=null)
				return professores;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	public List<Usuario> retornaProfessores() {
		@SuppressWarnings("unchecked")
		List <Usuario> usuarios = (List<Usuario>) procuraTodos(Usuario.class, -1, -1);
		List <Usuario> professores = new ArrayList<Usuario>();
		for(Usuario usuario : usuarios) {  
			  if (usuario.getTipoUsuario() == 1)
					  professores.add(usuario);
		}  
		return professores;
	}
	*/
	
	public Usuario retornaUsuarioEmail(String email) {
		try {
			Query query = getSession().createQuery("SELECT u FROM Usuario AS u LEFT JOIN FETCH u.curso WHERE u.email = :email");
			query.setParameter("email", email);
			
			Usuario usuario = (Usuario) query.uniqueResult();
			getSession().close();
			if (usuario!=null)
				return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@SuppressWarnings("finally")
	@Override
	public Usuario retornaUsuarioEmail(String email) throws HibernateException, Exception {
		Usuario usuario = null;
		try {

			Criteria criteria = getSession()
					.createCriteria(Usuario.class, "usuario")
					.add(Restrictions.eq("usuario.email", email))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			usuario = (Usuario) criteria.uniqueResult();
		} catch (HibernateException e) {
			System.err.println(e.fillInStackTrace());
		} finally {
			getSession().close();
			return usuario;
		}
	}*/

}
