package wschun.shop.bean;

public class User {
	private String id;
	private String username;
	private String password;
	private String nickname;
	private String email; // 接受激活码
	private String telephone;
	private String activationcode; // 邮件激活码，由系统生成
	private Integer isactivated = 0; // 1:激活 0：未激活
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getActivationcode() {
		return activationcode;
	}
	public void setActivationcode(String activationcode) {
		this.activationcode = activationcode;
	}
	public User() {
		super();
	}
	public Integer getIsactivated() {
		return isactivated;
	}
	public void setIsactivated(Integer isactivated) {
		this.isactivated = isactivated;
	}
	public User(String id, String username, String password, String nickname,
				String email, String telephone, String activationcode,
				Integer isactivated) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.telephone = telephone;
		this.activationcode = activationcode;
		this.isactivated = isactivated;
	}
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", email=" + email
				+ ", telephone=" + telephone + ", activationcode="
				+ activationcode + ", isactivated=" + isactivated + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
