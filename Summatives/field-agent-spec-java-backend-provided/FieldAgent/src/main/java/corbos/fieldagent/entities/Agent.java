package corbos.fieldagent.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent implements Serializable {

    @Id // maybe forces it to be globally unique? test this
    @Size(max = 25)
    private String identifier;
    @NotBlank
    @Size(max = 25)
    private String firstName;
    @Size(max = 25)
    private String middleName;
    @NotBlank
    @Size(max = 25)
    private String lastName;
    @Size(max = 255)
    private String pictureUrl;
    // Add between 1/1/1900 and ten years from the current day requirement
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    // Add 36-96 requirement
    private int height;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    private SecurityClearance securityClearance;

}
