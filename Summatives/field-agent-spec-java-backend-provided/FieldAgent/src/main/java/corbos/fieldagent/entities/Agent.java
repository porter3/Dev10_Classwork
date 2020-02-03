package corbos.fieldagent.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent implements Serializable {

    @Id // maybe forces it to be globally unique? test this
    @NotBlank(message="Identifier cannot be blank")
    @Size(max = 25)
    private String identifier;
    @NotBlank(message = "First name cannot be blank.")
    @Size(max = 25, message="First name must be under 25 characters.")
    private String firstName;
    @Size(max = 25, message="Middle name must be under 25 characters.")
    private String middleName;
    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 25, message="Last name must be under 25 characters.")
    private String lastName;
    @Size(max = 255, message="Picture URL must be under 255 characters.")
    private String pictureUrl;
    // Add between 1/1/1900 and ten years from the current day requirement
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Min(36)
    @Max(96)
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
