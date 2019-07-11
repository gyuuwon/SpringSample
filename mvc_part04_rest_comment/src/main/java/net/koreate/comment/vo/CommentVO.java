package net.koreate.comment.vo;

import java.util.Date;

public class CommentVO {

	private int cno;
	private int bno;
	private String commentText;
	private String commentAuth;
	private Date regdate;
	private Date updatedate;
	
	
	public CommentVO(int cno, int bno, String commentText, String commentAuth, Date regdate, Date updatedate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.commentText = commentText;
		this.commentAuth = commentAuth;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	
	public CommentVO() {
		super();
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommentAuth() {
		return commentAuth;
	}
	public void setCommentAuth(String commentAuth) {
		this.commentAuth = commentAuth;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", commentText=" + commentText + ", commentAuth="
				+ commentAuth + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
	
}
