package com.example.jack.myapp.bean;

import com.example.tulib.util.http.IModel;

import java.io.Serializable;

/**
 * Created by administor on 2017/6/28.
 */

public class BaseObject<T> implements Serializable, IModel {
   private T data;
   private int errorCode;
   private String errorMsg;

   public T getData() {
      return data;
   }

   public void setData(T data) {
      this.data = data;
   }

   public int getErrorCode() {
      return errorCode;
   }

   public void setErrorCode(int errorCode) {
      this.errorCode = errorCode;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   @Override
   public boolean isNull() {
      return false;
   }

   @Override
   public boolean isAuthError() {
      return false;
   }

   @Override
   public boolean isBizError() {
      return false;
   }
}
