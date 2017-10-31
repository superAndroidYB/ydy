package com.ydy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDFiveUtils {
	
	 /**
	   * MD5加密
	   * @param str String
	   * @return String
	   * @throws NoSuchAlgorithmException
	   */
	  public static synchronized String encrypt(String str) {
	    if ( str == null ) {
	      return null;
	    }
	    try {
	      MessageDigest alga = MessageDigest.getInstance("md5");
	      alga.update(str.getBytes());
	      byte[] digesta = alga.digest();

	      return byte2hex(digesta);
	    }
	    catch(NoSuchAlgorithmException e )
	    {
	      e.printStackTrace();
	      return null;
	    }
	  }

	  /**
	   * 二行制转字符串
	   * @param b byte[]
	   * @return String
	   */
	  public static String byte2hex(byte[] b) {
	    String sHexStr = "";
	    String sTmp = "";
	    for (int n = 0; n < b.length; n++) {
	      sTmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
	      if (sTmp.length() == 1) {
	        sHexStr = sHexStr + "0" + sTmp;
	      } else {
	        sHexStr = sHexStr + sTmp;
	      }
	      if (n < b.length - 1) {
	        sHexStr = sHexStr;
	      }
	    }
	    return sHexStr.toLowerCase();
	  }

}
