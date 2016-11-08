package br.com.froli.stationery.model;

public class Setor implements Comparable<Setor> {

	private Long id;
	private String descricao;
	
	public Setor(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
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

	@Override
	public int compareTo(Setor setor) {
		return descricao.compareTo(setor.descricao);
	}
	
	
}
