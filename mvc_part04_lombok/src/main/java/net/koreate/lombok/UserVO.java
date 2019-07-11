package net.koreate.lombok;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	private int uno;
	private String uid;
	private String upw;
	private String uname;
	private Date regdate;
	
	@Singular("list")
	private List<String> friendList;

}
