package pantallas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import beanUtil.ConnectDB;

import java.util.Calendar;
import java.util.Date;

public class Informativa {

	public static String carga = "C1";
	public static String descarga = "D1";
	public static String formato_hora = "HH24:MI AM";
	public static String formato_fecha = "DD/MM/RRRR";
	public static String status = "A";
	public static String load = "C";
	public static String llegada = "WHU";
	public static String paquete = "PACKETS";
	public static String sobres = "ENVELOPES";
	public static String valija = "VALIJA";
	public static String sobre = "ENVELOPE";
	public static String acuse = "ACUSES DE GUIAS";
	public static String trn = "TRN";
	public static String nrc = "NRC";
	public static String otn = "OTN";
	public static String segm_flag = "Y";
	public static String brnc_flag = "Y";
	public static String truck_type1 = "AUT";
	public static String truck_type2 = "AER";
	public static String vehi_type = "TRAI";
	public static String like1 = "%ACOPIO%";
	public static String docu_t1 = "P";
	public static String docu_t2 = "Q";
	public static String docu_t3 = "G";
	public static String servicio1 = "SHP-G";
	public static String servicio2 = "SHP-E";
	public static String ead_flag = "1";
	public static String ocu_flag = "0";
	public static String g_type1 = "I";
	public static String g_type2 = "H";
	public static String g_type3 = "IA";
	public static String g_type4 = "IN";
	public static String g_type5 = "IC";
	public static String g_type6 = "C";
	public static String g_type7 = "R";
	public static String shiptype  = "R";
	public static String g_eadf1 = "0";
	public static String g_eadf2 = "2";
	public static String g_eadf3 = "3";
	public static String g_eadf4 = "5";
	public static String intervalo = "7";
	public static String number0 = "0";
	public static String number1 = "1";

	public ArrayList getbitacoras(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();

		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select distinct(WT_TRIP_NO),WT_MOVE_ID,WL_TRUCK_NO,WL_TRAL_ID,WL_SEGM_ID,WT_SEGM_NO,nvl(FUN_VALIDA_SALIDA(WT_TRIP_NO, WL_BRNC_ID ,WL_SEGM_ID),'PROCESO') as stus,WL_BRNC_ID "
					+ "from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate) "
					+ "and WL_BRNC_ID = ? "
					+ "and WT_MOVE_ID = ? "
					+ "AND WT_END_DATE is null "
					+ "union all "
					+ "select distinct(WT_TRIP_NO),WT_MOVE_ID,WL_TRUCK_NO,WL_TRAL_ID,WL_SEGM_ID,WT_SEGM_NO,nvl(FUN_VALIDA_LLEGADA(WT_TRIP_NO, WL_BRNC_ID, WL_SEGM_ID),'PROCESO') as stus,WL_BRNC_ID "
					+ "from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate) "
					+ "and WL_BRNC_ID = ? "
					+ "and WT_MOVE_ID = ?  "
					+ "AND WT_END_DATE is null "
					+ "AND WT_TRIP_NO not in (select TS_TRIP_NO "
					+ "from trf_trip_segm_mstr "
					+ "where TS_DEST_BRNC =  ?  "
					+ "and TS_ACTL_ARVL_DATE is not null "
					+ "and TS_ACTL_DEPR_DATE is null) "
					+ "order by WT_MOVE_ID,WT_TRIP_NO";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, carga);
			psmt.setString(3, sucursal);
			psmt.setString(4, descarga);
			psmt.setString(5, sucursal);
			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("viaje", rs.getString("WT_TRIP_NO") == null ? ""
						: rs.getString("WT_TRIP_NO"));
				values.put("process", rs.getString("WT_MOVE_ID") == null ? ""
						: rs.getString("WT_MOVE_ID"));
				values.put("tracto", rs.getString("WL_TRUCK_NO") == null ? ""
						: rs.getString("WL_TRUCK_NO"));
				values.put(
						"caja",
						rs.getString("WL_TRAL_ID") == null ? "" : rs
								.getString("WL_TRAL_ID"));
				values.put("segmento", rs.getString("WL_SEGM_ID") == null ? ""
						: rs.getString("WL_SEGM_ID"));
				values.put("segno", rs.getString("WT_SEGM_NO") == null ? ""
						: rs.getString("WT_SEGM_NO"));
				values.put(
						"estatus",
						rs.getString("STUS") == null ? "" : rs
								.getString("STUS"));
				values.put("sucursal", rs.getString("WL_BRNC_ID") == null ? ""
						: rs.getString("WL_BRNC_ID"));

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

	public ArrayList get_bita_asig(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();

		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select distinct(WT_TRIP_NO),WT_MOVE_ID,WL_TRUCK_NO,WL_TRAL_ID,WL_SEGM_ID,WT_SEGM_NO,nvl(FUN_VALIDA_SALIDA(WT_TRIP_NO, WL_BRNC_ID ,WL_SEGM_ID),'PROCESO') as stus,WL_BRNC_ID,WL_NUM_PANT "
					+ "from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate) "
					+ "and WL_BRNC_ID = ? "
					+ "and WT_MOVE_ID = ? "
					+ "AND WT_END_DATE is null and WL_TRUCK_NO not in (? , ?)"
					+ "union all "
					+ "select distinct(WT_TRIP_NO),WT_MOVE_ID,WL_TRUCK_NO,WL_TRAL_ID,WL_SEGM_ID,WT_SEGM_NO,nvl(FUN_VALIDA_LLEGADA(WT_TRIP_NO, WL_BRNC_ID ,WL_SEGM_ID),'PROCESO') as stus,WL_BRNC_ID,WL_NUM_PANT "
					+ "from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate) "
					+ "and WL_BRNC_ID = ? "
					+ "and WT_MOVE_ID = ?  "
					+ "AND WT_END_DATE is null "
					+ "AND WT_TRIP_NO not in (select TS_TRIP_NO "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) = substr( ? ,1,3) "
					+ "and TS_ACTL_ARVL_DATE is not null "
					+ "and TS_ACTL_DEPR_DATE is null) and WL_TRUCK_NO not in (? , ?) "
					+ "order by WT_MOVE_ID,WT_TRIP_NO";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, carga);
			psmt.setString(3, truck_type1);
			psmt.setString(4, truck_type2);
			psmt.setString(5, sucursal);
			psmt.setString(6, descarga);
			psmt.setString(7, sucursal);
			psmt.setString(8, truck_type1);
			psmt.setString(9, truck_type2);
			
			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("viaje", rs.getString("WT_TRIP_NO") == null ? ""
						: rs.getString("WT_TRIP_NO"));
				values.put("process", rs.getString("WT_MOVE_ID") == null ? ""
						: rs.getString("WT_MOVE_ID"));
				values.put("tracto", rs.getString("WL_TRUCK_NO") == null ? ""
						: rs.getString("WL_TRUCK_NO"));
				values.put(
						"caja",
						rs.getString("WL_TRAL_ID") == null ? "" : rs
								.getString("WL_TRAL_ID"));
				values.put("segmento", rs.getString("WL_SEGM_ID") == null ? ""
						: rs.getString("WL_SEGM_ID"));
				values.put("segno", rs.getString("WT_SEGM_NO") == null ? ""
						: rs.getString("WT_SEGM_NO"));
				values.put(
						"estatus",
						rs.getString("STUS") == null ? "" : rs
								.getString("STUS"));
				values.put("sucursal", rs.getString("WL_BRNC_ID") == null ? ""
						: rs.getString("WL_BRNC_ID"));
				values.put(
						"numpantalla",
						rs.getString("WL_NUM_PANT") == null ? "" : rs
								.getString("WL_NUM_PANT"));

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

	public String getnombre(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String nombre = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select BM_BRNC_NAME_OPT from sys_brnc_mstr where BM_BRNC_ID = ? ";
			
			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);

			rs = psmt.executeQuery();

			if (rs.next()) {
				nombre = rs.getString("BM_BRNC_NAME_OPT");

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
		return nombre;
	}

	public String getnombresite(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String nombre = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select SM_SITE_NAME from SYS_SITE_MSTR where SM_SITE_ID = ? ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);

			rs = psmt.executeQuery();

			if (rs.next()) {
				nombre = rs.getString(1);

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
		return nombre;
	}

	public String get_valida_disp(String bitacora, String tipo,
			String pantalla, String remolque) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String display = "N";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select WL_NUM_PANT from wir_task_mstr where WT_TRIP_NO = ? and WT_MOVE_ID = ?  and WL_NUM_PANT = ? and WL_TRAL_ID = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, bitacora);
			psmt.setString(2, tipo);
			psmt.setString(3, pantalla);
			psmt.setString(4, remolque);
			rs = psmt.executeQuery();
			if (rs.next()) {
				display = rs.getString(1);

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
		return display;
	}

	public String get_off_disp(String sucursal, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String display = "N";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select DISP_STATUS from sys_disp_power where DISP_BRNC = ? and DISP_ID = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);

			rs = psmt.executeQuery();
			if (rs.next()) {
				display = rs.getString(1);

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
		return display;
	}

	public ArrayList getdatosbitacora(String bitacora, String sucursal,
			String segmno) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select TS_TRIP_NO,TS_ROUT_ID,TS_SEGM_ID,TS_TRUCK_NO,TS_TRAL_1,TS_TRAL_2,to_char(TS_SCHD_DEPR_DATE,'HH24:MI') as salida_esp,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR HH24:MI') as salida_real,to_char(TS_SCHD_ARVL_DATE,'HH24:MI') as llegada_esp from trf_trip_segm_mstr where TS_TRIP_NO = ? and TS_STRT_BRNC = ?   and TS_SEGM_SEQN_NO= ?";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, segmno);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("TS_ROUT_ID") == null ? "" : rs
								.getString("TS_ROUT_ID"));
				values.put("segmento", rs.getString("TS_SEGM_ID") == null ? ""
						: rs.getString("TS_SEGM_ID"));
				values.put("tracto", rs.getString("TS_TRUCK_NO") == null ? ""
						: rs.getString("TS_TRUCK_NO"));
				values.put(
						"caja1",
						rs.getString("TS_TRAL_1") == null ? "" : rs
								.getString("TS_TRAL_1"));
				values.put(
						"caja2",
						rs.getString("TS_TRAL_2") == null ? "" : rs
								.getString("TS_TRAL_2"));
				values.put(
						"salida_esp",
						rs.getString("SALIDA_ESP") == null ? "" : rs
								.getString("SALIDA_ESP"));
				values.put(
						"salida_real",
						rs.getString("SALIDA_REAL") == null ? "" : rs
								.getString("SALIDA_REAL"));
				values.put(
						"llegada_esp",
						rs.getString("LLEGADA_ESP") == null ? "" : rs
								.getString("LLEGADA_ESP"));

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

	public ArrayList getdatosbitacora_descarga(String bitacora,
			String sucursal, String segmno) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select TS_TRIP_NO,TS_ROUT_ID,TS_SEGM_ID,TS_TRUCK_NO,TS_TRAL_1,TS_TRAL_2,to_char(TS_SCHD_DEPR_DATE,'DD/MM/RRRR HH24:MI') as salida_esp,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR HH24:MI') as salida_real,to_char(TS_SCHD_ARVL_DATE,'HH:MI') as llegada_esp from trf_trip_segm_mstr where TS_TRIP_NO = ? and TS_DEST_BRNC = ?   and TS_SEGM_SEQN_NO= ?";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, segmno);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("TS_ROUT_ID") == null ? "" : rs
								.getString("TS_ROUT_ID"));
				values.put("segmento", rs.getString("TS_SEGM_ID") == null ? ""
						: rs.getString("TS_SEGM_ID"));
				values.put("tracto", rs.getString("TS_TRUCK_NO") == null ? ""
						: rs.getString("TS_TRUCK_NO"));
				values.put(
						"caja1",
						rs.getString("TS_TRAL_1") == null ? "" : rs
								.getString("TS_TRAL_1"));
				values.put(
						"caja2",
						rs.getString("TS_TRAL_2") == null ? "" : rs
								.getString("TS_TRAL_2"));
				values.put(
						"salida_esp",
						rs.getString("SALIDA_ESP") == null ? "" : rs
								.getString("SALIDA_ESP"));
				values.put(
						"salida_real",
						rs.getString("SALIDA_REAL") == null ? "" : rs
								.getString("SALIDA_REAL"));
				values.put(
						"llegada_esp",
						rs.getString("LLEGADA_ESP") == null ? "" : rs
								.getString("LLEGADA_ESP"));

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

	public ArrayList get_dest_carga(String bitacora, String remolque,
			String segmento, String tipo, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String proceso = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();
		if (tipo.equals("R")) {

			proceso = "D1";
		} else {
			proceso = "C1";
		}

		try {
			con = ConnectDB.getConnection();

			query = "select  distinct(TO_BRNC_ID) from TRF_TRIP_SEGM_SHIP_DEST where TRIP_NO = ? and SEGM_SEQ_NO = ? and TYPE_REG = ? and TS_TRAL_1 = ? union all "
					+ "select distinct(WL_DEST_BRNC) from WIR_LABEL_SCAN where WL_CRTD_DATE >= to_date(sysdate) and WL_TRIP_NO =  ? and WL_TRAIL_ID = ? and WL_DEST_BRNC not in (select TO_BRNC_ID "
					+ "from TRF_TRIP_SEGM_SHIP_DEST where TRIP_NO = ? and SEGM_SEQ_NO = ? and TYPE_REG =  ? and TS_TRAL_1 = ? ) and WL_MOVE_ID = ? and WL_STUS_FLAG= ? and WL_SEGM_ID = ? order by TO_BRNC_ID ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, segmento);
			psmt.setString(3, tipo);
			psmt.setString(4, caja);
			psmt.setString(5, bitacora);
			psmt.setString(6, remolque);
			psmt.setString(7, bitacora);
			psmt.setString(8, segmento);
			psmt.setString(9, tipo);
			psmt.setString(10, caja);
			psmt.setString(11, proceso);
			psmt.setString(12, status);
			psmt.setString(13, segmento);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("destino", rs.getString("TO_BRNC_ID") == null ? ""
						: rs.getString("TO_BRNC_ID"));

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

	public ArrayList get_carga_destinos(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT V1.DEST,SUM(V2.BULTOS) BULTOS,count(distinct(v1.GH_GUIA_NO)) as guias FROM (SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA,SUBSTR(GH_DEST_SITE_ID,1,3) AS DEST FROM BOK_GUIA_HEAD WHERE GH_ISSE_DATE >= TO_DATE(SYSDATE -7 ,?) AND GH_ISSE_DATE <= TO_DATE(SYSDATE ,?)+23.9998/24 AND SUBSTR(GH_CURR_LOCA,1,3) = ? AND GH_ACTV_FLAG= ? AND GH_GUIA_STUS <> ? and SUBSTR(GH_CURR_LOCA,1,3) <>SUBSTR(GH_DEST_SITE_ID,1,3)) V1,(SELECT GL_GUIA_NO,GL_VLUE_1 AS PESO,GL_VLUE_2 * GL_QUNT AS VOLUMEN,GL_QUNT AS BULTOS FROM BOK_GUIA_SRVC_ITEM WHERE GL_SRVC_ID in ( ?, ?, ? , ? , ? )) V2 WHERE V1.GH_GUIA_NO=V2.GL_GUIA_NO GROUP BY V1.DEST order by BULTOS desc";

			psmt = con.prepareStatement(query);

			psmt.setString(1, formato_fecha);
			psmt.setString(2, formato_fecha);
			psmt.setString(3, sucursal);
			psmt.setString(4, status);
			psmt.setString(5, trn);
			psmt.setString(6, paquete);
			psmt.setString(7, sobres);
			psmt.setString(8, sobre);
			psmt.setString(9, valija);
			psmt.setString(10, acuse);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"destino",
						rs.getString("DEST") == null ? "" : rs
								.getString("DEST"));
				values.put(
						"bulto",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put(
						"guia",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));

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

	public String get_guias_completas(String bitacora, String destino,
			String tipo, String segmento, String bitacora2, String destino2,
			String tipo2, String segmento2) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias_c = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select count(*) from (select distinct(WL_FORM_NO),WL_TOTL_PACK from WIR_LABEL_SCAN where WL_CRTD_DATE >= to_date(sysdate-2) and WL_TRIP_NO = ? and substr(WL_DEST_BRNC,1,3) = ? and WL_MOVE_ID = ? and WL_STUS_FLAG= ? and WL_SEGM_ID = ? )t1,(select WL_FORM_NO,count(WL_FORM_NO) as bultos from WIR_LABEL_SCAN where WL_CRTD_DATE >= to_date(sysdate-2) and WL_TRIP_NO = ? and substr(WL_DEST_BRNC,1,3) = ? and WL_MOVE_ID = ? and WL_STUS_FLAG= ? and WL_SEGM_ID = ? group by WL_FORM_NO,WL_TOTL_PACK)t2 where t1.WL_FORM_NO = t2.WL_FORM_NO  and t1.WL_TOTL_PACK = t2.bultos ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, tipo);
			psmt.setString(4, status);
			psmt.setString(5, segmento);
			psmt.setString(6, bitacora2);
			psmt.setString(7, destino2);
			psmt.setString(8, tipo2);
			psmt.setString(9, status);
			psmt.setString(10, segmento2);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias_c = rs.getString(1);

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
		return guias_c;
	}

	public String get_bultos_escaneados(String bitacora, String destino,
			String tipo, String segmento) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String bultos_escan = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select count(*) from WIR_LABEL_SCAN where WL_CRTD_DATE >= to_date(sysdate) and WL_TRIP_NO = ? and substr(WL_DEST_BRNC,1,3) = ? and WL_MOVE_ID = ? and WL_STUS_FLAG= ? and WL_SEGM_ID = ? ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, tipo);
			psmt.setString(4, status);
			psmt.setString(5, segmento);

			rs = psmt.executeQuery();

			if (rs.next()) {
				bultos_escan = rs.getString(1);

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
		return bultos_escan;
	}

	public ArrayList get_carga_destinos_suc(String bitacora, String destino,
			String segmento, String bitacora1, String destino1,
			String segmento1, String bitacora2, String destino2,
			String segmento2, String bitacora3, String destino3,
			String segmento3, String bitacora4, String destino4,
			String segmento4, String sucursal, String sucursal2,
			String destino5, String destino6, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String viaje = "bitacora";

		String remolque = caja;
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT count(distinct(v1.GH_GUIA_NO)) as guias,count(distinct(v1.GH_GUIA_NO)) - FUN_GUIAS_COMPLETAS( ?, ?,'C1', ?, ?) as g_alm,FUN_GUIAS_COMPLETAS(  ?, ?,'C1', ?, ? ) as guias_c, SUM(V2.BULTOS) BULTOS, SUM(V2.BULTOS) - FUN_BULTOS_ESCANEADOS(  ?, ?,'C1', ?, ?) as b_alm ,FUN_BULTOS_ESCANEADOS(   ?, ?,'C1', ?, ? ) as bultos_e, round(FUN_BULTOS_ESCANEADOS(  ?, ?,'C1', ?, ?) * 100/SUM(V2.BULTOS),0) as porc "
					+ "FROM (SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA,GH_DEST_BRNC_ID AS DEST FROM BOK_GUIA_HEAD  WHERE GH_ISSE_DATE >= TO_DATE(SYSDATE -7 , ?) AND GH_ISSE_DATE <= TO_DATE(SYSDATE , ?)+23.9998/24 AND GH_CURR_LOCA in (?,?) AND GH_DEST_BRNC_ID in (?,?)     AND GH_ACTV_FLAG= ? AND GH_GUIA_STUS <> ? and SUBSTR(GH_CURR_LOCA,1,3) <>SUBSTR(GH_DEST_SITE_ID,1,3)) V1, "
					+ "(SELECT GL_GUIA_NO,GL_QUNT AS BULTOS  FROM BOK_GUIA_SRVC_ITEM WHERE GL_SRVC_ID in ( ?, ? , ? , ? , ? )) V2  WHERE V1.GH_GUIA_NO=V2.GL_GUIA_NO  GROUP BY V1.DEST  order by BULTOS desc";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, remolque);
			psmt.setString(5, bitacora1);
			psmt.setString(6, destino1);
			psmt.setString(7, segmento1);
			psmt.setString(8, remolque);
			psmt.setString(9, bitacora2);
			psmt.setString(10, destino2);
			psmt.setString(11, segmento2);
			psmt.setString(12, remolque);
			psmt.setString(13, bitacora3);
			psmt.setString(14, destino3);
			psmt.setString(15, segmento3);
			psmt.setString(16, remolque);
			psmt.setString(17, bitacora4);
			psmt.setString(18, destino4);
			psmt.setString(19, segmento4);
			psmt.setString(20, remolque);
			psmt.setString(21, formato_fecha);
			psmt.setString(22, formato_fecha);
			psmt.setString(23, sucursal);
			psmt.setString(24, sucursal2);
			psmt.setString(25, destino5);
			psmt.setString(26, destino6);
			psmt.setString(27, status);
			psmt.setString(28, trn);
			psmt.setString(29, paquete);
			psmt.setString(30, sobres);
			psmt.setString(31, valija);
			psmt.setString(32, sobre);
			psmt.setString(33, acuse);

			rs = psmt.executeQuery();

			while (rs.next()) {

				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"g_almacen",
						rs.getString("G_ALM") == null ? "" : rs
								.getString("G_ALM"));
				values.put("g_completas", rs.getString("GUIAS_C") == null ? ""
						: rs.getString("GUIAS_C"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put(
						"b_almacen",
						rs.getString("B_ALM") == null ? "" : rs
								.getString("B_ALM"));
				values.put(
						"b_escananeado",
						rs.getString("BULTOS_E") == null ? "" : rs
								.getString("BULTOS_E"));
				values.put(
						"porcen",
						rs.getString("PORC") == null ? "" : rs
								.getString("PORC"));

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

	public ArrayList get_carga_bitacora(String sucursal, String sucursal2,
			String destino) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String viaje = "bitacora";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT  count(distinct(GH_GUIA_NO)) as guias,FUN_GUIAS_COMPLETAS_DEST_V2( ? , ?, ? ) as guiasc, (NVL(SUM(decode(GH_NUMB_PACK,?,?,GH_NUMB_PACK)),?)) AS BULTOS , FUN_PAQ_ESCANEADOS_DEST_V2(? , ?, ? ) BULTOS_ESC "
					+ "FROM BOK_GUIA_HEAD "
					+ "WHERE GH_ISSE_DATE >= sysdate - interval '7' Day "
					+ "AND GH_CURR_LOCA in ( ? , ? ) "
					+ "AND GH_DEST_BRNC_ID = ? "
					+ "AND GH_ACTV_FLAG= ? "
					+ "AND GH_GUIA_TYPE not in (?,?,?,?,?,?,?) "
					+ "AND FUN_GET_STATUS_GUIA(GH_GUIA_NO,GH_DEST_BRNC_ID) = ? "
					+ "AND GH_GUIA_STUS not in (?,?,?) and GH_LOAD_STUS <> ? and GH_FLAG_3 is null and GH_DEST_BRNC_ID <> GH_CURR_LOCA ";
			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, sucursal2);
			psmt.setString(3, destino);
			
			psmt.setString(4, number0);
			psmt.setString(5, number1);
			psmt.setString(6, number0);
			
			
			psmt.setString(7, sucursal);
			psmt.setString(8, sucursal2);
			psmt.setString(9, destino);
			psmt.setString(10, sucursal);
			psmt.setString(11, sucursal2);
			psmt.setString(12, destino);
			psmt.setString(13, status);
			psmt.setString(14, g_type1);
			psmt.setString(15, g_type2);
			psmt.setString(16, g_type3);
			psmt.setString(17, g_type4);
			psmt.setString(18, g_type5);
			psmt.setString(19, g_type6);
			psmt.setString(20, g_type7);
			psmt.setString(21, segm_flag);
			psmt.setString(22, trn);
			psmt.setString(23, nrc);
			psmt.setString(24, otn);
			psmt.setString(25, load);

			rs = psmt.executeQuery();

			while (rs.next()) {

				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"guias_c",
						rs.getString("GUIASC") == null ? "" : rs
								.getString("GUIASC"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put("bultos_esc", rs.getString("BULTOS") == null ? ""
						: rs.getString("BULTOS_ESC"));

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

	public ArrayList get_descarga_destinos_suc(String bitacora, String destino,
			String segmento, String bitacora1, String destino1,
			String segmento1, String bitacora2, String destino2,
			String segmento2, String bitacora3, String destino3,
			String segmento3, String bitacora4, String destino4,
			String segmento4, String bitacora5, String destino5,
			String bitacora6, String segmento6, String destino6, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String remolque = caja;
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT count(distinct(v1.GH_GUIA_NO)) as guias,count(distinct(v1.GH_GUIA_NO)) - FUN_GUIAS_COMPLETAS( ?, ?, 'D1', ?, ?) as g_rem,FUN_GUIAS_COMPLETAS( ?, ?,'D1', ?, ?) as guias_c,SUM(V2.BULTOS) BULTOS,SUM(V2.BULTOS) - FUN_BULTOS_ESCANEADOS( ? , ?,'D1', ?, ?) as b_rem, "
					+ "FUN_BULTOS_ESCANEADOS( ?, ?,'D1', ? , ? ) as bultos_e, to_char(FUN_BULTOS_ESCANEADOS( ?, ?,'D1', ?, ? ) * 100/SUM(V2.BULTOS),'999.9') as porc "
					+ "FROM (SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA,SUBSTR(GH_DEST_BRNC_ID,1,3) AS DEST FROM BOK_GUIA_HEAD WHERE GH_CURR_TRIP = ? AND GH_DEST_BRNC_ID = ? AND GH_LOAD_STUS = ? and GH_CUR_TRP_TRLR_REF = ? AND GH_ACTV_FLAG= ? UNION ALL SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA, "
					+ "SUBSTR(GH_DEST_BRNC_ID,1,3) AS DEST FROM BOK_GUIA_HEAD WHERE GH_GUIA_NO in (SELECT distinct(GS_GUIA_NO) from bok_guia_stus where GS_COM_TRNS_REF_CODE in (select CT_TRNS_NO from com_ctt where CT_PRIM_ENTY = ? and CT_TEXT_17 = ? and CT_TRNS_TYPE in ( ? ))) AND GH_DEST_BRNC_ID = ? AND GH_ACTV_FLAG in (?)  ) V1, "
					+ "(SELECT GL_GUIA_NO,GL_VLUE_1 AS PESO,GL_VLUE_2 * GL_QUNT AS VOLUMEN,GL_QUNT AS BULTOS FROM BOK_GUIA_SRVC_ITEM WHERE GL_SRVC_ID in  ( ? , ? , ? , ? , ?)) V2 WHERE V1.GH_GUIA_NO=V2.GL_GUIA_NO GROUP BY V1.DEST order by BULTOS desc";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, remolque);
			psmt.setString(5, bitacora1);
			psmt.setString(6, destino1);
			psmt.setString(7, segmento1);
			psmt.setString(8, remolque);
			psmt.setString(9, bitacora2);
			psmt.setString(10, destino2);
			psmt.setString(11, segmento2);
			psmt.setString(12, remolque);
			psmt.setString(13, bitacora3);
			psmt.setString(14, destino3);
			psmt.setString(15, segmento3);
			psmt.setString(16, remolque);
			psmt.setString(17, bitacora4);
			psmt.setString(18, destino4);
			psmt.setString(19, segmento4);
			psmt.setString(20, remolque);

			psmt.setString(21, bitacora5);
			psmt.setString(22, destino5);

			psmt.setString(23, load);
			psmt.setString(24, remolque);

			psmt.setString(25, status);
			psmt.setString(26, bitacora6);
			psmt.setString(27, segmento6);
			psmt.setString(28, llegada);
			psmt.setString(29, destino6);

			psmt.setString(30, status);
			psmt.setString(31, paquete);
			psmt.setString(32, sobres);
			psmt.setString(33, valija);
			psmt.setString(34, sobre);
			psmt.setString(35, acuse);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put("g_remolque", rs.getString("G_REM") == null ? ""
						: rs.getString("G_REM"));
				values.put("g_completas", rs.getString("GUIAS_C") == null ? ""
						: rs.getString("GUIAS_C"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put("b_remolque", rs.getString("B_REM") == null ? ""
						: rs.getString("B_REM"));
				values.put(
						"b_escananeado",
						rs.getString("BULTOS_E") == null ? "" : rs
								.getString("BULTOS_E"));
				values.put(
						"porcen",
						rs.getString("PORC") == null ? "" : rs
								.getString("PORC"));

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

	public ArrayList getbitacoras_llegada(String sucursal, String sucursal2) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();
		String query = "";
		String query2 = "";
		try {
			con = ConnectDB.getConnection();

			query = "select TS_TRIP_NO,TRACTO,CAJA,NOMBRE,TS_SEGM_ID,FECHA_PLANEADA,HORA_PLANEADA,FECHA_SALIDA,HORA_SALIDA,FECHA_ESP,HORA_ESP,SEGMNO,TS_DEST_BRNC,GUIAS_EAD, "
					+ "BULTOS_EAD,VOLUMEN_EAD,GUIAS_OCU,BULTOS_OCU,VOLUMEN_OCU,BULTOS_TRB "
					+ "from (select distinct(GH_CURR_TRIP)as viaje "
					+ "from bok_guia_head "
					+ "where GH_DLVY_DATE is null "
					+ "and GH_CURR_LOCA <> GH_CURR_DEST "
					+ "and GH_DEST_BRNC_ID in (select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) like  substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "and GH_ACTV_FLAG = ? "
					+ "and GH_LOAD_STUS = ? "
					+ "and GH_GUIA_TYPE not in ( ?, ?, ? , ?, ?, ?, ? ))v1, "
					+ "(select TS_TRIP_NO,TS_TRUCK_NO as tracto,nvl(TS_TRAL_1,'-') as caja,FUN_NOMBRE_SITE(TS_DEST_BRNC) as nombre,TS_SEGM_ID,to_char(TS_SCHD_DEPR_DATE,'DD/MM/RRRR') as fecha_planeada,to_char(TS_SCHD_DEPR_DATE,'HH24:MI') as hora_planeada,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR') as fecha_salida,to_char(TS_ACTL_DEPR_DATE + (nvl(FUN_HORA_LOCAL(TS_STRT_BRNC)*3600,0)/86400),'HH24:MI') as hora_salida,to_char(TS_SCHD_ARVL_DATE,'DD/MM/RRRR') as fecha_esp,to_char(TS_SCHD_ARVL_DATE,'HH:MI AM') as hora_esp,TS_SEGM_SEQN_NO as segmno,TS_DEST_BRNC, "
					+ "FUN_GUIAS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_1 ) as guias_ead, "
					+ "FUN_BULTOS_BITACORA_SITE(TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_1 ) as bultos_ead, "
					+ "FUN_VOLUMEN_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_1 ) as volumen_ead, "
					+ "FUN_GUIAS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_1  ) as guias_ocu, "
					+ "FUN_BULTOS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_1 ) as bultos_ocu, "
					+ "FUN_VOLUMEN_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_1 ) as volumen_ocu, "
					+ "FUN_TRASB_BULTOS( TS_TRIP_NO ,TS_TRAL_1,TS_SEGM_ID,TS_DEST_BRNC ) as bultos_trb "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) like substr( ? ,1,3) "
					+ "AND TS_TRUCK_NO not in ( ? , ? ) "
					+ "AND TS_SCHD_ARVL_DATE >= to_date(sysdate - 5,'DD/MM/RRRR') "
					+ "and TS_ACTL_ARVL_DATE is null "
					+ "AND TS_ACTL_DEPR_DATE is not null "
					+ "and TS_TRAL_1 is not null AND FUN_GET_LOCA_BITACORA(TS_TRIP_NO,TS_SEGM_SEQN_NO) = ? "
					+ "union all "
					+ "select TS_TRIP_NO,TS_TRUCK_NO as tracto,nvl(TS_TRAL_2,'-') as caja,FUN_NOMBRE_SITE(TS_DEST_BRNC) as nombre,TS_SEGM_ID,to_char(TS_SCHD_DEPR_DATE,'DD/MM/RRRR') as fecha_planeada,to_char(TS_SCHD_DEPR_DATE,'HH24:MI') as hora_planeada,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR') as fecha_salida,to_char(TS_ACTL_DEPR_DATE + (nvl(FUN_HORA_LOCAL(TS_STRT_BRNC)*3600,0)/86400),'HH24:MI') as hora_salida,to_char(TS_SCHD_ARVL_DATE,'DD/MM/RRRR') as fecha_esp,to_char(TS_SCHD_ARVL_DATE,'HH:MI AM') as hora_esp,TS_SEGM_SEQN_NO as segmno,TS_DEST_BRNC, "
					+ "FUN_GUIAS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_2 ) as guias_ead, "
					+ "FUN_BULTOS_BITACORA_SITE(TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_2 ) as bultos_ead, "
					+ "FUN_VOLUMEN_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'1', TS_TRAL_2 ) as volumen_ead, "
					+ "FUN_GUIAS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_2  ) as guias_ocu, "
					+ "FUN_BULTOS_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_2 ) as bultos_ocu, "
					+ "FUN_VOLUMEN_BITACORA_SITE( TS_TRIP_NO ,TS_DEST_BRNC,'0', TS_TRAL_2 ) as volumen_ocu, "
					+ "FUN_TRASB_BULTOS( TS_TRIP_NO ,TS_TRAL_2,TS_SEGM_ID,TS_DEST_BRNC ) as bultos_trb "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) like substr( ? ,1,3) "
					+ "AND TS_TRUCK_NO not in ( ?, ? ) "
					+ "AND TS_SCHD_ARVL_DATE >= to_date(sysdate - 5,'DD/MM/RRRR') "
					+ "and TS_ACTL_ARVL_DATE is null "
					+ "AND TS_ACTL_DEPR_DATE is not null "
					+ "and TS_TRAL_2 is not null AND FUN_GET_LOCA_BITACORA(TS_TRIP_NO,TS_SEGM_SEQN_NO) = ? )v2 "
					+ "where v1.viaje = v2.TS_TRIP_NO ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, brnc_flag);
			psmt.setString(3, status);
			psmt.setString(4, load);
			psmt.setString(5, g_type1);
			psmt.setString(6, g_type2);
			psmt.setString(7, g_type3);
			psmt.setString(8, g_type4);
			psmt.setString(9, g_type5);
			psmt.setString(10, g_type6);
			psmt.setString(11, g_type7);
			psmt.setString(12, sucursal);
			psmt.setString(13, truck_type1);
			psmt.setString(14, truck_type2);
			psmt.setString(15, segm_flag);
			psmt.setString(16, sucursal);
			psmt.setString(17, truck_type1);
			psmt.setString(18, truck_type2);
			psmt.setString(19, segm_flag);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("viaje", rs.getString("TS_TRIP_NO") == null ? ""
						: rs.getString("TS_TRIP_NO"));
				values.put(
						"tracto",
						rs.getString("TRACTO") == null ? "" : rs
								.getString("TRACTO"));
				values.put(
						"caja",
						rs.getString("CAJA") == null ? "" : rs
								.getString("CAJA"));
				values.put(
						"nombre",
						rs.getString("NOMBRE") == null ? "" : rs
								.getString("NOMBRE"));
				values.put("segmento", rs.getString("TS_SEGM_ID") == null ? ""
						: rs.getString("TS_SEGM_ID"));
				values.put(
						"f_planeada",
						rs.getString("FECHA_PLANEADA") == null ? "" : rs
								.getString("FECHA_PLANEADA"));
				values.put(
						"h_planeada",
						rs.getString("HORA_PLANEADA") == null ? "" : rs
								.getString("HORA_PLANEADA"));
				values.put(
						"f_salida",
						rs.getString("FECHA_SALIDA") == null ? "" : rs
								.getString("FECHA_SALIDA"));
				values.put("h_salida", rs.getString("HORA_SALIDA") == null ? ""
						: rs.getString("HORA_SALIDA"));
				values.put(
						"f_esp",
						rs.getString("FECHA_ESP") == null ? "" : rs
								.getString("FECHA_ESP"));
				values.put(
						"h_esp",
						rs.getString("HORA_ESP") == null ? "" : rs
								.getString("HORA_ESP"));
				values.put(
						"segmno",
						rs.getString("SEGMNO") == null ? "" : rs
								.getString("SEGMNO"));
				values.put(
						"suc_dest",
						rs.getString("TS_DEST_BRNC") == null ? "" : rs
								.getString("TS_DEST_BRNC"));
				values.put("guias_ead", rs.getString("GUIAS_EAD") == null ? ""
						: rs.getString("GUIAS_EAD"));
				values.put(
						"bultos_ead",
						rs.getString("BULTOS_EAD") == null ? "" : rs
								.getString("BULTOS_EAD"));
				values.put(
						"volumen_ead",
						rs.getString("VOLUMEN_EAD") == null ? "" : rs
								.getString("VOLUMEN_EAD"));
				values.put("guias_ocu", rs.getString("GUIAS_OCU") == null ? ""
						: rs.getString("GUIAS_OCU"));
				values.put(
						"bultos_ocu",
						rs.getString("BULTOS_OCU") == null ? "" : rs
								.getString("BULTOS_OCU"));
				values.put(
						"volumen_ocu",
						rs.getString("VOLUMEN_OCU") == null ? "" : rs
								.getString("VOLUMEN_OCU"));
				values.put(
						"bultos_trb",
						rs.getString("BULTOS_TRB") == null ? "" : rs
								.getString("BULTOS_TRB"));

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

	public ArrayList getbitacoras_llegada_trn(String sucursal, String sucursal2) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();
		String query = "";
		String query2 = "";
		try {
			con = ConnectDB.getConnection();

			query = "select TS_TRIP_NO,TRACTO,CAJA,NOMBRE,TS_SEGM_ID,FECHA_PLANEADA,HORA_PLANEADA,FECHA_SALIDA,HORA_SALIDA,FECHA_ESP,HORA_ESP,SEGMNO, "
					+ "TS_DEST_BRNC,GUIAS,GUIAS_T,paq,paq_t "
					+ "from (select distinct(GH_CURR_TRIP)as viaje "
					+ "from bok_guia_head "
					+ "where GH_DLVY_DATE is null "
					+ "and GH_CURR_LOCA <> GH_CURR_DEST "
					+ "and GH_DEST_BRNC_ID in (select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) like  substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "and GH_ACTV_FLAG = ? "
					+ "and GH_LOAD_STUS = ? "
					+ "and GH_GUIA_TYPE not in ( ?, ?, ? , ?, ?, ?, ? ))v1, "
					+ "(select TS_TRIP_NO,TS_TRUCK_NO as tracto,nvl(TS_TRAL_1,'-') as caja,FUN_NOMBRE_SITE(TS_DEST_BRNC) as nombre,TS_SEGM_ID,to_char(TS_SCHD_DEPR_DATE,'DD/MM/RRRR') as fecha_planeada,to_char(TS_SCHD_DEPR_DATE,'HH:MI AM') as hora_planeada,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR') as fecha_salida,to_char(TS_ACTL_DEPR_DATE + (nvl(FUN_HORA_LOCAL(TS_STRT_BRNC)*3600,0)/86400),'HH24:MI') as hora_salida,to_char(TS_SCHD_ARVL_DATE,'DD/MM/RRRR') as fecha_esp,to_char(TS_SCHD_ARVL_DATE,'HH:MI AM') as hora_esp,TS_SEGM_SEQN_NO as segmno,TS_DEST_BRNC, "
					+ "FUN_TOTAL_GUIAS_BITACORA_S(TS_TRIP_NO,TS_TRAL_1,TS_DEST_BRNC) as guias, "
					+ "FUN_TRASB_GUIAS( TS_TRIP_NO ,TS_TRAL_1,TS_SEGM_ID,TS_DEST_BRNC ) guias_t, "
					+ "FUN_TOTAL_BULTOS_BITACORA_S(TS_TRIP_NO,TS_TRAL_1,TS_DEST_BRNC) as paq, "
					+ "FUN_TRASB_BULTOS( TS_TRIP_NO ,TS_TRAL_1,TS_SEGM_ID,TS_DEST_BRNC )  as paq_t "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) like substr( ? ,1,3) "
					+ "AND TS_TRUCK_NO not in ( ? , ? ) "
					+ "AND TS_SCHD_ARVL_DATE >= to_date(sysdate - 5,'DD/MM/RRRR') "
					+ "and TS_ACTL_ARVL_DATE is null "
					+ "and TS_CURR_SEGM_FLAG = ? "
					+ "and TS_TRAL_1 is not null AND FUN_GET_LOCA_BITACORA(TS_TRIP_NO,TS_SEGM_SEQN_NO) = ? "
					+ "union all "
					+ "select TS_TRIP_NO,TS_TRUCK_NO as tracto,nvl(TS_TRAL_2,'-') as caja,FUN_NOMBRE_SITE(TS_DEST_BRNC) as nombre,TS_SEGM_ID,to_char(TS_SCHD_DEPR_DATE,'DD/MM/RRRR') as fecha_planeada,to_char(TS_SCHD_DEPR_DATE,'HH:MI AM') as hora_planeada,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR') as fecha_salida,to_char(TS_ACTL_DEPR_DATE + (nvl(FUN_HORA_LOCAL(TS_STRT_BRNC)*3600,0)/86400),'HH24:MI') as hora_salida,to_char(TS_SCHD_ARVL_DATE,'DD/MM/RRRR') as fecha_esp,to_char(TS_SCHD_ARVL_DATE,'HH:MI AM') as hora_esp,TS_SEGM_SEQN_NO as segmno,TS_DEST_BRNC, "
					+ "FUN_TOTAL_GUIAS_BITACORA_S(TS_TRIP_NO,TS_TRAL_2,TS_DEST_BRNC) as guias, "
					+ "FUN_TRASB_GUIAS( TS_TRIP_NO ,TS_TRAL_2,TS_SEGM_ID,TS_DEST_BRNC )  as guias_t, "
					+ "FUN_TOTAL_BULTOS_BITACORA_S(TS_TRIP_NO,TS_TRAL_2,TS_DEST_BRNC) as paq, "
					+ "FUN_TRASB_BULTOS( TS_TRIP_NO ,TS_TRAL_2,TS_SEGM_ID,TS_DEST_BRNC )  as paq_t "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) like substr( ? ,1,3) "
					+ "AND TS_TRUCK_NO not in ( ?, ? ) "
					+ "AND TS_SCHD_ARVL_DATE >= to_date(sysdate - 5,'DD/MM/RRRR') "
					+ "and TS_ACTL_ARVL_DATE is null "
					+ "and TS_CURR_SEGM_FLAG = ? "
					+ "and TS_TRAL_2 is not null AND FUN_GET_LOCA_BITACORA(TS_TRIP_NO,TS_SEGM_SEQN_NO) = ? )v2 "
					+ "where v1.viaje = v2.TS_TRIP_NO order by 1";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, brnc_flag);
			psmt.setString(3, status);
			psmt.setString(4, load);
			psmt.setString(5, g_type1);
			psmt.setString(6, g_type2);
			psmt.setString(7, g_type3);
			psmt.setString(8, g_type4);
			psmt.setString(9, g_type5);
			psmt.setString(10, g_type6);
			psmt.setString(11, g_type7);
			psmt.setString(12, sucursal);
			psmt.setString(13, truck_type1);
			psmt.setString(14, truck_type2);
			psmt.setString(15, segm_flag);
			psmt.setString(16, segm_flag);
			
			psmt.setString(17, sucursal);
			psmt.setString(18, truck_type1);
			psmt.setString(19, truck_type2);
			psmt.setString(20, segm_flag);
			psmt.setString(21, segm_flag);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("viaje", rs.getString("TS_TRIP_NO") == null ? ""
						: rs.getString("TS_TRIP_NO"));
				values.put(
						"tracto",
						rs.getString("TRACTO") == null ? "" : rs
								.getString("TRACTO"));
				values.put(
						"caja",
						rs.getString("CAJA") == null ? "" : rs
								.getString("CAJA"));
				values.put(
						"nombre",
						rs.getString("NOMBRE") == null ? "" : rs
								.getString("NOMBRE"));
				values.put("segmento", rs.getString("TS_SEGM_ID") == null ? ""
						: rs.getString("TS_SEGM_ID"));
				values.put(
						"f_planeada",
						rs.getString("FECHA_PLANEADA") == null ? "" : rs
								.getString("FECHA_PLANEADA"));
				values.put(
						"h_planeada",
						rs.getString("HORA_PLANEADA") == null ? "" : rs
								.getString("HORA_PLANEADA"));
				values.put(
						"f_salida",
						rs.getString("FECHA_SALIDA") == null ? "" : rs
								.getString("FECHA_SALIDA"));
				values.put("h_salida", rs.getString("HORA_SALIDA") == null ? ""
						: rs.getString("HORA_SALIDA"));
				values.put(
						"f_esp",
						rs.getString("FECHA_ESP") == null ? "" : rs
								.getString("FECHA_ESP"));
				values.put(
						"h_esp",
						rs.getString("HORA_ESP") == null ? "" : rs
								.getString("HORA_ESP"));
				values.put(
						"segmno",
						rs.getString("SEGMNO") == null ? "" : rs
								.getString("SEGMNO"));
				values.put(
						"suc_dest",
						rs.getString("TS_DEST_BRNC") == null ? "" : rs
								.getString("TS_DEST_BRNC"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"guias_t",
						rs.getString("GUIAS_T") == null ? "" : rs
								.getString("GUIAS_T"));
				values.put("paquetes",
						rs.getString("PAQ") == null ? "" : rs.getString("PAQ"));
				values.put("paquetes_t", rs.getString("PAQ_T") == null ? ""
						: rs.getString("PAQ_T"));

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

	
	public ArrayList getdetalle_trn(String bitacora, String segmento,String remolque,  String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();
		String query = "";
		String query2 = "";
		try {
			con = ConnectDB.getConnection();

			query = "select v1.guia,v1.GH_DEST_BRNC_ID,v1.gh_guia_no,v1.GH_CUR_TRP_TRLR_REF,v2.GL_QUNT,v2.SS_DESC,v2.GL_SLAB_NO,v2.GL_QUNT * v2.GL_VLUE_2 as vol "
                    +" from (select GH_ORGN_BRNC_ID||'-'||GH_FORM_NO as guia,GH_DEST_BRNC_ID,GH_DOCU_TYPE,GH_GUIA_NO,GH_CUR_TRP_TRLR_REF "
                    +"from bok_guia_head where GH_CURR_TRIP = ? and GH_LOAD_STUS = ?  "
                    +"AND GH_DOCU_TYPE in ( ? , ? , ? ) "
                    +"AND GH_CUR_TRP_TRLR_REF =  ? "
                    +"and GH_ACTV_FLAG = ? "
                    +"and substr(GH_DEST_BRNC_ID,1,3) = substr( ? ,1,3))v1, "
                    +"(select t1.GL_GUIA_NO,t1.GL_QUNT,t2.SS_DESC,t1.GL_SLAB_NO,t1.GL_VLUE_2  "
                    +"from (select GL_GUIA_NO,GL_QUNT,GL_DESC,GL_SLAB_NO,GL_VLUE_2  "
                    +"from bok_guia_srvc_item where GL_REFR_SRVC_ID = ? )t1, "
                    +"(SELECT SYS_SHP_DESC_MSTR.SS_CODE,SYS_SHP_DESC_MSTR.SS_DESC  "
                    +"FROM SYS_SHP_DESC_MSTR)t2  "
                    +"where t1.GL_DESC = t2.SS_CODE )v2 "
                    +"where v1.GH_GUIA_NO = v2.GL_GUIA_NO "
                    +"union all "
                    +"select v1.guia,v1.GH_DEST_BRNC_ID,v1.gh_guia_no,v1.GH_CUR_TRP_TRLR_REF,v2.GL_QUNT,v2.SS_DESC,v2.GL_SLAB_NO,v2.GL_QUNT * v2.GL_VLUE_2 as vol "
                    +"from (select GH_ORGN_BRNC_ID||'-'||GH_FORM_NO as guia,GH_DEST_BRNC_ID,GH_DOCU_TYPE,GH_GUIA_NO,GH_CUR_TRP_TRLR_REF "
                    +"from bok_guia_head where GH_CURR_TRIP = ? and GH_LOAD_STUS = ? "
                    +"AND GH_DOCU_TYPE in ( ?, ? , ? ) "
                    +"AND GH_CUR_TRP_TRLR_REF =  ? "
                    +"and GH_ACTV_FLAG = ? "
                    +"and substr(GH_DEST_BRNC_ID,1,3) in (  "
                    +"select substr(TO_BRNC_ID,1,3) "  
                    +"from TRF_TRIP_SEGM_SHIP_DEST "
                    +"where TRIP_NO  = ? "
                    +"and SEGM_SEQ_NO = ?  "
                    +"and TYPE_REG = ? "
                    +"and substr(TO_BRNC_ID,1,3) <> substr( ? ,1,3)))v1, "
                    +"(select t1.GL_GUIA_NO,t1.GL_QUNT,t2.SS_DESC,t1.GL_SLAB_NO,t1.GL_VLUE_2  "
                    +"from (select GL_GUIA_NO,GL_QUNT,GL_DESC,GL_SLAB_NO,GL_VLUE_2 "
                    +"from bok_guia_srvc_item where GL_REFR_SRVC_ID = ? )t1, "
                    +"(SELECT SYS_SHP_DESC_MSTR.SS_CODE,SYS_SHP_DESC_MSTR.SS_DESC "
                    +"FROM SYS_SHP_DESC_MSTR)t2  "
                    +"where t1.GL_DESC = t2.SS_CODE )v2 "
                    +"where v1.GH_GUIA_NO = v2.GL_GUIA_NO ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, load);
			psmt.setString(3, docu_t1);
			psmt.setString(4, docu_t2);
			psmt.setString(5, docu_t3);
			psmt.setString(6, remolque);
			psmt.setString(7,status);
			psmt.setString(8, sucursal);
			psmt.setString(9, servicio1);
			psmt.setString(10, bitacora);
			psmt.setString(11, load);
			psmt.setString(12, docu_t1);
			psmt.setString(13, docu_t2);
			psmt.setString(14, docu_t3);
			psmt.setString(15, remolque);
			psmt.setString(16,status);
			psmt.setString(17, bitacora);
			psmt.setString(18, segmento);
			psmt.setString(19,shiptype);
			psmt.setString(20, sucursal);
			psmt.setString(21, servicio1);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("guia", rs.getString("GUIA") == null ? ""
						: rs.getString("GUIA"));
				values.put(
						"destino",
						rs.getString("GH_DEST_BRNC_ID") == null ? "" : rs
								.getString("GH_DEST_BRNC_ID"));
				values.put(
						"rastreo",
						rs.getString("GH_GUIA_NO") == null ? "" : rs
								.getString("GH_GUIA_NO"));
				values.put(
						"remolque",
						rs.getString("GH_CUR_TRP_TRLR_REF") == null ? "" : rs
								.getString("GH_CUR_TRP_TRLR_REF"));
				values.put("paquete", rs.getString("GL_QUNT") == null ? ""
						: rs.getString("GL_QUNT"));
				values.put(
						"contenido",
						rs.getString("SS_DESC") == null ? "" : rs
								.getString("SS_DESC"));
				values.put(
						"tarifa",
						rs.getString("GL_SLAB_NO") == null ? "" : rs
								.getString("GL_SLAB_NO"));
				values.put(
						"volumen",
						rs.getString("VOL") == null ? "" : rs
								.getString("VOL"));
				
				
				
				
				
				

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
		
	public String get_capacidad(String remolque) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String capacidad = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select nvl(to_char(round(VM_VHCL_VLUM)),0) from SYS_VHCL_MSTR where VM_VHCL_TYPE = ? and VM_VHCL_ID = ?  ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, vehi_type);
			psmt.setString(2, remolque);

			rs = psmt.executeQuery();

			if (rs.next()) {
				capacidad = rs.getString(1);

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
		return capacidad;
	}

	public String get_hora(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String hora = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_HORA_LOCAL( ? ) from dual  ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);

			rs = psmt.executeQuery();

			if (rs.next()) {
				hora = rs.getString(1);

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
		return hora;
	}

	public String get_volumen(String tipo, String bitacora, String segmento,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String volumen = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GET_VOLUMEN( ?, ?, ?,? ) from dual  ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, tipo);
			psmt.setString(2, bitacora);
			psmt.setString(3, segmento);
			psmt.setString(4, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				volumen = rs.getString(1);

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
		return volumen;
	}

	public String get_volumen_disp(String capacidad, String volumen) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String volumen_disponible = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GET_DISPONIBLE( ?, ?) from dual";

			psmt = con.prepareStatement(query);

			psmt.setString(1, capacidad);
			psmt.setString(2, volumen);

			rs = psmt.executeQuery();

			if (rs.next()) {
				volumen_disponible = rs.getString(1);

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
		return volumen_disponible;
	}

	public String get_cronometro(String bitacora, String sucursal, String segn) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String cronometro = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GET_CRONOMETRO( ?, ?, ?) from dual   ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, segn);

			rs = psmt.executeQuery();

			if (rs.next()) {
				cronometro = rs.getString(1);

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
		return cronometro;
	}

	public String get_cronometroD(String bitacora, String sucursal, String segn) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String cronometro = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GET_CRONOMETRO_D( ?, ?, ?) from dual   ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, segn);

			rs = psmt.executeQuery();

			if (rs.next()) {
				cronometro = rs.getString(1);

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
		return cronometro;
	}

	public String get_finDescarga(String sucursal, String pantalla,
			String bitacora, String remolque, String segmn) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String valor = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_FIN_DESCARGA( ?, ? , ?, ?, ? ) as fin from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);
			psmt.setString(3, bitacora);
			psmt.setString(4, remolque);
			psmt.setString(5, segmn);

			rs = psmt.executeQuery();

			if (rs.next()) {
				valor = rs.getString(1);

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
		return valor;
	}

	public String get_finCarga(String sucursal, String pantalla,
			String bitacora, String remolque, String segmento) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String valor = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_FIN_CARGA( ?, ?, ?, ?, ?) as fin from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);
			psmt.setString(3, bitacora);
			psmt.setString(4, remolque);
			psmt.setString(5, segmento);

			rs = psmt.executeQuery();

			if (rs.next()) {
				valor = rs.getString(1);

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
		return valor;
	}

	public ArrayList getpantalla(String sucursal, String numpantalla) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		HashMap values = new HashMap();
		ArrayList result = new ArrayList();
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select distinct(WT_TRIP_NO),WT_MOVE_ID,WL_TRUCK_NO,WL_TRAL_ID,WL_SEGM_ID,WT_SEGM_NO,nvl(FUN_VALIDA_SALIDA(WT_TRIP_NO, WL_BRNC_ID,WL_SEGM_ID),'PROCESO') as stus from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate)  and WL_BRNC_ID = ? and WL_NUM_PANT = ? and WT_MOVE_ID in ( ?, ?) AND WT_END_DATE is null order by WT_MOVE_ID";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, numpantalla);
			psmt.setString(3, carga);
			psmt.setString(4, descarga);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("viaje", rs.getString("WT_TRIP_NO") == null ? ""
						: rs.getString("WT_TRIP_NO"));
				values.put("process", rs.getString("WT_MOVE_ID") == null ? ""
						: rs.getString("WT_MOVE_ID"));
				values.put("tracto", rs.getString("WL_TRUCK_NO") == null ? ""
						: rs.getString("WL_TRUCK_NO"));
				values.put(
						"caja",
						rs.getString("WL_TRAL_ID") == null ? "" : rs
								.getString("WL_TRAL_ID"));
				values.put("segmento", rs.getString("WL_SEGM_ID") == null ? ""
						: rs.getString("WL_SEGM_ID"));
				values.put("segno", rs.getString("WT_SEGM_NO") == null ? ""
						: rs.getString("WT_SEGM_NO"));
				values.put(
						"estatus",
						rs.getString("STUS") == null ? "" : rs
								.getString("STUS"));

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

	public String get_suc_trasbordo(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String trasbordo = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select BM_BRNC_ID from sys_brnc_mstr where BM_DC_OPR_BRNC = ? ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);

			rs = psmt.executeQuery();

			if (rs.next()) {
				trasbordo = rs.getString(1);
			} else {
				trasbordo = "PQE";
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
		return trasbordo;
	}

	public String get_count_trasb(String bitacora, String caja,
			String segmento, String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		String viaje = bitacora;
		String tipo = "R";
		String load = "C";

		try {
			con = ConnectDB.getConnection();

			query = "SELECT  to_char(count(*)) " + "FROM BOK_GUIA_HEAD "
					+ "WHERE GH_CURR_TRIP =  ? "
					+ "and GH_CUR_TRP_TRLR_REF = ? "
					+ "and substr(GH_DEST_BRNC_ID,1,3) in ( "
					+ "select substr(TO_BRNC_ID,1,3) "
					+ "from TRF_TRIP_SEGM_SHIP_DEST " + "where TRIP_NO  = ? "
					+ "and SEGM_SEQ_NO = ? " + "and TYPE_REG = ? "
					+ "and substr(TO_BRNC_ID,1,3) <> substr( ? ,1,3)) "
					+ "AND GH_LOAD_STUS = ? "
					+ "and GH_DOCU_TYPE in (  ? ,  ? ,  ? )   ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, caja);
			psmt.setString(3, viaje);
			psmt.setString(4, segmento);
			psmt.setString(5, tipo);
			psmt.setString(6, sucursal);
			psmt.setString(7, load);
			psmt.setString(8, docu_t1);
			psmt.setString(9, docu_t2);
			psmt.setString(10, docu_t3);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_suc_acopio(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String acopio = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select nvl(BM_BRNC_ID,'N') from sys_brnc_mstr where BM_DC_FOR_BRNC = ? and BM_BRNC_NAME like ?   ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, like1);

			rs = psmt.executeQuery();

			if (rs.next()) {
				acopio = rs.getString(1);
			} else {
				acopio = "PQE";
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
		return acopio;
	}

	public ArrayList get_sucursales() {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query2 = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query2 = "select BM_BRNC_ID,BM_BRNC_NAME from sys_brnc_mstr where  BM_WB_DEST = 'Y' order by BM_BRNC_ID";

			query = "select distinct(WL_BRNC_ID),FUN_NOMBRE_SITE(WL_BRNC_ID)as sucursal from WIR_TASK_MSTR  where WT_CRTD_DATE >= to_date(sysdate) and WT_MOVE_ID in ( ?, ?) AND WT_END_DATE is null order by sucursal";

			psmt = con.prepareStatement(query);
			psmt.setString(1, carga);
			psmt.setString(2, descarga);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("sucursal", rs.getString("WL_BRNC_ID") == null ? ""
						: rs.getString("WL_BRNC_ID"));
				values.put(
						"nombre",
						rs.getString("SUCURSAL") == null ? "" : rs
								.getString("SUCURSAL"));

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

	public ArrayList get_sucursales_all() {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query2 = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query2 = "select BM_BRNC_ID,BM_BRNC_NAME from sys_brnc_mstr where  BM_WB_DEST = ? order by BM_BRNC_ID";
			query = "select SM_SITE_ID,SM_SITE_NAME from sys_site_mstr order by SM_SITE_ID";

			psmt = con.prepareStatement(query);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("sucursal", rs.getString("SM_SITE_ID") == null ? ""
						: rs.getString("SM_SITE_ID"));
				values.put("nombre", rs.getString("SM_SITE_NAME") == null ? ""
						: rs.getString("SM_SITE_NAME"));

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

	public ArrayList getdatosbitacora_op(String bitacora, String segmno) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select TS_TRIP_NO,TS_ROUT_ID,TS_SEGM_ID,TS_TRUCK_NO,TS_TRAL_1,TS_TRAL_2,to_char(TS_SCHD_DEPR_DATE,'HH24:MI AM') as salida_esp,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR')||'  '||to_char(TS_ACTL_DEPR_DATE + (nvl(FUN_HORA_LOCAL(TS_STRT_BRNC)*3600,0)/86400),'HH24:MI') as salida_real,to_char(TS_SCHD_ARVL_DATE,'HH24:MI AM') as llegada_esp,FUN_NOMBRE_OPERADOR(TS_POSN_1_OPER_ID) as oper1,FUN_NOMBRE_OPERADOR(TS_POSN_2_OPER_ID) as oper2,FUN_NOMBRE_OPERADOR(TS_POSN_3_OPER_ID) as oper3  from trf_trip_segm_mstr where TS_TRIP_NO = ? and TS_SEGM_SEQN_NO= ?";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, segmno);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("TS_ROUT_ID") == null ? "" : rs
								.getString("TS_ROUT_ID"));
				values.put("segmento", rs.getString("TS_SEGM_ID") == null ? ""
						: rs.getString("TS_SEGM_ID"));
				values.put("tracto", rs.getString("TS_TRUCK_NO") == null ? ""
						: rs.getString("TS_TRUCK_NO"));
				values.put(
						"caja1",
						rs.getString("TS_TRAL_1") == null ? "" : rs
								.getString("TS_TRAL_1"));
				values.put(
						"caja2",
						rs.getString("TS_TRAL_2") == null ? "" : rs
								.getString("TS_TRAL_2"));
				values.put(
						"salida_esp",
						rs.getString("SALIDA_ESP") == null ? "" : rs
								.getString("SALIDA_ESP"));
				values.put(
						"salida_real",
						rs.getString("SALIDA_REAL") == null ? "" : rs
								.getString("SALIDA_REAL"));
				values.put(
						"llegada_esp",
						rs.getString("LLEGADA_ESP") == null ? "" : rs
								.getString("LLEGADA_ESP"));
				values.put(
						"oper1",
						rs.getString("OPER1") == null ? "" : rs
								.getString("OPER1"));
				values.put(
						"oper2",
						rs.getString("OPER2") == null ? "" : rs
								.getString("OPER2"));
				values.put(
						"oper3",
						rs.getString("OPER3") == null ? "" : rs
								.getString("OPER3"));

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

	public ArrayList getdetalle_destino(String bitacora, String caja,
			String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String viaje = bitacora;
		String suc = sucursal;
		String caja1 = caja;

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query1 = "select TS_TRIP_NO,TS_ROUT_ID,TS_SEGM_ID,TS_TRUCK_NO,TS_TRAL_1,TS_TRAL_2,to_char(TS_SCHD_DEPR_DATE,'HH24:MI AM') as salida_esp,to_char(TS_ACTL_DEPR_DATE,'DD/MM/RRRR HH24:MI AM') as salida_real,to_char(TS_SCHD_ARVL_DATE,'HH24:MI AM') as llegada_esp,FUN_NOMBRE_OPERADOR(TS_POSN_1_OPER_ID) as oper1,FUN_NOMBRE_OPERADOR(TS_POSN_2_OPER_ID) as oper2,FUN_NOMBRE_OPERADOR(TS_POSN_3_OPER_ID) as oper3  from trf_trip_segm_mstr where TS_TRIP_NO = ? and TS_SEGM_SEQN_NO= ?";

			query = "SELECT GH_DEST_BRNC_ID as suc,FUN_NOMBRE_SITE(GH_DEST_BRNC_ID) as nombre,count(gh_guia_no) as guias,sum(GH_NUMB_PACK) as butos,to_char(sum(GH_TOTL_VLUM),'999.99') as volumen, "
					+ "FUN_GUIAS_BITACORA( ? ,GH_DEST_BRNC_ID,'1', ? ) as guias_ead, "
					+ "FUN_BULTOS_BITACORA( ? ,GH_DEST_BRNC_ID,'1', ? ) as bultos_ead, "
					+ "FUN_VOLUMEN_BITACORA( ? ,GH_DEST_BRNC_ID,'1', ? ) as volumen_ead, "
					+ "FUN_GUIAS_BITACORA( ? ,GH_DEST_BRNC_ID,'0', ?  ) as guias_ocu, "
					+ "FUN_BULTOS_BITACORA( ? ,GH_DEST_BRNC_ID,'0', ? ) as bultos_ocu,"
					+ "FUN_VOLUMEN_BITACORA( ? ,GH_DEST_BRNC_ID,'0', ? ) as volumen_ocu "
					+ "FROM BOK_GUIA_HEAD "
					+ "WHERE GH_CURR_TRIP =  ? "
					+ "and GH_CUR_TRP_TRLR_REF =  ? "
					+ "and substr(GH_DEST_BRNC_ID,1,3)  = substr(?,1,3) "
					+ "AND GH_LOAD_STUS = 'C' "
					+ "and GH_DOCU_TYPE in ( ?,  ?, ? ) "
					+ "group by GH_DEST_BRNC_ID ";

			psmt = con.prepareStatement(query);
			psmt.setString(1, viaje);
			psmt.setString(2, caja1);
			psmt.setString(3, viaje);
			psmt.setString(4, caja1);
			psmt.setString(5, viaje);
			psmt.setString(6, caja1);
			psmt.setString(7, viaje);
			psmt.setString(8, caja1);
			psmt.setString(9, viaje);
			psmt.setString(10, caja1);
			psmt.setString(11, viaje);
			psmt.setString(12, caja1);
			psmt.setString(13, viaje);
			psmt.setString(14, caja);
			psmt.setString(15, sucursal);
			psmt.setString(16, docu_t1);
			psmt.setString(17, docu_t2);
			psmt.setString(18, docu_t3);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("suc",
						rs.getString("SUC") == null ? "" : rs.getString("SUC"));
				values.put(
						"nombre",
						rs.getString("NOMBRE") == null ? "" : rs
								.getString("NOMBRE"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BUTOS") == null ? "" : rs
								.getString("BUTOS"));
				values.put(
						"volumen",
						rs.getString("VOLUMEN") == null ? "" : rs
								.getString("VOLUMEN"));
				values.put(
						"g_ead",
						rs.getString("GUIAS_EAD") == null ? "" : rs
								.getString("GUIAS_EAD"));
				values.put("b_ead", rs.getString("BULTOS_EAD") == null ? ""
						: rs.getString("BULTOS_EAD"));
				values.put("v_ead", rs.getString("VOLUMEN_EAD") == null ? ""
						: rs.getString("VOLUMEN_EAD"));
				values.put(
						"g_ocu",
						rs.getString("GUIAS_OCU") == null ? "" : rs
								.getString("GUIAS_OCU"));
				values.put("b_ocu", rs.getString("BULTOS_OCU") == null ? ""
						: rs.getString("BULTOS_OCU"));
				values.put("v_ocu", rs.getString("VOLUMEN_OCU") == null ? ""
						: rs.getString("VOLUMEN_OCU"));

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

	public ArrayList getdetalle_trasbordo(String bitacora, String caja,
			String segmento, String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String viaje = bitacora;
		String suc = sucursal;
		String tipo = "R";
		String load = "C";
		String caja1 = caja;

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT GH_DEST_BRNC_ID as suc,FUN_NOMBRE_SITE(GH_DEST_BRNC_ID) as nombre,count(gh_guia_no) as guias,sum(GH_NUMB_PACK) as butos,to_char(sum(GH_TOTL_VLUM),'999.99') as volumen, "
					+ "FUN_GUIAS_BITACORA( ? ,GH_DEST_BRNC_ID,'1', ?) as guias_ead, "
					+ "FUN_BULTOS_BITACORA(  ? ,GH_DEST_BRNC_ID,'1', ?) as bultos_ead, "
					+ "FUN_VOLUMEN_BITACORA( ? ,GH_DEST_BRNC_ID,'1', ?) as volumen_ead, "
					+ "FUN_GUIAS_BITACORA(  ? ,GH_DEST_BRNC_ID,'0', ?) as guias_ocu, "
					+ "FUN_BULTOS_BITACORA(  ?  ,GH_DEST_BRNC_ID,'0', ?) as bultos_ocu, "
					+ "FUN_VOLUMEN_BITACORA( ? ,GH_DEST_BRNC_ID,'0', ?) as volumen_ocu "
					+ "FROM BOK_GUIA_HEAD "
					+ "WHERE GH_CURR_TRIP =  ?  "
					+ "and GH_CUR_TRP_TRLR_REF = ? "
					+ "and substr(GH_DEST_BRNC_ID,1,3) in (  "
					+ "select substr(TO_BRNC_ID,1,3)  "
					+ "from TRF_TRIP_SEGM_SHIP_DEST "
					+ "where TRIP_NO  = ? "
					+ "and SEGM_SEQ_NO = ?  "
					+ "and TYPE_REG = ? "
					+ "and substr(TO_BRNC_ID,1,3) <> substr( ? ,1,3))  "
					+ "AND GH_LOAD_STUS = ? "
					+ "and GH_DOCU_TYPE in (  ? ,  ? ,  ? )  "
					+ "group by GH_DEST_BRNC_ID  "
					+ "order by GH_DEST_BRNC_ID ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, caja1);

			psmt.setString(3, viaje);
			psmt.setString(4, caja1);

			psmt.setString(5, viaje);
			psmt.setString(6, caja1);

			psmt.setString(7, viaje);
			psmt.setString(8, caja1);

			psmt.setString(9, viaje);
			psmt.setString(10, caja1);

			psmt.setString(11, viaje);
			psmt.setString(12, caja1);

			psmt.setString(13, viaje);
			psmt.setString(14, caja1);

			psmt.setString(15, viaje);
			psmt.setString(16, segmento);
			psmt.setString(17, tipo);
			psmt.setString(18, sucursal);
			psmt.setString(19, load);
			psmt.setString(20, docu_t1);
			psmt.setString(21, docu_t2);
			psmt.setString(22, docu_t3);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put("suc",
						rs.getString("SUC") == null ? "" : rs.getString("SUC"));
				values.put(
						"nombre",
						rs.getString("NOMBRE") == null ? "" : rs
								.getString("NOMBRE"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BUTOS") == null ? "" : rs
								.getString("BUTOS"));
				values.put(
						"volumen",
						rs.getString("VOLUMEN") == null ? "" : rs
								.getString("VOLUMEN"));
				values.put(
						"g_ead",
						rs.getString("GUIAS_EAD") == null ? "" : rs
								.getString("GUIAS_EAD"));
				values.put("b_ead", rs.getString("BULTOS_EAD") == null ? ""
						: rs.getString("BULTOS_EAD"));
				values.put("v_ead", rs.getString("VOLUMEN_EAD") == null ? ""
						: rs.getString("VOLUMEN_EAD"));
				values.put(
						"g_ocu",
						rs.getString("GUIAS_OCU") == null ? "" : rs
								.getString("GUIAS_OCU"));
				values.put("b_ocu", rs.getString("BULTOS_OCU") == null ? ""
						: rs.getString("BULTOS_OCU"));
				values.put("v_ocu", rs.getString("VOLUMEN_OCU") == null ? ""
						: rs.getString("VOLUMEN_OCU"));

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

	public ArrayList getdetalle_rutas(String bitacora, String sucursal,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String viaje = bitacora;
		String suc = sucursal;
		String tipo = "R";

		String caja1 = caja;

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select v1.RUTA,v1.GH_ORGN_BRNC_ID,v1.GH_CURR_TRIP,v1.GH_GUIA_NO,v1.GUIA,v1.GH_DEST_CLNT_NAME,v3.GL_QUNT,FUN_DESC_TIPO(v3.GL_DESC) as descripcion,v3.GL_SLAB_NO,v3.GL_CONT,v2.GA_STRT_NAME as calle,nvl(v2.GA_DRNR,'000') as numero,nvl(v2.GA_ADDR_LIN6,'SN') as colonia,nvl(v2.GA_ZIP_CODE,'00000') as codigo,nvl(v2.GA_ADDR_LIN5,'SN') as delegacion "
					+ "from "
					+ "(select  GH_ORGN_BRNC_ID,GH_CURR_TRIP,GH_DEST_CLNT_ID,GH_DEST_CLNT_NAME,GH_GUIA_NO,GH_PREP_BRNC_ID||'-'||GH_FORM_NO as guia,GH_NUMB_PACK,nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta "
					+ "from bok_guia_head "
					+ "where GH_CURR_TRIP = ?  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_LOAD_STUS = ?  "
					+ "AND GH_CUR_TRP_TRLR_REF =  ? "
					+ "AND GH_ACTV_FLAG = ?  "
					+ "AND GH_DOCU_TYPE in ( ? , ? , ? ) "
					+ "AND GH_EAD_FLAG = ? "
					+ " )v1, "
					+ "(select GA_GUIA_NO,GA_STRT_NAME,GA_DRNR,GA_ADDR_LIN6,GA_ADDR_LIN5,GA_ZIP_CODE,GA_ADDR_LIN4 "
					+ "from BOK_GUIA_ADDR "
					+ "where GA_ADDR_TYPE='DESTINATION')v2, "
					+ "(select GL_GUIA_NO,GL_DESC,GL_SLAB_NO,GL_CONT,GL_QUNT "
					+ "from bok_guia_srvc_item "
					+ "where GL_REFR_SRVC_ID in ( ? , ? ))v3 "
					+ "where v1.GH_GUIA_NO = v2.GA_GUIA_NO "
					+ "and v1.GH_GUIA_NO = v3.GL_GUIA_NO "
					+ "order by v1.ruta,V1.GH_DEST_CLNT_ID,v1.GH_DEST_CLNT_NAME,GH_GUIA_NO ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, brnc_flag);
			psmt.setString(4, load);
			psmt.setString(5, caja);
			psmt.setString(6, status);
			psmt.setString(7, docu_t1);
			psmt.setString(8, docu_t2);
			psmt.setString(9, docu_t3);
			psmt.setString(10, ead_flag);
			psmt.setString(11, servicio1);
			psmt.setString(12, servicio2);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put("rastreo", rs.getString("GH_GUIA_NO") == null ? ""
						: rs.getString("GH_GUIA_NO"));
				values.put(
						"guia",
						rs.getString("GUIA") == null ? "" : rs
								.getString("GUIA"));
				values.put(
						"cliente",
						rs.getString("GH_DEST_CLNT_NAME") == null ? "" : rs
								.getString("GH_DEST_CLNT_NAME"));
				values.put(
						"bultos",
						rs.getString("GL_QUNT") == null ? "" : rs
								.getString("GL_QUNT"));
				values.put(
						"descripcion",
						rs.getString("DESCRIPCION") == null ? "" : rs
								.getString("DESCRIPCION"));
				values.put("tarifa", rs.getString("GL_SLAB_NO") == null ? ""
						: rs.getString("GL_SLAB_NO"));
				values.put("contenido", rs.getString("GL_CONT") == null ? ""
						: rs.getString("GL_CONT"));
				values.put(
						"calle",
						rs.getString("CALLE") == null ? "" : rs
								.getString("CALLE"));
				values.put(
						"numero",
						rs.getString("NUMERO") == null ? "" : rs
								.getString("NUMERO"));
				values.put(
						"colonia",
						rs.getString("COLONIA") == null ? "" : rs
								.getString("COLONIA"));
				values.put(
						"codigo",
						rs.getString("CODIGO") == null ? "" : rs
								.getString("CODIGO"));
				values.put(
						"delegacion",
						rs.getString("DELEGACION") == null ? "" : rs
								.getString("DELEGACION"));

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

	public ArrayList getdetalle_ocurre(String bitacora, String sucursal,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String viaje = bitacora;
		String suc = sucursal;
		String tipo = "R";

		String caja1 = caja;

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select v1.RUTA,v1.GH_ORGN_BRNC_ID,v1.GH_CURR_TRIP,v1.GH_GUIA_NO,v1.GUIA,v1.GH_DEST_CLNT_NAME,v3.GL_QUNT,FUN_DESC_TIPO(v3.GL_DESC) as descripcion,v3.GL_SLAB_NO,v3.GL_CONT,v2.GA_STRT_NAME as calle,nvl(v2.GA_DRNR,'000') as numero,nvl(v2.GA_ADDR_LIN6,'SN') as colonia,nvl(v2.GA_ZIP_CODE,'00000') as codigo,nvl(v2.GA_ADDR_LIN5,'SN') as delegacion "
					+ "from "
					+ "(select  GH_ORGN_BRNC_ID,GH_CURR_TRIP,GH_DEST_CLNT_ID,GH_DEST_CLNT_NAME,GH_GUIA_NO,GH_PREP_BRNC_ID||'-'||GH_FORM_NO as guia,GH_NUMB_PACK,nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta "
					+ "from bok_guia_head "
					+ "where GH_CURR_TRIP = ?  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_LOAD_STUS = ?  "
					+ "AND GH_CUR_TRP_TRLR_REF =  ? "
					+ "AND GH_ACTV_FLAG = ?  "
					+ "AND GH_DOCU_TYPE in ( ? , ? , ? ) "
					+ "AND GH_EAD_FLAG = ? "
					+ " )v1, "
					+ "(select GA_GUIA_NO,GA_STRT_NAME,GA_DRNR,GA_ADDR_LIN6,GA_ADDR_LIN5,GA_ZIP_CODE,GA_ADDR_LIN4 "
					+ "from BOK_GUIA_ADDR "
					+ "where GA_ADDR_TYPE='DESTINATION')v2, "
					+ "(select GL_GUIA_NO,GL_DESC,GL_SLAB_NO,GL_CONT,GL_QUNT "
					+ "from bok_guia_srvc_item "
					+ "where GL_REFR_SRVC_ID in ( ? , ? ))v3 "
					+ "where v1.GH_GUIA_NO = v2.GA_GUIA_NO "
					+ "and v1.GH_GUIA_NO = v3.GL_GUIA_NO "
					+ "order by v1.ruta,V1.GH_DEST_CLNT_ID,v1.GH_DEST_CLNT_NAME,GH_GUIA_NO ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, brnc_flag);
			psmt.setString(4, load);
			psmt.setString(5, caja);
			psmt.setString(6, status);
			psmt.setString(7, docu_t1);
			psmt.setString(8, docu_t2);
			psmt.setString(9, docu_t3);
			psmt.setString(10, ocu_flag);
			psmt.setString(11, servicio1);
			psmt.setString(12, servicio2);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put("rastreo", rs.getString("GH_GUIA_NO") == null ? ""
						: rs.getString("GH_GUIA_NO"));
				values.put(
						"guia",
						rs.getString("GUIA") == null ? "" : rs
								.getString("GUIA"));
				values.put(
						"cliente",
						rs.getString("GH_DEST_CLNT_NAME") == null ? "" : rs
								.getString("GH_DEST_CLNT_NAME"));
				values.put(
						"bultos",
						rs.getString("GL_QUNT") == null ? "" : rs
								.getString("GL_QUNT"));
				values.put(
						"descripcion",
						rs.getString("DESCRIPCION") == null ? "" : rs
								.getString("DESCRIPCION"));
				values.put("tarifa", rs.getString("GL_SLAB_NO") == null ? ""
						: rs.getString("GL_SLAB_NO"));
				values.put("contenido", rs.getString("GL_CONT") == null ? ""
						: rs.getString("GL_CONT"));
				values.put(
						"calle",
						rs.getString("CALLE") == null ? "" : rs
								.getString("CALLE"));
				values.put(
						"numero",
						rs.getString("NUMERO") == null ? "" : rs
								.getString("NUMERO"));
				values.put(
						"colonia",
						rs.getString("COLONIA") == null ? "" : rs
								.getString("COLONIA"));
				values.put(
						"codigo",
						rs.getString("CODIGO") == null ? "" : rs
								.getString("CODIGO"));
				values.put(
						"delegacion",
						rs.getString("DELEGACION") == null ? "" : rs
								.getString("DELEGACION"));

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

	public ArrayList getdetalle_ead(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String suc = sucursal;
		String tipo = "R";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select v1.RUTA,v1.GH_ORGN_BRNC_ID,v1.GH_CURR_TRIP,v1.GH_GUIA_NO,v1.GUIA,v1.GH_DEST_CLNT_NAME,v3.GL_QUNT,FUN_DESC_TIPO(v3.GL_DESC) as descripcion,v3.GL_SLAB_NO,v3.GL_CONT,v2.GA_STRT_NAME as calle,nvl(v2.GA_DRNR,'000') as numero,nvl(v2.GA_ADDR_LIN6,'SN') as colonia,nvl(v2.GA_ZIP_CODE,'00000') as codigo,nvl(v2.GA_ADDR_LIN5,'SN') as delegacion,fun_get_bitacora(v1.GH_GUIA_NO) as bitacora "
					+ "from "
					+ "(select  GH_ORGN_BRNC_ID,GH_CURR_TRIP,GH_DEST_CLNT_ID,GH_DEST_CLNT_NAME,GH_GUIA_NO,GH_PREP_BRNC_ID||'-'||GH_FORM_NO as guia,GH_NUMB_PACK,nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta "
					+ "from bok_guia_head "
					+ "where GH_DLVY_DATE is null "
					+ "and GH_DEST_BRNC_ID = GH_CURR_LOCA "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_ACTV_FLAG = ? "
					+ "and GH_GUIA_TYPE not in ( ?, ?, ? , ?, ?,? ,? ) "
					+ "AND GH_EAD_FLAG not in ( ?, ?, ?, ?) and GH_FLAG_3 is null"
					+ " )v1, "
					+ "(select GA_GUIA_NO,GA_STRT_NAME,GA_DRNR,GA_ADDR_LIN6,GA_ADDR_LIN5,GA_ZIP_CODE,GA_ADDR_LIN4 "
					+ "from BOK_GUIA_ADDR "
					+ "where GA_ADDR_TYPE='DESTINATION')v2, "
					+ "(select GL_GUIA_NO,GL_DESC,GL_SLAB_NO,GL_CONT,GL_QUNT "
					+ "from bok_guia_srvc_item "
					+ "where GL_REFR_SRVC_ID in ( ? , ? ))v3 "
					+ "where v1.GH_GUIA_NO = v2.GA_GUIA_NO "
					+ "and v1.GH_GUIA_NO = v3.GL_GUIA_NO "
					+ "order by v1.ruta,V1.GH_DEST_CLNT_ID,v1.GH_DEST_CLNT_NAME,GH_GUIA_NO ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, brnc_flag);
			psmt.setString(3, status);
			psmt.setString(4, g_type1);
			psmt.setString(5, g_type2);
			psmt.setString(6, g_type3);
			psmt.setString(7, g_type4);
			psmt.setString(8, g_type5);
			psmt.setString(9, g_type6);
			psmt.setString(10, g_type7);
			psmt.setString(11, g_eadf1);
			psmt.setString(12, g_eadf2);
			psmt.setString(13, g_eadf3);
			psmt.setString(14, g_eadf4);
			psmt.setString(15, servicio1);
			psmt.setString(16, servicio2);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put("rastreo", rs.getString("GH_GUIA_NO") == null ? ""
						: rs.getString("GH_GUIA_NO"));
				values.put(
						"guia",
						rs.getString("GUIA") == null ? "" : rs
								.getString("GUIA"));
				values.put(
						"cliente",
						rs.getString("GH_DEST_CLNT_NAME") == null ? "" : rs
								.getString("GH_DEST_CLNT_NAME"));
				values.put(
						"bultos",
						rs.getString("GL_QUNT") == null ? "" : rs
								.getString("GL_QUNT"));
				values.put(
						"descripcion",
						rs.getString("DESCRIPCION") == null ? "" : rs
								.getString("DESCRIPCION"));
				values.put("tarifa", rs.getString("GL_SLAB_NO") == null ? ""
						: rs.getString("GL_SLAB_NO"));
				values.put("contenido", rs.getString("GL_CONT") == null ? ""
						: rs.getString("GL_CONT"));
				values.put(
						"calle",
						rs.getString("CALLE") == null ? "" : rs
								.getString("CALLE"));
				values.put(
						"numero",
						rs.getString("NUMERO") == null ? "" : rs
								.getString("NUMERO"));
				values.put(
						"colonia",
						rs.getString("COLONIA") == null ? "" : rs
								.getString("COLONIA"));
				values.put(
						"codigo",
						rs.getString("CODIGO") == null ? "" : rs
								.getString("CODIGO"));
				values.put(
						"delegacion",
						rs.getString("DELEGACION") == null ? "" : rs
								.getString("DELEGACION"));
				values.put("bitacora", rs.getString("BITACORA") == null ? ""
						: rs.getString("BITACORA"));

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

	public ArrayList getdetalle_ead_concentrado(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String suc = sucursal;
		String tipo = "R";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select v1.RUTA,'SUCURSAL' as bitacora,'EN PISO' as Remolque,v1.GUIA,v1.GH_DEST_CLNT_NAME,v3.GL_QUNT,FUN_DESC_TIPO(v3.GL_DESC) as descripcion,v3.GL_SLAB_NO,v3.GL_CONT,v2.GA_STRT_NAME as calle,nvl(v2.GA_DRNR,'000') as numero,nvl(v2.GA_ADDR_LIN6,'SN') as colonia,nvl(v2.GA_ZIP_CODE,'00000') as codigo,nvl(v2.GA_ADDR_LIN5,'SN') as delegacion "
					+ "from "
					+ "(select  GH_ORGN_BRNC_ID,GH_CURR_TRIP,GH_DEST_CLNT_ID,GH_DEST_CLNT_NAME,GH_GUIA_NO,GH_PREP_BRNC_ID||'-'||GH_FORM_NO as guia,GH_NUMB_PACK,nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta "
					+ "from bok_guia_head "
					+ "where GH_DEST_BRNC_ID = GH_CURR_LOCA  "
					+ "and GH_DLVY_DATE is null "
					+ "AND GH_ACTV_FLAG = ?  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "and GH_GUIA_TYPE not in ( ?, ? , ?, ?, ?, ? , ? )  "
					+ "AND GH_EAD_FLAG not in ( ? , ? , ? , ? ) "
					+ " )v1, "
					+ "(select GA_GUIA_NO,GA_STRT_NAME,GA_DRNR,GA_ADDR_LIN6,GA_ADDR_LIN5,GA_ZIP_CODE,GA_ADDR_LIN4 "
					+ "from BOK_GUIA_ADDR "
					+ "where GA_ADDR_TYPE='DESTINATION')v2, "
					+ "(select GL_GUIA_NO,GL_DESC,GL_SLAB_NO,GL_CONT,GL_QUNT "
					+ "from bok_guia_srvc_item "
					+ "where GL_REFR_SRVC_ID in ( ? , ? ))v3 "
					+ "where v1.GH_GUIA_NO = v2.GA_GUIA_NO "
					+ "and v1.GH_GUIA_NO = v3.GL_GUIA_NO "
					+ "union all "
					+ "select v1.RUTA,v1.GH_CURR_TRIP,v1.caja,v1.GUIA,v1.GH_DEST_CLNT_NAME,v3.GL_QUNT,FUN_DESC_TIPO(v3.GL_DESC) as descripcion,v3.GL_SLAB_NO,v3.GL_CONT,v2.GA_STRT_NAME as calle,nvl(v2.GA_DRNR,'000') as numero,nvl(v2.GA_ADDR_LIN6,'SN') as colonia,nvl(v2.GA_ZIP_CODE,'00000') as codigo,nvl(v2.GA_ADDR_LIN5,'SN') as delegacion "
					+ "from "
					+ "(select  GH_ORGN_BRNC_ID,GH_CURR_TRIP,GH_DEST_CLNT_ID,GH_DEST_CLNT_NAME,GH_GUIA_NO,GH_PREP_BRNC_ID||'-'||GH_FORM_NO as guia,GH_NUMB_PACK,nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta ,GH_CUR_TRP_TRLR_REF caja "
					+ "from bok_guia_head "
					+ "where GH_CURR_TRIP in (select distinct(TS_TRIP_NO)  "
					+ "from trf_trip_segm_mstr "
					+ "where substr(TS_DEST_BRNC,1,3) like substr( ?,1,3) "
					+ "AND TS_TRUCK_NO not in ( ? , ? ) "
					+ "AND TS_SCHD_ARVL_DATE >= to_date(sysdate,'DD/MM/RRRR') "
					+ "and TS_ACTL_ARVL_DATE is null "
					+ "AND TS_CURR_SEGM_FLAG = ? "
					+ "AND TS_ACTL_DEPR_DATE is not null )  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_LOAD_STUS = ? "
					+ "AND GH_ACTV_FLAG = ? "
					+ "AND GH_DOCU_TYPE in ( ? , ? , ? ) "
					+ "AND GH_EAD_FLAG = ? "
					+ ")v1, "
					+ "(select GA_GUIA_NO,GA_STRT_NAME,GA_DRNR,GA_ADDR_LIN6,GA_ADDR_LIN5,GA_ZIP_CODE,GA_ADDR_LIN4 "
					+ "from BOK_GUIA_ADDR "
					+ "where GA_ADDR_TYPE='DESTINATION')v2, "
					+ "(select GL_GUIA_NO,GL_DESC,GL_SLAB_NO,GL_CONT,GL_QUNT "
					+ "from bok_guia_srvc_item "
					+ "where GL_REFR_SRVC_ID in ( ?, ? ))v3 "
					+ "where v1.GH_GUIA_NO = v2.GA_GUIA_NO "
					+ "and v1.GH_GUIA_NO = v3.GL_GUIA_NO "
					+ "order by RUTA,GUIA,GH_DEST_CLNT_NAME ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, status);
			psmt.setString(2, sucursal);
			psmt.setString(3, brnc_flag);
			psmt.setString(4, g_type1);
			psmt.setString(5, g_type2);
			psmt.setString(6, g_type3);
			psmt.setString(7, g_type4);
			psmt.setString(8, g_type5);
			psmt.setString(9, g_type6);
			psmt.setString(10, g_type7);
			psmt.setString(11, g_eadf1);
			psmt.setString(12, g_eadf2);
			psmt.setString(13, g_eadf3);
			psmt.setString(14, g_eadf4);
			psmt.setString(15, servicio1);
			psmt.setString(16, servicio2);
			psmt.setString(17, sucursal);
			psmt.setString(18, truck_type1);
			psmt.setString(19, truck_type2);
			psmt.setString(20, segm_flag);
			psmt.setString(21, sucursal);
			psmt.setString(22, brnc_flag);
			psmt.setString(23, load);
			psmt.setString(24, status);
			psmt.setString(25, docu_t1);
			psmt.setString(26, docu_t2);
			psmt.setString(27, docu_t3);
			psmt.setString(28, ead_flag);
			psmt.setString(29, servicio1);
			psmt.setString(30, servicio2);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put(
						"viaje",
						rs.getString("BITACORA") == null ? "" : rs
								.getString("BITACORA"));
				values.put(
						"caja",
						rs.getString("REMOLQUE") == null ? "" : rs
								.getString("REMOLQUE"));
				values.put(
						"guia",
						rs.getString("GUIA") == null ? "" : rs
								.getString("GUIA"));
				values.put(
						"cliente",
						rs.getString("GH_DEST_CLNT_NAME") == null ? "" : rs
								.getString("GH_DEST_CLNT_NAME"));
				values.put(
						"bultos",
						rs.getString("GL_QUNT") == null ? "" : rs
								.getString("GL_QUNT"));
				values.put(
						"descripcion",
						rs.getString("DESCRIPCION") == null ? "" : rs
								.getString("DESCRIPCION"));
				values.put("tarifa", rs.getString("GL_SLAB_NO") == null ? ""
						: rs.getString("GL_SLAB_NO"));
				values.put("contenido", rs.getString("GL_CONT") == null ? ""
						: rs.getString("GL_CONT"));
				values.put(
						"calle",
						rs.getString("CALLE") == null ? "" : rs
								.getString("CALLE"));
				values.put(
						"numero",
						rs.getString("NUMERO") == null ? "" : rs
								.getString("NUMERO"));
				values.put(
						"colonia",
						rs.getString("COLONIA") == null ? "" : rs
								.getString("COLONIA"));
				values.put(
						"codigo",
						rs.getString("CODIGO") == null ? "" : rs
								.getString("CODIGO"));
				values.put(
						"delegacion",
						rs.getString("DELEGACION") == null ? "" : rs
								.getString("DELEGACION"));

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

	public ArrayList get_totales_ead(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String suc = sucursal;
		String tipo = "R";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta,count(*) as guias,to_char(sum(GH_NUMB_PACK),'999,999') as bultos,to_char(sum(FUN_PESO_GUIA(gh_guia_no)),'999,999.99') as peso,to_char(sum(FUN_VOL_GUIA(gh_guia_no)),'999,999.99') as volumen "
					+ "from bok_guia_head "
					+ "where GH_DLVY_DATE is null  "
					+ "and GH_DEST_BRNC_ID = GH_CURR_LOCA "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_ACTV_FLAG =  ?  "
					+ "and GH_GUIA_TYPE not in (  ?, ? , ?, ?, ? , ?, ? )  "
					+ "AND GH_EAD_FLAG not in ( ?, ? , ? , ?) "
					+ "group by GH_EAD_DEFA_ROUT";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, brnc_flag);
			psmt.setString(3, status);
			psmt.setString(4, g_type1);
			psmt.setString(5, g_type2);
			psmt.setString(6, g_type3);
			psmt.setString(7, g_type4);
			psmt.setString(8, g_type5);
			psmt.setString(9, g_type6);
			psmt.setString(10, g_type7);
			psmt.setString(11, g_eadf1);
			psmt.setString(12, g_eadf2);
			psmt.setString(13, g_eadf3);
			psmt.setString(14, g_eadf4);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put(
						"peso",
						rs.getString("PESO") == null ? "" : rs
								.getString("PESO"));
				values.put(
						"volumen",
						rs.getString("VOLUMEN") == null ? "" : rs
								.getString("VOLUMEN"));

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

	public ArrayList get_totales_bitacora(String bitacora, String sucursal,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String suc = sucursal;
		String tipo = "R";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta,count(*) as guias,to_char(sum(GH_NUMB_PACK),'999,999') as bultos,to_char(sum(FUN_PESO_GUIA(gh_guia_no)),'999,999.99') as peso,to_char(sum(FUN_VOL_GUIA(gh_guia_no)),'999,999.99') as volumen "
					+ "from bok_guia_head "
					+ "where GH_CURR_TRIP = ?  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_LOAD_STUS = ? "
					+ "AND GH_CUR_TRP_TRLR_REF =  ?  "
					+ "AND GH_ACTV_FLAG = ? "
					+ "AND GH_DOCU_TYPE in ( ? , ?  , ?  ) "
					+ "AND GH_EAD_FLAG = ? " + "group by GH_EAD_DEFA_ROUT";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, brnc_flag);
			psmt.setString(4, load);
			psmt.setString(5, caja);
			psmt.setString(6, status);
			psmt.setString(7, docu_t1);
			psmt.setString(8, docu_t2);
			psmt.setString(9, docu_t3);
			psmt.setString(10, ead_flag);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put(
						"peso",
						rs.getString("PESO") == null ? "" : rs
								.getString("PESO"));
				values.put(
						"volumen",
						rs.getString("VOLUMEN") == null ? "" : rs
								.getString("VOLUMEN"));

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

	public ArrayList get_totales_ocurre(String bitacora, String sucursal,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String query1 = "";
		String suc = sucursal;
		String tipo = "R";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select nvl(GH_EAD_DEFA_ROUT,'SIN RUTA') as ruta,count(*) as guias,to_char(sum(GH_NUMB_PACK),'999,999') as bultos,to_char(sum(FUN_PESO_GUIA(gh_guia_no)),'999,999.99') as peso,to_char(sum(FUN_VOL_GUIA(gh_guia_no)),'999,999.99') as volumen "
					+ "from bok_guia_head "
					+ "where GH_CURR_TRIP = ?  "
					+ "AND GH_DEST_BRNC_ID in ( "
					+ "select BM_BRNC_ID "
					+ "from sys_brnc_mstr "
					+ "where substr(BM_BRNC_ID,1,3) = substr( ? ,1,3) "
					+ "and BM_WB_DEST = ? ) "
					+ "AND GH_LOAD_STUS = ? "
					+ "AND GH_CUR_TRP_TRLR_REF =  ?  "
					+ "AND GH_ACTV_FLAG = ? "
					+ "AND GH_DOCU_TYPE in ( ? , ?  , ?  ) "
					+ "AND GH_EAD_FLAG = ? " + "group by GH_EAD_DEFA_ROUT";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, brnc_flag);
			psmt.setString(4, load);
			psmt.setString(5, caja);
			psmt.setString(6, status);
			psmt.setString(7, docu_t1);
			psmt.setString(8, docu_t2);
			psmt.setString(9, docu_t3);
			psmt.setString(10, ocu_flag);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"ruta",
						rs.getString("RUTA") == null ? "" : rs
								.getString("RUTA"));
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));
				values.put(
						"peso",
						rs.getString("PESO") == null ? "" : rs
								.getString("PESO"));
				values.put(
						"volumen",
						rs.getString("VOLUMEN") == null ? "" : rs
								.getString("VOLUMEN"));

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

	public void pro_ins_log(String user, String sucursal, String app,
			String app_op, String app_ip) {

		Connection con = null;
		PreparedStatement psmt = null;

		String query = "";
		try {
			con = ConnectDB.getConnection();

			psmt = con.prepareCall(("{call PRO_INS_LOG (?,?,?,?,?)}"));

			psmt.setString(1, user);
			psmt.setString(2, sucursal);
			psmt.setString(3, app);
			psmt.setString(4, app_op);
			psmt.setString(5, app_ip);

			psmt.executeUpdate();

			psmt.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public ArrayList get_descarga_bitacora_V31(String bitacora, String destino,
			String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String remolque = caja;
		String dest = destino;
		String bitacora1 = bitacora;
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT count(distinct(GH_GUIA_NO)) as guias ,NVL(SUM(decode(GH_NUMB_PACK,0,1,GH_NUMB_PACK)),0) bultos "
					+ "FROM BOK_GUIA_HEAD WHERE GH_CURR_TRIP = ? "
					+ "AND GH_DEST_BRNC_ID = ?  "
					+ "AND GH_LOAD_STUS = ? and GH_CUR_TRP_TRLR_REF = ?  "
					+ "AND GH_ACTV_FLAG= ? ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora1);
			psmt.setString(2, dest);
			psmt.setString(3, load);
			psmt.setString(4, remolque);
			psmt.setString(5, status);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));

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

	public ArrayList get_descarga_bitacora(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";
		String remolque = caja;
		String dest = destino;
		String bitacora1 = bitacora;
		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "SELECT count(distinct(v1.GH_GUIA_NO)) as guias,SUM(V2.BULTOS) bultos "
					+ "FROM (SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA,SUBSTR(GH_DEST_BRNC_ID,1,3) AS DEST "
					+ "FROM BOK_GUIA_HEAD WHERE GH_CURR_TRIP = ? "
					+ "AND GH_DEST_BRNC_ID = ?  AND GH_LOAD_STUS = ? and GH_CUR_TRP_TRLR_REF = ?  AND GH_ACTV_FLAG= ? "
					+ "UNION ALL SELECT GH_GUIA_NO,SUBSTR(GH_CURR_LOCA,1,3) AS LOCA, "
					+ "SUBSTR(GH_DEST_BRNC_ID,1,3) AS DEST FROM BOK_GUIA_HEAD WHERE GH_GUIA_NO in (SELECT distinct(GS_GUIA_NO) from bok_guia_stus "
					+ "where GS_COM_TRNS_REF_CODE in (select CT_TRNS_NO from com_ctt "
					+ "where CT_PRIM_ENTY = ?  and CT_TEXT_17 = ? and CT_TRNS_TYPE in ( ? ))) AND GH_DEST_BRNC_ID = ?  and GH_CUR_TRP_TRLR_REF = ? AND GH_ACTV_FLAG= ?  ) V1, "
					+ "(SELECT GL_GUIA_NO,GL_VLUE_1 AS PESO,GL_VLUE_2 * GL_QUNT AS VOLUMEN,GL_QUNT AS BULTOS "
					+ "FROM BOK_GUIA_SRVC_ITEM WHERE GL_SRVC_ID in  ( ?, ? , ? , ? , ? )) V2 "
					+ "WHERE V1.GH_GUIA_NO=V2.GL_GUIA_NO GROUP BY V1.DEST order by BULTOS desc ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora1);
			psmt.setString(2, dest);
			psmt.setString(3, load);
			psmt.setString(4, remolque);
			psmt.setString(5, status);
			psmt.setString(6, bitacora1);
			psmt.setString(7, segmento);
			psmt.setString(8, llegada);
			psmt.setString(9, dest);
			psmt.setString(10, remolque);
			psmt.setString(11, status);
			psmt.setString(12, paquete);
			psmt.setString(13, sobres);
			psmt.setString(14, valija);
			psmt.setString(15, sobre);
			psmt.setString(16, acuse);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"guias",
						rs.getString("GUIAS") == null ? "" : rs
								.getString("GUIAS"));
				values.put(
						"bultos",
						rs.getString("BULTOS") == null ? "" : rs
								.getString("BULTOS"));

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

	public String get_descarga_guias(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GUIAS_COMPLETAS_DESC_BITA ( ? , ?, ? , ?, ? ) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, descarga);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_descarga_guias_resp(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GUIAS_COMPLETAS_DESC_BITA ( ? , ?, ? , ?, ? ) from dual ";
			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, descarga);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_carga_guias(String bitacora, String destino,
			String segmento, String sucursal, String trasbordo, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GUIAS_COMPLETAS_CARGA_V2 ( ?, ?, ?, ?, ?, ?) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, sucursal);
			psmt.setString(5, trasbordo);
			psmt.setString(6, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_carga_paquetes(String bitacora, String destino,
			String segmento, String sucursal, String trasbordo, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_BULTOS_ESCANEADOS_CARGA_V2( ? , ?, ?, ?, ?, ?) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, sucursal);
			psmt.setString(5, trasbordo);
			psmt.setString(6, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_carga_paquetes_exd(String bitacora, String destino,
			String segmento, String sucursal, String trasbordo, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_BULTOS_ESCAN_CARGA_EXD_V2 ( ?, ?, ?, ?, ?, ?) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, sucursal);
			psmt.setString(5, trasbordo);
			psmt.setString(6, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_carga_guias_exd(String bitacora, String destino,
			String segmento, String sucursal, String trasbordo, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GUIAS_COMP_CARGA_EXD_V2( ?, ?, ?, ?, ?, ?) from dual";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, segmento);
			psmt.setString(4, sucursal);
			psmt.setString(5, trasbordo);
			psmt.setString(6, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_descarga_paquetes(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_BULTOS_ESCANEADOS_DESC ( ?,  ?, ?, ?, ? ) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, descarga);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_descarga_guias_exd(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_GUIAS_COMPLETAS__DESC_EXD ( ?, ?, ?, ?, ? ) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, descarga);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public String get_descarga_paquetes_exd(String bitacora, String destino,
			String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String guias = "";
		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select FUN_BULTOS_ESCANEADOS_DESC_EXD ( ?, ?, ?, ?, ? ) from dual ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, destino);
			psmt.setString(3, descarga);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			rs = psmt.executeQuery();

			if (rs.next()) {
				guias = rs.getString(1);

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
		return guias;
	}

	public ArrayList get_display_ONOFF(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select a.DISP_ID||b.DISP_STATUS as DISP_ID,a.DISP_NAME from "
					+"(select DISP_ID,DISP_NAME from sys_disp_mstr where  DISP_BRNC = ?  )a, "
					+"(select DISP_ID,DISP_STATUS from sys_disp_power where DISP_BRNC = ? )b "
					+"where a.DISP_ID = b.DISP_ID order by DISP_NAME  ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			psmt.setString(2, sucursal);

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"disp_id",
						rs.getString("DISP_ID") == null ? "" : rs
								.getString("DISP_ID"));
				values.put("disp_name", rs.getString("DISP_NAME") == null ? ""
						: rs.getString("DISP_NAME"));

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

	
	public ArrayList get_display(String sucursal) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String query = "";

		ArrayList result = new ArrayList();
		HashMap values = new HashMap();

		try {
			con = ConnectDB.getConnection();

			query = "select DISP_ID,DISP_NAME from sys_disp_mstr where  DISP_BRNC = ? order by DISP_NAME ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, sucursal);
			

			rs = psmt.executeQuery();

			while (rs.next()) {
				values.put(
						"disp_id",
						rs.getString("DISP_ID") == null ? "" : rs
								.getString("DISP_ID"));
				values.put("disp_name", rs.getString("DISP_NAME") == null ? ""
						: rs.getString("DISP_NAME"));

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
	
	
	public void pro_ins_pantalla(String bitacora, String sucursal,
			String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update sys_disp_mstr set DISP_STATUS = 'A', DISP_BITACORA = ? where DISP_BRNC = ? and DISP_ID = ? ");

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, pantalla);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public void pro_upd_busy(String sucursal, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update sys_disp_mstr set DISP_STATUS = 'I',DISP_BITACORA = null where DISP_BRNC = ? and DISP_ID = ? ");

			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public void pro_screen_off(String activo, String sucursal, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update sys_disp_power set DISP_STATUS= ? where DISP_BRNC= ?  and DISP_ID = ? ");

			psmt.setString(1, activo);
			psmt.setString(2, sucursal);
			psmt.setString(3, pantalla);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public void pro_off_pantalla(String sucursal, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update wir_task_mstr set WL_NUM_PANT = null where WL_BRNC_ID = ? and WL_NUM_PANT = ? ");

			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public void pro_upd_pantalla(String pantalla, String bitacora, String tipo,
			String sucursal, String segmento, String caja) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update WIR_TASK_MSTR set WL_NUM_PANT = ? where WT_TRIP_NO = ? and WT_MOVE_ID = ? and WL_SEGM_ID = ? and WL_TRAL_ID = ?  ");

			psmt.setString(1, pantalla);
			psmt.setString(2, bitacora);
			psmt.setString(3, tipo);
			psmt.setString(4, segmento);
			psmt.setString(5, caja);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	
	public void pro_null_pantalla(String sucursal, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareStatement("update WIR_TASK_MSTR set WL_NUM_PANT = null where WL_BRNC_ID = ? 	and WL_NUM_PANT = ?  ");

			psmt.setString(1, sucursal);
			psmt.setString(2, pantalla);

			int retorno = psmt.executeUpdate();

			psmt.close();
			con.commit();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}
	
	
	public String[] get_parametro(String bitacora, String pantalla) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String tipo = "";
		String remolque = "";
		String segmento = "";

		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select WT_MOVE_ID,WL_TRAL_ID,WL_SEGM_ID from WIR_TASK_MSTR where WT_TRIP_NO = ?  and WL_NUM_PANT = ?  ";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, pantalla);

			rs = psmt.executeQuery();

			if (rs.next()) {
				tipo = rs.getString(1);
				remolque = rs.getString(2);
				segmento = rs.getString(3);

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

		return new String[] { tipo, remolque, segmento };
	}

	public void pro_ins_disp(String bitacora, String sucursal, String tipo,
			String segmento, String remolque, String var1, String var2,
			String var3, String var4, String var5, String var6, String var7) {

		Connection con = null;
		PreparedStatement psmt = null;

		String query = "";
		try {
			con = ConnectDB.getConnection();

			psmt = con
					.prepareCall(("{call PRO_DISP_TOTAL (?,?,?,?,?,?,?,?,?,?,?,?)}"));

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, tipo);
			psmt.setString(4, segmento);
			psmt.setString(5, remolque);
			psmt.setString(6, var1);
			psmt.setString(7, var2);
			psmt.setString(8, var3);
			psmt.setString(9, var4);
			psmt.setString(10, var5);
			psmt.setString(11, var6);
			psmt.setString(12, var7);

			psmt.executeUpdate();

			psmt.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Error " + e);
		}

	}

	public String[] get_totales(String bitacora, String sucursal, String tipo,
			String segmento, String remolque) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String col1 = "";
		String col2 = "";
		String col3 = "";
		String col4 = "";
		String col5 = "";
		String col6 = "";
		String col7 = "";

		String query = "";
		try {
			con = ConnectDB.getConnection();

			query = "select DISP_COL_1,DISP_COL_2,DISP_COL_3,DISP_COL_4,DISP_COL_5,DISP_COL_6,DISP_COL_7 "
					+ " from sys_disp_total where ID =(select max(id) from sys_disp_total  where DISP_BITACORA = ? "
					+ " and DISP_SUCURSAL = ? and DISP_TIPO = ? and DISP_SEGMENTO = ? and DISP_REMOLQUE = ? )";

			psmt = con.prepareStatement(query);

			psmt.setString(1, bitacora);
			psmt.setString(2, sucursal);
			psmt.setString(3, tipo);
			psmt.setString(4, segmento);
			psmt.setString(5, remolque);

			rs = psmt.executeQuery();

			if (rs.next()) {
				col1 = rs.getString(1);
				col2 = rs.getString(2);
				col3 = rs.getString(3);
				col4 = rs.getString(4);
				col5 = rs.getString(5);
				col6 = rs.getString(6);
				col7 = rs.getString(7);

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

		return new String[] { col1, col2, col3, col4, col5, col6, col7 };
	}

}
