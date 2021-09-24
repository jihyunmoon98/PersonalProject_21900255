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
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
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

			case "ls_name_asc":
				l.sortByName();
				System.out.println("제목순으로 List가 정렳되었습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목 역순으로 List가 정렬되었습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜 순으로 List가 정렬되었습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				break;
			
			case "find":
				String word = sc.next().trim();
				int count = 1;
				int find_num=0;
				for (TodoItem item: l.getList()) {
					String line = item.getCategory()+item.getTitle()+item.getDesc()+item.getDue_date()+item.getCurrent_date();
					if (line.contains(word)) {
						System.out.println(count+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
						find_num++;
					}
					count++;
				}
				System.out.println("총 "+find_num+"개의 항목을 찾았습니다.");
				break;
			
			case "find_cate":
				String keyword = sc.next().trim();
				int c=1;
				int line_num=0;
				for (TodoItem item: l.getList()) {
					String line = item.getCategory();
					if (line.contains(keyword)) {
						System.out.println(c+".    [ " + item.getCategory() + " ] - " +item.getTitle() +" - " +item.getDesc()+" - "+item.getDue_date() +" - "+item.getCurrent_date() );
						line_num++;
					}
					c++;
				}
				System.out.println("총 "+line_num+"개의 항목을 찾았습니다.");
				break;
				
			case "ls_cate":
				ArrayList<String> cate1 = new ArrayList<String>();
				ArrayList<String> cate2 = new ArrayList<String>();
				
				for (TodoItem item: l.getList()) {
					cate1.add(item.getCategory());
				}
				
				for (int i=0 ;i<cate1.size();i++) {
					if (!cate2.contains(cate1.get(i))) {
						cate2.add(cate1.get(i));
					}
				}
				
				for (int j=0;j<cate2.size();j++) {
					if (j!=(cate2.size()-1)) {
						System.out.print(cate2.get(j)+" / ");
					}else {
						System.out.println(cate2.get(j));
					}
				}
				System.out.println("총 "+cate2.size()+"개의 카테고리가 등록되어 있습니다.");
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
