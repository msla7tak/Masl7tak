package com.application.masl7tak.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ReadmeDTO {
    @JsonIgnore
    private  final long PREFIX = 1234L;
    @JsonIgnore
    private  final long SUFFIX = 5678L;
    private Long id;
    private Long services_id;
    private Long user_id;
    String user_name;
    String user_phone;
    String user_avatar;
    private String schedule_date;
    private String schedule_time;
    private String service_name;
    private Double service_discount;
    private Double promo_code_discount;
    private String business_name;
    private String business_Logo;
    private long coupon_code;

    //    ---------------business_branch-----------------
    private Long business_branch_ID;
    private String business_branch_address;
    private String locationLink;
    private String branch_phone_number;
    private String branch_openTime;
    private String branch_closureTime;
    private String branch_working_days;
//    --------------
    private String service_expiration;
//------------------------------------
    private String readme_date;
    private String stateName;
    private Boolean invoiced;
    private String invoiced_path;
    private String reason;
    private Boolean comment;
    private String comment_str;
    private String promo_code;
    private String total_invoice;

//    ---------------------------
    private int confirm_date;
    private int confirm_invoice;
    private int schedule_mode;
    private String business_app_promo_code;


    public ReadmeDTO(Long id, Long services_id, Long user_id, String schedule_date, String schedule_time, String service_name, Double service_discount,
                     String business_Logo, String business_name, Long business_branch_ID, String business_branch_address, String locationLink, String branch_phone_number,
                     String branch_openTime, String branch_closureTime,
               String service_expiration,
                     String readme_date, String stateName, Boolean invoiced, Boolean comment,
                     String total_invoice,int confirm_date,int confirm_invoice ,String reason,int schedule_mode,String comment_str,
                     Double promo_code_discount,String promo_code,String business_app_promo_code) {
        this.id = id;
        this.services_id = services_id;
        this.user_id = user_id;
        this.schedule_date = schedule_date;
        this.schedule_time = schedule_time;
        this.service_name = service_name;
        this.service_discount = service_discount;
        this.business_Logo = business_Logo;
        this.business_branch_ID = business_branch_ID;
        this.business_branch_address = business_branch_address;
        this.locationLink = locationLink;
        this.branch_phone_number = branch_phone_number;
        this.branch_openTime = branch_openTime;
        this.branch_closureTime = branch_closureTime;
        this.business_app_promo_code = business_app_promo_code;

        this.service_expiration = service_expiration;
        this.business_name = business_name;
        this.readme_date = readme_date;
        this.stateName = stateName;
        this.invoiced = invoiced;
        this.comment = comment;
        this.coupon_code =generateCouponCode(this.id);
        this.total_invoice =total_invoice;
        this.confirm_date =confirm_date;
        this.confirm_invoice =confirm_invoice;
        this.reason =reason;
        this.schedule_mode =schedule_mode;
        this.comment_str =comment_str;
        this.promo_code_discount =promo_code_discount;
        this.promo_code =promo_code;
    }

    public ReadmeDTO(Long id, Long services_id, Long user_id,String user_name,String user_phone,String user_avatar, String schedule_date, String schedule_time, String service_name, Double service_discount,
                     String business_Logo, String business_name, Long business_branch_ID, String business_branch_address, String locationLink, String branch_phone_number,
                     String branch_openTime, String branch_closureTime,
                   String service_expiration,
                     String readme_date, String stateName, Boolean invoiced, Boolean comment,
                     String total_invoice,String invoiced_path, int confirm_date,int confirm_invoice,String reason,
                     int schedule_mode,String comment_str,Double promo_code_discount, String promo_code,String business_app_promo_code) {
        this.id = id;
        this.services_id = services_id;
        this.user_id = user_id;
        this.schedule_date = schedule_date;
        this.schedule_time = schedule_time;
        this.service_name = service_name;
        this.service_discount = service_discount;
        this.business_Logo = business_Logo;
        this.business_branch_ID = business_branch_ID;
        this.business_branch_address = business_branch_address;
        this.locationLink = locationLink;
        this.branch_phone_number = branch_phone_number;
        this.branch_openTime = branch_openTime;
        this.branch_closureTime = branch_closureTime;
        this.business_app_promo_code = business_app_promo_code;

        this.service_expiration = service_expiration;
        this.business_name = business_name;
        this.readme_date = readme_date;
        this.stateName = stateName;
        this.invoiced = invoiced;
        this.comment = comment;
        this.coupon_code =generateCouponCode(this.id);
        this.total_invoice =total_invoice;
        this.invoiced_path = invoiced_path;
        this.confirm_date = confirm_date;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_avatar = user_avatar;
        this.confirm_invoice = confirm_invoice;
        this.reason = reason;
        this.schedule_mode = schedule_mode;
        this.comment_str = comment_str;
        this.promo_code_discount = promo_code_discount;
        this.promo_code = promo_code;
    }
//    public ReadmeServiceDTO(Long id, Long user_id, Long services_id, Long business_id, Long business_branch, String schedule_date, String schedule_time, String readme_date, String documentPath,
//                            String stateName, float rate, String comment) {
//        this.id = id;
//        this.user_id = user_id;
//        this.services_id = services_id;
//        this.business_id = business_id;
//        this.business_branch = business_branch;
//        this.schedule_date = schedule_date;
//        this.schedule_time = schedule_time;
//        this.readme_date = readme_date;
//        this.documentPath = documentPath;
//        this.stateName = stateName;
//        this.rate = rate;
//        this.comment = comment;
//    }


    public ReadmeDTO(String invoiced_path,Long id, Long services_id, Long user_id, String schedule_date, String schedule_time, String service_name, Double service_discount,
                     String business_Logo, String business_name, Long business_branch_ID, String business_branch_address, String locationLink, String branch_phone_number,
                     String branch_openTime, String branch_closureTime,
                     String service_expiration,
                     String readme_date, String stateName, Boolean invoiced, Boolean comment,
                     String total_invoice,int confirm_date,int confirm_invoice ,String reason,
                     int schedule_mode,String comment_str,Double promo_code_discount, String promo_code,String business_app_promo_code) {
        this.id = id;
        this.services_id = services_id;
        this.user_id = user_id;
        this.schedule_date = schedule_date;
        this.schedule_time = schedule_time;
        this.service_name = service_name;
        this.service_discount = service_discount;
        this.business_Logo = business_Logo;
        this.business_branch_ID = business_branch_ID;
        this.business_branch_address = business_branch_address;
        this.locationLink = locationLink;
        this.branch_phone_number = branch_phone_number;
        this.branch_openTime = branch_openTime;
        this.branch_closureTime = branch_closureTime;
        this.business_app_promo_code = business_app_promo_code;

        this.service_expiration = service_expiration;
        this.business_name = business_name;
        this.readme_date = readme_date;
        this.stateName = stateName;
        this.invoiced = invoiced;
        this.comment = comment;
        this.coupon_code =generateCouponCode(this.id);
        this.total_invoice =total_invoice;
        this.confirm_date =confirm_date;
        this.confirm_invoice =confirm_invoice;
        this.reason =reason;
        this.schedule_mode =schedule_mode;
        this.comment_str =comment_str;
        this.invoiced_path =invoiced_path;
        this.promo_code_discount =promo_code_discount;
        this.promo_code =promo_code;
    }


        public long generateCouponCode(long id) {
            if (id < 1L || id > 99999L) {
                throw new IllegalArgumentException("Invalid coupon ID: " + id);
            }
            long couponCode = PREFIX * 100000L + id * 10L + SUFFIX;
            return couponCode;
        }

        public  long extractCouponId(long couponCode) {
            long id = (couponCode - PREFIX * 100000L - SUFFIX) / 10L;
            if (id < 1L || id > 99999L) {
                throw new IllegalArgumentException("Invalid coupon code: " + couponCode);
            }
            return id;
        }








    public ReadmeDTO() {

    }
}
