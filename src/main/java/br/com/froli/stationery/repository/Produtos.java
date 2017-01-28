package br.com.froli.stationery.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.froli.stationery.model.Produto;
import br.com.froli.stationery.model.Produto_;

public class Produtos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	
	public Produtos(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Produto> todos() {
		TypedQuery<Produto> query = manager.createQuery("from Produto", Produto.class);
		return query.getResultList();
	}
	
	public List<Produto> emOferta() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		TypedQuery<Produto> typedQuery = manager.createQuery(
		    query.select(from )
		    .where(
		       //builder.equal(from.get("oferta"), true)
		       builder.equal(from.get(Produto_.oferta), true)
		    )
		);
		
		return typedQuery.getResultList();
	}
}
