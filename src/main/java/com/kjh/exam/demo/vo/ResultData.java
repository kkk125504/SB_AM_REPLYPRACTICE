package com.kjh.exam.demo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResultData<DT> {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;
	@Getter
	private DT data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;

	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		return rd;
	}

	public static <DT> ResultData<DT> newData(ResultData Rd, String data1Name, DT data1) {

		return from(Rd.getResultCode(), Rd.getMsg(), data1Name, data1);
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public void setData2(String dataName, Object data) {
		this.data2Name = dataName;
		this.data2 = data;
	}

}
