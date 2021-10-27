package com.ktable.service;

import org.apache.kafka.streams.kstream.KTable;
import org.springframework.cloud.stream.annotation.Input;

public interface StockBinder {

	@Input("stock-input-channel")
	public KTable<String, String> stockInputStream();
	
	
}
