package org.jim.chen.modbus.utill;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;

/**
 * modbus4j写入数据
 * 
 * @author xq
 *
 */
public class Modbus4jWriteUtils {
	static Log log = LogFactory.getLog(Modbus4jWriteUtils.class);
	/**
	 * 工厂。
	 */
	static ModbusFactory modbusFactory;
	static {
		if (modbusFactory == null) {
			modbusFactory = new ModbusFactory();
		}
	}

	/**
	 * 获取tcpMaster
	 * 
	 * @return
	 * @throws ModbusInitException
	 */
	public static ModbusMaster getMaster() throws ModbusInitException {
		IpParameters params = new IpParameters();
		params.setHost("localhost");
		params.setPort(502);

		ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, false);
		tcpMaster.init();
		tcpMaster.setMultipleWritesOnly(true);
		return tcpMaster;
	}

	/**
	 * 写 [01 Coil Status(0x)]写一个 function ID = 5
	 * 
	 * @param slaveId
	 *            slave的ID
	 * @param writeOffset
	 *            位置
	 * @param writeValue
	 *            值
	 * @return 是否写入成功
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static boolean writeCoil(int slaveId, int writeOffset, boolean writeValue)
			throws ModbusTransportException, ModbusInitException {
		// 获取master
		ModbusMaster tcpMaster = getMaster();
		// 创建请求
		WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
		// 发送请求并获取响应对象
		WriteCoilResponse response = (WriteCoilResponse) tcpMaster.send(request);
		if (response.isException()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 写[01 Coil Status(0x)] 写多个 function ID = 15
	 * 
	 * @param slaveId
	 *            slaveId
	 * @param startOffset
	 *            开始位置
	 * @param bdata
	 *            写入的数据
	 * @return 是否写入成功
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static boolean writeCoils(int slaveId, int startOffset, boolean[] bdata)
			throws ModbusTransportException, ModbusInitException {
		// 获取master
		ModbusMaster tcpMaster = getMaster();
		// 创建请求
		WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, bdata);
		// 发送请求并获取响应对象
		WriteCoilsResponse response = (WriteCoilsResponse) tcpMaster.send(request);
		if (response.isException()) {
			return false;
		} else {
			return true;
		}

	}

	/***
	 * 写[03 Holding Register(4x)] 写一个 function ID = 6
	 * 
	 * @param slaveId
	 * @param writeOffset
	 * @param writeValue
	 * @return
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static boolean writeRegister(int slaveId, int writeOffset, short writeValue)
			throws ModbusTransportException, ModbusInitException {
		// 获取master
		ModbusMaster tcpMaster = getMaster();
		// 创建请求对象
		WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
		WriteRegisterResponse response = (WriteRegisterResponse) tcpMaster.send(request);
		if (response.isException()) {
			log.error(response.getExceptionMessage());
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 
	 * 写入[03 Holding Register(4x)]写多个 function ID=16
	 * 
	 * @param slaveId
	 *            modbus的slaveID
	 * @param startOffset
	 *            起始位置偏移量值
	 * @param sdata
	 *            写入的数据
	 * @return 返回是否写入成功
	 * @throws ModbusTransportException
	 * @throws ModbusInitException
	 */
	public static boolean writeRegisters(int slaveId, int startOffset, short[] sdata)
			throws ModbusTransportException, ModbusInitException {
		// 获取master
		ModbusMaster tcpMaster = getMaster();
		// 创建请求对象
		WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, sdata);
		// 发送请求并获取响应对象
		ModbusResponse response = tcpMaster.send(request);
		if (response.isException()) {
			log.error(response.getExceptionMessage());
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
	 * 
	 * @param slaveId
	 * @param offset
	 * @param value
	 *            写入值,Number的子类,例如写入Float浮点类型,Double双精度类型,以及整型short,int,long
	 * @param registerCount
	 *            ,com.serotonin.modbus4j.code.DataType
	 * @throws ModbusTransportException
	 * @throws ErrorResponseException
	 * @throws ModbusInitException
	 */
	public static void writeHoldingRegister(int slaveId, int offset, Number value, int dataType)
			throws ModbusTransportException, ErrorResponseException, ModbusInitException {
		// 获取master
		ModbusMaster tcpMaster = getMaster();
		// 类型
		BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType);
		tcpMaster.setValue(locator, value);
	}

	public static void writeList(List<ValueObj> list) throws ModbusInitException, ModbusTransportException, ErrorResponseException {
		ModbusMaster tcpMaster = getMaster();
		for(ValueObj obj : list) {
			// 类型
			BaseLocator<Number> locator = BaseLocator.holdingRegister(obj.getSlaveId(), obj.getOffset(), obj.getDataType());
			tcpMaster.setValue(locator, obj.getValue());
		}
	}
	
	
//	/**
//     * @param <T>
//     * @param item 多个储位序列化成list
//     * @return
//     * @throws ErrorResponseException 
//     * @throws ModbusTransportException 
//     */
//    public static <T> boolean writeObjToModbusBatch( ModbusMaster modbusMaster, List<T> item ,int slaveId, Integer startOffset) throws ModbusTransportException, ErrorResponseException {
//        
//        List<Pair<BaseLocator<?>, Object>> list = writeObjToModbus(item , slaveId,  startOffset );
//        
//    
//        list.parallelStream().forEach(pair ->{
//            try {
//                modbusMaster.setValue(pair.first, pair.second);
//            } catch (Exception e) {
//                
//                log.error("wirte_to_modbus_error:{}" , Throwables.getStackTraceAsString(e));
//            } 
//        });
//        return true;
//
//        
//
//    };
//    
//    /**
//     * @param <T>
//     * @param item 多个储位序列化成list
//     * @return
//     */
//    public static <T> List<Pair<BaseLocator<?>, Object>> writeObjToModbus(List<T> item ,int slaveId, Integer startOffset) {
//        final Pair<Integer, Integer> offsetTemp = Pair.makePair(startOffset, null) ;
//        List<Pair<BaseLocator<?>, Object>> list  = Lists.newArrayList();
//        for ( T t : item ) {
//            List<Pair<BaseLocator<?>, Object>> modelList = getJsonModelList( t , slaveId , offsetTemp ) ;
//            list.addAll( modelList);
//            
//        }
//        return list;
//    };
//
//    /**
//     * 单个实体转换为modbus 定位器
//     * 
//     * @param <T>
//     * @param i
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    private static <T> List<Pair<BaseLocator<?>, Object>> getJsonModelList(T i , int slaveId, Pair<Integer, Integer> offsetTemp ) {
//        Class<? extends Object> itemClazz = i.getClass();
//        if (Objects.isNull(itemClazz)) {
//            return Lists.newArrayList();
//        }
//        ModBusItem modBusItem = itemClazz.getAnnotation(ModBusItem.class);
//        ToolUtil.isNull(modBusItem, "the javabean must annotatied ModBusItem annotation");
//
//    
//        
//        Map<Field, ModBusItem> map = CLAZZ_FILED_ANNOTATIONS.get(itemClazz);
//        List<?> collect = map.entrySet().stream().filter(mapEntry -> Objects.nonNull(mapEntry.getValue())).map(item ->{
//            ModBusItem modbusItem = item.getValue();
//            BaseLocator<?> loc = null;
//            if(modbusItem.loc() == NumericLocator.class) {
//                loc =  BaseLocator.holdingRegister( slaveId, offsetTemp.first , modbusItem.dataType());
//            
//            }
//            if(modbusItem.loc() == StringLocator.class) {
//                loc =  new StringLocator(slaveId, RegisterRange.HOLDING_REGISTER,  offsetTemp.first, DataType.CHAR, modbusItem.maxRegisterCount());
//    
//            }
//        
//            offsetTemp.setFirst(offsetTemp.first +  modbusItem.maxRegisterCount());
//            Object value =  null;
//            try {
//                 value = item.getKey().get( i);
//            } catch (IllegalArgumentException | IllegalAccessException e) {
//                log.error("getJsonModelList_error:{}" , Throwables.getStackTraceAsString(e)) ;
//            }
//            
//            return Pair.makePair(loc, value );
//        }).collect(Collectors.toList());
//        
//        return (List<Pair<BaseLocator<?>, Object>>) collect;
//    }
	
	public static void main(String[] args) {

		try {
			//@formatter:off
			// 测试01
//			boolean t01 = writeCoil(1, 0, true);
//			System.out.println("T01:" + t01);

			// 测试02
//			boolean t02 = writeCoils(1, 0, new boolean[] { true, false, true });
//			System.out.println("T02:" + t02);

			// 测试03
//			short v = -3;
//			boolean t03 = writeRegister(1, 0, v);
//			System.out.println("T03:" + t03);
			// 测试04
//			boolean t04 = writeRegisters(1, 0, new short[] { -3, 3, 9 });
//			System.out.println("t04:" + t04);
			//写模拟量
			writeHoldingRegister(1,0, 10.1f, DataType.FOUR_BYTE_FLOAT);
			
			//@formatter:on
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}