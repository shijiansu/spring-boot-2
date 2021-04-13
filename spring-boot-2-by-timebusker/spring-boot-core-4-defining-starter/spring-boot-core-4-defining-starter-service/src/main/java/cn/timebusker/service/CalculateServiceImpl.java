package cn.timebusker.service;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculateServiceImpl implements CalculateService {

  private int scale;

  public CalculateServiceImpl(int scale) {
    log.info(">>>>>>>> >>>>>>>> >>>>>>>> >>>>>>>> {}", scale);
    this.scale = scale;
  }

  @Override
  public double add(double arg1, double arg2) {
    return arg1 + arg2;
  }

  @Override
  public double sub(double arg1, double arg2) {
    return arg1 - arg2;
  }

  @Override
  public double div(double arg1, double arg2) {
    return arg1 / arg2;
  }

  @Override
  public double mul(double arg1, double arg2) {
    return arg1 * arg2;
  }

  @Override
  public double setScale(double arg, int scale) {
    return new BigDecimal(arg).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  @Override
  public double setScale(double arg) {
    return new BigDecimal(arg).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
}
