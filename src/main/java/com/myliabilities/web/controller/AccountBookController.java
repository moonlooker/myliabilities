package com.myliabilities.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myliabilities.dao.po.AccountBookPo;
import com.myliabilities.dao.po.AccountPo;
import com.myliabilities.dao.po.LiabilitiesPo;
import com.myliabilities.enums.DC;
import com.myliabilities.service.AccountBookService;
import com.myliabilities.service.LiabilitiesService;
import com.myliabilities.service.NowUser;
import com.myliabilities.service.UserService;
import com.myliabilities.utils.MyDateUtils;
import com.myliabilities.utils.MyStringUtils;
import com.myliabilities.web.vo.AccountBookVo;

@Controller
public class AccountBookController {

    private static final Logger log = LoggerFactory.getLogger(AccountBookController.class);

    private AccountBookService abs = new AccountBookService();
    private UserService us = new UserService();
    private LiabilitiesService ls = new LiabilitiesService();

    @RequestMapping(value = "accountbook")
    public String accountbook(Model model) throws ParseException {

        log.info("查询用户[{}]", NowUser.loginUser);
        accoutbooktemp(null, null, model);
        return "accountbook";
    }

    @RequestMapping(value = "accountbook2")
    public String accountbook2(@RequestParam("begindate") String begindate, @RequestParam("enddate") String enddate,
            Model model) throws ParseException {

        log.info("查询用户[{}],时间区间[{}]-[{}]", NowUser.loginUser, begindate, enddate);
        accoutbooktemp(begindate, enddate, model);
        return "accountbook";
    }

    private void accoutbooktemp(String begindate, String enddate, Model model) throws ParseException {

        AccountPo accountPo = us.getAcount(NowUser.loginUser);
        List<AccountBookPo> list = new ArrayList<>();
        if(!MyStringUtils.isEmpty(begindate)&&!MyStringUtils.isEmpty(enddate)) {
           
           Date[] d = MyDateUtils.monthBeginEnd(new SimpleDateFormat("yyyy-MM-dd").parse(begindate));
           Date[] d2 = MyDateUtils.monthBeginEnd(new SimpleDateFormat("yyyy-MM-dd").parse(enddate));
            if(d2[1].compareTo(d[0]) < 0) {
                log.error("起始日期不能大于终止日期");
                throw new RuntimeException("起始日期不能大于终止日期");
            }
            list = abs.monthBooks(d[0], d2[1]);
        }else {
            list = abs.monthBooks();
        }
        List<AccountBookVo> listvo = list.stream().map(ab -> {
            AccountBookVo abv = new AccountBookVo();
            abv.setId(ab.getId());
            abv.setAccountid(ab.getAccountid());
            abv.setDc(DC.getName(ab.getDc()));
            abv.setAmount(ab.getAmount());
            abv.setTag(ab.getTag());
            abv.setTagname(ab.getTagname());
            abv.setAcountdate(new SimpleDateFormat("yyyy-MM-dd").format(ab.getAcountdate()));
            abv.setMemo(ab.getMemo());
            return abv;
        }).collect(Collectors.toList());

        List<LiabilitiesPo> listliab = ls.getUserLiabilities();
        BigDecimal monthliab = new BigDecimal("0");
        if (!listliab.isEmpty()) {
            for (LiabilitiesPo tmp : listliab) {
                monthliab = monthliab.add(tmp.getMonthpay());
            }
        }

        model.addAttribute("accountid", NowUser.loginUser);
        model.addAttribute("assets", accountPo.getAssets());
        model.addAttribute("liabilities", accountPo.getLiabilities());
        model.addAttribute("hope", accountPo.getHope());
        model.addAttribute("left", accountPo.getAssets().subtract(monthliab));
        model.addAttribute("accountBooks", listvo);
        model.addAttribute("begindate", begindate);
        model.addAttribute("enddate", enddate);
    }

    @RequestMapping("/")
    public String index(Model model) {

        log.info("查询用户[{}]", NowUser.loginUser);
        AccountPo accountPo = us.getAcount(NowUser.loginUser);
        List<AccountBookPo> list = abs.monthBooks();
        List<AccountBookVo> listvo = list.stream().map(ab -> {
            AccountBookVo abv = new AccountBookVo();
            abv.setId(ab.getId());
            abv.setAccountid(ab.getAccountid());
            abv.setDc(DC.getName(ab.getDc()));
            abv.setAmount(ab.getAmount());
            abv.setTag(ab.getTag());
            abv.setTagname(ab.getTagname());
            abv.setAcountdate(new SimpleDateFormat("yyyy-MM-dd").format(ab.getAcountdate()));
            abv.setMemo(ab.getMemo());
            return abv;
        }).collect(Collectors.toList());
        model.addAttribute("accountid", NowUser.loginUser);
        model.addAttribute("assets", accountPo.getAssets());
        model.addAttribute("liabilities", accountPo.getLiabilities());
        model.addAttribute("left", accountPo.getAssets().subtract(accountPo.getLiabilities()));
        model.addAttribute("accountBooks", listvo);
        return "accountbook";
    }

    @RequestMapping("/delbook")
    public String del(@RequestParam("id") Long id) {

        boolean result = abs.delBook(id);
        if (!result) {
            log.warn("用户[{}]删除ID[{}]失败", NowUser.loginUser, id);
        }
        return "redirect:/accountbook";
    }

    @RequestMapping("/addbookweb")
    public String addbookweb() {

        return "addbook";
    }

    @RequestMapping("/doaddbook")
    public String addbook(@RequestParam("dc") int dc, @RequestParam("amount") String amount,
            @RequestParam("memo") String memo, @RequestParam("accountdate") String accountdate) throws ParseException {

        abs.addBook(DC.get(dc), new BigDecimal(amount), new SimpleDateFormat("yyyy-MM-dd").parse(accountdate), "a",
            "日常", memo);
        return "redirect:/accountbook";
    }
}
