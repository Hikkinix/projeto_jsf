/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao implements Serializable{

    private static Connection con = null;

	public static Connection getConnection() {
		if (con == null) {
			try {
				String driver = "org.postgresql.Driver";
				String url = "jdbc:postgresql://localhost:5432/IscaeCia";
				String usuario = "postgres";
				String senha = "postgres";
				Class.forName(driver);
				con = DriverManager.getConnection(url, usuario, senha);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
