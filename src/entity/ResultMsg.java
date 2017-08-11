package entity;

public class ResultMsg {
	private int status;  // 状态: 0 -成功, 1 -失败
	private Object data; // 数据
	private String msg; // 信息-- 传递数据相关的一些提示
	
	public ResultMsg() {
		super();
	}
	public ResultMsg(int status, Object data, String msg) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "ResultMsg [status=" + status + ", data=" + data + ", msg="
				+ msg + "]";
	}
	
	
}
