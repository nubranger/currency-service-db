package lt.mano.currency.controller;

import lt.mano.currency.model.Rate;
import lt.mano.currency.repository.RateRepository;
import lt.mano.currency.services.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class CurrencyController {

    @Autowired
    RateRepository rateRepository;

    XMLService xmlService = new XMLService();
    Map<String, String> currencyMap = xmlService.getCurrencyList();

    @GetMapping("/")
    public String getIndex(Model model,
                           @RequestParam(value = "selectedCurrencyCode", required = false) String selectedCurrencyCode,
                           @RequestParam(value = "amount", required = false) String amount) {

        List<Rate> currentFxRates = (List<Rate>) rateRepository.findAll();
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String ldt = simpleDateFormat.format(new Date());
        model.addAttribute("date", ldt);

        model.addAttribute("currentFxRates", currentFxRates);
        model.addAttribute("currencyMap", currencyMap);

        if (amount == null || amount.equals("")) {
            model.addAttribute("amount", amount = "1");
        } else {
            model.addAttribute("amount", amount);
        }

        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(amount);

        if (matcher.matches()) {
            model.addAttribute("amount", amount);
        } else {
            model.addAttribute("amount", amount = "1");
        }


        if (selectedCurrencyCode == null || selectedCurrencyCode.equals("")) {
            model.addAttribute("selectedCurrencyCode", selectedCurrencyCode = "USD");
            model.addAttribute("selectedCurrencyName", currencyMap.get(selectedCurrencyCode));
        } else {
            model.addAttribute("selectedCurrencyCode", selectedCurrencyCode);
            model.addAttribute("selectedCurrencyName", currencyMap.get(selectedCurrencyCode));
        }

        Double currencyRate = null;

        for (Rate tmp : currentFxRates) {
            if (tmp.getCurrency().equals(selectedCurrencyCode)) {
                currencyRate = tmp.getRate();
            }
        }

        if (currencyRate != null) {
            BigDecimal bd1 = new BigDecimal(amount);
            BigDecimal bd2 = new BigDecimal(currencyRate);
            BigDecimal bd3 = bd1.multiply(bd2);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(3);
            df.setMinimumFractionDigits(3);

            String currencyResult = df.format(bd3);
            model.addAttribute("currencyResult", currencyResult);
            model.addAttribute("currencyRate", currencyRate);
        }
        return "index";
    }


    @GetMapping("/currencyinfo")
    public String getRate(Model model,
                          @RequestParam(value = "selectedCurrencyCode", required = false) String selectedCurrencyCode,
                          String fromDate,
                          String toDate
    ) {

        List<Rate> currentFxRates = xmlService.getCurrentFxRates();
        model.addAttribute("currentFxRates", currentFxRates);

        model.addAttribute("selectedCurrencyName", currencyMap.get(selectedCurrencyCode));
        model.addAttribute("selectedCurrencyCode", selectedCurrencyCode);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String ldt = simpleDateFormat.format(new Date());

        if (fromDate == null || fromDate.equals("")) {
            model.addAttribute("fromDate", fromDate = ldt);
        } else {
            model.addAttribute("fromDate", fromDate);
        }

        if (toDate == null || toDate.equals("")) {
            model.addAttribute("toDate", toDate = ldt);
        } else {
            model.addAttribute("toDate", toDate);
        }

        List<Rate> ratesList = xmlService.getFxRatesForCurrency(selectedCurrencyCode, toDate, fromDate);

        model.addAttribute("ratesList", ratesList);

        model.addAttribute("currencyMap", currencyMap);

        return "currencyinfo";
    }
}