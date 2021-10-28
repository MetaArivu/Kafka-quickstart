package com.kstream.service;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = WordCountBinder.class)
public class WordCountService {

	@StreamListener(value = "word-count-input-channel")
	public void process(KStream<String, String> input) {

		KStream<String, String> wordCountStream = input
	                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split(" ")));
		 
		wordCountStream
			 .groupBy((k,v)->v)
			 .count()
			 .toStream()
			 //.map((k,v)-> KeyValue.pair(k, v.toString()))
			 //.to("aggregate-word-count-topic", Produced.with(Serdes.String(),Serdes.String()));
			 .foreach((k, v) -> log.info("Key = {},  Value = {}", k, v));

	}

}
