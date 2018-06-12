package com.lenovo.tripnote.webchat.convert;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lenovo.tripnote.webchat.utils.Bash64Utils;

public class ResultIDEncryptionSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if (value instanceof Integer) {
			jgen.writeString(Bash64Utils.enBase64(value.toString()));
		} else {
			jgen.writeObject(value);
		}

	}
}
