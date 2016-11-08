package br.com.froli.stationery.model;

import java.math.BigDecimal;

public class Produto implements Comparable<Produto> {

	private Long id;
	private String descricao;
	private Long setor;
	private String fabricante;
	private String complemento;
	private BigDecimal preco;
	private Boolean oferta;
	
	public Produto(Long id, String descricao, Long setor, String fabricante, String complemento, BigDecimal preco,
			Boolean oferta) {
		this.id = id;
		this.descricao = descricao;
		this.setor = setor;
		this.fabricante = fabricante;
		this.complemento = complemento;
		this.preco = preco;
		this.oferta = oferta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getOferta() {
		return oferta;
	}

	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}

	@Override
	public int compareTo(Produto produto) {
		return descricao.compareTo(produto.descricao);
	}

}
