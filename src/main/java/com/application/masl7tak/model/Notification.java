package com.application.masl7tak.model;


import com.application.masl7tak.dto.InsuranceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private  String title;
    @Column(name = "creationDate")
    private  String creationDate;
    @Column(name = "description")
    private  String description;
    @Column(name = "status")
    private  String status;
    @Column(name = "type")
    private  String type;
    @Column(name = "statusReviewed")
    private  String statusReviewed;
    @Column(name = "user_id")
    private  Long  user_id;


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_id", referencedColumnName = "id")
    private Insurance insurance;



    public Notification(Insurance insurance) {
         LocalDateTime now = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.title = "نقوم حالياً بمراجعة طلب التأمين";
        this.creationDate = formatter.format(now);
        this.description = "يقوم فريقنا في الوقت الحالي بمراجعة طلب التأمين وسنقوم بالرد عليك بعرض السعر في أقرب فرصة";
        this.statusReviewed = "pending";
        this.insurance = insurance;
        this.user_id = insurance.getUser().getId();


    }
    public Notification(Insurance insurance,String Reviewed) {
         LocalDateTime now = LocalDateTime.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.title = "تم إرسال عرض سعر لطلب التامين لسيارتك";
        this.creationDate = formatter.format(now);
        this.description = "تم إرسال عرض سعر لطلب التامين لسيارتك";
        this.statusReviewed = Reviewed;
        this.insurance = insurance;
        this.user_id = insurance.getUser().getId();

    }
}
