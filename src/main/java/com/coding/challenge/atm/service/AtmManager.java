/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.service;

import com.coding.challenge.atm.dao.AccountInfoDao;
import com.coding.challenge.atm.dao.AtmBalanceDao;
import com.coding.challenge.atm.dao.CredentialsDao;
import com.coding.challenge.atm.entity.AccountInfo;
import com.coding.challenge.atm.entity.AtmBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ravi Prakash
 */
@Service
public class AtmManager {

  private static final int FIFTY_EUROS = 50;
  private static final int TWENTY_EUROS = 20;
  private static final int TEN_EUROS = 10;
  private static final int FIVE_EUROS = 5;

  @Autowired
  private AccountInfoDao accountInfoDao;

  @Autowired
  private CredentialsDao credentialsDao;

  @Autowired
  private AtmBalanceDao atmBalanceDao;

  public String getBalance(final String accountNumber, final int pin) {
    if (pin == credentialsDao.checkPin(accountNumber)) {
      try {
        final AccountInfo resultSet = accountInfoDao.getBalanceDetails(accountNumber);
        return "The balance in your account is:- €" + resultSet.getOpeningBalance() +
            ". And you can withdraw" + "upto :- €" +
            calculateWithdrawableBalance(resultSet.getOpeningBalance(), resultSet.getOverdraft());
      } catch (final RuntimeException e) {
        System.out.println("Exception while reading account info from table" + e);
        return "No Balance information available at the moment. Thanks";
      }
    }
    return "Wrong pin entered";
  }

  public String withdrawMoney(final String accountNumber, final int pin, final double amount) {
    if (pin == credentialsDao.checkPin(accountNumber)) {
      final AccountInfo accountInfo = accountInfoDao.getBalanceDetails(accountNumber);
      final AtmBalance atmBalance = atmBalanceDao.getATMBalance();
      double amountToWithdraw = amount;
      int amountWithdrawn = 0;

      if (amountToWithdraw % 5 == 0) {
        if (amountToWithdraw <= calculateWithdrawableBalance(accountInfo.getOpeningBalance(),
            accountInfo.getOverdraft())) {
          if (amountToWithdraw <= atmBalance.getTotalBalance()) {
            final StringBuffer result = new StringBuffer();
            result.append("Thank you. You have withdrawn - €")
                .append(amountToWithdraw)
                .append(". Please collect your notes below in denominations - \n");
            if (atmBalance.getEuro50Bills() > 0 && amountWithdrawn <= amount) {
              result.append("€50 x ");
              final int noteCount = (int) (amountToWithdraw / FIFTY_EUROS);
              final int count =
                  noteCount >= atmBalance.getEuro50Bills() ? atmBalance.getEuro5Bills() : noteCount;
              atmBalance.setEuro50Bills(noteCount >= atmBalance.getEuro50Bills() ?
                  0 :
                  atmBalance.getEuro50Bills() - noteCount);
              amountToWithdraw -= count * FIFTY_EUROS;
              amountWithdrawn += count * FIFTY_EUROS;
              result.append(count)
                  .append("\n");
            }
            if (atmBalance.getEuro20Bills() > 0 && amountWithdrawn <= amount) {
              result.append("€20 x ");
              final int noteCount = (int) (amountToWithdraw / TWENTY_EUROS);
              final int count = noteCount >= atmBalance.getEuro20Bills() ?
                  atmBalance.getEuro20Bills() :
                  noteCount;
              atmBalance.setEuro20Bills(noteCount >= atmBalance.getEuro20Bills() ?
                  0 :
                  atmBalance.getEuro20Bills() - noteCount);
              amountToWithdraw -= count * TWENTY_EUROS;
              amountWithdrawn += count * TWENTY_EUROS;
              result.append(count)
                  .append("\n");
            }
            if (atmBalance.getEuro10Bills() > 0 && amountWithdrawn <= amount) {
              result.append("€10 x ");
              final int noteCount = (int) (amountToWithdraw / TWENTY_EUROS);
              final int count = noteCount >= atmBalance.getEuro10Bills() ?
                  atmBalance.getEuro10Bills() :
                  noteCount;
              atmBalance.setEuro10Bills(noteCount >= atmBalance.getEuro10Bills() ?
                  0 :
                  atmBalance.getEuro10Bills() - noteCount);
              amountToWithdraw -= count * TEN_EUROS;
              amountWithdrawn += count * TEN_EUROS;
              result.append(count)
                  .append("\n");
            }
            if (atmBalance.getEuro5Bills() > 0 && amountWithdrawn <= amount) {
              result.append("€5 x ");
              final int noteCount = (int) (amountToWithdraw / TWENTY_EUROS);
              final int count =
                  noteCount >= atmBalance.getEuro5Bills() ? atmBalance.getEuro5Bills() : noteCount;
              atmBalance.setEuro20Bills(noteCount >= atmBalance.getEuro5Bills() ?
                  0 :
                  atmBalance.getEuro5Bills() - noteCount);
              amountToWithdraw -= count * FIVE_EUROS;
              //amountWithdrawn then could be used in updating the values in the Account_Info table.
              amountWithdrawn += count * FIVE_EUROS;
              result.append(count)
                  .append("\n");
            }
            //ToDo - Implement the logic to update the tables with the amount as well.
            return String.valueOf(result);
          } else {
            return "Not enough balance in the ATM machine";
          }
        } else {
          return "Not enough balance in the your account";
        }
      } else {
        return "please enter amount multiples of the following denominations " +
            "only :- €5, €10, €20 and €50";
      }
    }
    return "Wrong pin entered";
  }

  private static double calculateWithdrawableBalance(final double openingBalance,
      final double overdraft) {
    final double withdrawableBal = openingBalance - overdraft;
    if (withdrawableBal >= 0) {
      return withdrawableBal;
    }
    return 0;
  }
}
