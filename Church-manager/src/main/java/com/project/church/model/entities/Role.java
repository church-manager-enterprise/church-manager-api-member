package com.project.church.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Role extends Base {

    @Column(nullable = false)
    private String name;
}