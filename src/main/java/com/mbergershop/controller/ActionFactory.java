package com.mbergershop.controller;

import com.mbergershop.controller.action.Action;
import com.mbergershop.controller.action.DeleteCart;
import com.mbergershop.controller.action.DeleteOrder;
import com.mbergershop.controller.action.IdCheckFormAction;
import com.mbergershop.controller.action.IndexAction;
import com.mbergershop.controller.action.IndexNAction;
import com.mbergershop.controller.action.InsertCartAction;
import com.mbergershop.controller.action.JoinAccess;
import com.mbergershop.controller.action.JoinAction;
import com.mbergershop.controller.action.JoinFormAction;
import com.mbergershop.controller.action.KakaoLoginAction;
import com.mbergershop.controller.action.LoginAction;
import com.mbergershop.controller.action.LogoutAction;
import com.mbergershop.controller.action.ManagerDetailFormAction;
import com.mbergershop.controller.action.ManagerFormAction;
import com.mbergershop.controller.action.MenuBurgerAction;
import com.mbergershop.controller.action.MenuDrinkAction;
import com.mbergershop.controller.action.MenuFormAction;
import com.mbergershop.controller.action.MenuRecommandAction;
import com.mbergershop.controller.action.MenuSideAction;
import com.mbergershop.controller.action.NaverLoginAction;
import com.mbergershop.controller.action.OrderAction;
import com.mbergershop.controller.action.OrderinquiryDetailAction;
import com.mbergershop.controller.action.OrderinquiryFromAction;
import com.mbergershop.controller.action.ProductDetailAction;
import com.mbergershop.controller.action.UpdateOrderState1;
import com.mbergershop.controller.action.UpdateOrderState2;
import com.mbergershop.controller.action.UpdateOrderState3;

public class ActionFactory {

	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {

	}
	
	public static ActionFactory getInstance() {
		 return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
	    System.out.println("ActionFactory 요청을 받음을 확인 : " + command);
	    
	    if(command.equals("index")) {
	    	action = new IndexAction();
	    }else if(command.equals("indexNaver")) {
	    	action = new NaverLoginAction();
	    }else if(command.equals("indexKakao")) {
	    	action = new KakaoLoginAction();
	    }else if(command.equals("login")) {
	    	action = new LoginAction();
	    }else if(command.equals("join_form")) {
	    	action = new JoinFormAction();
	    }else if(command.equals("join")) {
	    	action = new JoinAction();
	    }else if(command.equals("menu")) {
	    	action = new MenuFormAction();
	    }else if(command.equals("logout")) {
	    	action = new LogoutAction();
	    }else if(command.equals("menu_burger")) {
	    	action = new MenuBurgerAction();
	    }else if(command.equals("menu_side")) {
	    	action = new MenuSideAction();
	    }else if(command.equals("menu_drink")) {
	    	action = new MenuDrinkAction();
	    }else if(command.equals("menu_recommand")) {
	    	action = new MenuRecommandAction();
	    }else if(command.equals("product_detail")) {
	    	action = new ProductDetailAction();
	    }else if(command.equals("insert_cart")) {
	    	action = new InsertCartAction();
	    }else if(command.equals("insert_order")) {
	    	action = new OrderAction();
	    }else if(command.equals("mypage")) {
	    	action = new OrderinquiryFromAction();
	    }else if(command.equals("mypageDetail")) {
	    	action = new OrderinquiryDetailAction();
	    }else if(command.equals("manager")) {
	    	action = new ManagerFormAction();
	    }else if(command.equals("managerDetail")) {
	    	action = new ManagerDetailFormAction();
	    }else if(command.equals("manager_Detail_Up1")) {
	    	action = new UpdateOrderState1();
	    }else if(command.equals("manager_Detail_Up2")) {
	    	action = new UpdateOrderState2();
	    }else if(command.equals("manager_Detail_Up3")) {
	    	action = new UpdateOrderState3();
	    }else if(command.equals("deleteorder")){
	    	action = new DeleteOrder();
	    }else if(command.equals("join_access")){
	    	action = new JoinAccess();
	    }else if(command.equals("id_check_form")){
	    	action = new IdCheckFormAction();
	    }else if(command.equals("delete_Cart")){
	    	action = new DeleteCart();
	    }
	    
	    
	    
	    
	    
	    
	    
	    return action;
	}
}
