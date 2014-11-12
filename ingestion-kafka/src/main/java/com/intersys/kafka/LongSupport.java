package com.intersys.kafka;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kafka.serializer.Decoder;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class LongSupport implements Decoder<Long>, Encoder<Long> {

	public LongSupport(VerifiableProperties vp) {
		
	}
	
	@Override
	public byte[] toBytes(Long obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			return new byte[0];
		}
	}

	@Override
	public Long fromBytes(byte[] b) {
		try {
			return (Long) new ObjectInputStream(new ByteArrayInputStream(b)).readObject();
		} catch (Exception e) {
			return null;
		}
	}

}
