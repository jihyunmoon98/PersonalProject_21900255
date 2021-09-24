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
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 추가 ---\n"
				+ "추가하고 싶은 제목 입력 ) ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복되기 때문에 저장이 안됩니다.");
			return;
		}
		
		sc.nextLine();
		System.out.println("카테고리 입력 ) ");
		category = sc.next().trim();
		
		sc.nextLine();
		System.out.println("내용 입력 ) ");
		desc = sc.nextLine().trim();
		
		System.out.println("마감 일자 입력 ) ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("입력한 데이터가 리스트에 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 삭제---\n"
				+ "삭제하고 싶은 항목의 번호 입력 ) ");
		int del_num = sc.nextInt();
		int count = 1;
		for (TodoItem item : l.getList()) {
			if (count == del_num) {
				System.out.println(count+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
				System.out.println("위 항목을 삭제하시겠습니까? (y/n) > ");
				String answer = sc.next().trim();
				if (answer.equals("y")) {
					l.deleteItem(item);
					System.out.println("입력한 데이터가 리스트에서 삭제되었습니다.");
					break;
				}
			}
			count++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---항목 수정---\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		
		int edit_number = sc.nextInt();
		int count =1;
		for (TodoItem item: l.getList()) {
			if (count==edit_number) {
				System.out.println(count+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
				l.deleteItem(item);
				break;
			}
			count++;
		}
		
		//String title = sc.next().trim();
		//if (!l.isDuplicate(title)) {
			//System.out.println("리스트에 존재하는 title입니다.");
			//return;
		//}

		System.out.println("새로운 제목을 입력 ) ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("리스트 내에 같은 title이 존재합니다.");
			return;
		}
		
		sc.nextLine();
		System.out.println("새로운 카테고리 입력 ) ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.println("새로운 제목을 입력 ) ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("새로운 마감 일자 입력 ) ");
		String new_due_date = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
			l.addItem(t);
			System.out.println("리스트가 수정되었습니다.");
			break;
		}

	}

	public static void listAll(TodoList l) {
		int count=0;
		for (TodoItem t: l.getList()) {
			count++;
		}
		System.out.println("---전체 목록---   총 "+count+"개");
		
		count = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(count+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
			count++;
		
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
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);
				count++;

			}
			br.close();
			System.out.println(count+"개의 항목을 읽었습니다.");	

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
