package com.todo.menu;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[add] title과 description 추가");
        System.out.println("[del] title과 description 삭제");
        System.out.println("[edit] title과 description 변경");
        System.out.println("[ls] 입력된 title과 description 모두 출력");
        System.out.println("[ls_name_asc] title 순으로 입력된 데이터 정렬");
        System.out.println("[ls_name_desc] title 순으로 reverse로 데이터 정렬");
        System.out.println("[ls_date] 입력된 시간 순으로 데이터 정렬");
        System.out.println("[help] 메뉴 다시 보기 / 도움말");
        System.out.println("[exit] 프로그램 종료");
    }
    
    public static void prompt() {
    	System.out.println("\n실행하고 싶은 명령어를 입력해주세요; ");
    }
}
