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
@NamedQueries({@NamedQuery(name = "ATM.getATMStats", query = " SELECT e FROM AtmBalance e ")})

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AtmBalance {

  @Id
  @Basic
  @Column(name = "TOTAL_BAL", nullable = false)
  @JsonProperty("total_bal")
  private double totalBalance;

  @Basic
  @Column(name = "EURO_5")
  @JsonProperty("euro_5_bills")
  private int euro5Bills;

  @Basic
  @Column(name = "EURO_10")
  @JsonProperty("euro_10_bills")
  private int euro10Bills;

  @Basic
  @Column(name = "EURO_20")
  @JsonProperty("euro_20_bills")
  private int euro20Bills;

  @Basic
  @Column(name = "EURO_50")
  @JsonProperty("euro_50_bills")
  private int euro50Bills;

}
