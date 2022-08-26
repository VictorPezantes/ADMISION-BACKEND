package com.pe.ttk.admision.dto.entity.master;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="cargo")
public class Cargo  implements Serializable {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false, length = 20)
        private String nombreCargo;
        @Column(nullable = true, length = 200)
        private String descripcion;



}
