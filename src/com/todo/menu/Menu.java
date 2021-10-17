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
        System.out.println("[ls_date_desc] 현재 목록을 날짜 역순으로 정렬");
        System.out.println("[find <keyword>] 현재 목록 내의 제목과 내용에서 <keyword>를 포함한 모든 항목 출력");
        System.out.println("[find_cate <keyword>] 현재 목록의 카테고리를 검색 <keyword>를 포함하고 있는 모든 항목을 출력");
        System.out.println("[ls_cate] 현재 등록되어 있는 카테고리를 중복되지 않도록 출력");
        System.out.println("[comp <numbers(,를 넣어주세요)>] 완료된 todolist를 체크");
        System.out.println("[ls_comp] 체크된 list만 출력");
        System.out.println("[Monthly <YYYY/MM/DD or YYYY or MM or DD>] 확인하고 싶은 월의 planTime를 order순으로 제공");
        System.out.println("[help] 메뉴 다시 보기 / 도움말");
        System.out.println("[exit] 프로그램 종료");
    }
    
    public static void prompt() {
    	System.out.println("\n실행하고 싶은 명령어를 입력해주세요; ");
    }
}
