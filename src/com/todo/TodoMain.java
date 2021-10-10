package com.todo;

import java.util.Scanner;
import java.util.ArrayList;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		
		//TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				//l.sortByName();
				System.out.println("제목순으로 List가 정렳되었습니다.");
				//isList = true;
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_name_desc":
				//l.sortByName();
				//l.reverseList();
				System.out.println("제목 역순으로 List가 정렬되었습니다.");
				//isList = true;
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				//l.sortByDate();
				System.out.println("날짜 순으로 List가 정렬되었습니다.");
				//isList = true;
				TodoUtil.listAll(l,"due_date",1);
				break;
				
			case "ls_date_desc":
				//l.sortByDate();
				//l.reverseList();
				System.out.println("날짜 역순으로 List가 정렬되었습니다.");
				//isList = true;
				TodoUtil.listAll(l,"due_date",0);
				break;
			
			case "find":
				String word = sc.nextLine().trim();
				TodoUtil.findList(l,word);
				break;
			
			case "find_cate":
				String keyword = sc.nextLine().trim();
				TodoUtil.findListCate(l,keyword);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("메뉴에 존재하지 않는 명령어입니다. 정확한 명령어를 다시 입력해주세요. 메뉴를 잊었으면 help를 입력해주세요.");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l,"todolist.txt");
	}
}
