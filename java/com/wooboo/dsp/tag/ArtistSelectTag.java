package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.service.artist.ArtistService;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import java.util.List;

import javax.servlet.jsp.JspException;


public class ArtistSelectTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long artistId;
	
	private ArtistService artistService;
	
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
		artistService = SpringUtil.getBean("artistService",ArtistService.class);
		StringBuffer sbuf = new StringBuffer();
		
		List<ArtistInfo> list =   artistService.queryArtistInfo();
		for(ArtistInfo product : list){
			sbuf.append("<option value=\""+product.getId()+"\"  "+(StringUtil.equals(product.getId(),artistId)?"selected":"")+"   >"+product.getArtist_name()+" </option>");

		}
		return sbuf.toString();
	}

	public Long getArtistId() {
		return artistId;
	}

	public void setArtistId(Long artistId) {
		this.artistId = artistId;
	}

	
	
}
