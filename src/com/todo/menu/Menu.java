package com.todo.menu;

public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[add] title�� description �߰�");
        System.out.println("[del] title�� description ����");
        System.out.println("[edit] title�� description ����");
        System.out.println("[ls] �Էµ� title�� description ��� ���");
        System.out.println("[ls_name_asc] title ������ �Էµ� ������ ����");
        System.out.println("[ls_name_desc] title ������ reverse�� ������ ����");
        System.out.println("[ls_date] �Էµ� �ð� ������ ������ ����");
        System.out.println("[ls_date_desc] ���� ����� ��¥ �������� ����");
        System.out.println("[find <keyword>] ���� ��� ���� ����� ���뿡�� <keyword>�� ������ ��� �׸� ���");
        System.out.println("[find_cate <keyword>] ���� ����� ī�װ��� �˻� <keyword>�� �����ϰ� �ִ� ��� �׸��� ���");
        System.out.println("[ls_cate] ���� ��ϵǾ� �ִ� ī�װ��� �ߺ����� �ʵ��� ���");
        System.out.println("[comp <numbers(,�� �־��ּ���)>] �Ϸ�� todolist�� üũ");
        System.out.println("[ls_comp] üũ�� list�� ���");
        System.out.println("[Monthly <YYYY/MM/DD or YYYY or MM or DD>] Ȯ���ϰ� ���� ���� planTime�� order������ ����");
        System.out.println("[help] �޴� �ٽ� ���� / ����");
        System.out.println("[exit] ���α׷� ����");
    }
    
    public static void prompt() {
    	System.out.println("\n�����ϰ� ���� ��ɾ �Է����ּ���; ");
    }
}
