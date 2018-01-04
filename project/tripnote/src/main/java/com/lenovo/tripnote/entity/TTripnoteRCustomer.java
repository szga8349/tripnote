package com.lenovo.tripnote.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class TTripnoteRCustomer implements Serializable {
    private Integer tripnoteId;

    private Integer customerId;
}