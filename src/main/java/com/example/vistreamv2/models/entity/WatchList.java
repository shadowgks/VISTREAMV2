//package com.example.vistreamv2.models.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class WatchList {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private AppUser appUsers;
//    @ManyToOne
//    @JoinColumn(name = "media_id")
//    @JsonBackReference
//    private Media media;
//}