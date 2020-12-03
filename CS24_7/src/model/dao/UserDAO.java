package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ���� USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ����
 */
public class UserDAO {
	private JDBCUtil jdbcUtil = null;

	public UserDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * ����� ID�� �ش��ϴ� ����ڸ� ����.
	 */
	public int cremove(String userId) throws SQLException {
		String sql = "DELETE FROM customer WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� delete���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * ����� ID�� �ش��ϴ� �����(����)�� ����.
	 */
	public int mremove(String userId) throws SQLException {
		String sql = "DELETE FROM master WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� delete���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * ������ ����� ������ ����.
	 */
	public int cupdate(User user) throws SQLException {
		String sql = "UPDATE customer "
				+ "SET name=?, phone=?, pwd=?, email=?, birth=TO_DATE(?, 'YYYY-MM-DD'), gender=? " + "WHERE id=?";
		Object[] param = new Object[] { user.getName(), user.getPhone(), user.getPwd(), user.getEmail(),
				user.getBirth(), user.getGender(), user.getId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����
		System.out.println(user.getPhone());
		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * ������ ������ ������ ����.
	 */
	public int mupdate(User user) throws SQLException {
		String sql = "UPDATE master "
				+ "SET name=?, phone=?, pwd=?, email=?, birth=TO_DATE(?, 'YYYY-MM-DD'), licenseno=? " + "WHERE id=?";
		Object[] param = new Object[] { user.getName(), user.getPhone(), user.getPwd(), user.getEmail(),
				user.getBirth(), user.getLicenseNo(), user.getId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * CUSTOMER ���̺� ���ο� �����(�Һ���) ����.
	 */
	public int createCustomer(User user) throws SQLException {
		String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, TO_DATE(? , 'YYYY-MM-DD'))";
		Object[] param = new Object[] { user.getId(), user.getGender(), user.getName(), user.getPhone(), user.getPwd(),
				user.getEmail(), user.getBirth() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * MASTER ���̺� ���ο� �����(����) ����.
	 */
	public int createMaster(User user) throws SQLException {
		String sql = "INSERT INTO master (id, licenseno, name, phone, pwd, email, birth) VALUES (?, ?, ?, ?, ?, ?, TO_DATE(? , 'YYYY-MM-DD'))";
		Object[] param = new Object[] { user.getId(), user.getLicenseNo(), user.getName(), user.getPhone(),
				user.getPwd(), user.getEmail(), user.getBirth() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		try {
			int result = jdbcUtil.executeUpdate(); // insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * �����(�Һ���) ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public User findcustomer(String userId) throws SQLException {
		String sql = "SELECT * " + "FROM customer " + "WHERE ID = ?";

		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			while (rs.next()) {
				User customer = new User( // User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getString("pwd"),
						rs.getString("email"), rs.getString("birth"), rs.getString("gender"));
				return customer;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * �����(����) ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public User findmaster(String userId) throws SQLException {
		String sql = "SELECT * " + "FROM master " + "WHERE id = ?";

		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			while (rs.next()) {
				User master = new User( // User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getString("pwd"),
						rs.getString("birth"), rs.getString("licenseNo"), rs.getString("email"), 0);
				return master;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ���ְ� �����ϴ��� �˻�
	 */
	public boolean existingMuser(String id) throws SQLException {
		String sql = "SELECT count(*) FROM master WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return false;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� customer�� �����ϴ��� �˻�
	 */
	public boolean existingCuser(String id) throws SQLException {
		String sql = "SELECT count(*) FROM customer WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return false;
	}

}
