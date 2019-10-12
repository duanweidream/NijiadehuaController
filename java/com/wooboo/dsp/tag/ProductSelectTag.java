package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.model.GoodsProductInfo;
import com.wooboo.dsp.service.stock.GoodsProductService;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import java.util.List;

import javax.servlet.jsp.JspException;


public class ProductSelectTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;
	
	private GoodsProductService goodsProductService;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
		pageContext.getOut().write(html());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
	public String html(){
		goodsProductService = SpringUtil.getBean("goodsProductService",GoodsProductService.class);
		StringBuffer sbuf = new StringBuffer();
		
		List<GoodsProductInfo> list =   goodsProductService.queryGoodsProductInfo();
		for(GoodsProductInfo product : list){
			sbuf.append("<option value=\""+product.getProduct_id()+"\"  "+(StringUtil.equals(product.getProduct_id(),productId)?"selected":"")+"   >"+product.getProduct_name()+" </option>");

		}
		return sbuf.toString();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
	


	
	
	
}
