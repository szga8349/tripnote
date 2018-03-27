package com.lenovo.spider.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpInfo implements Serializable {

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

    public int getPort() {
        return StringUtils.isNotEmpty(port) ? Integer.parseInt(port) : 0;
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