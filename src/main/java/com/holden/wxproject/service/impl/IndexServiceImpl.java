package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONObject;
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
    public Double MA(String code, Integer day, String date) {
        try {
            //截止当日的MA
            List<StockDTO> ma = indexMapper.MA(code, day, date);
            double current_price = ma.stream().mapToDouble(StockDTO::getCurrent_price).sum();
            return current_price / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }

    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double BIAS(String code, Integer day, String date) {
        try {
            //n日内收盘均价
            double avg = AVG(code, date, day, BaseConstant.CLOSEPRICE);
            double first = indexMapper.current(code, date).getCurrent_price();
            return (first - avg) * 100 / avg;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }


    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double WR(String code, Integer day, String date) {
        try {
            //n日内最高价
            double highest = HIGH(code, date, day, BaseConstant.HIGHEST);
            //n日内最低价
            double lowest = LOW(code, date, day, BaseConstant.LOWEST);
            //当日收盘价
            double current_price = indexMapper.current(code, date).getCurrent_price();
            return (highest - current_price) * 100 / (highest - lowest);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: WR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double ROC(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
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
    public Double PSY(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
            double sum = stock.stream().mapToDouble(x -> x.getCurrent_price() - x.getT_1_price() > 0 ? 1 : 0).sum();
            if (stock.size() <= 12) {
                sum -= 1;
            }
            return sum * 100 / 12;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: PSY]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double ATR(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
            if (stock.size() < 14) {
                return 0.0;
            }
            double mtr = 0;
            for (StockDTO stockDTO : stock) {
                double hsl = stockDTO.getHighest() - stockDTO.getLowest();
                double ll = Math.abs(stockDTO.getT_1_price() - stockDTO.getLowest());
                double lh = Math.abs(stockDTO.getT_1_price() - stockDTO.getHighest());
                mtr += hsl > lh ? Math.max(hsl, ll) : Math.max(lh, ll);
            }
            return mtr / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ATR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double EMV(String code, String date, Integer day) {
        try {
            List<StockDTO> list = indexMapper.List(code, date, day);
            double emv = 0.0;
            for (StockDTO dto : list) {
                StockDTO yesterday = indexMapper.current(code, getTargetDate(code, dto.getDs(), BaseConstant.ONE));
                double last_hl = yesterday.getHighest() + yesterday.getLowest();
                double mid = (dto.getHighest() + dto.getLowest() - last_hl) * 100 / (dto.getLowest() + dto.getHighest());
                StringBuilder sb = new StringBuilder();
                sb.append(BaseConstant.HIGHEST).append(BaseConstant.SUB).append(BaseConstant.LOWEST);
                double h_l = AVG(code, dto.getDs(), day, sb.toString());
                double vol = AVG(code, dto.getDs(), day, BaseConstant.DEALVOL) / dto.getDeal_vol().doubleValue();
                emv += mid * vol * (dto.getHighest() - dto.getLowest()) / Math.max(1.0, h_l);
            }
            return emv / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: EMV]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double DPO(String code, String date, Integer day, Integer circle) {
        try {
            StockDTO current = indexMapper.current(code, date);
            Double lag_ma = MA(code, day, getTargetDate(code, date, circle));
            return current.getCurrent_price() - lag_ma;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: DPO]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double MTM(String code, String date, Integer circle) {
        try {
            Double closingPrice = indexMapper.getClosingPrice(code, getTargetDate(code, date, circle));
            StockDTO current = indexMapper.current(code, date);
            return current.getCurrent_price() - closingPrice;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MTM]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double ASI(String code, String date, Integer day) {
        try {
            List<StockDTO> list = indexMapper.List(code, date, day);
            double asi = 0.0;
            for (StockDTO dto : list) {
                StockDTO yesterday = indexMapper.current(code, lastExecute(dto.getDs()));
                double aa = Math.abs(dto.getHighest() - dto.getT_1_price());
                double bb = Math.abs(dto.getLowest() - dto.getT_1_price());
                double cc = Math.abs(dto.getHighest() - yesterday.getLowest());
                double dd = Math.abs(yesterday.getCurrent_price() - yesterday.getOpening_price());
                double x = dto.getCurrent_price() - dto.getT_1_price() + (dto.getCurrent_price() - dto.getOpening_price()) / 2 + yesterday.getCurrent_price() - yesterday.getOpening_price();
                double r = 0.0;
                if (aa > bb && aa > cc) {
                    r = aa + bb / 2 + dd / 4;
                } else {
                    if (bb > cc && bb > aa) {
                        r = bb + aa / 2 + dd / 4;
                    } else {
                        r = cc + dd / 4;
                    }
                }
                asi += 16 * x / r * Math.max(aa, bb);
            }
            return asi;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ASI]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public JSONObject ENE(String code, Integer day, String date) {
        try {
            Double ma25 = MA(code, day, date);
            JSONObject results = new JSONObject();
            double upper = (1 + (6.0 / 100)) * ma25;
            double lower = (1 - (6.0 / 100)) * ma25;
            results.put("upper_ene", upper);
            results.put("lower_ene", lower);
            results.put("ene", (upper + lower) / 2);
            return results;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ENE]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return new JSONObject();
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public JSONObject BRAR(String code, Integer day, String date) {
        try {
            JSONObject result = new JSONObject();
            List<StockDTO> stock = indexMapper.List(code, date, day);
            double ar1 = stock.stream().mapToDouble(x -> x.getHighest() - x.getOpening_price()).sum();
            double ar2 = stock.stream().mapToDouble(x -> x.getOpening_price() - x.getLowest()).sum();
            double br1 = stock.stream().mapToDouble(x -> x.getHighest() - x.getT_1_price() > 0 ? x.getHighest() - x.getT_1_price() : 0).sum();
            double br2 = stock.stream().mapToDouble(x -> x.getT_1_price() - x.getLowest() > 0 ? x.getT_1_price() - x.getLowest() : 0).sum();
            result.put("ar", ar1 * 100 / ar2);
            result.put("br", br1 * 100 / br2);
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BRAR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return new JSONObject();
        }
    }

    @Override
    @SourceChange(BaseConstant.SPIDER)
    public Double BBI(String code, String date) {
        try {
            Integer num = indexMapper.num(code);
            if (num >= BaseConstant.BBI) {
                return (MA(code, 3, date) + MA(code, 6, date) + MA(code, 12, date) + MA(code, 24, date)) / 4;
            }
            return 0.0;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BBI]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public boolean ifExecute(String date) {
        try {
            List<String> list = indexMapper.ifExecute(date);
            return list.size() == BaseConstant.ONE;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ifExecute]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return false;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public String lastExecute(String date) {
        try {
            return indexMapper.lastExecute(date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: lastExecute]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return date;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double getHighest(String code, String date) {
        try {
            return indexMapper.getHighest(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getHighest]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double getLowest(String code, String date) {
        try {
            return indexMapper.getLowest(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getLowest]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double getClosingPrice(String code, String date) {
        try {
            return indexMapper.getClosingPrice(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getClosingPrice]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public String getTargetDate(String code, String date, Integer day) {
        try {
            return indexMapper.getTargetDate(code, date, day);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getTargetDate]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return date;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double AVG(String code, String date, Integer day, String field) {
        try {
            return indexMapper.AVG(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: AVG]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double HIGH(String code, String date, Integer day, String field) {
        try {
            return indexMapper.HIGH(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: HIGH]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @SourceChange(BaseConstant.SPIDER)
    public Double LOW(String code, String date, Integer day, String field) {
        try {
            return indexMapper.LOW(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: LOW]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }


}
