package com.streams;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface OrderBinder {

	@Input("order-input-channel")
	public KStream<String, Order> orderInputStream();

	@Output("order-output-channel")
	public KStream<String, Order> orderOutPutStream();
}
