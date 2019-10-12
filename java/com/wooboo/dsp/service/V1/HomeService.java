package com.wooboo.dsp.service.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.dao.V1.HomeDao;
import com.wooboo.dsp.model.Sales;
import com.wooboo.dsp.service.base.BaseService;

@Service
public class HomeService extends BaseService{
	
	private static Logger log = Logger.getLogger(HomeService.class);
	
	@Autowired
	private HomeDao homeDao;
	
	public List<Sales> home(){
		return homeDao.home();
	}
	

}
