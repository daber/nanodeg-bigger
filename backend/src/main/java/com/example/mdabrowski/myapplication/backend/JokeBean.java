/*
 * (c) Neofonie Mobile GmbH (2017)
 *
 * This computer program is the sole property of Neofonie Mobile GmbH (http://mobile.neofonie.de)
 * and is protected under the German Copyright Act (paragraph 69a UrhG).
 *
 * All rights are reserved. Making copies, duplicating, modifying, using or distributing
 * this computer program in any form, without prior written consent of Neofonie Mobile GmbH, is prohibited.
 * Violation of copyright is punishable under the German Copyright Act (paragraph 106 UrhG).
 *
 * Removing this copyright statement is also a violation.
 */
package com.example.mdabrowski.myapplication.backend;

/**
 * Created by mdabrowski on 24/01/17.
 */
public class JokeBean {
  private String joke;

  public void setJoke(String joke){
     this.joke = joke;
  }
  public String getJoke() {
    return this.joke;
  }


}
