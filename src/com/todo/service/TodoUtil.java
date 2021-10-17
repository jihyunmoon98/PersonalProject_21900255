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
		
		String category, title, desc, due_date, location, planTime;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "---�׸� �߰� ---\n"
				+ "�߰��ϰ� ���� ���� �Է� ) ");
		
		title = sc.nextLine();
		if (l.isDuplicate(title)) {
			System.out.println("������ �ߺ��Ǳ� ������ ������ �ȵ˴ϴ�.");
			return;
		}
		
		int is_completed = 0;
		
		System.out.println("ī�װ� �Է� ) ");
		category = sc.nextLine();
		//sc.nextLine();

		System.out.println("���� �Է� ) ");
		desc = sc.nextLine().trim();
		
		System.out.println("���� ���� �Է� ) ");
		due_date = sc.nextLine().trim();
		
		System.out.println("���� ��� �Է� ) ");
		location = sc.nextLine().trim();
		
		System.out.println("�����ϰ��� �ϴ� plan �ð� �Է� (�ʼ�) ) ");
		planTime = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, is_completed, desc, category, due_date, planTime, location);
		
		if (l.addItem(t)>0) {
			System.out.println("�Է��� �����Ͱ� ����Ʈ�� �߰��Ǿ����ϴ�.");		
		}

	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� ����]\n"
				+"������ �׸��� ��ȣ�� �Է��Ͻÿ� (���ÿ� ���� ���� �����ϰ� ���� ��� ,�� �̿����ּ���) > ");
		String indexs = sc.nextLine().trim();
		
		String[] del_array = indexs.split(",");
		for (int j=0;j<del_array.length;j++) {
			if (l.deleteItem(Integer.parseInt(del_array[j]))>0) {
				System.out.print("");
			}
		}
		System.out.println("�����Ǿ����ϴ�.");
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			count++;
			System.out.println(item.ToString());
			
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

	public static void findListCate(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getListCategory(keyword)) {
			count++;
			System.out.println(item.ToString());
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item+"  ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}

	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date, new_planTime, new_location;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n"
				+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("�� plan �ð� (�ʼ�) > ");
		new_planTime = sc.nextLine().trim();
		System.out.print("�� ��� �Է� > ");
		new_location = sc.nextLine().trim();
		
		int new_is_completed = 0;
		
		TodoItem t = new TodoItem(new_title, new_is_completed, new_desc, new_category, new_due_date, new_planTime, new_location);
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
	}

	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item:l.getList()) {
			System.out.println(item.ToString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item: l.getOrderedList(orderby, ordering)) {
			System.out.println(item.ToString());
		}
	}
	
	public static void Monthly(TodoList l, String date) {
		int count = 0;
		for (TodoItem item : l.getListMonthly(date)) {
			count++;
			System.out.println(item.ToString());
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
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
				int is_completed = Integer.parseInt(st.nextToken());
				String desc = st.nextToken();
				String location = st.nextToken();
				String planTime = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, is_completed, desc, category, due_date, planTime, location);
				t.setCurrent_date(current_date);
				l.addItem(t);
			}
			br.close();
			System.out.println(count+"���� �׸��� �о����ϴ�.");	

		} catch (FileNotFoundException e) {
			System.out.println("������ �������� �ʾ� �ҷ����� ���Ͽ����ϴ�.");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void completeItem(TodoList l, String numbers) {
		String[] array = numbers.split(",");
		for (int i=0;i<array.length; i++) {
			if (l.completeItem(Integer.parseInt(array[i]))>0) {
				System.out.print("");
			}
		}
		System.out.println("�Ϸ� üũ�߽��ϴ�.");
	}
	
	
	public static void listAll(TodoList l, int is_completed) {
		int count = 0;
		for (TodoItem item : l.getList(is_completed)) {
			count++;
			System.out.println(item.ToString2());
		}
		System.out.printf("�� %d���� �׸��� �Ϸ�Ǿ����ϴ�.\n", count);
	}
}
