package net.joywise.bigdata.zid_rpc.thread;

import org.apache.log4j.Logger;

import net.joywise.bigdata.zid_rpc.RpcServiceConfig;

public class ConfigCheck implements Runnable {

	private Logger logger = Logger.getLogger(ConfigCheck.class);
	private RpcServiceConfig config = null;

	public ConfigCheck(RpcServiceConfig config) {
		this.config = config;
	}

	public void run() {
		Integer interval =  30;
		while (true) {
			try {
				config.reload();
				logger.info("RPC service config has been reloaded,after " + interval + " minute(s) reload again!");
				Thread.sleep(interval * 1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
