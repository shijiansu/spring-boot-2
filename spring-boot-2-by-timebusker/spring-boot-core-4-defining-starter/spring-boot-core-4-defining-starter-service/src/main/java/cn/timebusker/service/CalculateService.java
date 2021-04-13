package cn.timebusker.service;

public interface CalculateService {

  double add(double arg1, double arg2);

  double sub(double arg1, double arg2);

  double div(double arg1, double arg2);

  double mul(double arg1, double arg2);

  double setScale(double arg, int scale);

  double setScale(double arg);
}
