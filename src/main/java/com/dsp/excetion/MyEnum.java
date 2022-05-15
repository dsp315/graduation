package com.dsp.excetion;

public enum MyEnum {
    ADMIN_LOGIN_CODE_FAIL("001-001","验证码错误"),
    ADMIN_LOGIN_NOT_ACCOUNT("001-002","管理员登录失败"),
    ADMIN_MODIFY_INFO_FAIL("001-003","服务器出错了，修改信息失败，请联系开发人员"),
    ADMIN_MODIFY_IMG_FAIL("001-004","服务器出错了，修改头像失败，请联系开发人员"),
    ADMIN_MODIFY_PASSWORD_FAIL("001-004","服务器出错了，修改密码失败，请联系开发人员"),

    BUILDING_NOT_EXIST("002-001","建筑不存在"),
    BUILDING_ADD_FAIL("002-002","服务器繁忙，建筑添加失败，请稍后重试"),
    BUILDING_DELETE_FAIL("002-003","服务器繁忙，建筑删除失败，请稍后重试"),
    BUILDING_UPDATE_FAIL("002-004","服务器繁忙，建筑修改失败，请稍后重试"),

    UNIT_NOT_EXIST("003-001","单元不存在"),
    UNIT_ADD_FAIL("003-002","服务器繁忙，单元添加失败，请稍后重试"),
    UNIT_DELETE_FAIL("003-003","服务器繁忙，单元删除失败，请稍后重试"),
    UNIT_UPDATE_FAIL("003-004","服务器繁忙，单元修改失败，请稍后重试"),

    ROOM_NOT_EXIST("004-001","房间不存在"),
    ROOM_ADD_FAIL("004-002","服务器繁忙，房间添加失败，请稍后重试"),
    ROOM_DELETE_FAIL("004-003","服务器繁忙，房间删除失败，请稍后重试"),
    ROOM_UPDATE_FAIL("004-004","服务器繁忙，房修改失败，请稍后重试"),
    ROOM_HAVE_MASTER("004-005","该房屋已有主人，请重新选择"),

    CAR_NOT_EXIST("005-001","车位不存在"),
    CAR_ADD_FAIL("005-002","服务器繁忙，车位添加失败，请稍后重试"),
    CAR_DELETE_FAIL("005-003","服务器繁忙，车位删除失败，请稍后重试"),
    CAR_UPDATE_FAIL("005-004","服务器繁忙，车位修改失败，请稍后重试"),
    CAR_HAVE_MASTER("005-005","该车位已有主人，请重新选择"),

    PAY_NOT_EXIST("005-001","缴费类型不存在"),
    PAY_ADD_FAIL("005-002","服务器繁忙，缴费类型添加失败，请稍后重试"),
    PAY_DELETE_FAIL("005-003","服务器繁忙，缴费类型删除失败，请稍后重试"),
    PAY_UPDATE_FAIL("005-004","服务器繁忙，缴费类型修改失败，请稍后重试"),

    NOTICE_NOT_EXIST("006-001","公告不存在"),
    NOTICE_ADD_FAIL("006-002","服务器繁忙，公告添加失败，请稍后重试"),
    NOTICE_DELETE_FAIL("006-003","服务器繁忙，公告删除失败，请稍后重试"),
    NOTICE_UPDATE_FAIL("006-004","服务器繁忙，公告修改失败，请稍后重试"),

    MAINTAIN_NOT_EXIST("007-001","维修不存在"),
    MAINTAIN_DELETE_FAIL("007-003","服务器繁忙，维修删除失败，请稍后重试"),
    MAINTAIN_UPDATE_FAIL("007-004","服务器繁忙，维修受理失败，请稍后重试"),

    Guestbook_NOT_EXIST("008-001","留言不存在"),
    Guestbook_DELETE_FAIL("008-002","服务器繁忙，留言删除失败，请稍后重试"),
    Guestbook_UPDATE_FAIL("008-003","服务器繁忙，留言处理失败，请稍后重试"),

    USER_PAY_UPDATE_FAIL("009-001", "服务器繁忙，缴费失败失败，请稍后重试"),
    USER_PAY_INSERT_FAIL("009-002", "服务器繁忙，缴费通知失败，请稍后重试"),

    USER_NOT_EXIST("010-001","用户不存在，请输入正确的用户名字"),
    USER_ADD_FAIL("010-002","服务器繁忙，用户添加失败，请稍后重试"),
    USER_DELETE_FAIL("006-003","服务器繁忙，用户删除失败，请稍后重试"),
    USER_UPDATE_FAIL("006-004","服务器繁忙，用户修改失败，请稍后重试"),

    USER_DELETE_CAR_FAIL("006-010","服务器繁忙，停用车位失败，请稍后重试"),
    USER_DELETE_ROOM_FAIL("006-011","服务器繁忙，停用房屋失败，请稍后重试"),
    USER_CREATE_CAR_FAIL("006-012","服务器繁忙，分配车位失败，请稍后重试"),
    USER_CREATE_ROOM_FAIL("006-013","服务器繁忙，分配房屋失败，请稍后重试"),

    USER_NO_CAR("006-021","该用户没有车位！"),
    USER_NO_ROOM("006-022","该用户没有房屋！"),

    ;

    private String code;
    private String message;

    MyEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
