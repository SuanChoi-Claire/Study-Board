package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	@Size(min =3 , max = 25)
	@NotEmpty(message = "사용자 ID는 필수 항목 입니다.")
	String username;
	
	@NotEmpty(message = "비밀번호는 필수항목 입니다.")
	String password1;
	
	@NotEmpty(message = " 비밀번호 확인은 필수항목 입니다.")
	String password2;
	
	@NotEmpty(message = "이메일은 필수항목 입니다.")
	@Email
	String email;

}
