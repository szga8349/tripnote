package com.lenovo.tripnote.webchat.vo;

import com.lenovo.tripnote.webchat.convert.IDDeEncryption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IDVo {
   @IDDeEncryption
   private Integer id;
}
