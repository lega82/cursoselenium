package mx.com.paquetexpress.informativa.core;

import mx.com.paquetexpress.informativa.dao.InfoDAO;
import mx.com.paquetexpress.informativa.dto.InfoDTO;

public class DisplayHandler {
    final String DISPLAY_ACTIVO = "A";
    final String DISPLAY_INACTIVO = "I";
    
    /**
     * Método que evalua el estatus de la pantalla si esta siendo ocupada por una bitacora o no, y envia el
     * mensaje al cliente I si esta inactivo o el número de la bitacora si esta ocupada
     * @param bitacora
     * @param moveId
     * @param remolqueId
     * @param segmento
     * @param branch
     * @param displayId
     * @return
     */

    public String handlerDisplay(String bitacora, String moveId, String remolqueId,String segmento, String branch, String displayId){
        String msg = null;
        InfoDAO daoInfo = new InfoDAO();
        try{
            InfoDTO info = daoInfo.getInfo(branch, displayId);
            if(info.getDispStatus().equals(DISPLAY_INACTIVO)){
                msg = DISPLAY_INACTIVO;
                daoInfo.updateBitacora(bitacora, moveId, remolqueId, segmento, DISPLAY_ACTIVO, branch, displayId);
            }else{
                msg = info.getDisBitacora();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return msg;
    }
    
    /**
     * Método que se utilizará para cuando la panta este ocupadapor alguna bitacora.
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

    public String handlerDisplayBusy(String bitA, String moveIdA, String remolqueIdA, String segmentoA, String bitR, String moveIdR, String remolqueIdR, String segmentoR, String branch, String displayId){
        String tran = "I";
        InfoDAO daoInfo = new InfoDAO();
        tran = daoInfo.updateBitacoraBusy(bitA, moveIdA, remolqueIdA, segmentoA, bitR, moveIdR, remolqueIdR, segmentoR, DISPLAY_ACTIVO, branch, displayId);
        return tran;
    }
    
}
