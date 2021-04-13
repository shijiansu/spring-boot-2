package cn.timebusker.service;

import cn.timebusker.annotation.TimebuskerBean;

@TimebuskerBean
public interface OrderService {

	String addOrder(String name);
}
