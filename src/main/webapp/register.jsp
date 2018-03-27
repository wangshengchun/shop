<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<%@include file="inc/common_head.jsp"%>
	<script type="text/javascript">
		$("#username").blur(function () {
            if (is_registered(this.value)){
                alert("ajax")
			}
        });
	</script>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block block1">
		<div class="blank"></div>
		<div class="usBox">
			<div class="usBox_1">
				<div class="login_tab">
					<ul>
						<li onclick="location.href='login.jsp';">
							<a href="javascript:;">用户登录</a>
						</li>
						<li class="active">用户注册</li>
					</ul>
				</div>
				<form action="${root}/register" method="post" name="formUser"
					onsubmit="return register();">
					<table width="100%" border="0" align="left" cellpadding="5"
						cellspacing="3">
						<tr>
							<td width="70px" class="justify">用户名</td>
							<td>
								<input name="username" type="text" id="username" placeholder="请输入用户名" 
									 class="inputBg" />
								<span id="username_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">昵称</td>
							<td>
								<input name="nickname" type="text" id="nickname" placeholder="请输入昵称" 
									onkeyup="check_nickname(this.value);" class="inputBg" />
								<span id="nickname_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">密码</td>
							<td>
								<input name="password" type="password" id="password1" placeholder="请输入密码" 
									onkeyup="check_password(this.value);checkIntensity(this.value)" class="inputBg" />
								<span id="password_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">密码强度</td>
							<td>
								<table width="310px" border="0" cellspacing="0" cellpadding="1">
									<tr align="center">
										<td width="33%" style="border-bottom:5px solid #ccc;" id="pwd_lower">弱</td>
										<td width="33%" style="border-bottom:5px solid #ccc;" id="pwd_middle">中</td>
										<td width="33%" style="border-bottom:5px solid #ccc;" id="pwd_high">强</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="justify">确认密码</td>
							<td>
								<input name="confirm_password" type="password" id="conform_password"
									onkeyup="check_conform_password(this.value);" class="inputBg" placeholder="请输入确认密码"  />
								<span id="conform_password_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">邮箱地址</td>
							<td>
								<input type="text" name="email" id="email" 
									onkeyup="check_email(this.value);" 
									class="inputBg" placeholder="请输入邮箱地址" />
								<span id="email_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">手机号码</td>
							<td>
								<input type="text" name="telephone" id="telephone" 
									onkeyup="check_telephone(this.value);" 
									class="inputBg" placeholder="请输入手机号码" />
								<span id="telephone_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify"></td>
							<td>
								<a href="javascript:;" class="get_code">获取短信验证码</a>
								<a href="javascript:;" class="get_code">获取语音验证码</a>
							</td>
						</tr>
						<tr>
							<td class="justify">手机验证码</td>
							<td>
								<input type="text" name="code_phone" id="code_phone" 
									onkeyup="check_tel_code(this.value);" 
									class="inputBg" placeholder="请输入手机验证码" />
								<span id="code_phone_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">图片验证码</td>
							<td>
								<input id="captcha" name="captcha" class="inputBg" 
									placeholder="请输入图片验证码" maxlength="4"
									onkeyup="check_captcha(this.value);" 
								/><img id="vcode" src="validatecode.jsp" 
									onclick="src='validatecode.jsp?'+Math.random()" />
								<span id="captcha_notice"></span>
							</td>
						</tr>
						<tr>
							<td class="justify">服务条款</td>
							<td>
								<a href="javascript:;" class="abtn">点此查看《Estore商城服务条款》</a>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<a href="javascript:;">
									<input id="regBtn" value="同意服务条款并注册" 
										type="submit" class="us_Submit_reg"/>
								</a>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<a href="javascript:;" onclick="location='login.jsp'">
									<input id="loginBtn" value="已有账号？立即登录" 
										type="button" class="us_Submit_log" />
								</a>
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>
				</form>
				<div class="blank"></div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>