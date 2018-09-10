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
@NamedQueries({@NamedQuery(name = "ATM.getPin", query = " SELECT e FROM Credentials e " +
    " WHERE e.accountNumber  = :acc_nbr ")})

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Credentials {

  @Id
  @Basic
  @Column(name = "ACC_NBR", nullable = false, length = 9)
  @JsonProperty("acc_nbr")
  private String accountNumber;

  @Basic
  @Column(name = "PIN", length = 4)
  @JsonProperty("pin")
  private int pin;
}
