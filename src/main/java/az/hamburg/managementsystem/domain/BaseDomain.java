package az.hamburg.managementsystem.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseDomain {

    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime modified;
    private String modifiedBy;

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.created == null) {
            this.created = LocalDateTime.now();
        }
        this.modified = LocalDateTime.now();
    }

}