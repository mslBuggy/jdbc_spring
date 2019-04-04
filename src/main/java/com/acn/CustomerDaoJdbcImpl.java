package com.acn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.model.Address;
import com.model.Customer;

public class CustomerDaoJdbcImpl implements CustomerDao {

	// private Connection mycon;
	private DataSource ds;

	public CustomerDaoJdbcImpl() {

	}

	public CustomerDaoJdbcImpl(DataSource myConnector) {
		ds = myConnector;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> custList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Customer cust = null;
		String sql = "select * from customer";

		try (Connection conn = ds.getConnection()) {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cust = new Customer(rs.getLong("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("hobby"), rs.getString("telephone"), rs.getString("gender").charAt(0));
				List<Address> addrList = getAddressByID(cust.getId(), conn);
				for (Address Address : addrList) {
					cust.addAddress(Address);
				}
				custList.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return custList;
	}

	@Override
	public List<Customer> getCustomerByName(String fname, String lname) {
		List<Customer> custList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer cust = null;
		String SQLString = "select * from customer WHERE fname LIKE ? AND lname LIKE ?";
		try (Connection conn = ds.getConnection()) {
			stmt = conn.prepareStatement(SQLString);
			stmt.setString(1, fname);
			stmt.setString(2, lname);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cust = new Customer(rs.getLong("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("hobby"), rs.getString("telephone"), rs.getString("gender").charAt(0));
				List<Address> addrList = getAddressByID(cust.getId(), conn);
				for (Address Address : addrList) {
					cust.addAddress(Address);
				}
				custList.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return custList;
	}

	@Override
	public Customer getCustomerById(Long id) {
		List<Customer> custList = new ArrayList<>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer cust = null;
		String SQLString = "select * from customer WHERE id = ?";
		try (Connection conn = ds.getConnection()) {
			stmt = conn.prepareStatement(SQLString);
			stmt.setLong(1, id);

			rs = stmt.executeQuery();
			while (rs.next()) {
				cust = new Customer(rs.getLong("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("hobby"), rs.getString("telephone"), rs.getString("gender").charAt(0));
				List<Address> addrList = getAddressByID(cust.getId(), conn);
				for (Address Address : addrList) {
					cust.addAddress(Address);
				}
				custList.add(cust);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!custList.isEmpty())
			return custList.get(0);
		return null;
	}

	@Override
	public void addCustomer(Customer cust) {

		PreparedStatement stmt = null;

		String SQLString = "INSERT INTO Customer (fname,lname,telephone) VALUES (?, ?,?)";
		try (Connection conn = ds.getConnection()) {
			stmt = conn.prepareStatement(SQLString, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, cust.getFirstName());
			stmt.setString(2, cust.getLastName());
			stmt.setString(3, cust.getTelephone());

			int numOfRows = stmt.executeUpdate();

			if (numOfRows == 0) {
				throw new Exception("oof");
			}
			ResultSet rSet = stmt.getGeneratedKeys();
			if (rSet.next()) {
				cust.setId(rSet.getLong(1));
			}
			if (cust.getAddressList() != null) {
				for (Address Address : cust.getAddressList()) {
					addAddressByID(Address, conn, cust.getId());
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void changeCustomer(Customer cust) {

		PreparedStatement stmt = null;

		String SQLString = "UPDATE Customer SET fname = ?, lname = ?, hobby = ?, telephone = ?, gender = ? WHERE id = ?";
		Connection conn = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			stmt = conn.prepareStatement(SQLString);
			stmt.setString(1, cust.getFirstName());
			stmt.setString(2, cust.getLastName());
			stmt.setString(3, cust.getHobby());
			stmt.setString(4, cust.getTelephone());
			stmt.setString(5, "" + cust.getGender());
			stmt.setLong(6, cust.getId());
			int numOfRows = stmt.executeUpdate();
			if (numOfRows != 1) {
				throw new IllegalArgumentException();
			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteCustomer(Customer cust) {

		PreparedStatement stmt = null;
		Connection conn = null;

		String SQLString = "DELETE FROM Customer WHERE id = ?";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			stmt = conn.prepareStatement(SQLString);
			stmt.setLong(1, cust.getId());

			int rows = stmt.executeUpdate();
			if (rows != 1) {
				throw new IllegalArgumentException();
			}
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<Address> getAddressByID(Long id, Connection mycon) {
		List<Address> addrList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Address addr = null;

		String sql = "select * from Address WHERE custid = ?";

		try {

			stmt = mycon.prepareStatement(sql);
			stmt.setLong(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				addr = new Address(rs.getLong("id"), rs.getString("zipcode"), rs.getString("city"),
						rs.getString("street"));
				addrList.add(addr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return addrList;

	}

	private void addAddressByID(Address Address, Connection connection, Long id) throws SQLException {
		String sql = "insert into Address (zipcode, city,street,custid) VALUES (?,?,?,?)";
		PreparedStatement statement;

		statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, Address.getZipcode());
		statement.setString(2, Address.getCity());
		statement.setString(3, Address.getStreet());
		statement.setLong(4, id);

		statement.executeUpdate();
		ResultSet rSet = statement.getGeneratedKeys();
		if (rSet.next()) {
			Address.setId(rSet.getLong(1));
		}
	}

}
