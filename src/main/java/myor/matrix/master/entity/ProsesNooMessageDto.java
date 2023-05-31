package myor.matrix.master.entity;

import java.util.ArrayList;
import java.util.List;

public class ProsesNooMessageDto {

	MessageDto message = new MessageDto();
	List<String> messageList = new ArrayList<>();
	String flag = "";
	ProsesNooEditDto detailEdit;
	public ProsesNooMessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProsesNooMessageDto(MessageDto message, List<String> messageList, String flag, ProsesNooEditDto detailEdit) {
		super();
		this.message = message;
		this.messageList = messageList;
		this.flag = flag;
		this.detailEdit = detailEdit;
	}
	public MessageDto getMessage() {
		return message;
	}
	public void setMessage(MessageDto message) {
		this.message = message;
	}
	public List<String> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ProsesNooEditDto getDetailEdit() {
		return detailEdit;
	}
	public void setDetailEdit(ProsesNooEditDto detailEdit) {
		this.detailEdit = detailEdit;
	}
}
