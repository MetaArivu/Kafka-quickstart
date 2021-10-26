package com.ktable.service;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.WindowedSerdes;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import com.ktabel.model.Department;
import com.ktabel.model.Employee;

import lombok.extern.log4j.Log4j2;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@EnableBinding(value = EmployeeBinder.class)
public class EmployeeService {

	@StreamListener(value = "employee-input-channel")
	public void process(KStream<String, String> input) {

			input
				.mapValues(Employee::instance)
				.map((k,v)-> KeyValue.pair(v.getEmpId(), v))
				.peek((k,v)-> log.info("Key={}, Value={}",k,v))
				.toTable(Materialized.with(Serdes.String(), new JsonSerde<>(Employee.class)))
				.groupBy((k,v) -> KeyValue.pair(v.getDeptId(), v),
						Grouped.with(Serdes.String(),new JsonSerde<>(Employee.class)))
				.aggregate(()-> DepartmentBuilder.init(),
						   (k,v,aggr)-> DepartmentBuilder.add(v, aggr), 
						   (k,v,aggr)-> DepartmentBuilder.sub(v, aggr),
						   Materialized.with(Serdes.String(), new JsonSerde<>(Department.class)))
				.toStream()
				.foreach((k,v)-> log.info("Key={}, Value={}",k,v));
				
				
				
	}
	
	

}
