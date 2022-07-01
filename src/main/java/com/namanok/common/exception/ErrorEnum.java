package com.namanok.common.exception;

/**
 * �����ڵ�/�޼��� ���� Ŭ����
 */
public enum ErrorEnum {
	//�ý��� ���� ����
	SYS9999("SYS9999", "�ý��� ������ �߻��߽��ϴ�. ����� �ٽ� �õ��� �ּ���."),

	//���� ���� ����
	ERR0000("ERR0000", "�Ͻ����� ������ �߻��߽��ϴ�. ����� �ٽ� �õ����ּ���."),
	ERR0001("ERR0001", "ȸ�������� �Ұ��� �մϴ�."),
	ERR0002("ERR0002", "�̹� �����ϴ� ���̵��Դϴ�."),
	ERR0003("ERR0003", "�̹� �����ϴ� ȸ���Դϴ�."),
	ERR0004("ERR0004", "ȸ�������� �������� �ʽ��ϴ�."),
	ERR0005("ERR0005", "��ū ��ȿ�ð��� ����Ǿ����ϴ�. �ٽ� �α��� �� �ּ���"),
	ERR0008("ERR0008", "�α��ο� �����Ͽ����ϴ�."),
	ERR0010("ERR0010", "���̵� �Ǵ� ��й�ȣ�� �Է����ּ���"),
	ERR9999("ERR9999", "������ �߻��߽��ϴ�. ����� �ٽ� �õ��� �ּ���.");

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorEnum(final String code, final String message) {
        this.message = message;
        this.code = code;
    }
}
