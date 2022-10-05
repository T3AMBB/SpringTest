package com.kim.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kim.biz.board.BoardVO;

public class BoardDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	final String sql_selectAll_TITLE="SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_selectAll_WRITER="SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT,UPLOADFILE) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=?,UPLOADFILE=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	final String sql_cnt="UPDATE BOARD SET CNT=CNT+1 WHERE BID=?";

	void insertBoard(BoardVO vo) {
		jdbcTemplate.update(sql_insert,vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
	}
	void updateBoard(BoardVO vo) {
		jdbcTemplate.update(sql_update,vo.getTitle(),vo.getContent(),vo.getFileName(),vo.getBid());
	}
	void deleteBoard(BoardVO vo) {
		jdbcTemplate.update(sql_delete,vo.getBid());
	}
	BoardVO selectOneBoard(BoardVO vo) {
		Object[] args= {vo.getBid()};
		jdbcTemplate.update(sql_cnt,vo.getBid());
		return jdbcTemplate.queryForObject(sql_selectOne,args,new BoardRowMapper());
	}
	List<BoardVO> selectAllBoard(BoardVO vo) {
		Object[] args= {vo.getSearchContent()};
		if(vo.getSearchCondition().equals("TITLE")) {
			return jdbcTemplate.query(sql_selectAll_TITLE,args,new BoardRowMapper());
		}
		else {
			return jdbcTemplate.query(sql_selectAll_WRITER,args,new BoardRowMapper());
		}
	}
}
class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setCnt(rs.getInt("CNT"));
		data.setRegdate(rs.getString("REGDATE"));
		data.setFileName(rs.getString("UPLOADFILE"));
		return data;
	}
	
}
