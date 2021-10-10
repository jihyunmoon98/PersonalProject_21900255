package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 추가 ---\n"
				+ "추가하고 싶은 제목 입력 ) ");
		
		title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("제목이 중복되기 때문에 저장이 안됩니다.");
			return;
		}
		
		System.out.println("카테고리 입력 ) ");
		category = sc.next();
		sc.nextLine();

		System.out.println("내용 입력 ) ");
		desc = sc.nextLine().trim();
		
		System.out.println("마감 일자 입력 ) ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		
		if (l.addItem(t)>0) {
			System.out.println("입력한 데이터가 리스트에 추가되었습니다.");		
		}

	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 삭제]\n"
				+"삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if (l.deleteItem(index)>0) {
			System.out.println("삭제되었습니다.");
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			count++;
			System.out.println(item.ToString());
			
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void findListCate(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getListCategory(keyword)) {
			count++;
			System.out.println(item.ToString());
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item+"  ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}

	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"
				+"수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일지 > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item:l.getList()) {
			System.out.println(item.ToString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item: l.getOrderedList(orderby, ordering)) {
			System.out.println(item.ToString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			FileWriter w = new FileWriter(filename);
			
			for (TodoItem item: l.getList()) {
				w.write(item.toString());
			}
			
			w.close();
			System.out.println("데이터 정보 file에 저장 완료! ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));

			String oneline;
			int count=0;
			
			while ((oneline=br.readLine())!=null)
			{
				count++;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);

			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다.");	

		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않아 불러오지 못하였습니다.");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
