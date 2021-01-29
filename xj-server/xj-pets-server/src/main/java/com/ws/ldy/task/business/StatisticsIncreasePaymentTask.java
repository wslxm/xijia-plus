package com.ws.ldy.task.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.utils.BigDecimalUtil;
import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.yw.pets.model.entity.PetsOrder;
import com.ws.ldy.modules.yw.pets.service.PetsOrderService;
import com.ws.ldy.modules.yw.statistics.model.entity.StatisticsIncreasePayment;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreasePaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 缴费增长每小时统计表
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:13
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class StatisticsIncreasePaymentTask {

    @Autowired
    private StatisticsIncreasePaymentService statisticsIncreasePaymentService;


    @Autowired
    private PetsOrderService petsOrderService;

    @Scheduled(cron = "40 0 0/1 * * ?")
    //@Scheduled(cron = "0/10 * * * * ?")
    private void configureTasks() {
        log.info("===>  缴费增长每小时统计: START: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
        statisticsTime();
        log.info("===>  缴费增长每小时统计: EBD: {}", LocalDateTimeUtil.parse(LocalDateTime.now()));
    }


    /**
     * 统计指定时间内的数据
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2021/1/19 0019 14:46
     * @version 1.0.0
     */
    public void statisticsTime() {
        // 1、获取开始时间和结束时间
        List<StatisticsIncreasePayment> list = statisticsIncreasePaymentService.list(new LambdaQueryWrapper<StatisticsIncreasePayment>()
                .select(StatisticsIncreasePayment::getStartTime, StatisticsIncreasePayment::getEndTime)
                .orderByDesc(StatisticsIncreasePayment::getEndTime)
                .last(" limit 0,1")
        );
        // 开始时间 (上一次的结束时间，如果为第一次统计,指定开始时间)
        LocalDateTime startTime = list.size() == 0
                ? LocalDateTimeUtil.parse("2021-01-01 00:00:00")
                : list.get(0).getEndTime();
        // 结束时间（分/秒设置为0）
        LocalDateTime endTime = LocalDateTimeUtil.getTheHour(LocalDateTime.now());
        if (endTime.equals(startTime)) {
            return;
        }

        // 2、查询数据（支付成功）
        List<PetsOrder> petsOrders = petsOrderService.list(new LambdaQueryWrapper<PetsOrder>()
                .select(PetsOrder::getMoney, PetsOrder::getIsFirst)
                .between(PetsOrder::getPayTime, startTime, endTime)
                .in(PetsOrder::getOrderState, Enums.Pet.OrderState.ORDER_STATE_3.getValue(), Enums.Pet.OrderState.ORDER_STATE_2.getValue())
        );

        // 3、数据收集
        StatisticsIncreasePayment increasePayment = new StatisticsIncreasePayment();
        increasePayment.setStartTime(startTime);
        increasePayment.setEndTime(endTime);
        if (petsOrders.size() == 0) {
            increasePayment.setPaymentNum(0);
            increasePayment.setPaymentMoney(new BigDecimal("0"));
            increasePayment.setPaymentBoyMoneyTotal(new BigDecimal("0"));
            increasePayment.setPaymentGirlMoneyTotal(new BigDecimal("0"));
            //
            increasePayment.setFirstNum(0);
            increasePayment.setFirstMoney(new BigDecimal("0"));
            increasePayment.setRenewNum(0);
            increasePayment.setRenewMoney(new BigDecimal("0"));
        } else {
            BigDecimal paymentMoney = BigDecimalUtil.parse(new BigDecimal(petsOrders.stream().mapToDouble(p -> p.getMoney().doubleValue()).sum()));
            increasePayment.setPaymentNum(petsOrders.size());
            increasePayment.setPaymentMoney(paymentMoney);
            increasePayment.setPaymentBoyMoneyTotal(new BigDecimal("0"));
            increasePayment.setPaymentGirlMoneyTotal(new BigDecimal("0"));
            //
            // 首次支付
            Map<Boolean, List<PetsOrder>> groupIsFirstMap = petsOrders.stream().filter(p -> p.getIsFirst() != null)
                    .collect(Collectors.groupingBy(PetsOrder::getIsFirst));
            if (groupIsFirstMap.containsKey(true)) {
                List<PetsOrder> isFirstTrues = groupIsFirstMap.get(true);
                BigDecimal isFirstTruesPaymentMoney = BigDecimalUtil.parse(new BigDecimal(isFirstTrues.stream().mapToDouble(p -> p.getMoney().doubleValue()).sum()));
                //
                increasePayment.setFirstNum(isFirstTrues.size());
                increasePayment.setFirstMoney(isFirstTruesPaymentMoney);
            } else {
                increasePayment.setFirstNum(0);
                increasePayment.setFirstMoney(new BigDecimal("0"));
            }
            // 续费
            if (groupIsFirstMap.containsKey(false)) {
                List<PetsOrder> isFirstFalses = groupIsFirstMap.get(false);
                BigDecimal isFirstFalsesPaymentMoney = BigDecimalUtil.parse(new BigDecimal(isFirstFalses.stream().mapToDouble(p -> p.getMoney().doubleValue()).sum()));
                //
                increasePayment.setRenewNum(isFirstFalses.size());
                increasePayment.setRenewMoney(isFirstFalsesPaymentMoney);
            } else {
                increasePayment.setRenewNum(0);
                increasePayment.setRenewMoney(new BigDecimal("0"));
            }
        }

        // 4、添加
        statisticsIncreasePaymentService.save(increasePayment);
    }
}