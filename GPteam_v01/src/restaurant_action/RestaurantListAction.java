package restaurant_action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import restaurant_svc.RestaurantListService;
import vo.ActionForward;
import vo.DTO_AD;
import vo.PageInfo;
import action.Action;

 public class RestaurantListAction implements Action {
	  
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		  System.out.println("가격="+request.getParameter("price"));
        DTO_AD dto = new DTO_AD();
        dto.setPrice(request.getParameter("price"));
        dto.setType(request.getParameter("type"));
        dto.setLocation(request.getParameter("location"));
		ArrayList<DTO_AD> articleList=new ArrayList<DTO_AD>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("page")==null){
			page=1;
		}
		
		RestaurantListService restaurantListService = new RestaurantListService();
		int listCount=restaurantListService.getListCount(dto);
	
			articleList = restaurantListService.getArticleList(page,limit,dto);
			System.out.println(articleList.get(0).getReadcount());
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		ActionForward forward= new ActionForward();
   		forward.setPath("/searchpage.jsp");
   		return forward;
   		
	 }
	 
 }