package tn.tunisietelecom.event.dao;

import java.util.List;

import tn.tunisietelecom.event.entities.Piece;

public interface PieceDao {

	void save(Piece piece);

	Piece findById(long id);

	List<Piece> findAll();

	List<Piece> findPiecesByPage(int firstResult, int maxResult);

	long countPieces();

	void delete(Piece piece);
}
