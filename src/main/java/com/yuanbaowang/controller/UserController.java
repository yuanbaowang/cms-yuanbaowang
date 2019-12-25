/**
 * 
 */
package com.yuanbaowang.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yuanbaowang.bean.Article;
import com.yuanbaowang.bean.Category;
import com.yuanbaowang.bean.Channel;
import com.yuanbaowang.bean.User;
import com.yuanbaowang.common.CmsContant;
import com.yuanbaowang.service.ArticleService;
import com.yuanbaowang.service.UserService;

import yuanbaowang_cms_utils.FileUtils;
import yuanbaowang_cms_utils.StringUtils;



/**
 * @author 袁保旺
 *
 * 2019年12月10日 上午11:02:32 
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ArticleService aService;

	
	/**
	 * 	跳转到普通用户页面
	 */
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public String list(Model m) {
		m.addAttribute("u",new User());
		return "user/list";
	}
	
	
	/**
	 * 	跳转到注册页面
	 */
	@RequestMapping(value = "register",method = RequestMethod.GET)
	public String register(Model m) {
		m.addAttribute("u",new User());
		return "user/register";
	}
	
	/**
	 * 	接收注册页面发送过来的请求
	 */
	@RequestMapping(value = "register",method = RequestMethod.POST)
	public String register(Model m,@Valid @ModelAttribute("u") User u,BindingResult result ) {
		//如果有问题返回注册页面
		if(result.hasErrors()) {
			return "user/register";
		}
		//自定义密码错误
		if(StringUtils.isNumber(u.getPassword())) {
			result.rejectValue("password", "", "密码太过简单，请重新输入！");
			return "user/register";
		}
		//唯一验证 判断用户名是否重名
		User user = service.getUserByUsername(u.getUsername());
		if(user != null) {
			result.rejectValue("username", "", "用户名已重复，请重新输入！");

		}
		//进行注册
		int i =service.register(u);
		if(i == 0) {
			m.addAttribute("error","注册失败，请稍后再试！");
			return "user/register";
		}
		System.out.println(i+"注册成功！");
		//注册成功 跳转登录页面
		return "user/login";
	}
	
	/**
	 * 	立即检查用户名是否重复
	 */
	@ResponseBody
	@RequestMapping("checkName")
	public boolean checkUserName(String username) {
		User user = service.getUserByUsername(username);
		return user == null;
	}
	
	/**
	 * 	跳转到登录页面
	 */
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(Model m) {
		return "user/login";
	}
	
	/**
	 * 	接收登录界面发送过来的请求
	 */
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response, User u) {
		//取明文密码
		String pwd = u.getPassword();
		User user = service.login(u);
		//登录失败
		if(user == null) {
			request.getSession().setAttribute("error", "用户名或密码错误！");
			return "user/login";
		}
		//登录成功 存到session中
		request.getSession().setAttribute(CmsContant.USER_KEY, user);
	
		if(u.getMdl() == 1) {
			//将用户信息保存到cookie中
			Cookie cookieUserName = new Cookie("username", u.getUsername());
			cookieUserName.setPath("/");
			//设置过期时间
			cookieUserName.setMaxAge(10*24*3600);
			Cookie cookieUserPwd = new Cookie("userpwd",pwd );
			cookieUserPwd.setPath("/");
			//设置过期时间
			cookieUserPwd.setMaxAge(10*24*3600);
			//返回到客户端
			response.addCookie(cookieUserPwd);
			response.addCookie(cookieUserName);
		}
		
		//跳转管理员页面
		if(user.getRole() == CmsContant.USER_ROLE_ADMIN) {
			return "redirect:/admin/index";
		}
		//登录成功 跳转到个人中心
		return "user/list";
	}
	
	/**
	 * 	返回我的文章
	 */
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue = "1") int pageNum) {
		//从session域中获取到登录的user 对象的信息
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		//返回分页和查询的的数据
		PageInfo<Article> article = aService.listByUser(user.getId(),pageNum);
		//存到session 域中
		request.getSession().setAttribute("articlePage", article);
		return "user/articles/list";
	}
	
	/**
	 * 	返回我的评论
	 */
	@RequestMapping("comment")
	public String comment() {
		return "user/comment/list";
	}
	
	/**
	 * 	删除文章
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(int id) {
		int i = aService.delArticle(id);
		return i > 0;
	}
	
	/**
	 * 	跳转到发布文章的页面
	 */
	@RequestMapping("postArticle")
	public String postArticle(HttpServletRequest request) {
		//查询所有channel对象
		List<Channel> list = aService.channelList();
		request.getSession().setAttribute("channelList", list);
		return "user/articles/post";
	}
	
	
	/**
	 * 	根据栏目id 获取所有的分类对象  跳转到post页面
	 */
	@RequestMapping("getCategoris")
	@ResponseBody
	public List<Category> getCategoris(int cid) {
		//查询所有channel对象
		List<Category> list = aService.getCateGorisByCid(cid);
		return list;
	}
	
	/**
	 * 	接收jsp发布请求 对数据进行添加
	 */
	
	@RequestMapping(value = "postArticle",method = RequestMethod.POST)
	@ResponseBody
	public boolean postArticleAdd(HttpServletRequest request,Article article,MultipartFile file) {
		String picUrl = "";
		//调用处理上传文件方法
		try {
			picUrl = processFile(file);
			article.setPicture(picUrl);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取登录人的user_id
	
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUser_id(user.getId());
		int i =  aService.add(article); 
		return i > 0 ;
	}
	
	/**
	 * 	进行处理上传文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {
		
		if(file.isEmpty()) {
			return "";
		}
		
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		//图片存放的路径
		File path = new File(picRootPath+"/"+subPath);
		//判断目标目录时间是否存在
		if(!path.exists()) {
			//路径不存在创建
			path.mkdirs();
		}
		//计算文件的扩展名
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		//使用UUid生成随机文件名
		String fileName = UUID.randomUUID().toString()+suffixName;
		//把文件另存到新的文件夹中
		file.transferTo(new File(picRootPath+"/"+subPath+"/"+fileName));
		return subPath+"/"+fileName;
	}
	
	
	/**
	 * 	跳转到修改页面
	 */
	@RequestMapping(value = "updateArticle",method = RequestMethod.GET)
	public String updateArticle(HttpServletRequest request,int id) {
		//查询所有channel对象
		List<Channel> list = aService.channelList();
		request.getSession().setAttribute("channelList", list);
		//获取修改的文章
		Article article = aService.getById(id);
		System.out.println(article);
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(article.getUser_id() != user.getId()) {
			//不是同一个 用户 做异常处理
			
		}
		String content = StringUtils.htmlspecialchars(article.getContent());
		request.getSession().setAttribute("article", article);
		request.getSession().setAttribute("content", content);
		return "user/articles/upd";
	}
	
	
	/**
	 * 	接收修改文章页面传输过来的数据
	 */
	@RequestMapping(value = "updateArticle",method = RequestMethod.POST)
	@ResponseBody
	public boolean updateArticle(HttpServletRequest request,Article article,MultipartFile file) {
		//调用处理上传文件方法
		try {
			//判断是否修改图片
			String picUrl = processFile(file);
			if(StringUtils.haveValue(picUrl)) {
				article.setPicture(picUrl);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取登录人的user_id
		User user = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUser_id(user.getId());
		int i = aService.upd(article,user.getId());
		return i > 0 ;
	}
	
	/**
	 * 	注销
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().removeAttribute(CmsContant.USER_KEY);
		return "index";
	}
	
}
