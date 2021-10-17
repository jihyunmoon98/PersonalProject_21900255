package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private int id;
    private int is_completed;
    private String location;
    private String planTime;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	public TodoItem(String title, int is_completed, String desc, String category, String due_date, String planTime, String location){
		this.category = category;
        this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        this.is_completed = is_completed;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.location = location;
        this.planTime = planTime;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	@Override
	public String toString() {
		return category+"##"+title+"##"+is_completed+"##"+desc+"##"+location+"##"+planTime+"##"+due_date+"##"+current_date+"\n";
	}
	
	public String ToString() {
		if (is_completed == 1) {
			return id+"    ["+category+"]  "+title+"[V]"+"  -  "+desc+"  -  "+location+"  -  "+planTime+"  -  "+due_date+"  -  "+current_date;
		}
		return id+"    ["+category+"]  "+title+"[ ]"+"  -  "+desc+"  -  "+location+"  -  "+planTime+"  -  "+due_date+"  -  "+current_date;
	}
	
	public String ToString2() {
		return id+"    ["+category+"]  "+title+"[V]"+"  -  "+desc+"  -  "+location+"  -  "+planTime+"  -  "+due_date+"  -  "+current_date;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
    
}
