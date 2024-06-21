package global.util.chabun.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.job.vo.BoardJobVO;
//import a.b.c.com.kos.mem.vo.KosMemberVO;
//import a.b.c.com.kos.rboard.vo.KosRboardVO;

@Repository
public class KosChabunDAOImpl implements KosChabunDAO {
	Logger logger = LogManager.getLogger(KosChabunDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;

/*	@Override
	public KosMemberVO getKosMemberChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosMemberChabun 함수 진입 >>> : ");
		return sqlSession.selectOne("getKosMemberChabun"); // getKosMemberChabun
	}
*/
	@Override
	public BoardJobVO getKosBoardChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosBoardChabun 함수 진입 >>> : ");
		return sqlSession.selectOne("getKosBoardChabun"); // getKosBoardChabun
	}

/*	@Override
	public KosRboardVO getKosRboardChabun() {
		// TODO Auto-generated method stub
		logger.info("getKosRboardChabun 함수 진입 >>> : ");
		return sqlSession.selectOne("getKosRboardChabun"); // getKosRboardChabun
	}
*/
}
