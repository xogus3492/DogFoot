package global.util.chabun.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import domain.job.vo.BoardJobVO;
//import a.b.c.com.kos.mem.vo.KosMemberVO;
//import a.b.c.com.kos.rboard.vo.KosRboardVO;
import global.util.chabun.dao.KosChabunDAO;

@Service
@Transactional
public class KosChabunServiceImpl implements KosChabunService {
	Logger logger = LogManager.getLogger(KosChabunServiceImpl.class);

	@Autowired(required=false)
	private KosChabunDAO kosChabunDAO;
	
/*	@Override
	public KosMemberVO getKosMemberChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosMemberChabun 함수 진입 >>> : ");
		return kosChabunDAO.getKosMemberChabun();
	}
*/
	
	@Override
	public BoardJobVO getKosBoardChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosBoardChabun 함수 진입 >>> : ");
		return kosChabunDAO.getKosBoardChabun();
	}

	
/*	@Override
	public KosRboardVO getKosRboardChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosRboardChabun 함수 진입 >>> : ");
		return kosChabunDAO.getKosRboardChabun();
	}
*/
}
