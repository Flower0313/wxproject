package com.holden.wxproject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @ClassName wxproject-NursingInfo
 * @Author Holden_—__——___———____————_____Xiao
 * @Create 2022年10月24日14:19 - 周一
 * @Describe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "陪护信息")
public class NursingInfo {
    private Long id;
    private String name;
    private String nickName;
    private Integer age;
    private String birthday;
    private String coverImage;
    private String email;
    private Integer gender;
    private String mobile;
    private String nation;//民族
    private String hometown;
    private String constellation;
    private String graduateSchool;
    private String graduateDate;//毕业时间
    private String educationType;
    private String highestDegree;
    private String urgentMobile;
    private String urgentName;
    private String address;
    private String originAddress;
    private String id_card;
    private String major;
    private Integer marriageStatus;
    private String workAddress;
    private BigDecimal workAge;
    private String bankAccount;
    private String bankCardNumber;
    private String entryDate;
    private Integer jobStatus;
    private String firstWorkDate;
    private String note;
    private String certificate;
}
