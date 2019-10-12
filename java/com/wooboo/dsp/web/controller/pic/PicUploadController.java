package com.wooboo.dsp.web.controller.pic;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wooboo.dsp.base.ftp.FolderManager;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.system.util.ApiError;

/**
 * 类名称：OppoFtpUploadController 类描述：OPPO文件上传控制中心
 * 
 * @version 1.0
 * @auther zhangxiaofei
 * @datetime 2019-08-23 15:23:42 Copyright © 2019 哇棒技术 All Rights Reserved
 */
@Controller
@RequestMapping("/pic")
public class PicUploadController {

	private static Logger logger = Logger.getLogger(PicUploadController.class);

	/**
	 * 方法：savePicture 描述：FTP上传图片、视频
	 * 
	 * @param paramFile
	 *            上传的图片对象
	 * @param request
	 *            请求对象
	 * @param response
	 *            响应对象
	 * @return
	 * @auther zhangxiaofei
	 * @version 1.0
	 * @datetime 2019-08-23 15:28:01 Copyright © 2019 哇棒技术 All Rights Reserved
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Result savePicture(
			@RequestParam("pictureFile") MultipartFile paramFile,
			HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		try {
			// 得到上传时的文件名
			String fileName = paramFile.getOriginalFilename();
			int pos = fileName.lastIndexOf(".");
			// 得到文件类型
			String fileType = fileName.substring(pos + 1, fileName.length());

			List<String> setType = Arrays.asList("jpg", "gif", "png");
			if (!setType.contains(fileType.toLowerCase())) {
				return new Result(
						ApiError.Type.SERVICE_EXCEPTION.toException("不允许的文件类型"));
			}

			CommonsMultipartFile cf = (CommonsMultipartFile) paramFile;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File file = fi.getStoreLocation();
			String file_url = FolderManager.uploadFile(file, fileName);

			JSONObject json = new JSONObject();
			json.put("fileSavePath", file_url);
			result.setData(json);
		} catch (Exception e) {
			logger.logError("PicUploadController - savePicture()方法 上传失败", e);
			e.printStackTrace();
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException("上传文件失败"));
		}

		return result;
	}
}
