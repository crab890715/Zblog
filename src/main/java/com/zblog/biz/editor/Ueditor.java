package com.zblog.biz.editor;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.zblog.biz.UploadManager;
import com.zblog.core.WebConstants;
import com.zblog.core.dal.entity.Upload;
import com.zblog.web.support.ServletRequestReader;
import com.zblog.web.support.WebContextFactory;

/**
 * ueditor上传参数见:http://fex-team.github.io/ueditor/#dev-request_specification
 * 
 * @author zhou
 * 
 */
@Component
public class Ueditor {
	@Autowired
	private UploadManager uploadManager;

	public Map<String, Object> server(ServletRequestReader reader) {
		String action = reader.getAsString("action");

		Map<String, Object> result = null;
		if ("config".equals(action)) {
			result = config();
		} else if ("uploadimage".equals(action)) {
			result = uploadImage(reader);
		} else if ("listimage".equals(action)) {

		} else if ("uploadfile".equals(action)) {

		} else {
			result = new HashMap<String, Object>();
			result.put("state", "SUCCESS");
		}

		return result;
	}

	private Map<String, Object> config() {
		Map<String, Object> config = new HashMap<String, Object>();
		/* 上传图片配置项 */
		config.put("imageActionName", "uploadimage");
		config.put("imageFieldName", "upfile");
		config.put("imageMaxSize", 2048000);
		config.put("imageUrlPrefix", "");
		config.put("imageAllowFiles", Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp"));

		/* 上传文件配置 */
		config.put("fileActionName", "uploadfile");
		config.put("fileFieldName", "upfile");
		config.put("fileMaxSize", 51200000);
		config.put("fileAllowFiles",
				Arrays.asList(".png", ".jpg", ".jpeg", ".gif", ".bmp", ".zip", ".tar", ".gz", ".7z"));

		/* 上传视频配置 */
		config.put("videoActionName", "uploadvideo");
		config.put("videoFieldName", "upfile");
		config.put("videoMaxSize", 102400000);
		config.put("videoAllowFiles", Arrays.asList(".flv", ".swf", ".mkv", ".avi", ".rmvb", ".mpeg", ".mpg", ".mov",
				".wmv", ".mp4", ".webm"));

		/* 列出指定目录下的文件 */
		config.put("fileManagerActionName", "listfile");

		/* 列出指定目录下的图片 */
		config.put("imageManagerActionName", "listimage");

		return config;
	}

	public Map<String, Object> uploadImage(ServletRequestReader reader) {
		MultipartFile file = reader.getFile("upfile");
		Upload upload = null;
		try (InputStream in = file.getInputStream()) {
			upload = uploadManager.insertUpload(new InputStreamResource(in), new Date(), file.getOriginalFilename(),
					WebContextFactory.get().getUser().getId());
		} catch (Exception e) {
			e.printStackTrace();
			upload = null;
		}

		if (upload == null) {
			return new HashMap<String, Object>() {
				/**
				* 
				*/
				private static final long serialVersionUID = 6199410812253672130L;

				{
					put("state", "文件上传失败");
				}
			};
		}

		Map<String, Object> mc = new HashMap<String, Object>() {
			/**
			* 
			*/
			private static final long serialVersionUID = -4790306616232287609L;

			{
				put("state", "SUCCESS");
			}
		};
		mc.put("original", upload.getName());
		mc.put("title", upload.getName());
		mc.put("url", WebConstants.getDomain() + upload.getPath());

		return mc;
	}

}
