package net.joywise.bigdata.zid_rpc;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.joywise.bigdata.zid_rpc.thread.ConfigCheck;
import net.joywise.bigdata.zid_rpc.thread.DeviceStarData;
import net.joywise.bigdata.zid_rpc.thread.SystemStatusData;

public class ZidRpcConsumer {
	private static Logger logger = Logger.getLogger(ZidRpcConsumer.class);

	/**
	 * To get ipv6 address to work, add
	 * System.setProperty("java.net.preferIPv6Addresses", "true"); before
	 * running your application.
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-dubbo.xml" });
		context.start();
		String filePath = "service_execute_cycle.properties";
		RpcServiceConfig config = new RpcServiceConfig(filePath);

		// 热加载配置文件线程
		ConfigCheck check = new ConfigCheck(config);
		Thread checkThread = new Thread(check);
		checkThread.setName("ConfigCheckThread");
		checkThread.start();
		// 星光图数据更新线程
		DeviceStarData star = new DeviceStarData(config, context);
		Thread deviceStarThread = new Thread(star);
		deviceStarThread.setName("DeviceStarThread");
		deviceStarThread.start();
		// 系统状态更新线程
		SystemStatusData status = new SystemStatusData(config, context);
		Thread systemStatusThread = new Thread(status);
		systemStatusThread.setName("SystemStatusThread");
		systemStatusThread.start();
	}
}
