package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;

@Data
@Transactional
public class AnalyticsDTO {


    public int total_sales_num;
    public int  clients_num;
    public double visits_num;
    public int services_num;
    Map<LocalDate, Long> LastSixDays;

    public AnalyticsDTO(int total_sales_num, int clients_num, int services_num, double visits_num) {
        this.total_sales_num =  total_sales_num;
        this.clients_num = clients_num;
        this.visits_num =visits_num;
        this.services_num = services_num;
    }    public AnalyticsDTO(long total_sales_num, long clients_num, long services_num, double visits_num) {
        this.total_sales_num = (int)total_sales_num;
        this.clients_num = (int)clients_num;
        this.visits_num =visits_num;
        this.services_num =(int) services_num;
    }

    public AnalyticsDTO() {
    }
}
