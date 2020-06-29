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

		String action = request.getParameter("action");
		String viewName = null;
		
		if (action != null) {
			//�α����� �׽�Ʈ �� ���� ����
			if (action.equals("voca_info")) {
				//�α��� ���̵� ��� �޾� ����
				String ID = request.getParameter("id");
				String PW = request.getParameter("pw");
				try {
					//�α��� �� ����� �̸� �Ҹ���
					String user = (String) dao.login(ID, PW);
					if(user!=null) { //�α��� ����
						//�α����� ���̵� , �̸� ���ǿ� ����
						session.setAttribute("id",ID);
						session.setAttribute("username",user);	
						viewName = "/views/voca_info.jsp";										
					}
					else {//�α��ν��� 
						viewName = "redirect:/views/start.jsp";
					}
				} catch (ClassNotFoundException | SQLException e) {
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
					viewName = "redirect:/views/start.jsp";
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//ȸ������ ����
					viewName = "redirect:/views/user_insert.jsp";
				}
			}
			//�α׾ƿ�
			else if(action.equals("logout")) {
				session.invalidate();
				viewName = "redirect:/views/start.jsp";
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
				session.setAttribute("check_matter", new ArrayList<String>());
				session.setAttribute("check_score", new ArrayList<Integer>());
				viewName = "/views/voca_test.jsp";
			}
			
			//�׽�Ʈ ���� �� ���� 4~6��
			else if(action.equals("voca_test2")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//���� �� üũ
				int q1 = Integer.parseInt(request.getParameter("q1"));
				if(q1==1) {
					checkmatter.add("1�� ����");
					checkscore.add(q1);
				}
				else {
					checkmatter.add("1�� ����");
				}
				int q2 = Integer.parseInt(request.getParameter("q2"));
				if(q2==1) {
					checkmatter.add("2�� ����");
					checkscore.add(q2);
				}
				else {
					checkmatter.add("2�� ����");
				}
				int q3 = Integer.parseInt(request.getParameter("q3"));
				if(q3==1) {
					checkmatter.add("3�� ����");
					checkscore.add(q3);
				}
				else {
					checkmatter.add("3�� ����");
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
			
			//�׽�Ʈ ���� �� ���� 7~9��
			else if(action.equals("voca_test3")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//���� �� üũ
				int q4 = Integer.parseInt(request.getParameter("q4"));
				if(q4==1) {
					checkmatter.add("4�� ����");
					checkscore.add(q4);
				}
				else {
					checkmatter.add("4�� ����");
				}
				int q5 = Integer.parseInt(request.getParameter("q5"));
				if(q5==1) {
					checkmatter.add("5�� ����");
					checkscore.add(q5);
				}
				else {
					checkmatter.add("5�� ����");
				}
				int q6 = Integer.parseInt(request.getParameter("q6"));
				if(q6==1) {
					checkmatter.add("6�� ����");
					checkscore.add(q6);
				}
				else {
					checkmatter.add("6�� ����");
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
			
			//�׽�Ʈ ���� �� ���� 10~12��
			else if(action.equals("voca_test4")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//���� �� üũ
				int q7 = Integer.parseInt(request.getParameter("q7"));
				if(q7==1) {
					checkmatter.add("7�� ����");
					checkscore.add(q7);
				}
				else {
					checkmatter.add("7�� ����");
				}
				int q8 = Integer.parseInt(request.getParameter("q8"));
				if(q8==1) {
					checkmatter.add("8�� ����");
					checkscore.add(q8);
				}
				else {
					checkmatter.add("8�� ����");
				}
				int q9 = Integer.parseInt(request.getParameter("q9"));
				if(q9==1) {
					checkmatter.add("9�� ����");
					checkscore.add(q9);
				}
				else {
					checkmatter.add("9�� ����");
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
			
			//�׽�Ʈ ���� �� ���� 13~15��
			else if(action.equals("voca_test5")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//���� �� üũ
				int q10 = Integer.parseInt(request.getParameter("q10"));
				if(q10==1) {
					checkmatter.add("10�� ����");
					checkscore.add(q10);
				}
				else {
					checkmatter.add("10�� ����");
				}
				int q11 = Integer.parseInt(request.getParameter("q11"));
				if(q11==1) {
					checkmatter.add("11�� ����");
					checkscore.add(q11);
				}
				else {
					
					checkmatter.add("11�� ����");
				}
				int q12 = Integer.parseInt(request.getParameter("q12"));
				if(q12==1) {
					checkmatter.add("12�� ����");
					checkscore.add(q12);
				}
				else {
					checkmatter.add("12�� ����");
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
			
			//���� ���ȭ��
			else if(action.equals("result")) {
				List<String> checkmatter = (List<String>)session.getAttribute("check_matter");
				List<Integer> checkscore = (List<Integer>)session.getAttribute("check_score");
				//���� �� üũ
				int q13 = Integer.parseInt(request.getParameter("q13"));
				if(q13==1) {
					checkmatter.add("13�� ����");
					checkscore.add(q13);
				}
				else {
					checkmatter.add("13�� ����");
				}
				int q14 = Integer.parseInt(request.getParameter("q14"));
				if(q14==1) {
					checkmatter.add("14�� ����");
					checkscore.add(q14);
				}
				else {				
					checkmatter.add("14�� ����");
				}
				int q15 = Integer.parseInt(request.getParameter("q15"));
				if(q15==1) {
					checkmatter.add("15�� ����");
					checkscore.add(q15);
				}
				else {
					checkmatter.add("15�� ����");
				}
				
				//���� ���
				int score = dao.totalScore(checkscore);
				session.setAttribute("check_score", score);
				
				viewName = "/views/voca_result.jsp";
			}
			
			//���� ���� ���� �� ����Ʈ
			else if(action.equals("save_result")){
				//���� ����
				int score = (Integer)session.getAttribute("check_score");
				String id = (String)session.getAttribute("id");
				String name = (String)session.getAttribute("username");
				
				ToeicDTO dto = new ToeicDTO();
				
				dto.setId(id);
				dto.setName(name);
				dto.setScore(score);
				
				//List��ü DB����
				List<ToeicDTO> resultList = null;
				try {
					//���� ��� DB ����
					dao.inserscore(dto);
					
					//����� ���� ��� DB���� ��ȯ
					resultList = dao.getSaveResult(id);
					request.setAttribute("result_list", resultList);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				viewName = "/views/result_list.jsp";
			}
			
			//�׽�Ʈx direct ȸ������
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
