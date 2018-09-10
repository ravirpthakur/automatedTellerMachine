/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.dao;

import com.coding.challenge.atm.entity.AccountInfo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ravi Prakash
 */
@Component
public class AccountInfoDaoImpl
    implements AccountInfoDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public AccountInfo getBalanceDetails(final String accountNumber)
      throws RuntimeException {
    return entityManager.createNamedQuery("ATM.getBalanceDetails", AccountInfo.class)
        .setParameter("acc_nbr", accountNumber)
        .getSingleResult();
  }
}
