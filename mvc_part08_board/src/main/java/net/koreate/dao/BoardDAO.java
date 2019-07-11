package net.koreate.dao;

import java.util.List;
import java.util.Map;

import net.koreate.util.SearchCriteria;
import net.koreate.vo.BoardVO;

public interface BoardDAO {
	
	// 게시물 등록
	void register(BoardVO board) throws Exception;
	
	// 원본글 자신의 origin update
	void updateOrigin() throws Exception;
	
	// 첨부파일 등록
	void addAttach(String fullName) throws Exception;
	
	// 검색된 게시물 항목 중 페이징 처리된 게시물 리스트
	List<BoardVO> listReplyCriteria(SearchCriteria cri) throws Exception;
	
	// 검색된 게시물 총 개수
	int listReplyCount(SearchCriteria cri) throws Exception;
	
	// 조회수 업데이트
	void updateCnt(int bno) throws Exception;
	
	// 게시물 정보
	BoardVO readReply(int bno) throws Exception;
	
	// 첨부파일 목록
	List<String> getAttach(int bno) throws Exception;

	// 게시글 삭제
	void delete(int bno) throws Exception;
	
	// 첨부파일 목록 삭제
	void deleteAttach(int bno) throws Exception;

	// 게시물 정보 수정
	void update(BoardVO vo) throws Exception;
	
	// 첨부된 파일 정보 갱신
	void replaceAttach(Map<String, Object> paramMap);

	// 답글들의 정렬값 수정
	void updateReply(BoardVO vo) throws Exception;

	// 답글 등록
	void replyRegister(BoardVO vo) throws Exception;
	
}
