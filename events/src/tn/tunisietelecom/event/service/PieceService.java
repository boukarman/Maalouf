package tn.tunisietelecom.event.service;

import java.util.List;

import tn.tunisietelecom.event.entities.Piece;

public interface PieceService {

	void addPiece(Piece piece);

	Piece findById(long id);

	List<Piece> findAll();

	List<Piece> findPiecesByPage(int firstResult, int maxResult);

	long countPieces();

	void deletePiece(Piece piece);
}
