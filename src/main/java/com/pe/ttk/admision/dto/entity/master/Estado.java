package com.pe.ttk.admision.dto.entity.master;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "estado")
public class Estado  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String estado;

}

