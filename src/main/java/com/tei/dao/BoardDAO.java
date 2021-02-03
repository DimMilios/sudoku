package com.tei.dao;


import static com.tei.model.BoardModel.*;

public interface BoardDAO {

	Iterable<BoardModelItem> findAll();

	BoardModelItem findById(int id);

	BoardModelItem save(BoardModelItem entity);
}
