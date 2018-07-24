package com.duckduckgogogo.test;

import com.duckduckgogogo.services.EmployeeService;
import com.duckduckgogogo.services.impl.EmployeeServiceImp;
import com.duckduckgogogo.utils.PasswordEncodeAssistant;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class PasswordEncodeAssistantTest {
    private char[] chapters = "123456".toCharArray();

    // "123456" 密文
    public final static String SHA = "7c4a8d09ca3762af61e59520943dc26494f8941b";
    public final static String SHA256 = "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92";
    public final static String MD5 = "e10adc3949ba59abbe56e057f20f883e";

    // 使用SHA默认加密算法
    @Test
    public void testEncode() {
        Assert.assertTrue(SHA.equals(
                PasswordEncodeAssistant.encode(chapters)
        ));
    }

    // 使用SHA256加密算法
    @Test
    public void testEncodeSHA256() {
        Assert.assertTrue(SHA256.equals(
                PasswordEncodeAssistant.encode(chapters, PasswordEncodeAssistant.SHA256)
        ));
    }

    // 使用MD5加密算法
    @Test
    public void testEncodeMD5() {
        Assert.assertTrue(MD5.equals(
                PasswordEncodeAssistant.encode(chapters, PasswordEncodeAssistant.MD5)
        ));
    }

    @Test
    public void deleteAllEmployee(){
        EmployeeService employeeService = new EmployeeServiceImp();

        List<Integer> list = new LinkedList<>();

        list.add(97);
        list.add(107);
        list.add(112);
        list.add(113);
        list.add(114);
        list.add(115);
        list.add(116);
        list.add(117);
        list.add(125);
        list.add(133);
        list.add(158);
        list.add(159);
        list.add(161);
        list.add(162);
        list.add(163);
        list.add(164);
        list.add(175);
        list.add(176);
        list.add(178);
        list.add(189);
        list.add(190);
        list.add(191);
        list.add(197);
        list.add(198);
        list.add(199);
        list.add(200);
        list.add(202);
        list.add(203);
        list.add(204);
        list.add(205);
        list.add(206);
        list.add(207);
        list.add(209);
        list.add(212);
        list.add(213);

        employeeService.deleteEmployee(list);

    }
}
