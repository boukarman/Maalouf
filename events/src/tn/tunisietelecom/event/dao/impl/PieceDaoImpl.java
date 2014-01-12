package tn.tunisietelecom.event.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import tn.tunisietelecom.event.dao.PieceDao;
import tn.tunisietelecom.event.entities.Piece;

@Repository
public class PieceDaoImpl implements PieceDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Piece piece) {
		em.persist(piece);
		em.flush();
	}

	public Piece findById(long id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Piece> criteria = builder.createQuery(Piece.class);
		Root<Piece> pieceRoot = criteria.from(Piece.class);
		criteria.select(pieceRoot);
		criteria.where(builder.equal(pieceRoot.get("idPiece"), id));
		List<Piece> pieces = em.createQuery(criteria).getResultList();
		return pieces.size() > 0 ? pieces.get(0) : null;
	}

	public List<Piece> findAll() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Piece> criteria = builder.createQuery(Piece.class);
		Root<Piece> pieceRoot = criteria.from(Piece.class);
		criteria.select(pieceRoot);

		return em.createQuery(criteria).getResultList();
	}

	public List<Piece> findPiecesByPage(int firstResult, int maxResult) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Piece> criteria = builder.createQuery(Piece.class);
		Root<Piece> pieceRoot = criteria.from(Piece.class);
		criteria.select(pieceRoot);

		TypedQuery<Piece> typedQuery = em.createQuery(criteria);
		typedQuery.setFirstResult(firstResult);
		typedQuery.setMaxResults(maxResult);
		return typedQuery.getResultList();
	}

	public long countPieces() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Piece> pieceRoot = criteria.from(Piece.class);
		criteria.select(builder.count(pieceRoot));

		TypedQuery<Long> typedQuery = em.createQuery(criteria);
		return typedQuery.getSingleResult();

	}

	public void delete(Piece piece) {
		em.remove(piece);
	}

}
