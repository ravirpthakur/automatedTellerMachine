/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.dao;

import com.coding.challenge.atm.entity.Credentials;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ravi Prakash
 */
@Component
public class CredentialsDaoImpl
    implements CredentialsDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public int checkPin(final String accountNumber) {
    final Credentials rs = entityManager.createNamedQuery("ATM.getPin", Credentials.class)
        .setParameter("acc_nbr", accountNumber)
        .getSingleResult();
    return rs.getPin();
  }
}
