package com.ktable.service;

import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = StockBinder.class)
public class StockService {

	@StreamListener(value = "stock-input-channel")
	public void process(KTable<String, String> input) {

		input
		     //.filter((key, value) -> key.contains("HDFCBANK"))
			 .toStream()
			 .foreach((k, v) -> System.out.println("Key = " + k + " Value = " + v));

	}

}
