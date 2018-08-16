package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import beanUtil.ConnectDB;

public class JavBranchRecords {

	public ArrayList getLovRecords(String clientId) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select distinct pack_web.Fun_ftch_site_name(WO_DEST_SITE_ID) BM_SITE_NAME, WO_DEST_SITE_ID BM_SITE_ID from web_orgn_dest_clnt where WO_ORGN_CLNT_ID = ?  group by WO_DEST_SITE_ID order by 1";

			psmt = con.prepareStatement(query);

			psmt.setString(1, clientId);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("siteId", rs.getString("BM_SITE_ID") == null ? ""
						: rs.getString("BM_SITE_ID"));
				values.put(
						"siteName",
						rs.getString("BM_SITE_NAME") == null ? "" : rs
								.getString("BM_SITE_NAME"));

				result.add(values.clone());
				values.clear();
			}

		} catch (Exception e) {
			System.err.println("Error " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				System.err.println("Error2 " + e2);
			}
		}
		return result;
	}
}