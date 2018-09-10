/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.dao;

import com.coding.challenge.atm.entity.AtmBalance;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ravi Prakash
 */
@Component
public class AtmBalanceDaoImpl
    implements AtmBalanceDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public AtmBalance getATMBalance() {
    return entityManager.createNamedQuery("ATM.getATMStats", AtmBalance.class)
        .getSingleResult();
  }
}
