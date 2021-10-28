package com.kstream.service;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;

public interface WordCountBinder {

	@Input("word-count-input-channel")
	public KStream<String, String>  wordCountInputStream();
	
	
}
