package semi.kh.chart.model.vo;

import java.io.Serializable;

public class Favorite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int smartPhone = 0;
	private int monitor = 0;
	private int desktop = 0;
	private int wearable = 0;
	private int notebook = 0;
	private int tablet = 0;
	public Favorite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Favorite(int smartPhone, int monitor, int desktop, int wearable, int notebook, int tablet) {
		super();
		this.smartPhone = smartPhone;
		this.monitor = monitor;
		this.desktop = desktop;
		this.wearable = wearable;
		this.notebook = notebook;
		this.tablet = tablet;
	}
	public int getSmartPhone() {
		return smartPhone;
	}
	public void setSmartPhone(int smartPhone) {
		this.smartPhone = smartPhone;
	}
	public int getMonitor() {
		return monitor;
	}
	public void setMonitor(int monitor) {
		this.monitor = monitor;
	}
	public int getDesktop() {
		return desktop;
	}
	public void setDesktop(int desktop) {
		this.desktop = desktop;
	}
	public int getWearable() {
		return wearable;
	}
	public void setWearable(int wearable) {
		this.wearable = wearable;
	}
	public int getNotebook() {
		return notebook;
	}
	public void setNotebook(int notebook) {
		this.notebook = notebook;
	}
	public int getTablet() {
		return tablet;
	}
	public void setTablet(int tablet) {
		this.tablet = tablet;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Favorite [smartPhone=" + smartPhone + ", monitor=" + monitor + ", desktop=" + desktop + ", wearable="
				+ wearable + ", notebook=" + notebook + ", tablet=" + tablet + "]";
	}
	
}
