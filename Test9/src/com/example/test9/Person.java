package com.example.test9;
public class Person {
	private String name; //名前
	private int have; //所持金
	private int pay; //支払金
	private int balance;  //残高
	public int getMoney(){
		StackTraceElement previous = Thread.currentThread().getStackTrace()[3];
		String previousClass = previous.getClassName();
		StackTraceElement present = Thread.currentThread().getStackTrace()[2];
		String presentClass = present.getClassName();
		if(previousClass == presentClass){
			return 0;
		}else{
			return have;
		}
	}
	public int getPay(){
		return pay;
	}
	public int getBalance(){
		return balance;
	}
	public String getHuman(){
		StackTraceElement previous = Thread.currentThread().getStackTrace()[3];
		String previousClass = previous.getClassName();
		StackTraceElement present = Thread.currentThread().getStackTrace()[2];
		String presentClass = present.getClassName();
		if(previousClass == presentClass){
			return "NoName";
		}else{
			return name;
		}
	}
	public void setName(String name){
        this.name = name;
	}
	public void setHave(int have){
         this.have = have;
	}
	public void setPay(int pay){
        this.pay = pay;
	}
	public void setBalance(int balance){
        this.balance = balance;
	}	
}