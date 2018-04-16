package com.example.jack.myapp.bean;

import java.io.Serializable;

/**
 * Created by administor on 2017/6/28.
 */

public class BaseObject<T> implements Serializable {
   private T data;
   private int errorCode;
   private String errorMsg;
}
