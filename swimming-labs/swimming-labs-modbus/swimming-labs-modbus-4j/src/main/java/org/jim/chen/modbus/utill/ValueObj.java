package org.jim.chen.modbus.utill;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class ValueObj {

	private int slaveId;
	private int offset;
	private Number value;
	private int dataType;
	
	public static ValueObj of(int slaveId,int offset,Number value,int dataType) {
	 return 	ValueObj.builder().slaveId(slaveId)
			 .offset(offset)
			 .value(value)
			 .dataType(dataType)
		.build();
	}
}
