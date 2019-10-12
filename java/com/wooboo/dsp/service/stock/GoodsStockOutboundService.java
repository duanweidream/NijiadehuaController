package com.wooboo.dsp.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wooboo.dsp.dao.stock.GoodsStockOutboundDao;
import com.wooboo.dsp.model.GoodsStockOutbound;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;

@Service
public class GoodsStockOutboundService extends BaseService {

	@Autowired
	private GoodsStockOutboundDao goodsStockOutboundDao;

	public void searchGoodsStockOutbound(GoodsStockOutbound goodsStockOutbound,Page page) {
		goodsStockOutboundDao.searchGoodsStockOutbound(goodsStockOutbound, page);
	}

}
