package net.joywise.bigdata.zid_rpc.thread;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import info.zznet.zud.rpc.service.RpcPolicyService;
import net.joywise.bigdata.zid_rpc.RpcServiceConfig;
import net.joywise.bigdata.zid_rpc.util.Config;

public class DeviceStarData implements Runnable {
	private RpcServiceConfig config;
	private ApplicationContext context;
	private Logger logger = Logger.getLogger(DeviceStarData.class);

	public DeviceStarData(RpcServiceConfig config, ApplicationContext context) {
		this.config = config;
		this.context = context;
	}

	public void run() {
		while (true) {
			Integer interval = Integer.parseInt(config.getProperty(Config.DEVICE_STAR_MAP_DATA));
			try {
				RpcPolicyService rpcPolicyService = (RpcPolicyService) context.getBean("rpcPolicyService");
				@SuppressWarnings("rawtypes")
				List mapData = rpcPolicyService.getDeviceStarMapData();
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
