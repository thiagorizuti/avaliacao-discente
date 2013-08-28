package br.ufjf.avaliacao.test;

import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufjf.avaliacao.business.ProfessorBusiness;
import br.ufjf.avaliacao.model.Professor;

public class ProfessorBusinessTest {

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginTrue() throws HibernateException, Exception {
		ProfessorBusiness professorBusiness = new ProfessorBusiness();
		assertTrue("Deve retornar true",professorBusiness.login("profteste", "123"));
	}
	
	@Test
	public void testLoginFalse() throws HibernateException, Exception {
		ProfessorBusiness professorBusiness = new ProfessorBusiness();
		assertFalse("Deve retornar false",professorBusiness.login("profteste", "senhaincorreta"));
	}

	@Test
	public void testChecaLoginTrue() throws HibernateException, Exception {
		ProfessorBusiness professorBusiness = new ProfessorBusiness();
		Professor professor = new Professor();
		professor.setEmail("profteste");
		professor.setSenha("123");
		assertTrue("Deve retornar true",professorBusiness.checaLogin(professor));
	}
	
	@Test
	public void testChecaLoginFalse() throws HibernateException, Exception {
		ProfessorBusiness professorBusiness = new ProfessorBusiness();
		Professor professor = new Professor();
		professor.setEmail("profteste");
		professor.setSenha("senhaincorreta");
		assertFalse("Deve retornar false",professorBusiness.checaLogin(professor));
	}

}
