package corbos.fieldagent.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class SecurityClearance implements Serializable {

    @Id
    private int securityClearanceId;
    @NotBlank
    private String name;

}
