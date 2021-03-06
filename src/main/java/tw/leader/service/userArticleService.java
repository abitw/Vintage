package tw.leader.service;

import tw.leader.po.userArticle;
import tw.leader.vo.userArticleResp;

public interface userArticleService {

	public String getUserArticle(String email) throws Exception;
	
	public userArticleResp insertDescription(String email,String description);
	
	public userArticleResp insertArticle(String email,String articleContext);
	
	/*
	 * --------------------------------------
	 * 		BlogPage
	 * */
	public String findAllBlog() throws Exception;
	
	public String findBlogByName(String userName) throws Exception;
	
	
	/*
	 * ----------------------------------------
	 * 
	 * 		indexBlog
	 * */
	
	public String findIndexBlog() throws Exception;
}
