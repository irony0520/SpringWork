package org.zerock.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

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
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
	}

	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새 게시글 제목");
		board.setContent("새 게시글 내용");
		board.setWriter("새 게시글 작성자");
		
		service.register(board);
		log.info("생성된 게시물의 번호: " + board.getBno());
	}
	
	@Test 
	public void testGetList() {
		service.getList(new Criteria(3,10)).forEach(board ->log.info(board));
	}
	
	@Test 
	public void testGet() {
		log.info(service.get(11L));
	}
	
	@Test 
	public void testUpdate() {
		BoardVO board = service.get(1L);
		if(board == null) {
			return;
		}
		
		log.info("Modify result: " + service.modify(board));
	}
	
	@Test 
	public void testDelete() {
		log.info("Remove result: " + service.remove(7L));
	}
}
