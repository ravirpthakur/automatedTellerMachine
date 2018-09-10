/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Ravi Prakash
 */

@Entity
@NamedQueries({@NamedQuery(name = "ATM.getBalanceDetails", query = " SELECT e FROM AccountInfo e " +
    " WHERE e.accountNumber  = :acc_nbr ")})

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountInfo {
  @Id
  @Basic
  @Column(name = "ACC_NBR", nullable = false, length = 9)
  @JsonProperty("acc_nbr")
  private String accountNumber;

  @Basic
  @Column(name = "OPENING_BAL", precision = 2)
  @JsonProperty("opening_bal")
  private double openingBalance;

  @Basic
  @Column(name = "OVERDRAFT", precision = 2)
  @JsonProperty("overdraft")
  private double overdraft;

}
