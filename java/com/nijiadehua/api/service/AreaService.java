package com.nijiadehua.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.area.response.AreaResponse;
import com.nijiadehua.api.exception.ServiceException;

/**
 * ClassName:FlowerInfoService</br> Function:  地域信息 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2020-01-16 上午10:09:34</br>
 * 
 */
@Service
public class AreaService {
	
	private static Logger log = Logger.getLogger(AreaService.class);
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	
	public List<AreaResponse> queryAreaList() throws ServiceException{
		try {
			Sql sql = new Sql(" select * from art_area ORDER BY area_id ");
			List<AreaResponse> areas =  jdbcTemplate.queryForList(sql,AreaResponse.class);
			if(areas.size() == 0) {
				throw new ServiceException("数据为空");
			}
			
			//中国
			List<AreaResponse> chinas = getChinaList(areas,0);
			//省
			for(AreaResponse china : chinas) {
				
				//附加省
				List<AreaResponse> provinces = getChinaList(areas,china.getArea_id());
				china.setChild(provinces);
				
				for(AreaResponse provice : provinces) {
					
					//附加市
					List<AreaResponse> citys = getChinaList(areas,provice.getArea_id());	
					provice.setChild(citys);
					
					for(AreaResponse city : citys) {
						List<AreaResponse> countys = getChinaList(areas,city.getArea_id());
						city.setChild(countys);
					}
					
				}
			}
			return chinas;
		}catch(Exception e) {
			log.logError("地域信息查询失败",e);
			throw new ServiceException("地域信息查询失败："+e.getMessage());
		}
		
	}
	
	
    public static List<AreaResponse> getChinaList(List<AreaResponse> allChina, Integer condition) {
        List<AreaResponse> resChinaList = new ArrayList<AreaResponse>();
        for (AreaResponse china : allChina) {
            if (china.getParent_id() == condition) {
                resChinaList.add(china);
            }
        }
        return resChinaList;
    }

	
	
}
