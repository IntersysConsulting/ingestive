package com.intersys.kafka;

import kafka.serializer.Decoder;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class StringSupport implements Decoder<String>, Encoder<String> {

	public StringSupport(VerifiableProperties vp) {
		
	}

	@Override
	public String fromBytes(byte[] bytes) {
		return new String(bytes);
	}

	@Override
	public byte[] toBytes(String obj) {
		return obj.getBytes();
	}

}
