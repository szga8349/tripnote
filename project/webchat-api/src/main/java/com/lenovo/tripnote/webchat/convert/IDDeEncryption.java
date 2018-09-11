package com.lenovo.tripnote.webchat.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@JacksonAnnotationsInside
@JsonDeserialize(using = IDDeEncryptionDeserializer.class)
public @interface IDDeEncryption {
	
}