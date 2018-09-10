/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.dao;

import com.coding.challenge.atm.entity.AccountInfo;

/**
 * @author Ravi Prakash
 */
public interface AccountInfoDao {
  AccountInfo getBalanceDetails(String accountNumber)
      throws RuntimeException;
}
