package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board->log.info(board));
	}
	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("게시글 제목");
		board.setContent("게시글 내용");
		board.setWriter("ㅇㅇ");
		
		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("게시글 제목 SelectKey");
		board.setContent("게시글 내용 SelectKey");
		board.setWriter("ㅇㅇ");
		
		mapper.insertSelectKey(board);
		log.info(board);
	}
	
	@Test 
	public void testRead() {
		BoardVO board = mapper.read(9L);	
		log.info(board);
	}
	
	@Test 
	public void testDelete() {
		log.info("Delete count: " + mapper.delete(8L));
	}
	
	@Test 
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(7L);
		board.setTitle("게시글 제목 수정");
		board.setContent("게시글 내용 수정");
		board.setWriter("user1234");
		
		int count = mapper.update(board);
		log.info("update count: " + count);
	}
	
	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(1924);
		cri.setAmount(10);
		List<BoardVO> list =mapper.getListWithPaging(cri);
		list.forEach(board->log.info(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("수정");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
	}
	
}
