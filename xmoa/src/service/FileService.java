package service;

import java.text.SimpleDateFormat;
import java.util.Date;

import po.OaFile;
import util.PageUtil;
import dao.FileDao;

public class FileService {

	private FileDao fd = new FileDao();
	private PageUtil pu = new PageUtil();
	public PageUtil queryByPage(int page){
		return pu.queryByPage(page, fd);
	}
	public int insert(OaFile file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		file.setFlupdate(sdf.format(date));
		return fd.insert(file);
	}
}
