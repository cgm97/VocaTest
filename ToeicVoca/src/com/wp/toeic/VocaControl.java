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
		
		//DB 설정
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
		
		//로그인후 테스트 및 점수 선택
		if (action != null) {//로그인 성공 후
			if (action.equals("voca_info")) {
				//로그인 아이디 비번 받아 저장
				String ID = request.getParameter("id");
				String PW = request.getParameter("pw");
				try {
					//로그인 후 사용자 이름 불리옴
					String user = (String) dao.login(ID, PW);
					if(user!=null) { //로그인 성공
						session.setAttribute("id",user);
						viewName = "/views/voca_info.jsp";
						//로그인한 아이디 세션 저장
						session.setAttribute("username",user);	
					}
					else {//로그인실패 ##### 리다이랙션해야함 변경예정
						viewName = "/views/start.jsp";
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
			//회원가입 화면
			else if(action.equals("insert")) {
				viewName = "/views/user_insert.jsp";
			}
			//회원가입 처리
			else if(action.equals("insert_process")) {
				//입력된 값 받아오기
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				//값 DTO 넘김
				ToeicDTO dto = new ToeicDTO();
				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				
				try {//db 연결 후 처리
					dao.insertuser(dto);
					//회원가입 성공
					viewName = "/views/start.jsp";
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//회원가입 실패
					viewName = "/views/user_insert.jsp";
				}
			}
			//테스트 선택 및 문제 1~3번
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
			}//테스트 선택 및 문제 4~6번
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
			}//테스트 선택 및 문제 7~9번
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
			}//테스트 선택 및 문제 10~12번
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
			}//테스트 선택 및 문제 13~15번
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
