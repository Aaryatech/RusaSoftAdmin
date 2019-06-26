package com.ats.rusasoft.master;

public class SettingKeyValue {
 
	private int settingId;
	
	private String settingKey ;
	
	private int intValue;
	
	private String stringValue ;
	
	private int delStatus;

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public String getSettingKey() {
		return settingKey;
	}

	public void setSettingKey(String settingKey) {
		this.settingKey = settingKey;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public int getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	@Override
	public String toString() {
		return "SettingKeyValue [settingId=" + settingId + ", settingKey=" + settingKey + ", intValue=" + intValue
				+ ", stringValue=" + stringValue + ", delStatus=" + delStatus + ", getSettingId()=" + getSettingId()
				+ ", getSettingKey()=" + getSettingKey() + ", getIntValue()=" + getIntValue() + ", getStringValue()="
				+ getStringValue() + ", getDelStatus()=" + getDelStatus() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
