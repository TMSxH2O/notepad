package com.tms.test.dao.impl;

import com.tms.test.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: TMS_H2O
 * Date: 2020/04/28
 * Description:
 * Version: V1.0
 */
@Repository
public class AccountDaoAnnotationImpl2 implements IAccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户\n" +
                this.getClass().getName());
    }
}
