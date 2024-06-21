package global.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class ChabunUtil {
	static Logger logger = LogManager.getLogger(ChabunUtil.class);
	
	
	public static final String BIZ_GUBUN_M 	= "M"; // 회원
	public static final String BIZ_GUBUN_B 	= "B"; // 게시판
	public static final String BIZ_GUBUN_RB = "RB"; // 게시판 댓글
	
	public static final String BIZ_GUBUN_P 	= "P"; // 상품
	public static final String BIZ_GUBUN_C 	= "C"; // 카트
	public static final String BIZ_GUBUN_O 	= "O"; // 주문
	
	
	// type : D : YYYYMMDD, M : YYYYMM, Y : YYYY, N : 
	public static String numPad(String t, String c){
	
		for (int i=c.length(); i < 4; i++) {
			c = "0" + c;
		}				
		String ymd = DateFormatUtil.ymdFormats(t);
		
		return ymd.concat(c);
	}
	
	 
	public static String getMemberChabun(String type, String num) {	
		logger.info("getMemberChabun >>> : " + type + " : " + num);
		
		return BIZ_GUBUN_M.concat(ChabunUtil.numPad(type, num));
	}
	
	public static String getBoardChabun(String type, String num) {
		logger.info("getBoardChabun >>> : " + type + " : " + num);
		
		return BIZ_GUBUN_B.concat(ChabunUtil.numPad(type, num));
	}
	
	public static String getRboardChabun(String type, String num) {
		logger.info("getRboardChabun >>> : " + type + " : " + num);
		
		return BIZ_GUBUN_RB.concat(ChabunUtil.numPad(type, num));
	}
	
	public static String getProductChabun(String type, String memNum) {		
		return BIZ_GUBUN_P.concat(ChabunUtil.numPad(type, memNum));
	}	
	public static String getCartChabun(String type, String memNum) {		
		return BIZ_GUBUN_C.concat(ChabunUtil.numPad(type, memNum));
	}
	public static String getOrderChabun(String type, String memNum) {		
		return BIZ_GUBUN_O.concat(ChabunUtil.numPad(type, memNum));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String c = "1";
		System.out.println(">>> : " + ChabunUtil.getRboardChabun("N", c));		
	}
}
