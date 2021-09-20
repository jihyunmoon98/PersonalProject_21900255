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
				+ "---�׸� �߰� ---\n"
				+ "�߰��ϰ� ���� ���� �Է� ) \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��Ǳ� ������ ������ �ȵ˴ϴ�.");
			return;
		}
		sc.nextLine();
		System.out.println("���� �Է� )");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�Է��� �����Ͱ� ����Ʈ�� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---�׸� ����---\n"
				+ "�����ϰ� ���� �׸� �̸� �Է� ) \n"
				+ "\n");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("�Է��� �����Ͱ� ����Ʈ���� �����Ǿ����ϴ�.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---�׸� ����---\n"
				+ "�����ϰ� ���� �׸��� ���� �Է� ) \n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("����Ʈ�� �������� �ʴ� title�Դϴ�.");
			return;
		}

		System.out.println("���ο� ������ �Է� ) ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("����Ʈ ���� ���� title�� �����մϴ�.");
			return;
		}
		
		System.out.println("���ο� ������ �Է� ) ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("����Ʈ�� �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("---��ü ���---");
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
			System.out.println("������ ���� file�� ���� �Ϸ�! ");
			
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
			System.out.println("file �ҷ����� �Ϸ�! ");	

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("������ �������� �ʾ� �ҷ����� ���Ͽ����ϴ�.");
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
