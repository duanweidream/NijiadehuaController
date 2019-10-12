package com.wooboo.dsp.web.controller.artist;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.service.artist.ArtistService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;

@Controller
@RequestMapping(value="/artist")
public class ArtistController{
	
	@Autowired
	private ArtistService artistService;
	
	/**
	 * 币种资料->币种列表
	 *
	 * @return
	 */
	@RequestMapping(value="/search")
    public ModelAndView search(ArtistInfo artistInfo){
		ModelAndView model = new ModelAndView("/artist/search_artist");
		Page page = PagerFactory.createPage();
		artistService.searchArtistInfo(artistInfo, page);
		model.addObject("artistInfo",artistInfo);
		model.addObject(ATTPage.PAGE_LIST,page);
    	return model;
    }
	
	/**
	 * 币种资料->新建币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/to")
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/artist/add_artist");
		return model;
	}

	/**
	 * 币种资料->保存币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add/su")
	@ResponseBody
	public Result addSu(ArtistInfo artistInfo) {

		try {

			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();
			
			artistInfo.setCreate_time(currentTime);
			artistInfo.setCreator(uid);
			artistInfo.setModifyor(uid);
			artistInfo.setModify_time(currentTime);

			artistService.saveArtistInfo(artistInfo);

		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}

	/**
	 * 币种资料->修改币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modify/to")
	public ModelAndView toModify(Long id) {
		ModelAndView model = new ModelAndView("/artist/modify_artist");
		model.addObject("artistInfo", artistService.getArtistInfo(id));
		return model;
	}

	/**
	 * 币种资料->修改保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modify/su")
	@ResponseBody
	public Result modifySu(ArtistInfo artistInfo) {
		try {
			Date currentTime = new Date();
			Long uid = SessionHelper.getUserId();

			artistInfo.setModifyor(uid);
			artistInfo.setModify_time(currentTime);

			artistService.modifyArtistInfo(artistInfo);

		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}

	/**
	 * 币种资料->删除币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/remove/su")
	@ResponseBody
	public Result remove(Long id) {
		try {
			artistService.removeArtistInfo(id);
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
	
}
