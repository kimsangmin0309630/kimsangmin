package member;

import static db.JdbcUtil.*;
import java.sql.Connection;
import member.License_DAO;

public class License_service {
//����� ���̺� ���޹��� ���̵� �ִ��� Ȯ���ϱ����� License_DAO�������� �����ִ� ������
	public boolean isArticleWriter(String id) throws Exception {
		boolean isArticleWriter = false;
		Connection con = getConnection();
		License_DAO reviewDAO = License_DAO.getInstance();
		reviewDAO.setConnection(con);
		isArticleWriter = reviewDAO.isArticleBoardWriter(id);
		close(con);
		return isArticleWriter;

	}

}
