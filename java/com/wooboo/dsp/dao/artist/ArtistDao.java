package com.wooboo.dsp.dao.artist;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.wooboo.dsp.dao.base.BaseDao;
import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.statement.Statement;

@Repository
public class ArtistDao extends BaseDao{
	
	public void searchArtistInfo(ArtistInfo artistInfo,Page page){
		Statement stms = stmsFactory.createStatement(" from ArtistInfo  where 1 = 1 ");
		
		if(artistInfo != null){
			stms.append("and", "id", "=",artistInfo.getId());
			stms.appendLike(null, "and", "real_name", artistInfo.getReal_name());
		}
		
		stms.addOrderBy(" modify_time asc ");
        hibernateDao.search(stms, page);         
	}

	public List<ArtistInfo> queryArtistInfo(){
		Statement stms = stmsFactory.createStatement(" from ArtistInfo ");
		
		stms.addOrderBy(" modify_time asc ");
        return hibernateDao.query(stms);         
	}
	
}
