package com.simpson.kisen.payment.model.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private int paymentNo; //������ȣ
	private int fanNo; //������ȣ?
	private String payType; //����Ÿ��
	private String payContent; //��������
	private Date payDate; //���� ����
	private int amount; //�����ݾ�
	private int point; //�������Ʈ
	private String waybill; //������ȣ
	
	// new git repository  test
	
}
