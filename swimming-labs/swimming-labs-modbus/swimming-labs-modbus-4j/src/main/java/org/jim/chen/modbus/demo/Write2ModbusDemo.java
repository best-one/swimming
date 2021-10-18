package org.jim.chen.modbus.demo;

import static org.jim.chen.modbus.utill.Modbus4jWriteUtils.writeHoldingRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;

import org.jim.chen.modbus.utill.Modbus4jWriteUtils;
import org.jim.chen.modbus.utill.ValueObj;

public class Write2ModbusDemo {

	public static void main(String[] args) {
		while (true) {
//			int a = ra(0,65535);
//			System.out.println(a);
//			if(a<0) {System.exit(0);}
			
			writeList();
			//chinaMobileIot();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void chinaMobileIot() {
		write(2, 0, ra(0, 200), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 1, ra(0, 170), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 2, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 3, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 4, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		
		write(2, 7, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 8, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 9, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		
		write(2, 10, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 11, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 12, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 13, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 14, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
	}
	
	public static void writeList() {
		List<ValueObj> list = new ArrayList<>();
		
		list.add(ValueObj.of(2, 0, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 1, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 2, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 3, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 4, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		
		list.add(ValueObj.of(2, 7, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 8, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 9, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		
		list.add(ValueObj.of(2, 10, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 11, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 12, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 13, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		list.add(ValueObj.of(2, 14, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED));
		try {
			Modbus4jWriteUtils.writeList(list);
		} catch (ModbusInitException | ModbusTransportException | ErrorResponseException e) {
			e.printStackTrace();
		}
	}
	
	public static void test1() {
		write(2, 0, ra(0, 200), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 1, ra(0, 170), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 2, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 3, ra(0, 65535), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 4, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 5, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 6, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		
//		write(2, 7, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 8, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 9, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		
		
		write(2, 10, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 11, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 12, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 13, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
		write(2, 14, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 15, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 16, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 17, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 18, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 19, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 20, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 21, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 22, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 23, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 24, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 25, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 26, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 27, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 28, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 29, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 30, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 31, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 32, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 33, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 34, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 35, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 36, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 37, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 38, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		write(2, 39, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
	}
	
	public static void test2() {
		for(int i=0;i<=39;i++) {
			System.out.println("write(2, "+i+", ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);");
		}
	}
	
	
	public void test() {
		while (true) {
			write(2, 0, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
			write(2, 1, ra(0, 9999), DataType.EIGHT_BYTE_INT_SIGNED);
//		for(int i=0 ;i <=39;i++ ) {
//			write(2,i,ra(0,9999),DataType.EIGHT_BYTE_INT_SIGNED);
//		}
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void write(int slaveId, int offset, Number value, int dataType) {
		
		try {
			writeHoldingRegister(slaveId,offset,value,dataType);
			System.out.println("write success!!");
		} catch (ModbusTransportException | ErrorResponseException | ModbusInitException e) {
			e.printStackTrace();
		}
	}
	static Random rand = new Random();
	public static int ra(int min,int max) {
		return rand.nextInt(max-min)+min;
	}
	
}
