package net.koreate.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BanIPVO {

	private String ip;
	private int cnt;
	private Date bandate;


}
