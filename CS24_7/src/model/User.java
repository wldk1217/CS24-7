package model;

import java.sql.SQLException;
import java.util.Calendar;

import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class User {
	private String id; // ���̵�
	private String name; // �̸�
	private String phone; // ��ȭ��ȣ
	private String pwd; // ��й�ȣ
	private String email; // �̸���
	private String birth; // �������
	private String gender; // ����
	private String licenseNo; // ����ڵ�Ϲ�ȣ
	private int num;

	public User() {
		super();
	} // �⺻ ������

	public User(String id, String name, String phone, String pwd, String email, String birth, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
	} // �Һ��� ������

	public User(String id, String name, String phone, String pwd, String birth, String licenseNo, String email,
			int num) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.birth = birth;
		this.licenseNo = licenseNo;
		this.email = email;
		this.num = num;
	} // ���� ������

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	/* ��й�ȣ �˻� */
	public boolean matchPassword(String pwd) {
		if (pwd == null) {
			return false;
		}
		return this.pwd.equals(pwd);
	}

	public boolean isSameUser(String id) {
		return this.id.equals(id);
	}

	/* ���� ��� */
	public static int calAge(String birth) {
		String[] birthday = birth.split("-");
		int year = Integer.parseInt(birthday[0]);

		Calendar cal = Calendar.getInstance();

		int age = cal.get(Calendar.YEAR) - year + 1;

		return age;
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return user;
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
}
