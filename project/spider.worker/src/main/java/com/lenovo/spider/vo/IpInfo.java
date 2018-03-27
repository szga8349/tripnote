package com.lenovo.spider.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

public class IpInfo implements Serializable{
	
	private static final long serialVersionUID = 6704037904564928112L;
	
	private Long id;
    private String ip;
    private String port;
    private Integer using;
    private Integer available;
    private String deadline;
    private String province;
    private String city;
    private String carrieroperator;
    private Date createTime;
    private Date updateTime;
    private Integer source;
    
    //哪个站点的ip
    private SiteInfo site;
    
    public IpInfo(){
    }
    
    public Long getId() {
        return id;
    }
    
    public IpInfo(String ip, String port, String deadline) {
		super();
		this.ip = ip;
		this.port = port;
		this.deadline = deadline;
	}

	public void setId(Long id){
        this.id = id;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public int getPort() {
		return NumberUtils.toInt(port);
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getUsing() {
        return using;
    }
    
    public void setUsing(Integer using){
        this.using = using;
    }
    
    public Integer getAvailable() {
        return available;
    }
    
    public void setAvailable(Integer available){
        this.available = available;
    }
    
    public String getDeadline() {
        return deadline;
    }
    
    public void setDeadline(String deadline){
        this.deadline = deadline;
    }
    
    public String getProvince() {
        return province;
    }
    
    public void setProvince(String province){
        this.province = province;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCarrieroperator() {
        return carrieroperator;
    }
    
    public void setCarrieroperator(String carrieroperator){
        this.carrieroperator = carrieroperator;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
    public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public SiteInfo getSite() {
		return site;
	}

	public void setSite(SiteInfo site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpInfo other = (IpInfo) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IpInfo [ip=" + ip + ", port=" + port + ", deadline=" + deadline + ", site=" + site + "]";
	}
}