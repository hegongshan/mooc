package com.hegongshan.mooc.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

public class FileUtils {
	private static final String ACCESS_KEY = PropertiesUtils.getProperties("qiniu.accessKey");
	private static final String SECERT_KEY = PropertiesUtils.getProperties("qiniu.secertKey");
	private static final String BUCKET = PropertiesUtils.getProperties("qiniu.bucket");

	public static String uploadVideo(MultipartFile video) throws IOException {
		Configuration cfg = new Configuration(Zone.zone0());

		Auth auth = Auth.create(ACCESS_KEY, SECERT_KEY);
		String upToken = auth.uploadToken(BUCKET);
		String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), BUCKET).toString();
		// 设置断点续传文件进度保存目录
		FileRecorder fileRecorder = new FileRecorder(localTempDir);
		UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
		
		String filePrefix = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/static/video/");
		String fileSuffix = video.getOriginalFilename().substring(video.getOriginalFilename().lastIndexOf("."));
		String fileName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
		String absoluteFilePath = filePrefix + fileName + fileSuffix;
		
		File serverFile = new File(absoluteFilePath);
		if (!serverFile.getParentFile().exists()) {
			serverFile.getParentFile().mkdirs();
		}
		video.transferTo(serverFile);
		
		String url = PropertiesUtils.getProperties("qiniu.domain")+"/mooc/video/"+fileName+fileSuffix;
		String key = "mooc/video/"+fileName+fileSuffix;
		Response response = uploadManager.put(absoluteFilePath, key, upToken);

		serverFile.delete();
		
		// 解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("hash", putRet.hash);
		resultMap.put("key", putRet.key);
		System.out.println(resultMap.get("hash"));
		
		return url;
		
	}
	
	public static String uploadResource(MultipartFile resource) throws IOException {
		Configuration cfg = new Configuration(Zone.zone0());

		Auth auth = Auth.create(ACCESS_KEY, SECERT_KEY);
		String upToken = auth.uploadToken(BUCKET);
		String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), BUCKET).toString();
		// 设置断点续传文件进度保存目录
		FileRecorder fileRecorder = new FileRecorder(localTempDir);
		UploadManager uploadManager = new UploadManager(cfg, fileRecorder);
		
		String filePrefix = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/static/video/");
		String fileSuffix = resource.getOriginalFilename().substring(resource.getOriginalFilename().lastIndexOf("."));
		String fileName = DateUtils.format(new Date(), "yyyyMMddHHmmssSSS");
		String absoluteFilePath = filePrefix + fileName + fileSuffix;
		
		File serverFile = new File(absoluteFilePath);
		if (!serverFile.getParentFile().exists()) {
			serverFile.getParentFile().mkdirs();
		}
		resource.transferTo(serverFile);
		
		String url = PropertiesUtils.getProperties("qiniu.domain")+"/mooc/resource/"+fileName+fileSuffix;
		String key = "mooc/resource/"+fileName+fileSuffix;
		Response response = uploadManager.put(absoluteFilePath, key, upToken);

		serverFile.delete();
		
		// 解析上传成功的结果
		DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("hash", putRet.hash);
		resultMap.put("key", putRet.key);
		System.out.println(resultMap.get("hash"));
		
		return url;
		
	}
	
	public static String uploadImage(MultipartFile image) throws IllegalStateException, IOException {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		ServletContext application = webApplicationContext.getServletContext();
		String filePrefix = application.getRealPath("/");
		String fileSuffix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
		Date date = new Date(System.currentTimeMillis());
		String absoluteFilePath = filePrefix + "static" + File.separator + "images" + File.separator + "admin"
				+ File.separator + DateUtils.format(date, "yyyyMMddHHmmssSSS") + "." + fileSuffix;
		String relativeFilePath = application.getContextPath() + "/static/images/admin/"
				+ DateUtils.format(date, "yyyyMMddHHmmssSSS") + "." + fileSuffix;
		File file = new File(absoluteFilePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		image.transferTo(new File(absoluteFilePath));
		return relativeFilePath;
	}
}
