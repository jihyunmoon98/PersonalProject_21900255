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
				+ "---�׸� �߰� ---\n"
				+ "�߰��ϰ� ���� ���� �Է� ) ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��Ǳ� ������ ������ �ȵ˴ϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.println("ī�װ� �Է� ) ");
		category = sc.next().trim();
		
		sc.nextLine();
		System.out.println("���� �Է� ) ");
		desc = sc.nextLine().trim();
		
		System.out.println("���� ���� �Է� ) ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("�Է��� �����Ͱ� ����Ʈ�� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---�׸� ����---\n"
				+ "�����ϰ� ���� �׸��� ��ȣ �Է� ) ");
		int del_num = sc.nextInt();
		int count = 1;
		for (TodoItem item : l.getList()) {
			if (count == del_num) {
				System.out.println(count+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
				System.out.println("�� �׸��� �����Ͻðڽ��ϱ�? (y/n) > ");
				String answer = sc.next().trim();
				if (answer.equals("y")) {
					l.deleteItem(item);
					System.out.println("�Է��� �����Ͱ� ����Ʈ���� �����Ǿ����ϴ�.");
					break;
				}
			}
			count++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---�׸� ����---\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		
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
			//System.out.println("����Ʈ�� �����ϴ� title�Դϴ�.");
			//return;
		//}

		System.out.println("���ο� ������ �Է� ) ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("����Ʈ ���� ���� title�� �����մϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.println("���ο� ī�װ� �Է� ) ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.println("���ο� ������ �Է� ) ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("���ο� ���� ���� �Է� ) ");
		String new_due_date = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
			l.addItem(t);
			System.out.println("����Ʈ�� �����Ǿ����ϴ�.");
			break;
		}

	}

	public static void listAll(TodoList l) {
		int count=0;
		for (TodoItem t: l.getList()) {
			count++;
		}
		System.out.println("---��ü ���---   �� "+count+"��");
		
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
			System.out.println(count+"���� �׸��� �о����ϴ�.");	

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
