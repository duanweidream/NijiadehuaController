package com.wooboo.dsp.service.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wooboo.dsp.dao.stock.GoodsProductDao;
import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.Page.Page;

@Service
public class GoodsProductService extends BaseService {

	@Autowired
	private GoodsProductDao goodsProductDao;

	public void searchGoodsProductInfo(GoodsProductInfo goodsProductInfo, Page page) {
		goodsProductDao.searchGoodsProductInfo(goodsProductInfo, page);
	}

	public List<GoodsProductInfo> queryGoodsProductInfo(){
		return goodsProductDao.queryGoodsProductInfo();
	}
	
	public void saveGoodsProductInfo(GoodsProductInfo goodsProductInfo) {
		goodsProductInfo.setTotal_stock(0L);
		goodsProductInfo.setPre_stock(0L);
		goodsProductInfo.setRel_stock(0L);
		goodsProductInfo.setAva_stock(0L);
		goodsProductDao.saveObject(goodsProductInfo);
	}

	public GoodsProductInfo getGoodsProductInfo(Long id) {

		return goodsProductDao.getObject(id, GoodsProductInfo.class);

	}

	public void modifyGoodsProductInfo(GoodsProductInfo goodsProductInfo) {

		goodsProductDao.updateObject(goodsProductInfo);

	}

	public void removeGoodsProductInfo(Long id) {

		GoodsProductInfo goodsProductInfo = goodsProductDao.getObject(id, GoodsProductInfo.class);
		goodsProductDao.deleteObject(goodsProductInfo);

	}

}
