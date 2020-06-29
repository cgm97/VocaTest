package com.wp.toeic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

		String action = request.getParameter("action");
		String viewName = null;
		
		if (action != null) {
			//로그인후 테스트 및 점수 선택
			if (action.equals("voca_info")) {
				//로그인 아이디 비번 받아 저장
				String ID = request.getParameter("id");
				String PW = request.getParameter("pw");
				try {
					//로그인 후 사용자 이름 불리옴
					String user = (String) dao.login(ID, PW);
					if(user!=null) { //로그인 성공
						//로그인한 아이디 , 이름 세션에 저장
						session.setAttribute("id",ID);
						session.setAttribute("username",user);	
						viewName = "/views/voca_info.jsp";										
					}
					else {//로그인실패 
						viewName = "redirect:/views/start.jsp";
					}
				} catch (ClassNotFoundException | SQLException e) {
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
					viewName = "redirect:/views/start.jsp";
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//회원가입 실패
					viewName = "redirect:/views/user_insert.jsp";
				}
			}
			//로그아웃
			else if(action.equals("logout")) {
				session.invalidate();
				viewName = "redirect:/views/start.jsp";
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
				session.setAttribute("check_matter", new ArrayList<String>());
				session.setAttribute("check_score", new ArrayList<Integer>());
				viewName = "/views/voca_test.jsp";
			}
			
			//테스트 선택 및 문제 4~6번
			else if(action.equals("voca_test2")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//문제 답 체크
				int q1 = Integer.parseInt(request.getParameter("q1"));
				if(q1==1) {
					checkmatter.add("1번 정답");
					checkscore.add(q1);
				}
				else {
					checkmatter.add("1번 오답");
				}
				int q2 = Integer.parseInt(request.getParameter("q2"));
				if(q2==1) {
					checkmatter.add("2번 정답");
					checkscore.add(q2);
				}
				else {
					checkmatter.add("2번 오답");
				}
				int q3 = Integer.parseInt(request.getParameter("q3"));
				if(q3==1) {
					checkmatter.add("3번 정답");
					checkscore.add(q3);
				}
				else {
					checkmatter.add("3번 오답");
				}
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
			}
			
			//테스트 선택 및 문제 7~9번
			else if(action.equals("voca_test3")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//문제 답 체크
				int q4 = Integer.parseInt(request.getParameter("q4"));
				if(q4==1) {
					checkmatter.add("4번 정답");
					checkscore.add(q4);
				}
				else {
					checkmatter.add("4번 오답");
				}
				int q5 = Integer.parseInt(request.getParameter("q5"));
				if(q5==1) {
					checkmatter.add("5번 정답");
					checkscore.add(q5);
				}
				else {
					checkmatter.add("5번 오답");
				}
				int q6 = Integer.parseInt(request.getParameter("q6"));
				if(q6==1) {
					checkmatter.add("6번 정답");
					checkscore.add(q6);
				}
				else {
					checkmatter.add("6번 오답");
				}
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
			}
			
			//테스트 선택 및 문제 10~12번
			else if(action.equals("voca_test4")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//문제 답 체크
				int q7 = Integer.parseInt(request.getParameter("q7"));
				if(q7==1) {
					checkmatter.add("7번 정답");
					checkscore.add(q7);
				}
				else {
					checkmatter.add("7번 오답");
				}
				int q8 = Integer.parseInt(request.getParameter("q8"));
				if(q8==1) {
					checkmatter.add("8번 정답");
					checkscore.add(q8);
				}
				else {
					checkmatter.add("8번 오답");
				}
				int q9 = Integer.parseInt(request.getParameter("q9"));
				if(q9==1) {
					checkmatter.add("9번 정답");
					checkscore.add(q9);
				}
				else {
					checkmatter.add("9번 오답");
				}
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
			}
			
			//테스트 선택 및 문제 13~15번
			else if(action.equals("voca_test5")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//문제 답 체크
				int q10 = Integer.parseInt(request.getParameter("q10"));
				if(q10==1) {
					checkmatter.add("10번 정답");
					checkscore.add(q10);
				}
				else {
					checkmatter.add("10번 오답");
				}
				int q11 = Integer.parseInt(request.getParameter("q11"));
				if(q11==1) {
					checkmatter.add("11번 정답");
					checkscore.add(q11);
				}
				else {
					
					checkmatter.add("11번 오답");
				}
				int q12 = Integer.parseInt(request.getParameter("q12"));
				if(q12==1) {
					checkmatter.add("12번 정답");
					checkscore.add(q12);
				}
				else {
					checkmatter.add("12번 오답");
				}
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
			
			//점수 결과화면
			else if(action.equals("result")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//문제 답 체크
				int q13 = Integer.parseInt(request.getParameter("q13"));
				if(q13==1) {
					checkmatter.add("13번 정답");
					checkscore.add(q13);
				}
				else {
					checkmatter.add("13번 오답");
				}
				int q14 = Integer.parseInt(request.getParameter("q14"));
				if(q14==1) {
					checkmatter.add("14번 정답");
					checkscore.add(q14);
				}
				else {				
					checkmatter.add("14번 오답");
				}
				int q15 = Integer.parseInt(request.getParameter("q15"));
				if(q15==1) {
					checkmatter.add("15번 정답");
					checkscore.add(q15);
				}
				else {
					checkmatter.add("15번 오답");
				}
				
				//점수 계산
				int score = dao.totalScore(checkscore);
				session.setAttribute("check_score", score);
				
				viewName = "/views/voca_result.jsp";
			}
			
			//시험 점수 저장 및 리스트
			else if(action.equals("save_result")){
				//점수 저장
				int score = (Integer)session.getAttribute("check_score");
				String id = (String)session.getAttribute("id");
				String name = (String)session.getAttribute("username");
				
				ToeicDTO dto = new ToeicDTO();
				
				dto.setId(id);
				dto.setName(name);
				dto.setScore(score);
				
				//List객체 DB연결
				List<ToeicDTO> resultList = null;
				try {
					//점수 결과 DB 저장
					dao.inserscore(dto);
					
					//저장된 점수 결과 DB에서 반환
					resultList = dao.getSaveResult(id);
					request.setAttribute("result_list", resultList);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				viewName = "/views/result_list.jsp";
			}
			
			//테스트x direct 회차정보
			else if(action.equals("load_result")){
				String id = (String)session.getAttribute("id");
				List<ToeicDTO> resultList = null;
				try {
					resultList = dao.getSaveResult(id);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("result_list", resultList);
				viewName = "/views/result_list.jsp";
			}
		}
		
		if (viewName != null) {
			if (viewName.contains("redirect:")) {
				String location = viewName.split(":")[1];
				response.sendRedirect(request.getContextPath() + location);
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
