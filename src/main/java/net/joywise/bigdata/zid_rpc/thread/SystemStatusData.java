package net.joywise.bigdata.zid_rpc.thread;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import info.zznet.collector.rpc.api.RpcStatService;
import net.joywise.bigdata.zid_rpc.RpcServiceConfig;
import net.joywise.bigdata.zid_rpc.util.Config;

public class SystemStatusData implements Runnable {
	private RpcServiceConfig config;
	private ApplicationContext context;
	private Logger logger = Logger.getLogger(SystemStatusData.class);

	public SystemStatusData(RpcServiceConfig config, ApplicationContext context) {
		this.config = config;
		this.context = context;
	}

	public void run() {
		while (true) {
			Integer interval = Integer.parseInt(config.getProperty(Config.SYSTEM_STATUS));
			try {
				RpcStatService rpcStatService = (RpcStatService) context.getBean("rpcStatService");
				String mapData = rpcStatService.getSystemStatus();
				logger.info(mapData);
				logger.info("device star data done,after " + interval + " minute(s) request again!");
				Thread.sleep(interval * 1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
