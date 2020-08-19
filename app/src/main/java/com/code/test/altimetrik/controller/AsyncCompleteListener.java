package com.code.test.altimetrik.controller;

public interface AsyncCompleteListener<T> {
   void onTaskComplete(T response);
}