package dn.com.user.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity
@Table(name="authority")
@Data
public class Authority implements GrantedAuthority {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Long id;

    @Enumerated( EnumType.STRING)
    @Column(name="name")
    @JsonIgnore
    UserRoleName name;

//    @Enumerated( EnumType.STRING)
    @Column(name="location_id")
    @JsonProperty(value = "locationId")
    @JsonIgnore
    Long locationId;

    @Override
    public String getAuthority() {
        return name.name();
    }

}