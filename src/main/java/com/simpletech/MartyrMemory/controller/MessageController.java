package com.simpletech.MartyrMemory.controller;

import com.simpletech.MartyrMemory.annotations.Intent;
import com.simpletech.MartyrMemory.controller.base.GeneralController;
import com.simpletech.MartyrMemory.model.Bean;
import com.simpletech.MartyrMemory.model.Message;
import com.simpletech.MartyrMemory.model.Visitor;
import com.simpletech.MartyrMemory.service.FlowerService;
import com.simpletech.MartyrMemory.service.MessageService;
import com.simpletech.MartyrMemory.service.VisitorService;
import com.simpletech.MartyrMemory.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 数据库表message 的Controller层实现
 * @author zimu
 * @date 2015-11-16 14:34:13 中国标准时间
 */
@RestController
@Intent("数据库表message")
@RequestMapping("message")
public class MessageController extends GeneralController<Message>{

	@Autowired
	MessageService service;

	@Resource
	VisitorService visitorService;

	@Resource
	FlowerService flowerService;

	/**
	 * 添加信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("add")
	public Object add(@RequestBody Message model) throws Exception {
		service.insert(model);
		return null;
	}

	@RequestMapping("addMsg")
	public @ResponseBody Object addMsg(String call, String words) throws Exception {
		Visitor visitor = new Visitor();
		visitor.setId(UUID.randomUUID().toString());
		visitor.setVisitorName(call);
		visitor.setDeleteFlag(false);
		visitor.setCreateTime(new Date());
		visitorService.insert(visitor);

		Message message = new Message();
		message.setId(UUID.randomUUID().toString());
		message.setVisitorId(visitor.getId());
		message.setContent(words);
		message.setStatus(Message.UNCHECKED_MSG);
		message.setDeleteFlag(false);
		message.setCreateTime(new Date());
		service.insert(message);
		return "提交成功！";
	}

	/**
	 * 更新信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	/*@Override
	@RequestMapping("update")
	public Object update(@RequestBody Message model) throws Exception {
		service.update(model);
		return null;
	}*/
	/**
	 * 更新信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateStatus/{id}")
	public Object updateStatus(@PathVariable String id, String status) throws Exception {
		Message msg = service.findById(id);
		msg.setStatus(status);
		int res = service.update(msg);
		if(res == 1) {
			return "修改成功！";
		}else{
			return "修改失败！";
		}
	}

	/**
	 * 根据ID获取信息
	 * @param ID
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("get/{ID}")
	public Object get(@PathVariable String ID) throws Exception {
		Object model = service.findById(ID);
		if (model == null) {
			return "null";
		}
		return model;
	}

	/**
	 * 根据ID删除
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("delete/{ID}")
	public Object delete(@PathVariable String ID) throws Exception {
		Message msg = service.findById(ID);
		service.delete(ID);
		visitorService.delete(msg.getVisitorId());
		return "删除成功！";
	}

	/**
	 * 批量根据ID删除
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteSelectedMessage/{pageSize}/{pageNo}")
	public void deleteSelectedMessage(String[] selectedRow,@PathVariable Integer pageSize,@PathVariable Integer pageNo,
									  HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(selectedRow != null && selectedRow.length > 0) {
			for (String ID : selectedRow) {
				Message msg = service.findById(ID);
				service.delete(ID);
				visitorService.delete(msg.getVisitorId());
			}
		}
			request.getRequestDispatcher("/message/list/"+pageSize+"/"+pageNo).forward(request, response);
	}
	/**
	 * 批量根据ID修改留言状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editSelectedMessage/{msgType}/{pageSize}/{pageNo}")
	public void editSelectedMessage(@PathVariable String msgType, String[] selectedRow,@PathVariable Integer pageSize,@PathVariable Integer pageNo,
									  HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(msgType != null && !"".equals(msgType.trim())){
			if(selectedRow != null && selectedRow.length > 0) {
				for (String ID : selectedRow) {
					Message msg = service.findById(ID);
					msg.setStatus(msgType);
					service.update(msg);
				}
			}
		}
		//request.getRequestDispatcher("/message/list/"+msgType + "/" + pageSize+"/"+pageNo).forward(request, response);
		listByPage(msgType, pageSize, pageNo, request, response);
	}




	/**
	 * 统计全部
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("count")
	public Object count() throws Exception {
		return service.countAll();
	}

	/**
	 * 获取全部列表
	 * @return
	 * @throws Exception 
	 */
	@Override
	@RequestMapping("list")
	public Object list() throws Exception {
		return service.findAll();
	}

	/**
	 * 后台获取分页列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list/{msgType}/{pageSize}/{pageNo}")
	public void listByPage(@PathVariable String msgType, @PathVariable Integer pageSize,@PathVariable Integer pageNo,
						   HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(msgType == null || "".equals(msgType.trim())){
			msgType = Message.LEGAL_MSG;
		}
		if(pageSize == null || pageSize == 0) {
			pageSize = 5;
		}
		if(pageNo == null){
			pageNo = 0;
		}
		String where = "where status = " + msgType;
		Page<Message> pb = service.listWhereByPage(where, pageSize, pageNo);
		pb.setPageSize(pageSize);
		if(pb.getDatas() == null || pb.getDatas().size() == 0){
			pb = service.listWhereByPage(where, pageSize, 0);
		}
		request.setAttribute("page", pb);
		List<Visitor> visitorList = visitorService.findAll();
		for(Message msg : pb.getDatas()){
			for(Visitor v : visitorList){
				if(v.getId().equals(msg.getVisitorId())){
					msg.setVisitor(v);
				}
			}
		}
		//加载统计数据
		//1.获取献花总数
		Integer numFlower = flowerService.countAll();
		request.setAttribute("numFlower", numFlower);
		//2.获取留言总数
		Integer numMessage = service.countAll();
		request.setAttribute("numMessage", numMessage);
		//3.获取未审核留言总数
		Integer numUnCheckedMsg = service.countByPropertyName("status", Message.UNCHECKED_MSG);
		request.setAttribute("numUnCheckedMsg", numUnCheckedMsg);
		//4.获取已通过审核的留言总数
		Integer numCheckedMsg = service.countByPropertyName("status", Message.LEGAL_MSG);
		request.setAttribute("numCheckedMsg", numCheckedMsg);
		//0.设置各类统计数据
		if(Message.LEGAL_MSG.equals(msgType)){
			pb.setTotalPage(numCheckedMsg % pb.getPageSize() == 0?numCheckedMsg / pb.getPageSize():numCheckedMsg / pb.getPageSize() + 1);
		}else if(Message.ILLEGAL_MSG.equals(msgType)){
			pb.setTotalPage((numMessage-numUnCheckedMsg-numCheckedMsg) % pb.getPageSize() == 0?(numMessage-numUnCheckedMsg-numCheckedMsg) / pb.getPageSize():(numMessage-numUnCheckedMsg-numCheckedMsg) / pb.getPageSize() + 1);
		}else if(Message.UNCHECKED_MSG.equals(msgType)){
			pb.setTotalPage(numUnCheckedMsg % pb.getPageSize() == 0?numUnCheckedMsg / pb.getPageSize():numUnCheckedMsg / pb.getPageSize() + 1);
		}
		request.getRequestDispatcher("/views/manage/msg_manage.jsp").forward(request, response);
	}

	/**
	 * 前台获取分页列表
	 * @param pageSize
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listUI/{pageSize}/{pageNo}")
	public @ResponseBody Object listByPageIndex(@PathVariable Integer pageSize,@PathVariable Integer pageNo, Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String where = "where status = " + Message.LEGAL_MSG;
		if(pageSize == null || pageSize == 0) {
			pageSize = 4;
		}
		if(pageNo == null){
			pageNo = 0;
		}
		Page<Message> pb = service.listWhereByPage(where, pageSize, pageNo);
		pb.setPageSize(pageSize);
		List<Visitor> visitorList = visitorService.findAll();
		for(Message msg : pb.getDatas()){
			for(Visitor v : visitorList){
				if(v.getId().equals(msg.getVisitorId())){
					msg.setVisitor(v);
				}
			}
		}
		//获取已通过审核的留言总数
		Integer numCheckedMsg = service.countByPropertyName("status", Message.LEGAL_MSG);
		request.setAttribute("numCheckedMsg", numCheckedMsg);
		pb.setTotalPage(numCheckedMsg % pb.getPageSize() == 0?numCheckedMsg/pb.getPageSize():numCheckedMsg/pb.getPageSize() + 1);

		//反转留言顺序
		Collections.sort(pb.getDatas());
		return pb;
	}



	/*@RequestMapping("goToList")
	public String goToList(){
		System.out.println("------------");
		return "/WEB-INF/manage/msg_manage.jsp";
	}*/
}
