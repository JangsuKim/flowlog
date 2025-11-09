package com.flowlog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * チーム情報エンティティ
 * - Hibernateの遅延ロード(Lazy Loading)で発生する
 *   ByteBuddyエラーを防ぐため、JsonIgnorePropertiesを設定。
 */
@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Override
    public String toString() {
        return "Team{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return id != null && id.equals(team.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
