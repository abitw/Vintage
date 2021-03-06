package tw.leader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.leader.dao.userArticleDao;
import tw.leader.po.userArticle;
import tw.leader.vo.userArticleResp;

@Service
public class userArticleServiceImpl implements userArticleService{

	@Autowired
	private userArticleDao uADao;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public String getUserArticle(String email) throws Exception {
		List<userArticle> uList = uADao.selectUserArticle(email);
		String uJson = objectMapper.writeValueAsString(uList);
		System.out.println(uJson);
		return uJson;
	}
	
	@Override
	public userArticleResp insertDescription(String email,String description) {
		List<userArticle> result = uADao.selectUserArticle(email);
		if(result.isEmpty()) {
			userArticle aBean = new userArticle();
			aBean.setEmail(email);
			aBean.setDescription(description);
			
			uADao.save(aBean);
			userArticleResp insertMsg = new userArticleResp();
			insertMsg.setMessage("文章已新增");
			return insertMsg;
		}
		else {
			uADao.updateUserDescription(description, email);
			userArticleResp insertMsg = new userArticleResp();
			insertMsg.setMessage("文章已更新");
			return insertMsg;
		}
	}
	
	@Override
	public userArticleResp insertArticle(String email,String articleContext) {
		List<userArticle> result = uADao.selectUserArticle(email);
		if(result.isEmpty()) {
			userArticle aBean = new userArticle();
			aBean.setEmail(email);
			aBean.setArticleContext(articleContext);
			
			uADao.save(aBean);
			userArticleResp insertMsg = new userArticleResp();
			insertMsg.setMessage("文章已新增");
			return insertMsg;
		}
		else {
			uADao.updateUserArticle(articleContext, email);
			userArticleResp insertMsg = new userArticleResp();
			insertMsg.setMessage("文章已更新");
			return insertMsg;
		}
	}
	
	/*
	 *----------------------------------------------------
	 *	BlogPage
	 * */
	
	@Override
	public String findAllBlog() throws Exception {
		List<userArticle> uList = uADao.findAllBlog();
		String uJson = objectMapper.writeValueAsString(uList);
		System.out.println(uJson);
		return uJson;
	}
	
	@Override
	public String findBlogByName(String userName) throws Exception {
		List<userArticle> uList = uADao.findBlogByName(userName);
		String uJson = objectMapper.writeValueAsString(uList);
		System.out.println(uJson);
		return uJson;
	}
	
	/*
	 * -----------------------------------------------------
	 * 		indexBlog
	 * */
	
	@Override
	public String findIndexBlog() throws Exception {
		List<userArticle> uList = uADao.findIndexBlog();
		String uJson = objectMapper.writeValueAsString(uList);
		System.out.println(uJson);
		return uJson;
	}
	
	
	
}
