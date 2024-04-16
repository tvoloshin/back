package com.atomskills.back.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class Inspector extends AppUser {
}
