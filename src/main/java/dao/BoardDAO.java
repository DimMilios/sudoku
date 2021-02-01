package dao;


import model.BoardModel;

import static model.BoardModel.*;

public interface BoardDAO {

	Iterable<BoardModelItem> findAll();

	BoardModelItem findById(int id);

	BoardModelItem save(BoardModelItem entity);
}
