package com.tms.test.service.impl;

import com.tms.test.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/05/01
 * Description: 账户的业务层实现类
 * Version: V1.0
 */
@Service("accountService")
public class AccountServiceAnnotationImpl implements IAccountService {

    public void saveAccount() {
        System.out.println("save...");
        int i = 1 / 0;
    }

    public void updateAccount(int i) {
        System.out.println("update...");
    }

    public int deleteAccount(int i) {
        System.out.println("delete...");
        return 0;
    }
}
