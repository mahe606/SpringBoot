package com.example.school1.controller;

import com.example.school1.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HolidayController {


/*  Query Param approach http://localhost:8080/holidays?festival=true&federal=true
    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam boolean festival, @RequestParam boolean federal,  Model model) {
*/

        // Path param approach
        @GetMapping("/holidays/{display}")
        public String displayHolidays(@PathVariable String display,
                                      Model model) {

            if( null != display && display.equals("all")){
                model.addAttribute("festival", true);
                model.addAttribute("federal", true);
            }else if ( null != display && display.equals("festival")){
                model.addAttribute("festival",true);
            }else if ( null != display && display.equals("federal")){
                model.addAttribute("federal",true);
            }



        List<Holiday> holidays = Arrays.asList(new Holiday("Jan 14", "Pongal", Holiday.Type.FESTIVAL),
                new Holiday("April 14", "Tamil New Year", Holiday.Type.FESTIVAL),
                new Holiday("November 4", "Diwali", Holiday.Type.FESTIVAL),
                new Holiday("Jan 26", "Republic Day", Holiday.Type.FEDERAL),
                new Holiday("August 15", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday("October 2", "Gandhi Jainthi", Holiday.Type.FEDERAL));

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(), holidays.stream()
                    .filter(holiday->holiday.getType().equals(type))
                    .collect(Collectors.toList()));
        }

        return "holidays.html";
    }
}
