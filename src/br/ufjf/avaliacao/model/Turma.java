package br.ufjf.avaliacao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * DTO da Tabela {@code Turma} contém os atributos e relacionamentos da
 * mesma.
 * 
 */
@Entity
@Table(name = "Turma")
public class Turma implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Campo com ID da turma. Relaciona com a coluna
	 * {@code idTurma} do banco e é gerado por autoincrement do MySQL
	 * através das anotações {@code @GeneratedValue(generator = "increment")} e
	 * {@code @GenericGenerator(name = "increment", strategy = "increment")}
	 * 
	 */
	@Id
	@Column(name = "idTurma", unique = true, nullable = false)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int idTurma;
	
	/**
	 * Campo com a letra da turma. Relaciona com a coluna
	 * {@code turma} do banco através da anotação
	 * {@code @Column(name = "turma", length = 45, nullable = false)}
	 * .
	 */
	@Column(name = "letraTurma", length = 45, nullable = false)
	private String turma;
	
	/**
	 * Campo com o semestre da turma. Relaciona com a coluna
	 * {@code semestre} do banco através da anotação
	 * {@code @Column(name = "semestre", nullable = false)}
	 * .
	 */
	@Column(name = "semestre", nullable = false)
	private String semestre;
	
	/**
	 * Relacionamento N para 1 entre turma e usuário Mapeando
	 * {@link Usuario} na variável {@code usuario} e retorno do tipo
	 * {@code LAZY} que indica que não será carregado automáticamente este dado
	 * quando retornarmos o {@link Turma}.
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", nullable = false)
	private Usuario usuario;
	
	/**
	 * Relacionamento N para 1 entre turma e disciplina. Mapeando
	 * {@link Disciplina} na variável {@code disciplina} e retorno do tipo
	 * {@code LAZY} que indica que não será carregado automáticamente este dado
	 * quando retornarmos o {@link Turma}.
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDisciplina", nullable = false)
	private Disciplina disciplina;

	/**
	 * Relacionamento 1 para N entre turma e avaliação. Mapeada em
	 * {@link Avaliaçao} pela variável {@code turma} e retorno do tipo
	 * {@code LAZY} que indica que não será carregado automáticamente este dado
	 * quando retornarmos o {@link Turma}.
	 * 
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "turma")
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
}
