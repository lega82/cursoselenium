package mx.com.paquetexpress.informativa.dto;

import java.io.Serializable;

public class InfoDTO implements Serializable {

	String dispBrnc;
	String dispId;
	String dispName;
	String dispStatus;
	String disBitacora;

	public void setDispBrnc(String dispBrnc) {
		this.dispBrnc = dispBrnc;
	}

	public String getDispBrnc() {
		return dispBrnc;
	}

	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public String getDispId() {
		return dispId;
	}

	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	public String getDispName() {
		return dispName;
	}

	public void setDispStatus(String dispStatus) {
		this.dispStatus = dispStatus;
	}

	public String getDispStatus() {
		return dispStatus;
	}

	public void setDisBitacora(String disBitacora) {
		this.disBitacora = disBitacora;
	}

	public String getDisBitacora() {
		return disBitacora;
	}

}
