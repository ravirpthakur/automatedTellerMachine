/*
 * Copyright (C) GM Global Technology Operations LLC. All rights   reserved.
 * This information is confidential and proprietary to GM Global Technology
 * Operations LLC and may not be used, modified, copied or distributed.
 */

package com.coding.challenge.atm.controller;

import com.coding.challenge.atm.service.AtmManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ravi Prakash
 */
@RestController
public class AtmController {

  @Autowired
  private AtmManager atmManager;

  @ApiOperation(value = "Check balance in the account")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK - Successful Operation"),
      @ApiResponse(code = 400, message = "BAD REQUEST"),
      @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
  @RequestMapping(value = "/balance", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<String> checkBalance(
      @RequestParam("acc_num")
      final String accountNumber,
      @RequestParam("pin")
      final int pin) {
    try {
      return new ResponseEntity<>(atmManager.getBalance(accountNumber, pin), HttpStatus.OK);
    } catch (final RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ApiOperation(value = "Withdraw balance from the account")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK - Successful Operation"),
      @ApiResponse(code = 400, message = "BAD REQUEST"),
      @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
  @RequestMapping(value = "/withdraw", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<String> withdrawMoney(
      @RequestParam("acc_num")
      final String accountNumber,
      @RequestParam("pin")
      final int pin,
      @RequestParam("amount")
      final double amount) {
    try {
      return new ResponseEntity<>(atmManager.withdrawMoney(accountNumber, pin, amount),
          HttpStatus.OK);
    } catch (final RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
