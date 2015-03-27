package com.lynxspa.sdm.core.model.devices;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GPM_DEVICES")
public class Device {
	private Long				id;
	private String				deviceId;
	private String				type;
	private Date				registrationDate;
	private Date				updateDate;

	private List<DeviceSetting>	settings;
	private List<CustomerDeviceProperty> customerProperties;

	@Id
	@SequenceGenerator(name = "DEVICE_GEN", sequenceName = "DEVICE_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DEVICE_GEN")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="DEVICEID", length=64)
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name="TYPE", length=16)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REGISTRATIONDATE")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATEDATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@OneToMany(mappedBy="device", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<DeviceSetting> getSettings() {
		return settings;
	}

	public void setSettings(List<DeviceSetting> settings) {
		this.settings = settings;
	}

	@OneToMany(mappedBy="device", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<CustomerDeviceProperty> getCustomerProperties() {
		return customerProperties;
	}

	public void setCustomerProperties(List<CustomerDeviceProperty> identifiers) {
		this.customerProperties = identifiers;
	}
}
