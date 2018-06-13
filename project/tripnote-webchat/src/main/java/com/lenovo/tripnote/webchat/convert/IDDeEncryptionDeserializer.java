package com.lenovo.tripnote.webchat.convert;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lenovo.tripnote.webchat.utils.EncryptionUtils;

public class IDDeEncryptionDeserializer extends JsonDeserializer<Integer>{

	@Override
	public Integer deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
			throws IOException, JsonProcessingException {
		String test = jsonparser.getText();
		return Integer.valueOf(EncryptionUtils.deEncryption(test));
	}
}
