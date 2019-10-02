 package com.bdp.ex;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

public class Assert {

    private static Logger logger = LoggerFactory.getLogger(Assert.class);

	public static void isTrue(boolean flag, String message) throws Exception {
		if(!flag) {
			throw new Exception(message);
		}
	}
}
