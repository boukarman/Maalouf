package tn.tunisietelecom.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.tunisietelecom.event.dao.PieceDao;
import tn.tunisietelecom.event.entities.Piece;
import tn.tunisietelecom.event.service.PieceService;

@Service
public class PieceServiceImpl implements PieceService {

	@Autowired
	private PieceDao pieceDao;

	@Transactional
	public void addPiece(Piece piece) {
		this.pieceDao.save(piece);
	}

	public Piece findById(long id) {
		return this.pieceDao.findById(id);
	}

	public List<Piece> findAll() {
		return this.pieceDao.findAll();
	}

	public List<Piece> findPiecesByPage(int firstResult, int maxResult) {
		return this.pieceDao.findPiecesByPage(firstResult, maxResult);
	}

	public long countPieces() {
		return pieceDao.countPieces();
	}

	@Transactional
	public void deletePiece(Piece piece) {
		this.pieceDao.delete(piece);
	}

}
