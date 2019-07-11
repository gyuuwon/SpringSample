package net.koreate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.koreate.vo.CommentVO;

public interface CommentDAO {

	@Insert("INSERT INTO tbl_comment(bno,commentText,commentAuth,uno) VALUES(#{bno},#{commentText},#{commentAuth},#{uno})")
	void addComment(CommentVO vo) throws Exception;

	// 해당 게시글의 총 댓글 개수
	@Select("SELECT count(*) FROM tbl_comment WHERE bno = #{bno}")
	int totalCount(int bno) throws Exception;

	// 페이징 처리된 댓글 목록
	@Select("SELECT * FROM tbl_comment WHERE bno = #{bno} ORDER BY cno DESC limit #{cri.pageStart} , #{cri.perPageNum}")
	List<CommentVO> listPage(Map<String, Object> paramMap) throws Exception;

	// 댓글 수정
	@Update("UPDATE tbl_comment SET commentText = #{commentText}, updatedate = now() WHERE cno = #{cno}")
	void modifyComment(CommentVO vo);

	// 댓글 삭제
	@Delete("DELETE FROM tbl_comment WHERE cno = #{cno}")
	void removeComment(int cno);

	@Delete("DELETE FROM tbl_comment WHERE bno = #{bno}")
	void deleteComments(int bno);

}
