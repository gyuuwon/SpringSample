package net.koreate.lombok;

import java.util.ArrayList;

import org.junit.Test;

import lombok.extern.java.Log;

@Log
public class LombokTest {

	@Test
	public void testLombok() {
		UserVO user = new UserVO();
		user.setUid("id001");
		user.setUpw("pw001");
	
		System.out.println(user.getUid() + " / " + user.getUpw());
		System.out.println(user);
		log.info(user.toString());
		log.warning(user.toString());
		
		ArrayList<String> list = new ArrayList<>();
		list.add("파이리");
		list.add("꼬부기");
		UserVO user1 = UserVO.builder().uid("id001").upw("pw001").uname("피카츄").friendList(list).list("라이츄").list("이상해씨").uno(1).build();
		
		log.info(user1.toString());
	}
}
