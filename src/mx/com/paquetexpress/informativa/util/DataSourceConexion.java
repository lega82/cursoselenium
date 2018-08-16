package mx.com.paquetexpress.informativa.util;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DataSourceConexion {
	public DataSourceConexion() {
	}

	private static DataSourceConexion laConexion = null;

	public static DataSourceConexion getConexion() {
		if (laConexion == null) {
			synchronized (DataSourceConexion.class) {
				if (laConexion == null) {
					laConexion = new DataSourceConexion();
				}
			}
		}
		return laConexion;
	}

	public Connection Conecta(String jndiDataSource) throws NamingException {
		Connection con = null;
		InitialContext initialContext = new InitialContext();
		try {
			DataSource datasource = (DataSource) initialContext
					.lookup(jndiDataSource);
			con = datasource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
