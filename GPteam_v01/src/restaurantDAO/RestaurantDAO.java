package restaurantDAO;

import static db.JdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.DTO_AD;

public class RestaurantDAO {

	DataSource ds;
	Connection con;
	private static RestaurantDAO RestaurantDAO;

	private RestaurantDAO() {
		// TODO Auto-generated constructor stub
	}

	public static RestaurantDAO getInstance(){
		if(RestaurantDAO == null){
			RestaurantDAO = new RestaurantDAO();
		}
		return RestaurantDAO;
	}

	public  void  setConnection(Connection con){
		this.con = con;
	}

	//議곌� 寃��깋�뿉 �빐�떦�븯�뒗 寃뚯떆臾쇱씠 紐뉕컻�엳�뒗吏� 
	public int selectListCount(DTO_AD dto) {
          System.out.println("selectListCount"+dto.getLocation());
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt=con.prepareStatement("select count(*) from board where type=? and price = ? and home like '%"+dto.getLocation()+"%'");
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getPrice());
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){

		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;

	}

	//議곌굔寃��깋�뿉 �빐�떦�븯�뒗 �젙蹂대�� 媛��졇�삤�뒗 �옉�뾽	
	public ArrayList<DTO_AD> selectArticleList(int page,int limit,DTO_AD dto){
        System.out.println("3踰� select");
        System.out.println("selectArticleList"+page+"limut="+limit+"dto="+dto.getLocation());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from restaurant where type=? and price = ? and home like '%"+dto.getLocation()+"%' limit ?,12";
		ArrayList<DTO_AD> articleList = new ArrayList<DTO_AD>();
		DTO_AD board = null;
		int startrow=(page-1)*10;
	
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, dto.getType());
			pstmt.setString(2, dto.getPrice());
					
			pstmt.setInt(3, startrow);
		
			rs = pstmt.executeQuery();
            
			while(rs.next()){
				
				board = new DTO_AD();
				board.setRnum(rs.getInt("rnum"));
				board.setName(rs.getString("name"));
				board.setCell(rs.getString("cell"));
				board.setEmail(rs.getString("email"));
				board.setType(rs.getString("type"));
				board.setStore(rs.getString("store"));
				board.setPrice(rs.getString("price"));
				board.setHome(rs.getString("home"));
				board.setDethome(rs.getString("dethome"));
				board.setFile(rs.getString("file"));
				board.setReadcount(rs.getInt("readcount"));
				System.out.println("select�쑝濡� 媛��졇�삩 readcount"+rs.getInt("readcount"));
				articleList.add(board);
			}
			
			

		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}
	
//public ArrayList<DTO_AD> selectTitleList(DTO_AD DTO_AD){
//        System.out.println("selectTtitleList 筌롫뗄�꺖占쎈굡 占쎌삂占쎈짗");
//        System.out.println(DTO_AD.getKeyword());
//        String key = DTO_AD.getKeyword();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String board_list_sql="select * from board where rtitle = ?";
//		ArrayList<DTO_AD> articleList = new ArrayList<DTO_AD>();
//		DTO_AD board = null;	
//		
//		try{
//			pstmt = con.prepareStatement(board_list_sql);
//			pstmt.setString(1,key);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				System.out.println(rs.getString("rtitle"));
//				board = new DTO_AD();
//				board.setRnum(rs.getInt("rnum"));
//				board.setNick(rs.getString("nick"));
//				board.setRtitle(rs.getString("rtitle"));
//				board.setRcontent(rs.getString("rcontent"));
//				board.setRfile(rs.getString("rfile"));
//				board.setLocation(rs.getString("location"));
//				board.setReadcount(rs.getInt("readcount"));
//				board.setDate(rs.getDate("date"));
//				articleList.add(board);
//			}
//			
//			
//
//		}catch(Exception ex){
//			System.out.println(ex);
//		}finally{
//			close(rs);
//			close(pstmt);
//		}
//
//		return articleList;
//
//	}
//
//public ArrayList<DTO_AD> selectIdList(DTO_AD DTO_AD){
//	 String key = DTO_AD.getKeyword();
//	PreparedStatement pstmt = null;
//	ResultSet rs = null;
//	String board_list_sql="select * from board where nick= ?";
//	ArrayList<DTO_AD> articleList = new ArrayList<DTO_AD>();
//	DTO_AD board = null;
//	
//
//	try{
//		pstmt = con.prepareStatement(board_list_sql);
//		pstmt.setString(1,key);
//		rs = pstmt.executeQuery();
//        
//		while(rs.next()){
//			
//			board = new DTO_AD();
//			board.setRnum(rs.getInt("rnum"));
//			board.setNick(rs.getString("nick"));
//			board.setRtitle(rs.getString("rtitle"));
//			board.setRcontent(rs.getString("rcontent"));
//			board.setRfile(rs.getString("rfile"));
//			board.setLocation(rs.getString("location"));
//			board.setReadcount(rs.getInt("readcount"));
//			board.setDate(rs.getDate("date"));
//			articleList.add(board);
//		}
//		
//		
//
//	}catch(Exception ex){
//		System.out.println(ex);
//	}finally{
//		close(rs);
//		close(pstmt);
//	}
//
//	return articleList;
//
//}
//
	//�꽑�깮�븳 �떇�떦 �젙蹂대�� �옄�꽭�엳 蹂댁뿬二쇰뒗 �옉�뾽
	public DTO_AD selectArticle(int rnum){
		System.out.println("4踰�"+rnum);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DTO_AD dto = null;

		try{
			System.out.println("5踰�");
			pstmt = con.prepareStatement(
					"select * from restaurant where rnum = ?");
			pstmt.setInt(1, rnum);
			rs= pstmt.executeQuery();
			if(rs.next()){
				System.out.println("if臾� �옉�룞");
				dto = new DTO_AD();
				dto.setRnum(rs.getInt("rnum"));
				dto.setName(rs.getString("name"));
				dto.setCell(rs.getString("cell"));
				dto.setEmail(rs.getString("email"));
				dto.setType(rs.getString("type"));
				dto.setStore(rs.getString("store"));
				dto.setPrice(rs.getString("price"));
				dto.setHome(rs.getString("home"));
				dto.setContent(rs.getString("content"));
				dto.setPostnum(rs.getString("postnum"));
				dto.setFile(rs.getString("file"));
				dto.setDethome(rs.getString("dethome"));
			}
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return dto;

	}

	//占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻�뜝占�.
	public int insertArticle(DTO_AD article){
        System.out.println("2"+article.getLocation());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(rnum) from restaurant");
			rs = pstmt.executeQuery();
		
			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into restaurant (id,name,cell,email,type,store,price,home,content,postnum,file,dethome,readcount) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("dfsdf");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getName());
			pstmt.setString(3, article.getCell());
			pstmt.setString(4, article.getEmail());
			pstmt.setString(5, article.getType());
			pstmt.setString(6, article.getStore());
			pstmt.setString(7, article.getPrice());
			pstmt.setString(8, article.getHome());
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getPostnum());
			pstmt.setString(11, article.getFile());
			pstmt.setString(12, article.getDethome());
			pstmt.setInt(13, 0);

			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	
//	public int updateArticle(DTO_AD article){
//        System.out.println("6踰�");
//		int updateCount = 0;
//		PreparedStatement pstmt = null;
//		String sql="update board set rtitle=?,rcontent=? where rnum=?";
//
//		try{
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, article.getRtitle());
//			pstmt.setString(2, article.getRcontent());
//			pstmt.setInt(3, article.getRnum());
//			updateCount = pstmt.executeUpdate();
//		}catch(Exception ex){
//			System.out.println(ex);
//		}finally{
//			close(pstmt);
//		}
//
//		return updateCount;
//
//	}

	//占쎈쐻占쎈짗占쎌굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲.
	public int deleteArticle(int board_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from board where rnum=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, board_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//由щ럭 媛� �삱�젮二쇨린
	public int updateReadCount(int rnum){
       
         System.out.println("3踰�");  
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update restaurant set readcount = "+
				"readcount+1 where rnum = "+rnum;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println(ex);
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	//占쎈쐻占쎈솙占쎈섣占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎌넇占쎈쐻占쎈짗占쎌굲.
	public boolean isArticleBoardWriter(int board_num,String pass){
        System.out.println("3踰�"+board_num+pass);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from board where rnum=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, board_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("rpass"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println(ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}
	

}
