package br.com.froli.stationery.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-22T23:46:35.170-0200")
@StaticMetamodel(Produto.class)
public class Produto_ {
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, String> fabricante;
	public static volatile SingularAttribute<Produto, String> complemento;
	public static volatile SingularAttribute<Produto, Boolean> oferta;
	public static volatile SingularAttribute<Produto, Setor> setor;
}
