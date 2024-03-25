package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@RequiredArgsConstructor
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	private final BoardMapper boardMapper;
	private final ReplyMapper replyMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register..."+vo);
		boardMapper.updateReplyCnt(vo.getBno(),1);
		
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get..."+rno);
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify..."+vo);
		return replyMapper.update(vo);
	}
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove..."+rno);
		ReplyVO vo = replyMapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board"+bno);
		return replyMapper.getListWithPaging(cri,bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(
			replyMapper.getCountByBno(bno),
			replyMapper.getListWithPaging(cri, bno));
				
	}

	
	
}