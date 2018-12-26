package com.sen.api.functions;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Function implements Function{

	@Override
	public String execute(String[] args) {
		try {
			String key = args[0];
			return DigestUtils.md5Hex(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getReferenceKey() {
		// TODO Auto-generated method stub
		return "md5";
	}

}
