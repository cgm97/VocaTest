package com.wp.toeic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/vocamanage/*")
public class VocaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VocaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//DB ����
		ToeicDAO dao = (ToeicDAO) session.getAttribute("dao");
		if (dao == null) {
			ServletContext context = getServletContext();
			dao = new ToeicDAO(
				context.getInitParameter("jdbc_driver"),
				context.getInitParameter("db_url"),
				context.getInitParameter("db_userid"),
				context.getInitParameter("db_passwd")
			);
			session.setAttribute("dao", dao);
		}
				
		String pathInfo = request.getPathInfo();
		String action = request.getParameter("action");
		String viewName = null;
		
		//�α����� �׽�Ʈ �� ���� ����
		if (action != null) {//�α��� ���� ��
			if (action.equals("voca_info")) {
				//�α��� ���̵� ��� �޾� ����
				String ID = request.getParameter("id");
				String PW = request.getParameter("pw");
				try {
					//�α��� �� ����� �̸� �Ҹ���
					String user = (String) dao.login(ID, PW);
					if(user!=null) { //�α��� ����
						session.setAttribute("id",user);
						viewName = "/views/voca_info.jsp";
						//�α����� ���̵� ���� ����
						session.setAttribute("username",user);	
					}
					else {//�α��ν��� ##### �����̷����ؾ��� ���濹��
						viewName = "/views/start.jsp";
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
			//ȸ������ ȭ��
			else if(action.equals("insert")) {
				viewName = "/views/user_insert.jsp";
			}
			//ȸ������ ó��
			else if(action.equals("insert_process")) {
				//�Էµ� �� �޾ƿ���
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				//�� DTO �ѱ�
				ToeicDTO dto = new ToeicDTO();
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				
				try {//db ���� �� ó��
					dao.insertuser(dto);
					//ȸ������ ����
					viewName = "/views/start.jsp";
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//ȸ������ ����
					viewName = "/views/user_insert.jsp";
				}
			}
			//�׽�Ʈ ���� �� ���� 1~3��
			else if(action.equals("voca_test1")) {	
				try {
					for(int i=1;i<=3;i++) {
						ToeicDTO matter = dao.research(i);
						request.setAttribute("matter"+i, matter);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				session.setAttribute("check", "test1.jsp");
				viewName = "/views/voca_test.jsp";
			}//�׽�Ʈ ���� �� ���� 4~6��
			else if(action.equals("voca_test2")) {
				try {
					for(int i=4;i<=6;i++) {
						ToeicDTO matter = dao.research(i);
						request.setAttribute("matter"+i, matter);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				viewName = "/views/voca_test.jsp";
				session.setAttribute("check", "test2.jsp");
			}//�׽�Ʈ ���� �� ���� 7~9��
			else if(action.equals("voca_test3")) {
				try {
					for(int i=7;i<=9;i++) {
						ToeicDTO matter = dao.research(i);
						request.setAttribute("matter"+i, matter);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				viewName = "/views/voca_test.jsp";
				session.setAttribute("check", "test3.jsp");
			}//�׽�Ʈ ���� �� ���� 10~12��
			else if(action.equals("voca_test4")) {
				try {
					for(int i=10;i<=12;i++) {
						ToeicDTO matter = dao.research(i);
						request.setAttribute("matter"+i, matter);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				viewName = "/views/voca_test.jsp";
				session.setAttribute("check", "test4.jsp");
			}//�׽�Ʈ ���� �� ���� 13~15��
			else if(action.equals("voca_test5")) {
				try {
					for(int i=13;i<=15;i++) {
						ToeicDTO matter = dao.research(i);
						request.setAttribute("matter"+i, matter);
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				viewName = "/views/voca_test.jsp";
				session.setAttribute("check", "test5.jsp");
			}
		}
		
		if (viewName != null) {
		RequestDispatcher view = request.getRequestDispatcher(viewName);
		view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
