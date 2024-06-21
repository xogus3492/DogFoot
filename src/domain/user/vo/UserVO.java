package domain.user.vo;

import javax.servlet.http.HttpServletRequest;

import global.util.FileUploadUtil;
import global.util.PasswordEncryption;

public class UserVO {
	private String userId;
	private String email;
	private String password;
	private String name;
	private String role;
	private String tel;
	private String gender;
	private String info;
	private String birthDay;
	private String zoneCode;
	private String roadAddress;
	private String detailAddress;
	private String jibunAddress;
	private String photo;
	private String deleteyn;
	private String createdDate;
	private String modifiedDate;
	
	public UserVO() {
	}
	
	public UserVO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public UserVO(String userId, String email, String password, String name, String role,
			String tel, String gender, String info, String birthDay, String zoneCode,
			String roadAddress, String detailAddress, String jibunAddress, String photo, String deleteyn,
			String createdDate, String modifiedDate) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.role = role;
		this.tel = tel;
		this.gender = gender;
		this.info = info;
		this.birthDay = birthDay;
		this.zoneCode = zoneCode;
		this.roadAddress = roadAddress;
		this.detailAddress = detailAddress;
		this.jibunAddress = jibunAddress;
		this.photo = photo;
		this.deleteyn = deleteyn;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getJibunAddress() {
		return jibunAddress;
	}
	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public static UserVO of(FileUploadUtil fu) {
		String birth = fu.getParameter("birth_year") + "/" + fu.getParameter("birth_month") + "/" + fu.getParameter("birth_day");
		return new UserVO(
					null, fu.getParameter("email"), PasswordEncryption.hashPassword(fu.getParameter("password")),
					fu.getParameter("name"), null, fu.getParameter("tel"), 
					fu.getParameter("gender"), fu.getParameter("info"), birth,
					fu.getParameter("zone_code"), fu.getParameter("road_address"), fu.getParameter("detail_address"),
					fu.getParameter("jibun_address"), fu.getFileName("photo"), null,
					null, null
				);
		
	}
}
