package mx.com.paquetexpress.informativa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beanUtil.ConnectDB;
import mx.com.paquetexpress.informativa.dto.InfoDTO;
import mx.com.paquetexpress.informativa.util.DataSourceConexion;

public class InfoDAO {
   /**
     * getListInfo Metodo para obtener la lista de pantallas por sucurasl
     * @param branch
     * @return List<InfoDTO>
     */
    public List<InfoDTO> getListInfo(String branch){
        Connection con = null;
        
        List<InfoDTO> listInfo = new ArrayList<InfoDTO>();
       
        PreparedStatement stmt=null;
        ResultSet rs=null;
        String  qryInfo = "SELECT DISP_BRNC, DISP_ID, DISP_NAME, DISP_STATUS, DISP_BITACORA FROM SYS_DISP_MSTR WHERE DISP_BRNC = ?";
        try{
        	con = ConnectDB.getConnection();
        	
            stmt=con.prepareStatement(qryInfo);
            stmt.setString(1,branch);
            rs=stmt.executeQuery();
                while(rs.next()){
                    InfoDTO e = new InfoDTO();
                    e.setDispBrnc(rs.getString("DISP_BRNC"));
                    e.setDispId(rs.getString("DISP_ID"));
                    e.setDispName(rs.getString("DISP_NAME"));
                    e.setDispStatus(rs.getString("DISP_STATUS"));
                    e.setDisBitacora(rs.getString("DISP_BITACORA"));
                    listInfo.add(e);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
               if (rs != null) rs.close();
               if (stmt != null) stmt.close(); 
               if (con != null) con.close();
            }catch(Exception e){
               e.printStackTrace();
            }
        }
        return listInfo;
    }
    
    /**
     * getInfo M�todo para obtener una pantalla en especifico de una sucursal determinada
     * @param branch
     * @param displayId
     * @return
     */
    public InfoDTO getInfo(String branch, String displayId){
        Connection con = null;
        
        InfoDTO infoDTO = new InfoDTO();
      
        PreparedStatement stmt=null;
        ResultSet rs=null;
        String  qryInfo = "SELECT DISP_BRNC, DISP_ID, DISP_NAME, DISP_STATUS, DISP_BITACORA FROM SYS_DISP_MSTR WHERE DISP_BRNC = ? AND DISP_ID = ?  ";
        
        try{
            
            con = ConnectDB.getConnection();
            stmt=con.prepareStatement(qryInfo);
            stmt.setString(1,branch);
            stmt.setString(2,displayId);
            rs=stmt.executeQuery();
            if(rs.next()){
                infoDTO.setDispBrnc(rs.getString("DISP_BRNC"));
                infoDTO.setDispId(rs.getString("DISP_ID"));
                infoDTO.setDispName(rs.getString("DISP_NAME"));
                infoDTO.setDispStatus(rs.getString("DISP_STATUS"));
                infoDTO.setDisBitacora(rs.getString("DISP_BITACORA"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
               if (rs != null) rs.close();
               if (stmt != null) stmt.close(); 
               if (con != null) con.close();
            }catch(Exception e){
               e.printStackTrace();
            }
        }
        
        return infoDTO;
    }
    
    /**
     * updateBitacora M�todo que actualiza tablas de manejo de pantallas y tabla de control 
     * @param bitacora
     * @param moveId
     * @param remolqueId
     * @param segmento
     * @param status
     * @param branch
     * @param displayId
     */
    public void updateBitacora(String bitacora, String moveId, String remolqueId, String segmento, String status, String branch, String displayId){
        Connection con = null;
        
        PreparedStatement stmtDispMstr=null;
        PreparedStatement stmtWirTaskMstr=null;
        
        String qryUpadteSysDispMstr = "UPDATE SYS_DISP_MSTR SET DISP_STATUS = ?, DISP_BITACORA = ?  WHERE DISP_ID = ? AND DISP_BRNC = ?";
        String qryUpdateWirTaskMstr = "UPDATE WIR_TASK_MSTR SET WL_NUM_PANT = ? WHERE WT_TRIP_NO = ? AND WT_MOVE_ID = ?  AND WL_TRAL_ID = ? AND WL_SEGM_ID = ?";
        
        try{
        	con = ConnectDB.getConnection();
           
        	
        	
            stmtDispMstr=con.prepareStatement(qryUpadteSysDispMstr);
            stmtDispMstr.setString(1,status);
            stmtDispMstr.setString(2,bitacora);
            stmtDispMstr.setString(3,displayId);
            stmtDispMstr.setString(4,branch);
            stmtDispMstr.executeUpdate();
            
            stmtWirTaskMstr=con.prepareStatement(qryUpdateWirTaskMstr);
            stmtWirTaskMstr.setString(1,displayId);
            stmtWirTaskMstr.setString(2,bitacora);
            stmtWirTaskMstr.setString(3,moveId);
            stmtWirTaskMstr.setString(4,remolqueId);
            stmtWirTaskMstr.setString(5,segmento);
            stmtWirTaskMstr.executeUpdate();
            con.commit();
        }catch(Exception ex){
        	try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            ex.printStackTrace();
        }finally{
            try{
                if (stmtDispMstr != null) stmtDispMstr.close();
                if (stmtWirTaskMstr != null) stmtDispMstr.close();
                if (con != null) con.close();
            }catch(Exception e){
               e.printStackTrace();
            }
        }
        
    }
    
    /**
     * updateBitacoraBusy Metodo que actualiza la tabla de pantallas con infomacion nueva y actualiza la tabla deoperacion
     * en las dos bitacoras una con la pantalla seleccionada y la bitacora a sustituir con el valor null
     * @param bitAct
     * @param bitIna
     * @param moveIdAct
     * @param moveIdIna
     * @param remolqueIdAct
     * @param segmentoAct
     * @param remolqueIdIna
     * @param segmentoIna
     * @param status
     * @param branch
     * @param displayId
     */
    public String updateBitacoraBusy(String bitA, String moveIdA, String remolqueIdA, String segmentoA, String bitR, String moveIdR, String remolqueIdR, String segmentoR, String status, String branch, String displayId){
        Connection con = null;
        
        String tran = "I";
        PreparedStatement stmtDispMstr=null;
        PreparedStatement stmtWirTaskMstr=null;
        PreparedStatement stmtWirTaskMstrIna=null;
        
        String qryUpadteSysDispMstr = "UPDATE SYS_DISP_MSTR SET DISP_STATUS = ?, DISP_BITACORA = ? WHERE DISP_ID = ? AND DISP_BRNC = ?";
        String qryUpdateWirTaskMstr = "UPDATE WIR_TASK_MSTR SET WL_NUM_PANT = ? WHERE WT_TRIP_NO = ? AND WT_MOVE_ID = ?  AND WL_TRAL_ID = ? AND WL_SEGM_ID = ?";
        String qryUpdateWirTaskMstrNull = "UPDATE WIR_TASK_MSTR SET WL_NUM_PANT = ? WHERE WT_TRIP_NO = ? AND WT_MOVE_ID = ?  AND WL_TRAL_ID = ? AND WL_SEGM_ID = ?";
        String qryUpdateWirTaskMstrBusy = "UPDATE WIR_TASK_MSTR SET WL_NUM_PANT = NULL WHERE WT_TRIP_NO = ? AND WT_MOVE_ID = ?  AND WL_TRAL_ID = ? AND WL_SEGM_ID = ?";
        String qryUpdateWirTaskMstrBusyNull = "UPDATE WIR_TASK_MSTR SET WL_NUM_PANT = NULL WHERE WT_TRIP_NO = ? AND WT_MOVE_ID = ?  AND WL_TRAL_ID = ? AND WL_SEGM_ID = ?";
        
        try{
        	con = ConnectDB.getConnection();
            stmtDispMstr=con.prepareStatement(qryUpadteSysDispMstr);
            
            
            
            stmtDispMstr.setString(1,status);
            stmtDispMstr.setString(2,bitR);
            stmtDispMstr.setString(3,displayId);
            stmtDispMstr.setString(4,branch);
            stmtDispMstr.executeUpdate();
            
            if(segmentoR.equals("")){ 
            	
                stmtWirTaskMstr=con.prepareStatement(qryUpdateWirTaskMstrNull);
            }else{
                stmtWirTaskMstr=con.prepareStatement(qryUpdateWirTaskMstr);
                stmtWirTaskMstr.setString(5,segmentoR);
            }
    
            stmtWirTaskMstr.setString(1,displayId);
            stmtWirTaskMstr.setString(2,bitR);
            stmtWirTaskMstr.setString(3,moveIdR);
            stmtWirTaskMstr.setString(4,remolqueIdR);
            stmtWirTaskMstr.executeUpdate();
            
           
            if(segmentoA.equals("")){ 
            	
            	
            	if(!moveIdA.equals("")){
            		
            		stmtWirTaskMstrIna=con.prepareStatement(qryUpdateWirTaskMstrBusyNull);
            	}
            	
                
            }else{
            	
                stmtWirTaskMstrIna=con.prepareStatement(qryUpdateWirTaskMstrBusy);
                stmtWirTaskMstrIna.setString(4,segmentoA);
            }
            
            stmtWirTaskMstrIna.setString(1,bitA);
            stmtWirTaskMstrIna.setString(2,moveIdA);
            stmtWirTaskMstrIna.setString(3,remolqueIdA);
            stmtWirTaskMstrIna.setString(4,segmentoA);
            stmtWirTaskMstrIna.executeUpdate();
            con.commit();
            tran = "A";
        }catch(Exception ex){
        	try {
        		con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            ex.printStackTrace();
        }finally{
            try{
                if (stmtDispMstr != null) stmtDispMstr.close();
                if (stmtWirTaskMstr != null) stmtDispMstr.close();
                if (stmtWirTaskMstrIna != null) stmtDispMstr.close();
                if (con != null) con.close();
            }catch(Exception e){
               e.printStackTrace();
            }
        }
        return tran;
    }
    
}

