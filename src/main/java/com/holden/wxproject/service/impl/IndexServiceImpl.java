package com.holden.wxproject.service.impl;

import com.holden.wxproject.annotation.SourceChange;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.mapper.IndexMapper;
import com.holden.wxproject.pojo.StockDTO;
import com.holden.wxproject.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

/**
 * @ClassName wxproject-IndexServiceImpl
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年12月14日16:51 - 周三
 * @Describe
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double MA(String code, Integer day) {
        try {
            //截止当日的MA
            List<StockDTO> ma = indexMapper.MA(code, day);
            double current_price = ma.stream().mapToDouble(StockDTO::getCurrent_price).sum();
            return current_price / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }

    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double BIAS(String code, Integer day) {
        try {
            List<StockDTO> bias = indexMapper.BIAS(code, day);
            //n日内收盘均价
            double avg = bias.stream().mapToDouble(StockDTO::getCurrent_price).average().getAsDouble();
            double first = bias.stream().findFirst().get().getCurrent_price();
            return (first - avg) * 100 / avg;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double RSI(String code, Integer day) {
        return null;
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double WR(String code, Integer day) {
        try {
            List<StockDTO> wr = indexMapper.WR(code, day);
            //n日内最高价
            double highest = wr.stream().max(Comparator.comparing(StockDTO::getHighest)).orElse(null).getHighest();
            //n日内最低价
            double lowest = wr.stream().min(Comparator.comparing(StockDTO::getLowest)).orElse(null).getLowest();
            //当日收盘价
            double current_price = wr.stream().findFirst().get().getCurrent_price();
            return (current_price - highest) / (highest - lowest);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: WR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double ROC(String code, Integer day) {
        try {
            List<StockDTO> stock = indexMapper.List(code, day);
            double current_price = stock.stream().findFirst().get().getCurrent_price();
            Double first = stock.stream().skip(stock.size() - 1).findFirst().get().getCurrent_price();
            return (current_price - first) * 100 / first;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ROC]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double UPPER_ENE(String code, Integer day) {
        try {
            Double ma25 = MA(code, 25);
            return (1 + (6.0 / 100)) * ma25;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: UPPER_ENE]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double BBI(String code) {
        try {
            Integer num = indexMapper.num(code);
            if (num >= BaseConstant.BBI) {
                return (MA(code, 3) + MA(code, 6) + MA(code, 12) + MA(code, 24)) / 4;
            }
            return 0.0;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BBI]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }
}
