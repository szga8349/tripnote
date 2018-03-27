package com.lenovo.spider;

import com.lenovo.spider.selenium.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 18-1-25
 */
@Data
@AllArgsConstructor
public class DomEvent implements Serializable {
    private static final long serialVersionUID = 3204200569150302719L;

    private EventType eventType;
    private String eventTargetLocator;
}
