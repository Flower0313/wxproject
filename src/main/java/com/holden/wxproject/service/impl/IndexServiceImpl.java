package com.holden.wxproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.holden.wxproject.config.BaseConstant;
import com.holden.wxproject.mapper.IndexMapper;
import com.holden.wxproject.pojo.StockDTO;
import com.holden.wxproject.service.IndexService;
import com.holden.wxproject.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Double MA(String code, Integer day, String date) {
        try {
            //截止当日的MA
            List<StockDTO> ma = indexMapper.MA(code, day, date);
            double current_price = ma.stream().mapToDouble(StockDTO::getClosing_price).sum();
            return current_price / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }

    }

    @Override
    public DataResult<Object> MALIST(String code, Integer day, String year) {
        try {
            //截止当日的MA
            List<Map<String, Object>> malist = indexMapper.MALIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MALIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public Double BIAS(String code, Integer day, String date) {
        try {
            //n日内收盘均价
            double avg = AVG(code, date, day, BaseConstant.CLOSEPRICE);
            double first = indexMapper.current(code, date).getClosing_price();
            return (first - avg) * 100 / avg;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIAS]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public DataResult<Object> BIASLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.BIASLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BIASLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> WRLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.WRLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: WRLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> ROCLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.ROCLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ROCLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> BBILIST(String code, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.BBILIST(code, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BBILIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> KDJLIST(String code, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.KDJLIST(code, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: KDJLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> MACDLIST(String code, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.MACDLIST(code, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MACDLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> ENELIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.ENELIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ENELIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> PSYLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.PSYLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: PSYLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> BRARLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.BRARLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BRARLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> ATRLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.ATRLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ATRLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> EMVLIST(String code, Integer day, String year, Integer circle) {
        try {
            List<Map<String, Object>> malist = indexMapper.EMVLIST(code, day, year, circle);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: EMVLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> MTMLIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.MTMLIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MTMLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> DPOLIST(String code, Integer day, String year, Integer circle) {
        try {
            List<Map<String, Object>> malist = indexMapper.DPOLIST(code, day, year, circle);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: DPOLIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }

    @Override
    public DataResult<Object> ASILIST(String code, Integer day, String year) {
        try {
            List<Map<String, Object>> malist = indexMapper.ASILIST(code, day, year);
            return DataResult.ok(malist);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ASILIST]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return DataResult.fail("没数据");
        }
    }


    @Override
    public Double WR(String code, Integer day, String date) {
        try {
            //n日内最高价
            double highest = HIGH(code, date, day, BaseConstant.HIGHEST);
            //n日内最低价
            double lowest = LOW(code, date, day, BaseConstant.LOWEST);
            //当日收盘价
            double current_price = indexMapper.current(code, date).getClosing_price();
            System.out.println(highest + "||" + lowest + "||" + current_price + ">>>>");
            return (highest - current_price) * 100 / (highest - lowest);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: WR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double ROC(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
            double current_price = stock.stream().findFirst().get().getClosing_price();
            Double first = stock.stream().skip(stock.size() - 1).findFirst().get().getClosing_price();
            return (current_price - first) * 100 / first;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ROC]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double PSY(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
            double sum = stock.stream().mapToDouble(x -> x.getClosing_price() - getClosingPrice(code, lastExecute(date)) > 0 ? 1 : 0).sum();
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
    public Double ATR(String code, Integer day, String date) {
        try {
            List<StockDTO> stock = indexMapper.List(code, date, day);
            if (stock.size() < 14) {
                return 0.0;
            }
            double mtr = 0;
            for (StockDTO stockDTO : stock) {
                double hsl = stockDTO.getHighest() - stockDTO.getLowest();
                double last_closing = getClosingPrice(code, lastExecute(stockDTO.getDs()));
                double ll = Math.abs(last_closing - stockDTO.getLowest());
                double lh = Math.abs(last_closing - stockDTO.getHighest());
                mtr += hsl > lh ? Math.max(hsl, ll) : Math.max(lh, ll);
            }
            return mtr / day;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ATR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double EMV(String code, String date, Integer day, Integer circle) {
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
            return emv / circle;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: EMV]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double DPO(String code, String date, Integer day, Integer circle) {
        try {
            StockDTO current = indexMapper.current(code, date);
            Double lag_ma = MA(code, day, getTargetDate(code, date, circle));
            return current.getClosing_price() - lag_ma;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: DPO]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double MTM(String code, String date, Integer circle) {
        try {
            double closingPrice = indexMapper.getClosingPrice(code, getTargetDate(code, date, circle));
            StockDTO current = indexMapper.current(code, date);
            return current.getClosing_price() - closingPrice;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MTM]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return -999.0;
        }
    }

    @Override
    public Double ASI(String code, String date, Integer day) {
        try {
            List<StockDTO> list = indexMapper.List(code, date, day);
            double asi = 0.0;
            for (StockDTO dto : list) {
                StockDTO yesterday = indexMapper.current(code, lastExecute(dto.getDs()));
                double last_closing = getClosingPrice(code, lastExecute(dto.getDs()));
                double aa = Math.abs(dto.getHighest() - last_closing);
                double bb = Math.abs(dto.getLowest() - last_closing);
                double cc = Math.abs(dto.getHighest() - yesterday.getLowest());
                double dd = Math.abs(yesterday.getClosing_price() - yesterday.getOpening_price());
                double x = dto.getClosing_price() - last_closing + (dto.getClosing_price() - dto.getOpening_price()) / 2 + yesterday.getClosing_price() - yesterday.getOpening_price();
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
    public JSONObject BRAR(String code, Integer day, String date) {
        try {
            JSONObject result = new JSONObject();
            List<StockDTO> stock = indexMapper.List(code, date, day);
            double ar1 = stock.stream().mapToDouble(x -> x.getHighest() - x.getOpening_price()).sum();
            double ar2 = stock.stream().mapToDouble(x -> x.getOpening_price() - x.getLowest()).sum();
            double br1 = stock.stream().mapToDouble(x -> x.getHighest() - getClosingPrice(code, lastExecute(x.getDs())) > 0 ? x.getHighest() - getClosingPrice(code, lastExecute(x.getDs())) : 0).sum();
            double br2 = stock.stream().mapToDouble(x -> getClosingPrice(code, lastExecute(x.getDs())) - x.getLowest() > 0 ? getClosingPrice(code, lastExecute(x.getDs())) - x.getLowest() : 0).sum();
            result.put("ar", ar1 * 100 / ar2);
            result.put("br", br1 * 100 / br2);
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: BRAR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return new JSONObject();
        }
    }

    @Override
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

    @Override
    public JSONObject KDJ(String code, String date) {
        try {
            JSONObject result = new JSONObject();
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            if (list.size() == BaseConstant.ONE) {
                result.put("k", list.get(0).get("k"));
                result.put("d", list.get(0).get("d"));
                result.put("j", list.get(0).get("j"));
            }
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: KDJ]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public JSONObject MACD(String code, String date) {
        try {
            JSONObject result = new JSONObject();
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            if (list.size() == BaseConstant.ONE) {
                result.put("dif", list.get(0).get("dif"));
                result.put("dea", list.get(0).get("dea"));
                result.put("macd", list.get(0).get("macd"));
            }
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MACD]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Double SAR(String code, String date) {
        try {
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            return (Double) list.get(0).get("sar");
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: SAR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public JSONObject DMI(String code, String date) {
        try {
            JSONObject result = new JSONObject();
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            if (list.size() == BaseConstant.ONE) {
                result.put("pdi", list.get(0).get("pdi"));
                result.put("mdi", list.get(0).get("mdi"));
                result.put("adx", list.get(0).get("adx"));
            }
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: DMI]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public JSONObject MIKE(String code, String date) {
        try {
            JSONObject result = new JSONObject();
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            if (list.size() == BaseConstant.ONE) {
                result.put("stor", list.get(0).get("stor"));
                result.put("midr", list.get(0).get("midr"));
                result.put("wekr", list.get(0).get("wekr"));
                result.put("weks", list.get(0).get("weks"));
                result.put("mids", list.get(0).get("mids"));
                result.put("stos", list.get(0).get("stos"));
            }
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: MIKE]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public JSONObject RSI(String code, String date) {
        try {
            JSONObject result = new JSONObject();
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            if (list.size() == BaseConstant.ONE) {
                result.put("rsi6", list.get(0).get("rsi6"));
                result.put("rsi12", list.get(0).get("rsi12"));
                result.put("rsi24", list.get(0).get("rsi24"));
            }
            return result;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: RSI]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Double OBV(String code, String date) {
        try {
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            return (Double) list.get(0).get("obv");
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: SAR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Double RSV(String code, String date) {
        try {
            List<Map<String, Object>> list = indexMapper.recursiveIndex(code, date);
            return (Double) list.get(0).get("rsv");
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: SAR]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    public boolean ifExecute(String date) {
        try {
            List<String> list = indexMapper.ifExecute(date);
            return list.size() == BaseConstant.ONE;
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: ifExecute]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return false;
        }
    }

    public String lastExecute(String date) {
        try {
            return indexMapper.lastExecute(date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: lastExecute]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return date;
        }
    }

    public Double getHighest(String code, String date) {
        try {
            return indexMapper.getHighest(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getHighest]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    public Double getLowest(String code, String date) {
        try {
            return indexMapper.getLowest(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getLowest]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    public Double getClosingPrice(String code, String date) {
        try {
            return indexMapper.getClosingPrice(code, date);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getClosingPrice]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return 0.0;
        }
    }

    public String getTargetDate(String code, String date, Integer day) {
        try {
            return indexMapper.getTargetDate(code, date, day);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: getTargetDate]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return date;
        }
    }

    public Double AVG(String code, String date, Integer day, String field) {
        try {
            return indexMapper.AVG(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: AVG]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    public Double HIGH(String code, String date, Integer day, String field) {
        try {
            return indexMapper.HIGH(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: HIGH]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }

    public Double LOW(String code, String date, Integer day, String field) {
        try {
            return indexMapper.LOW(code, date, day, field);
        } catch (Exception e) {
            log.error("[class: IndexServiceImpl.java]-[method: LOW]-[function: {}] , [Message]: {}", e.getMessage(), e);
            return null;
        }
    }


}
