package com.lenovo.tripnote.sms;

import java.io.IOException;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

public interface ISmsSender {
	public boolean sendLoginCode(String phone, String code) throws ServerException, ClientException,IOException;
	public boolean sendResetCode(String phone, String code) throws ServerException, ClientException,IOException;
	public boolean sendRegisterCode(String phone, String code) throws ServerException, ClientException,IOException;
}
