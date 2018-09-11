package com.lenovo.tripnote.webchat.convert;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lenovo.tripnote.webchat.utils.EncryptionUtils;

public class IDEncryptionSerializer extends JsonSerializer<Integer> {

	@Override
	public void serialize(Integer value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString(EncryptionUtils.encryption(value.toString()));
	}
}