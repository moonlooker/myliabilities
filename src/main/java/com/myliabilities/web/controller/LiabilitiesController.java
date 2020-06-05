package com.myliabilities.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.enums.LiabilitiesStatusEnum;
import com.myliabilities.service.LiabilitiesService;
import com.myliabilities.service.NowUser;
import com.myliabilities.service.UserService;
import com.myliabilities.web.vo.LiabilitiesVo;

@Controller
public class LiabilitiesController {

    private static final Logger log = LoggerFactory.getLogger(LiabilitiesController.class);

    private LiabilitiesService ls = new LiabilitiesService();
    private UserService us = new UserService();

    @RequestMapping(value = "/liabilities")
    public String liabilities(@RequestParam("statu") String status, Model model) {

        log.info("查询用户[{}]", NowUser.loginUser);
        AccountPo accountPo = us.getAcount(NowUser.loginUser);
        Integer statu = null;
        if (status != null && !"".equals(status)) {
            statu = Integer.parseInt(status);
        }
        List<LiabilitiesPo> list = ls.getUserData(statu);
        List<LiabilitiesVo> listvo = list.stream().map(ab -> {
            LiabilitiesVo lv = new LiabilitiesVo();
            lv.setId(ab.getId());
            lv.setAccountid(ab.getAccountid());
            lv.setLiabilities(ab.getLiabilities());
            lv.setType("默认");
            lv.setMonthpay(ab.getMonthpay());
            lv.setPaymonth(ab.getPaymonth());
            lv.setStatu(LiabilitiesStatusEnum.getName(ab.getStatu()));
            lv.setStatuv(ab.getStatu());
            lv.setSettledate(new SimpleDateFormat("yyyy-MM-dd").format(ab.getSettledate()));
            lv.setMemo(ab.getMemo());
            return lv;
        }).collect(Collectors.toList());
        model.addAttribute("accountid", NowUser.loginUser);
        model.addAttribute("assets", accountPo.getAssets());
        model.addAttribute("liabilities", accountPo.getLiabilities());
        model.addAttribute("left", accountPo.getAssets().subtract(accountPo.getLiabilities()));
        model.addAttribute("liabs", listvo);
        model.addAttribute("statu", statu == null ? "" : statu);
        return "liabilities";
    }

    @RequestMapping("/addliabweb")
    public String addliabweb() {

        return "addliab";
    }

    @RequestMapping("/doaddliab")
    public String doaddliab(@RequestParam("settledate") String settledate, @RequestParam("monthpay") String monthpay,
            @RequestParam("memo") String memo, @RequestParam("nextMonthStart") boolean nextMonthStart)
            throws ParseException {

        ls.addLiabilities(new SimpleDateFormat("yyyy-MM-dd").parse(settledate), new BigDecimal(monthpay), null, memo,
            nextMonthStart);
        return "redirect:/liabilities?statu=";
    }

    /**
     * 销账,已经还清
     * @param dc
     * @param amount
     * @param memo
     * @return
     */
    @RequestMapping("/settle")
    public String settle(@RequestParam("id") Long id) {

        ls.settleRecord(id);
        return "redirect:/liabilities?statu=";
    }

    /**
     * 重新定义为待付清
     * @param dc
     * @param amount
     * @param memo
     * @return
     */
    @RequestMapping("/waitpay")
    public String waitpay(@RequestParam("id") Long id) {

        ls.waitpayRecord(id);
        return "redirect:/liabilities?statu=";
    }
    
    /**
     * 删除
     * @param dc
     * @param amount
     * @param memo
     * @return
     */
    @RequestMapping("/settledel")
    public String settledel(@RequestParam("id") Long id) {

        ls.settledel(id);
        return "redirect:/liabilities?statu=";
    }
}
