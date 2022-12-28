package aplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DBException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("Insert into seller " + "(Name,Email,BirthDate,BaseSalary,DepartmentId)"
					+ "values" + " (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "Carlos");
			ps.setString(2, "carlos@gmail.com");
			ps.setDate(3, new java.sql.Date(sdf.parse("12/02/1999").getTime()));
			ps.setDouble(4, 3000);
			ps.setInt(5, 4);
			int linhas = ps.executeUpdate();
			if (linhas > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Id incluido" + id);
				}

			} else {
				System.out.println("Nenhuma linha alterada");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		} catch (ParseException e) {
			throw new DBException(e.getMessage());
		} finally {
			DB.CloseStatement(ps);
			DB.CloseConnection();
		}

	}
}
