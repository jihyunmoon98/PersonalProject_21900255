package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 추가 ---\n"
				+ "추가하고 싶은 제목 입력 ) \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복되기 때문에 저장이 안됩니다.");
			return;
		}
		sc.nextLine();
		System.out.println("내용 입력 )");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("입력한 데이터가 리스트에 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 삭제---\n"
				+ "삭제하고 싶은 항목 이름 입력 ) \n"
				+ "\n");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("입력한 데이터가 리스트에서 삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 수정---\n"
				+ "수정하고 싶은 항목의 제목 입력 ) \n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("리스트에 존재하지 않는 title입니다.");
			return;
		}

		System.out.println("새로운 제목을 입력 ) ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("리스트 내에 같은 title이 존재합니다.");
			return;
		}
		
		System.out.println("새로운 제목을 입력 ) ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("리스트가 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("---전체 목록---");
		for (TodoItem item : l.getList()) {
			System.out.println("[ " + item.getTitle() + " ] " + item.getDesc()+" Time: "+item.getCurrent_date() );
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
			while ((oneline=br.readLine())!=null)
			{
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, current_date);
				l.addItem(t);

			}
			br.close();
			System.out.println("file 불러오기 완료! ");	

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일이 존재하지 않아 불러오지 못하였습니다.");
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
