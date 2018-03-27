package com.lenovo.spider.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteInfo implements Serializable{

	private static final long serialVersionUID = 1035843310630484059L;
	
	private Long id;
    private String siteName;
    private String domainName;
    
    /**1不需要 2需要cookie 3需要登录*/
    private Integer authType;
    private String note;
    private Date createTime;
    private Date updateTime;

    private Integer crawlerable;
    
	@Override
	public String toString() {
		return "[siteName=" + siteName + ", domainName=" + domainName + ", authType=" + authType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SiteInfo other = (SiteInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}