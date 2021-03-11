import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
	private int lineID;
	private int lineNo;
	private String lineName;
	private int vehicleTypeID;
	private List<Integer> directZero;
	private List<Integer> directOne;
	
	public Line(int lineID, int lineNo, String lineName, int vehicleTypeID) {
		this.lineID = lineID;
		this.lineNo = lineNo;
		this.lineName = lineName;
		this.vehicleTypeID = vehicleTypeID;
		directZero=new ArrayList<Integer>();
		directOne=new ArrayList<Integer>();
		
	}

	
	
	/**
	 * @return the lineID
	 */
	public int getLineID() {
		return lineID;
	}



	/**
	 * @param lineID the lineID to set
	 */
	public void setLineID(int lineID) {
		this.lineID = lineID;
	}



	/**
	 * @return the lineNo
	 */
	public int getLineNo() {
		return lineNo;
	}



	/**
	 * @param lineNo the lineNo to set
	 */
	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}



	/**
	 * @return the lineName
	 */
	public String getLineName() {
		return lineName;
	}



	/**
	 * @param lineName the lineName to set
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}



	/**
	 * @return the vehicleTypeID
	 */
	public int getVehicleTypeID() {
		return vehicleTypeID;
	}



	/**
	 * @param vehicleTypeID the vehicleTypeID to set
	 */
	public void setVehicleTypeID(int vehicleTypeID) {
		this.vehicleTypeID = vehicleTypeID;
	}

	

	/**
	 * @return the directZero
	 */
	public List<Integer> getDirectZero() {
		return directZero;
	}



	/**
	 * @return the directOne
	 */
	public List<Integer> getDirectOne() {
		return directOne;
	}

	public void addStop(int direction, int e) {
		if(direction==0) {
			directZero.add(e);
		}
		else {
			directOne.add(e);
		}
	}


	public String toString() {
		return lineID + " - " + lineNo + " - " + lineName + " - " + vehicleTypeID;
	}
	
}
