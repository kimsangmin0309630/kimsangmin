package restaurant_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import review_svc.ReviewListService;
import vo.ActionForward;
import vo.ReviewBean;
import vo.PageInfo;
import action.Action;

 public class ReviewSearchAction implements Action {
	  
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		  System.out.println("2�� ReviewSearchAction ������ ");
		  
		    ReviewBean reviewBean = new ReviewBean();
		    String search = (String) request.getParameter("searchvalues");
			String keyword = (String) request.getParameter("keyword");
			
		  System.out.println(search);
		   reviewBean.setSearch(search);
		   reviewBean.setKeyword(keyword);
 
		ArrayList<ReviewBean> articleList=new ArrayList<ReviewBean>();
		ReviewListService reviewListService = new ReviewListService();
	
		
		if(search.equals("title")) {
		System.out.println("tite Ȯ��");
		articleList = reviewListService.getTitleList(reviewBean);
		System.out.println("reviewsearchaction���������� ���Ϲ��� �� Ȯ��="+articleList.get(0).getRtitle());
		}
		else{
		articleList = reviewListService.getidList(reviewBean);
			
			
		}

		request.setAttribute("searchList", articleList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/board1.jsp");
   		return forward;
   		
	 }
	 
 }